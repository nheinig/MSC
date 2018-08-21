import java.util.*;

public class InferenceControll {

	static List<List<String>> listOfRules = new ArrayList<List<String>>();
	static ArrayList<Parameter> listOfParameters = new ArrayList<Parameter>();
	static String alarm = "None";

	// helper method that prints out the listOfParameters
	static void printValueLists() {
		for (int i = 0; i < listOfParameters.size(); i++) {
			Parameter param;
			param = listOfParameters.get(i);
			System.out.println(param.parameterType + " " + param.parameterValue + " " + param.timestamp);
		}
	}

	// method that handles new incoming Parameters and writes them into the  Arraylist<Parameter> listOfParameters
	static void handleNewParameterValue(Parameter param) {
		if (param.parameterType.equals("persons")) {
			listOfParameters.add(param);
		} else if (param.parameterType.equals("spo2")) {
			listOfParameters.add(param);
		}
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

	public static void main(String[] args) {
		// register Rule for people and spo2 value at IC
		PersonSpO2Rule pspo2 = new PersonSpO2Rule("pspo2");
		PersonVentilationTubeRule pvt = new PersonVentilationTubeRule("pvt");
		MetaRule master = new MetaRule("meta");
		
		// get dummy Parameters
		/*
		InputDummy id = new InputDummy();
		id.fillList();
		printValueLists();
		*/
		
		//forward parameters to a dummy Rule
		while (!listOfParameters.isEmpty() && !listOfParameters.isEmpty()) {
			List<String> tempList = listOfRules.get(0);
			System.out.println("Templist: " + tempList);
			
			//check if a rule needs the Parameter with the type persons
			if (tempList.contains("persons")) {
				pspo2.setPersons(listOfParameters.get(0));
			}
			//check if a rule needs the Parameter with the type spo2
			if (tempList.contains("spo2")) {
				pspo2.setSpO2(listOfParameters.get(0));
			}
			//prints the forwarded Parameters
			System.out.println(
					pspo2.persons.parameterType + " " + pspo2.persons.parameterValue + " " + pspo2.persons.timestamp);
			System.out.println(pspo2.spo2.parameterType + " " + pspo2.spo2.parameterValue + " " + pspo2.spo2.timestamp);

			//check if the rule has to be run
			if (pspo2.ruleLogic()) {
				pspo2.ruleAction();
			} else {
				//if the rule doesnt have to be run there is no alarm necessary
				setAlarm("None!");
			}
			System.out.println(alarm);
			
			//remove old parameters
			listOfParameters.remove(0);
			listOfParameters.remove(0);
		}

	}

}
