
import java.util.*;

public class Rule {

	String ruleName;
	
	boolean ruleRegistered = false;
	
	ArrayList<String> listOfParametersNeeded = new ArrayList<String>();
	ArrayList<String> listOfOutputs = new ArrayList<String>();	
	ArrayList<Parameter> listOfLastInputs = new ArrayList<Parameter>();
	
	Parameter ruleResult;	
	
	 int prevState = 0;
	 int state = 0;
	

	//Method that handles new Inputs for each rule by saving them in the listOfLastInputs and forwarding them to the updateState method
	void handleNewInput(Parameter input) {
		if(listOfParametersNeeded.contains(input.parameterType)) {
			for(int i = 0; i < listOfLastInputs.size(); i++) {
				if(listOfLastInputs.get(i).parameterType.equals(input.parameterType)){
					listOfLastInputs.set(i, input);
					//System.out.println(listOfLastInputs);
					updateState(input);
					updateEggLabels();
					return;
				}
			}
			listOfLastInputs.add(input);
			updateState(input);
			updateEggLabels();
		}
	}
	
	// method that evaluates the state machine and sets the alarm based on the state
	void evaluateStateMachine() {
	}

	// method that updates the rule state based on a new Parameter
	void updateState(Parameter newParameter) {
	}

	//method that forwards the result(alarm of a rule)
	Parameter forwardEvaluation() {
		return ruleResult;
	}
	
	// method that initializes the rule by registering it to the InferenceControll
	// and adding the parameters needed to the list of parameters
	void initializeRule() {

	}

	//forwards the OutputType (alarm.parameterType) to the IC 
	String getOutputType() {
		return null;
	}
	
	void test() {		
	}
	
	//-----------------
	//getter and setter
	//-----------------
	
	ArrayList<String> getParametersNeeded(){
		return listOfParametersNeeded;
	}
	
	String getRuleResultType() {
		return ruleResult.parameterType;
	}
	

	//Method that updates the Labels of a RuleEgg
	void updateEggLabels() {
	}

	//--------------
	//getter/ setter
	//--------------
		
	
	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
}
