
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
		// ArrayList<String> test = new ArrayList<String>();
		// test.add("persons");
		// test.add("13:28:58");
		// test.add("2");
		// listOfParameterValues.add(test);
		// System.out.println(listOfParameterValues.get(0));
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
		ruleRegistered = InferenceControll.registerRule(listOfParametersNeeded);
		if (!ruleRegistered) {
			System.out.println("A Rule with the same Name allready exists;");
		} 
		System.out.println(ruleRegistered);
	}
}
