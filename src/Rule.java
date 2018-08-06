
import java.util.*;

public class Rule {

	String ruleName;
	boolean ruleRegistered = false;
	ArrayList<String> listOfParametersNeeded = new ArrayList<String>();

	Rule(String ruleName) {
		this.ruleName = ruleName;
	}

	// method to decide if ruleAction has to be activated
	boolean ruleLogic() {
		return true;
	}

	// method that evaluates Parameters and forwards the result to the
	// InferenceControll
	void ruleAction() {
	}

	// method to get Parameters if they are missing
	void getParameters() {
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
