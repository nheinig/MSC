import java.sql.Timestamp;

public class MetaRule extends Rule {

	Parameter alarm = new Parameter("GlobalAlarm", new Timestamp(System.currentTimeMillis()), "none");
	
	Parameter pvAlarm = new Parameter("pvAlarm", null, "none");
	Parameter psAlarm= new Parameter("psAlarm", null, "none");;

	
	int state = 0;
	
	MetaRule() {
		super.ruleName = "MetaRule";
		initializeRule();
	}

	//Method to update the state based on new alarm from other rules
	void updateState(Parameter newAlarm) {
		if(newAlarm.parameterType.equals("pvAlarm")) {
			pvAlarm = newAlarm;
		} else if(newAlarm.parameterType.equals("psAlarm")) {
			psAlarm = newAlarm;
		}
		if(pvAlarm.parameterValue.equals("none") && psAlarm.parameterValue.equals("none")) {
			state = 0;
		} else if(pvAlarm.parameterValue.equals("hnr") || psAlarm.parameterValue.equals("hnr") && !(state == 1)) {
			state = 1;
			alarm.timestamp = new Timestamp(System.currentTimeMillis());
		} else if((pvAlarm.parameterValue.equals("local") || psAlarm.parameterValue.equals("local")) && !(state == 2)) {
			state = 2;
			alarm.timestamp = new Timestamp(System.currentTimeMillis());
		}
		System.out.println("Meta-State: " + state);
		evaluateStateMachine();
	}
	
	//Method to evaluate the state machine output
	void evaluateStateMachine() {
		if(state == 0) {
			alarm.parameterValue = "none";
		} else if(state == 1) {
			alarm.parameterValue  = "hnr";
		} else if(state == 2) {
			alarm.parameterValue  = "local";
		}
		System.out.println("Meta-Alarm: " + alarm.parameterValue);
	}
	
	//method to forward the globalAlarm
	Parameter forwardGlobalAlarm() {
		return alarm;
	}
		
	void initializeRule() {
		this.listOfParametersNeeded.add(ruleName);
		this.listOfParametersNeeded.add("pvAlarm");
		this.listOfParametersNeeded.add("psAlarm");
		this.registerRuleAtInferenceControll();		
	}
}
