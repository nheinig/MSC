import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class InferenceControll {

	static ArrayList<String> listOfAvailableParameters = new ArrayList<String>(Arrays.asList("persons", "tube", "spo2"));
	static List<List<String>> listOfRules = new ArrayList<List<String>>();
	static ArrayList<Parameter> listOfParameters = new ArrayList<Parameter>();
	static ArrayList<Parameter> listOfAlarms = new ArrayList<Parameter>();

	static ArrayList<Rule> ruleList = new ArrayList<Rule>();

	static String alarm = "None";

	// helper method that prints out the listOfParameters
	static void printValueLists() {
		for (int i = 0; i < listOfParameters.size(); i++) {
			Parameter param;
			param = listOfParameters.get(i);
			System.out.println(param.parameterType + " " + param.parameterValue + " " + param.timestamp);
		}
	}

	// method that handles new incoming Parameters and writes them into the
	// Arraylist<Parameter> listOfParameters
	static void handleNewParameterValue(Parameter param) {
		listOfParameters.add(param);
	}

	// method that handels new Alarms created by the rules
	static void handleNewAlarm(Parameter alarm) {
		listOfAlarms.add(alarm);
		// todo add event
	}

	// method that registers a Rule to lostOfRules
	static boolean registerRule(ArrayList<String> rule) {
		if (!listOfRules.contains(rule)) {
			listOfRules.add(rule);
			System.out.println("List of Rules " + listOfRules);
			return true;
		} else {
			return false;
		}

	}

	// set the alarm type
	static void setAlarm(String alarmtype) {
		alarm = alarmtype;
	}

	// adds available Parameters (from data stream and rules) to an ArrayList
	static void addAvailableParameter(String parameter) {
		if (!listOfAvailableParameters.contains(parameter)) {
			listOfAvailableParameters.add(parameter);
		}
	}

	public static void main(String[] args) {

		// register Rule for persons and spo2 value at IC
		PersonSpO2Rule pspo2 = new PersonSpO2Rule();
		pspo2.initializeRule();
		ruleList.add(pspo2);

		// register Rule for persons and tube value at IC
		PersonVentilationTubeRule pvt = new PersonVentilationTubeRule();
		pvt.initializeRule();
		ruleList.add(pvt);

		// register MetaRule at IC
		MetaRule meta = new MetaRule();
		meta.initializeRule();
		ruleList.add(meta);

		ConfigurationUI cUI = new ConfigurationUI(listOfAvailableParameters);
		cUI.updateParameterStrings(listOfAvailableParameters);
		cUI.createConfigurationUI();

		// get dummy Parameters
		InputDummy id = new InputDummy();
		id.fillList();
		printValueLists();

		// forwarding of Parameters to the rules

		while (!listOfParameters.isEmpty()) {
			for (int i = 0; i < ruleList.size(); i++) {
				if (ruleList.get(i).getParametersNeeded().contains(listOfParameters.get(0).parameterType)) {
					ruleList.get(i).updateState(listOfParameters.get(0));
				}
			}
			listOfParameters.remove(0);
			
			//forward Alarm to MetaRule
			if(!listOfAlarms.isEmpty()) {
				meta.updateState(listOfAlarms.get(0));
				listOfAlarms.remove(0);		
			}
		}
	}

}
