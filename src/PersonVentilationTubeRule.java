
public class PersonVentilationTubeRule extends Rule {
	
	Parameter alarm = new Parameter("pvAlarm", null, "none");
	
	PersonVentilationTubeRule() {
		super.ruleName = "PersonVentilationTubeRule";
	}
	
	//Method to update the state based on newParameter
	@Override
	void updateState(Parameter newParameter) {
		// what happens when its a Parameter of the type persons
		if(newParameter.parameterType.equals("persons")) {
			//no persons in the room 
			if(newParameter.parameterValue.equals("none")) {
				if(state == 0 || state == 3) {
					state = 1;
				} else if(state == 2|| state == 6) {
					state = 5;
				} else if(state == 4|| state == 7) {
					state = 8;
				} else if((state == 5 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000)) || state == 10) {
					state = 9;
				} 
			} 
			//many persons in the room
			else if(newParameter.parameterValue.equals("many")) {
				if(state == 0 || state == 1){
					state = 3;
				} else if(state == 2 || state == 5){
					state = 8;
				} else if(state == 4 || state == 8){
					state = 7;
				} else if((state == 6 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000)) || state == 9) {
					state = 10;
				} 
			}
		} 
		//what happens when the Parameter is spo2
		else if(newParameter.parameterType.equals("tube")) {
			//spo2 is normal
			if(newParameter.parameterValue.equals("disconnected")) {
				if(state == 0|| state == 4) {
					state = 2;
				} else if(state == 1|| state == 8){
					state = 5;
					alarm.timestamp = newParameter.timestamp;
				} else if(state == 3|| state == 7){
					state = 6;
					alarm.timestamp = newParameter.timestamp;
				} else if((state == 5 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000))) {
					state = 9;
				} else if((state == 6 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000))) {
					state = 10;
				}
			} 
			//spo2 is low
			else if(newParameter.parameterValue.equals("connected")) {
				if(state == 0 || state == 2) {
					state = 4;
				} else if(state == 1 || state == 5 || state == 9){
					state = 8;
				} else if(state == 3 || state == 6 || state == 10){
					state = 7;
				}
			}
			
		}
		System.out.println("PVT-State: " + state);
		evaluateStateMachine();
	}
	
	//method to evaluate the state machine
	@Override
	void evaluateStateMachine() {
		if(state == 9) {
			alarm.parameterValue = "hnr";
		} else if(state == 10) {
			alarm.parameterValue = "local";
		} else {
			alarm.parameterValue = "none";
		}
		System.out.println("PVT-Alarm: " + alarm.parameterValue);
		InferenceControll.handleNewAlarm(alarm);
	}		
	
	@Override
	void initializeRule() {
		this.listOfParametersNeeded.add(ruleName);
		this.listOfParametersNeeded.add("persons");
		this.listOfParametersNeeded.add("tube");
		this.registerRuleAtInferenceControll();
		InferenceControll.addAvailableParameter(getOutputType());
	}
	
	@Override
	String getOutputType() {
		return alarm.parameterType;
	}
}
