

public class KnowledgeBase {
	static int persons;
	static int spO2;
	
	//updates value of persons
	static boolean setPersons(int nPersons) {
		persons = nPersons;
		if (persons == nPersons) { 
			sendParametersToUI(persons);
			callRuleBase("persons", persons);
			return true;
		} else {
			setPersons(nPersons);
			return setPersons(nPersons);
		}
	}
	

	//updates value of spO2
	static boolean setSpO2(int nSpO2) {
		spO2 = nSpO2;
		if (spO2 == nSpO2) {
			UserInterface.updateSpO2(spO2);
			callRuleBase("spo2", spO2);
			return true;
		} else {
			setSpO2(nSpO2);
			return setSpO2(nSpO2);
		}
	}
	
	
	static void sendParametersToUI(int parameter) {
		InferenceControll.forwardParametersToUI(parameter);
	}
	
	static void callRuleBase(String parameterType, int parameterValue) {
		InferenceControll.forwardCallToKnowledgeBase(parameterType, parameterValue);
	}
}
