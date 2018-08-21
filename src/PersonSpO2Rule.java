
public class PersonSpO2Rule extends Rule {

	Parameter alarm = new Parameter("psAlarm", null, "none");
	
	Parameter persons;
	Parameter spo2;
	
	PersonSpO2Rule() {
		super.ruleName = "PersonSpO2Rule";
		initializeRule();
	}

	//Method to update the state based on newParameter
	void updateState(Parameter newParameter) {
		// what happens when its a Parameter of the type persons
		if(newParameter.parameterType.equals("persons")) {
			//none persons in the room 
			if(newParameter.parameterValue.equals("none")) {
				if(state == 0 || state == 3) {
					state = 1;
				} else if(state == 2|| state == 6) {
					state = 5;
				} else if(state == 4|| state == 8) {
					state = 8;
				}
			} 
			//many persons in the room
			else if(newParameter.parameterValue.equals("many")) {
				if(state == 0 || state == 1){
					state = 3;
				} else if(state == 2 || state == 5) {
					state = 6;
				} else if(state == 4 ||state == 8) {
					state = 7;
				} else if((state == 6 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 2000)) || state == 10) {
					state = 9;
				} 
			}
		} 
		//what happens when the Parameter is spo2
		else if(newParameter.parameterType.equals("spo2")) {
			//spo2 is normal
			if(newParameter.parameterValue.equals("low")) {
				if(state == 0|| state == 4) {
					state = 2;
				} else if(state == 1|| state == 8){
					state = 5;
					alarm.timestamp = newParameter.timestamp;
				} else if(state == 3|| state == 7){
					state = 6;
					alarm.timestamp = newParameter.timestamp;
				} else if((state == 5 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 2000)) || state == 9) {
					state = 10;
				} 
			} 
			//spo2 is low
			else if(newParameter.parameterValue.equals("normal")) {
				if(state == 0 || state == 2) {
					state = 4;
				} else if(state == 1 || state == 5){
					state = 8;
				} else if(state == 3 || state == 6){
					state = 7;
				}
			}
			
		}
		System.out.println("PSpO2-State: " + state);
		evaluateStateMachine();
	}
	
	//method to evaluate the state machine
	void evaluateStateMachine() {
		if(state == 9) {
			alarm.parameterValue = "hnr";
		} else if(state == 10) {
			alarm.parameterValue = "local";
		} else {
			alarm.parameterValue = "none";
		}
		System.out.println("PSpO2-Alarm: " + alarm.parameterValue);
		InferenceControll.handleNewAlarm(alarm);
	}	

	//method that initializes the rule by registering it to the InferenceControll and adding the parameters needed to the list of parameters
	void initializeRule() {
		this.listOfParametersNeeded.add(ruleName);
		this.listOfParametersNeeded.add("persons");
		this.listOfParametersNeeded.add("spo2");
		this.registerRuleAtInferenceControll();		
	}
		
	//getter / setter
	
	void setPersons(Parameter persons) {
		this.persons = persons;
		updateState(persons);
	}
	
	void setSpO2(Parameter spo2) {
		this.spo2 = spo2;
		updateState(spo2);
	}
	
}
