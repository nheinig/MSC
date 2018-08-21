
public class PersonVentilationTubeRule extends Rule {
	
	Parameter alarm = new Parameter("PVAlarm", null, "none");
	
	PersonVentilationTubeRule(String ruleName) {
		super(ruleName);
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
					alarm.timestamp = newParameter.timestamp;
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
					alarm.timestamp = newParameter.timestamp;
				} else if(state == 4 ||state == 8) {
					state = 7;
				} else if((state == 6 && (alarm.timestamp.getTime() - newParameter.timestamp.getTime() > 5)) || state == 10) {
					state = 9;
				} 
			}
		} 
		//what happens when the Parameter is tube
		else if(newParameter.parameterType.equals("tube")) {
			//tube is disconnected
			if(newParameter.parameterValue.equals("disconnected")) {
				if(state == 0|| state == 4) {
					state = 2;
				} else if(state == 1|| state == 8){
					state = 5;
					alarm.timestamp = newParameter.timestamp;
				} else if(state == 3|| state == 7){
					state = 6;
					alarm.timestamp = newParameter.timestamp;
				} else if((state == 5 && (alarm.timestamp.getTime() - newParameter.timestamp.getTime() > 5)) || state == 9) {
					state = 10;
				} 
			} 
			//tube is connected
			else if(newParameter.parameterValue.equals("connected")) {
				if(state == 0 || state == 2) {
					state = 4;
				} else if(state == 1 || state == 5){
					state = 8;
				} else if(state == 3 || state == 6){
					state = 7;
				}
			}
			
		}
	}
	
	//method to evaluate the state machine
	void evaluateStateMachine() {
		if(state == 9) {
			alarm.parameterValue = "hnr";
		} else if(state == 10) {
			alarm.parameterType = "local";
		} else {
			alarm.parameterValue = "none";
		}
	}		
	
	
	void initializeRule() {
		this.listOfParametersNeeded.add(ruleName);
		this.listOfParametersNeeded.add("persons");
		this.listOfParametersNeeded.add("tube");
		this.registerRuleAtInferenceControll();		
	}
}
