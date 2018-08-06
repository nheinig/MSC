import java.util.ArrayList;

public class PersonSpO2Rule extends Rule {

	Parameter persons;
	Parameter spo2;
	
	PersonSpO2Rule(String ruleName) {
		super(ruleName);
		initializeRule();
	}

	// method to decide if ruleAction has to be activated
	boolean ruleLogic() {
		if (spo2.parameterValue.equals("low")) {			
			return true;
		}else {
			return false;
		}
	}
	
	//method that evaluates Parameters and forwards the result to the InferenceControll
	void ruleAction() {
		if(persons.parameterValue.equals("none") && spo2.parameterValue.equals("low")) {
			InferenceControll.setAlarm("HNR ALARM!");
		} else if (persons.parameterValue.equals("many") && spo2.parameterValue.equals("low"))
			InferenceControll.setAlarm("LOCAL ALARM!");
		
	}
	
	//method to get Parameters if they are missing
	void getParameters() {
	}
	
	
	void setPersons(Parameter persons) {
		this.persons = persons;
	}
	
	void setSpO2(Parameter spo2) {
		this.spo2 = spo2;
	}
	
	//method that initializes the rule by registering it to the InferenceControll and adding the parameters needed to the list of parameters
	void initializeRule() {
		this.listOfParametersNeeded.add(ruleName);
		this.listOfParametersNeeded.add("persons");
		this.listOfParametersNeeded.add("spo2");
		this.registerRuleAtInferenceControll();		
	}
		
}
