import java.util.*;

public class InferenceControll {

	static ArrayList<ArrayList<String>> listOfParameterValues = new ArrayList<ArrayList<String>>();
	static ArrayList<String> listOfAvailableParameters = new ArrayList<String>();
	static ArrayList<Parameter> listOfNewParameters = new ArrayList<Parameter>();
	static ArrayList<Parameter> listOfAlarms = new ArrayList<Parameter>();

	static ArrayList<Rule> ruleList = new ArrayList<Rule>();


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
		Discreter infu = new Discreter();
		
		// register Rule for persons and spo2 value at IC
		PersonsSpO2Rule pspo2 = new PersonsSpO2Rule();
		ruleList.add(pspo2);

		//register Rule for persons and tube value at IC
		PersonsVentilationTubeRule pvt = new PersonsVentilationTubeRule();
		ruleList.add(pvt);

		

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
				//meta.handleNewInput(listOfAlarms.get(0));
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
	
	//Method that returns a parameterValue from the listOfParameterValues based on the ParameterType pt and the Index i		
	static String getParameterValue(String pt, int i) {
		String pv = "";
		for (int j = 0; j < InferenceControll.listOfParameterValues.size(); j++) {
			if (InferenceControll.listOfParameterValues.get(j).contains(pt)) {
				pv = InferenceControll.listOfParameterValues.get(j).get(i);
			}
		}
		return pv;
	}

	//declares a Rule as Last rule if it gets Marked as such in the UserInterface
	static void declareLastRule(String ruleName) {
		for(int i = 0; i < ruleList.size(); i++) {
			if(ruleList.get(i).ruleName.equals(ruleName)) {
				ruleList.get(i).changeIsLastRule(true);
			} else {
				ruleList.get(i).changeIsLastRule(false);
			}
		}
	}
	
}
