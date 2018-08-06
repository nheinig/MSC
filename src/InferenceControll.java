import java.util.*;

public class InferenceControll {

	static List<List<String>> listOfRules = new ArrayList<List<String>>();
	static ArrayList<Parameter> listOfPersonValues = new ArrayList<Parameter>();
	static ArrayList<Parameter> listOfSpO2Values = new ArrayList<Parameter>();
	static String alarm = "None";

	// helper method that prints out the lists of parameter values
	static void printValueLists() {
		for (int i = 0; i < listOfPersonValues.size(); i++) {
			Parameter param;
			param = listOfPersonValues.get(i);
			System.out.println(param.parameterType + " " + param.parameterValue + " " + param.timestamp);
		}
		for (int j = 0; j < listOfSpO2Values.size(); j++) {
			Parameter param;
			param = listOfSpO2Values.get(j);
			System.out.println(param.parameterType + " " + param.parameterValue + " " + param.timestamp);
		}

	}

	// method that handles new incoming Parameters and writes them in their specific
	// Arraylist<Parameter>
	static void handleNewParameterValue(Parameter param) {
		if (param.parameterType.equals("persons")) {
			listOfPersonValues.add(param);
		} else if (param.parameterType.equals("spo2")) {
			listOfSpO2Values.add(param);
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
		System.out.println("Rule pspo2 parameters needed: " + pspo2.listOfParametersNeeded);

		// get dummy Parameters
		InputDummy id = new InputDummy();
		id.fillList();
		printValueLists();

		
		//forward parameters to a dummy Rule
		while (!listOfPersonValues.isEmpty() && !listOfSpO2Values.isEmpty()) {
			List<String> tempList = listOfRules.get(0);
			System.out.println("Templist: " + tempList);
			
			//check if a rule needs the Parameter with the type persons
			if (tempList.contains("persons")) {
				pspo2.setPersons(listOfPersonValues.get(0));
			}
			//check if a rule needs the Parameter with the type spo2
			if (tempList.contains("spo2")) {
				pspo2.setSpO2(listOfSpO2Values.get(0));
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
			listOfPersonValues.remove(0);
			listOfSpO2Values.remove(0);
		}

	}

}
