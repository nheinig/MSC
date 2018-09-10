
import java.util.*;

public class Rule {

	String ruleName;
	
	boolean ruleRegistered = false;
	
	ArrayList<String> listOfParametersNeeded = new ArrayList<String>();
	
	Parameter ruleResult;	
	
	int state = 0;

	Rule() {
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
