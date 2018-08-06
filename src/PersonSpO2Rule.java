import java.util.ArrayList;

public class PersonSpO2Rule extends Rule {

	PersonSpO2Rule(String ruleName, ArrayList<String> parameterList) {
		super(ruleName, parameterList);
		// TODO Auto-generated constructor stub
	}

	// method to decide if ruleAction has to be activated
	boolean ruleLogic() {
		return false;
	}
	
	//method that evaluates Parameters and forwards the result to the InferenceControll
	void ruleAction() {
	}
	
	//method to get Parameters if they are missing
	void getParameters() {
	}
	
	//method that initializes the rule by registering it to the InferenceControll and adding the parameters needed to the list of parameters
	void initializeRule() {

		//registerRuleAtInferenceControll();
		
	}
	
	
		
}
