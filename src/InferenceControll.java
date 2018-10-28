import java.util.*;

public class InferenceControll {

	static ArrayList<ArrayList<String>> listOfParameterValues = new ArrayList<ArrayList<String>>();
	static ArrayList<String> listOfAvailableParameters = new ArrayList<String>();
	static ArrayList<Parameter> listOfNewParameters = new ArrayList<Parameter>();
	static ArrayList<Parameter> listOfAlarms = new ArrayList<Parameter>();

	static ArrayList<Rule> ruleList = new ArrayList<Rule>();

	static String alarm = "None";

	// helper method that prints out the listOfParameters
	static void printValueLists() {
		for (int i = 0; i < listOfNewParameters.size(); i++) {
			Parameter param;
			param = listOfNewParameters.get(i);
			System.out.println(param.parameterType + " " + param.parameterValue + " " + param.timestamp);
		}
	}

	// method that handles new incoming Parameters and writes them into the
	// ArrayList<Parameter> listOfParameters
	static void handleNewParameterValue(Parameter param) {
		listOfNewParameters.add(param);
	}

	// method that handles new Alarms created by the rules
	static void handleNewAlarm(Parameter alarm) {
		listOfAlarms.add(alarm);
		// todo add event
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
	
	// adds available Parameters (from data stream and rules) to an ArrayList
		static void addAvailableParameterValues(ArrayList<String> pvList) {
			if (!listOfParameterValues.contains(pvList)) {
				listOfParameterValues.add(pvList);
			}
		}
	

	public static void main(String[] args) {

		//initialize the InputFuzzyfier
		InputFuzzyfier infu = new InputFuzzyfier();
		
		// register Rule for persons and spo2 value at IC
		PersonSpO2Rule pspo2 = new PersonSpO2Rule();
		ruleList.add(pspo2);

		// register Rule for persons and tube value at IC
		PersonVentilationTubeRule pvt = new PersonVentilationTubeRule();
		ruleList.add(pvt);

		// register MetaRule at IC
		MetaRule meta = new MetaRule();
		ruleList.add(meta);

		ConfigurationUI cUI = new ConfigurationUI(listOfAvailableParameters);
		cUI.updateParameterStrings(listOfAvailableParameters);
		cUI.createConfigurationUI();

		// creates dummy Parameters
		InputDummy id = new InputDummy();
		id.fillList();
		printValueLists();

		// forwarding of Parameters to the rules that need them
		while (!listOfNewParameters.isEmpty()) {
			for (int i = 0; i < ruleList.size(); i++) {
				if (ruleList.get(i).getParametersNeeded().contains(listOfNewParameters.get(0).parameterType)) {
					ruleList.get(i).handleNewInput(listOfNewParameters.get(0));
				}
			}
			listOfNewParameters.remove(0);
			
			//forward Alarm to MetaRule
			if(!listOfAlarms.isEmpty()) {
				meta.handleNewInput(listOfAlarms.get(0));
				listOfAlarms.remove(0);		
			}
		}
	}
	
	
	//--------------
	//getter/ setter
	//--------------
	
	static ArrayList<Rule> getRuleList(){
		return ruleList;
	}

}
