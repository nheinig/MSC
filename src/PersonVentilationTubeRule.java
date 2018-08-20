import java.util.*;

public class PersonVentilationTubeRule {

	Parameter tube = null;
	Parameter persons = null;
	
	Parameter pvAlarm = new Parameter("PVAlarm", null, "none");
	
	Date timeOfPersons = null;
	Date timeOfTube = null;
	
	int state = 0;
	
	//Method to update the state based on newParameter
	void updateState(Parameter newParameter) {
		// what happens when its a Parameter of the type persons
		if(newParameter.parameterType.equals("persons")) {
			persons = newParameter;			
			timeOfPersons = newParameter.timestamp;
			System.out.println(persons);
			//none persons in the room 
			if(newParameter.parameterValue.equals("none")) {
				if(state == 0|| state == 3) {
					state = 1;
				} else if(state == 2|| state == 6) {
					state = 5;
					pvAlarm.timestamp = newParameter.timestamp;
				} else if(state == 4|| state == 8) {
					state = 8;
				}
				//todo timebased reaction if state == 5
			} 
			//many persons in the room
			else if(newParameter.parameterValue.equals("many")) {
				if(state == 0 || state == 1){
					state = 3;
				} else if(state == 2 || state == 5) {
					state = 6;
					pvAlarm.timestamp = newParameter.timestamp;
				} else if(state == 4 ||state == 8) {
					state = 7;
				}//todo timebased reaction if state == 6
			}
		} 
		//what happens when the Parameter is tube
		else if(newParameter.parameterType.equals("tube")) {
			tube = newParameter;
			timeOfTube = newParameter.timestamp;
			System.out.println(tube);
			
			//tube is disconnected
			if(newParameter.parameterValue.equals("disconnected")) {
				if(state == 0|| state == 4) {
					state = 2;
				} else if(state == 1|| state == 8){
					state = 5;
					pvAlarm.timestamp = newParameter.timestamp;
				} else if(state == 3|| state == 7){
					state = 6;
					pvAlarm.timestamp = newParameter.timestamp;
				} //todo timebased reaction if state == 5 or state == 6
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
		if(state == 5) {
			pvAlarm.parameterValue = "hnr";
		} else if(state == 6) {
			pvAlarm.parameterType = "local";
		} else if(state == 7 || state == 8) {
			pvAlarm.parameterValue = "none";
		} else {
			pvAlarm.parameterValue = "nep";
		}
	}
	
	Parameter forwardEvaluation() {
		return pvAlarm;
	}
	
}
