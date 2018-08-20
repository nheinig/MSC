import java.util.*;
public class MasterRule {

	Parameter pvAlarm;
	Parameter psAlarm;
	
	String globalAlarm = "none";
	
	int state = 0;
	
	//Method to update the state based on new alarm from other rules
	void updateState(Parameter newAlarm) {
		if(newAlarm.parameterType.equals("pvAlarm")) {
			pvAlarm = newAlarm;
		} else if(newAlarm.parameterType.equals("psALarm")) {
			psAlarm = newAlarm;
		}
		if(pvAlarm.parameterValue.equals("none") && psAlarm.parameterValue.equals("none")) {
			state = 0;
		} else if(pvAlarm.parameterValue.equals("hnr") || psAlarm.parameterValue.equals("hnr")) {
			state = 1;
		} else if(pvAlarm.parameterValue.equals("local") || psAlarm.parameterValue.equals("local")) {
			state = 2;
		}
		evaluateStateMachine();
	}
	
	//Method to evaluate the state machine output
	void evaluateStateMachine() {
		if(state == 0) {
			globalAlarm = "none";
		} else if(state == 1) {
			globalAlarm = "hnr";
		} else if(state == 2) {
			globalAlarm = "local";
		}
	}
	
	//method to forward the globalAlarm
	String forwardGlobalALarm() {
		return globalAlarm;
	}
}
