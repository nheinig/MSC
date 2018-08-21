
import java.util.*;

public class Rule {

	String ruleName;
	
	boolean ruleRegistered = false;
	
	ArrayList<String> listOfParametersNeeded = new ArrayList<String>();
	
	Parameter alarm;	
	
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
		return alarm;
	}
	
	// method that initializes the rule by registering it to the InferenceControll
	// and adding the parameters needed to the list of parameters
	void initializeRule() {

	}


	// method that registers the rule at the InferenceControll
	void registerRuleAtInferenceControll() {
		System.out.println("Rule is registred: " + ruleRegistered);
		ruleRegistered = InferenceControll.registerRule(listOfParametersNeeded);
		if (!ruleRegistered) {
			System.out.println("A Rule with the same Name allready exists;");
		} 
		System.out.println("Rule is registred: " + ruleRegistered);
	}
}
