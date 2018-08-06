import java.util.*;

public class InferenceControll {

	static List<List<String>> listOfRules = new ArrayList<List<String>>();
	static ArrayList<Parameter> listOfPersonValues = new ArrayList<Parameter>();
	static ArrayList<Parameter> listOfSpO2Values = new ArrayList<Parameter>();
	static String alarm = "None";
	
	
	
	static void printValueLists() {
		for(int i = 0; i < listOfPersonValues.size(); i++) {
			Parameter param;
			param = listOfPersonValues.get(i);
			System.out.println(param.parameterType + " " + param.parameterValue + " " + param.timestamp);
		}
		for(int j = 0; j < listOfSpO2Values.size(); j++) {
		Parameter param;
		param = listOfSpO2Values.get(j);
		System.out.println(param.parameterType + " " + param.parameterValue + " " + param.timestamp);
		}
		
	}
	
	static void handleNewParameterValue(Parameter param) {
		if (param.parameterType.equals("persons")) {
			listOfPersonValues.add(param);
		} else if(param.parameterType.equals("spo2")) {
			listOfSpO2Values.add(param);
		}
	}
	
	static boolean registerRule(List<String> rule) {
		if (!listOfRules.contains(rule)) {
			listOfRules.add(rule);
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public static void main(String[] args) {
		InputDummy id = new InputDummy();
		
		id.fillList();
		printValueLists();
	}

}
