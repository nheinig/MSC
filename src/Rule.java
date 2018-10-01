
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

	Rule() {
	}

	//Method that handles new Inputs for each rule by saving them in the listOfLastInputs and forwarding them to the updateState method
	void handleNewInput(Parameter input) {
		if(listOfParametersNeeded.contains(input.parameterType)) {
			for(int i = 0; i < listOfLastInputs.size(); i++) {
				if(listOfLastInputs.get(i).parameterType.equals(input.parameterType)){
					listOfLastInputs.remove(i);
					listOfLastInputs.add(input);
					//System.out.println(listOfLastInputs);
					updateState(input);
					return;
				}
			}
			listOfLastInputs.add(input);
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
	

}
