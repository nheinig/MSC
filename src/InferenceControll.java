import java.util.*;
import java.awt.event.*;

public class InferenceControll {

	static List<List<String>> listOfRules = new ArrayList<List<String>>();
	static ArrayList<Parameter> listOfParameters = new ArrayList<Parameter>();
	static ArrayList<Parameter> listOfAlarms = new ArrayList<Parameter>();

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

	public static void main(String[] args) {
		

		// register Rule for people and spo2 value at IC
		PersonSpO2Rule pspo2 = new PersonSpO2Rule();
		PersonVentilationTubeRule pvt = new PersonVentilationTubeRule();
		MetaRule meta = new MetaRule();
						
		// get dummy Parameters
		InputDummy id = new InputDummy();
		id.fillList();
		printValueLists();

		//forwarding of Parameters to the rules
		while (!listOfParameters.isEmpty() && !listOfParameters.isEmpty()) {			
			//forwards Parameters of the type "persons"
			if (listOfParameters.get(0).parameterType.equals("persons")) {
				for (int i = 0; i < listOfRules.size(); i++) {
					List<String> tempList = listOfRules.get(i);
					if (tempList.contains("persons")) {
						switch (tempList.get(0)) {
						case "PersonSpO2Rule":
							pspo2.updateState(listOfParameters.get(0));
							break;
						case "PersonVentilationTubeRule":
							pvt.updateState(listOfParameters.get(0));
							break;
						default:
							break;
						}
					}
				}
			} 
			//forwards Parameters of the type "spo2"
			else if (listOfParameters.get(0).parameterType.equals("spo2")) {
				for (int i = 0; i < listOfRules.size(); i++) {
					List<String> tempList = listOfRules.get(i);
					if (tempList.contains("spo2")) {
						switch (tempList.get(0)) {
						case "PersonSpO2Rule":
							pspo2.updateState(listOfParameters.get(0));
							break;
						default:
							break;
						}
					}
				}
			} 
			//forwards Parameters of the type "tube"
			else if (listOfParameters.get(0).parameterType.equals("tube")) {
				for (int i = 0; i < listOfRules.size(); i++) {
					List<String> tempList = listOfRules.get(i);
					if (tempList.contains("tube")) {
						switch (tempList.get(0)) {
						case "PersonVentilationTubeRule":
							pvt.updateState(listOfParameters.get(0));
							break;
						default:
							break;
						}
					}
				}
			}
			//remove old Parameter
			listOfParameters.remove(0);
			
			//forward Alarm to MetaRule
			if(!listOfAlarms.isEmpty()) {
				meta.updateState(listOfAlarms.get(0));
				listOfAlarms.remove(0);		
			}
		}

	}

}
