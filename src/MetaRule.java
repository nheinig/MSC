import java.sql.Timestamp;

public class MetaRule extends Rule {
	
	Parameter pvAlarm = new Parameter("pvAlarm", null, "none");
	Parameter psAlarm= new Parameter("psAlarm", null, "none");;

	
	int state = 0;
	
	MetaRule() {
		ruleName = "MetaRule";
		super.ruleResult = new Parameter("GlobalAlarm", null,"none");
		initializeRule();
	}

	//Method to update the state based on new alarm from other rules
	@Override
	void updateState(Parameter newAlarm) {
		if(newAlarm.parameterType.equals("pvAlarm")) {
			pvAlarm = newAlarm;
		} else if(newAlarm.parameterType.equals("psAlarm")) {
			psAlarm = newAlarm;
		}
		if(pvAlarm.parameterValue.equals("hnr") || psAlarm.parameterValue.equals("hnr")) {
			if (state != 1) {
			ruleResult.timestamp = new Timestamp(System.currentTimeMillis());
			}
			state = 1;
		} else if((pvAlarm.parameterValue.equals("local") || psAlarm.parameterValue.equals("local"))) {
			if (state != 2) {
				ruleResult.timestamp = new Timestamp(System.currentTimeMillis());
				}
			state = 2;
		} else if(pvAlarm.parameterValue.equals("none") && psAlarm.parameterValue.equals("none")) {
			state = 0;
		}
		System.out.println("Meta-State: " + state);
		evaluateStateMachine();
	}
	
	//Method to evaluate the state machine output
	@Override
	void evaluateStateMachine() {
		if(state == 0) {
			ruleResult.parameterValue = "none";
		} else if(state == 1) {
			ruleResult.parameterValue  = "hnr";
		} else if(state == 2) {
			ruleResult.parameterValue  = "local";
		}
		System.out.println("Meta-Alarm: " + ruleResult.parameterValue + " since " + ruleResult.timestamp );
	}
	
	//method to forward the globalAlarm
	Parameter forwardGlobalAlarm() {
		return ruleResult;
	}

	//OLD
	@Override
	void initializeRule() {
		this.listOfParametersNeeded.add("pvAlarm");
		this.listOfParametersNeeded.add("psAlarm");
		this.listOfOutputs.add(ruleResult.parameterType);
	}
}
