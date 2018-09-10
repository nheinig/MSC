import java.sql.Timestamp;

public class PersonSpO2Rule extends Rule {

	Timestamp personsTS = new Timestamp(System.currentTimeMillis());
	Timestamp spo2TS = new Timestamp(System.currentTimeMillis());

	PersonSpO2Rule() {
		ruleName = "PersonSpO2Rule";
		super.ruleResult = new Parameter("psAlarm", null,"none");
		initializeRule();
		InferenceControll.addAvailableParameter(getOutputType());
	}

	// Method to update the state based on newParameter
	@Override
	void updateState(Parameter newParameter) {
		// what happens when its a Parameter of the type persons
		if (newParameter.parameterType.equals("persons")) {
			personsTS = newParameter.timestamp;
			// no persons in the room
			if (newParameter.parameterValue.equals("none")) {
				if (state == 0 || state == 3) {
					state = 1;
				} else if (state == 2 || state == 6) {
					state = 5;
				} else if (state == 4 || state == 7) {
					state = 8;
				} else if ((state == 5 && (newParameter.timestamp.getTime() - ruleResult.timestamp.getTime() >= 2000))
						|| state == 10) {
					state = 9;
				}
				if(personsTS.getTime() > spo2TS.getTime() + 20000 && !(state == 9 || state == 10)) {
					state = 9;
					ruleResult.timestamp = newParameter.timestamp;
				}
			}
			// many persons in the room
			else if (newParameter.parameterValue.equals("many")) {
				if (state == 0 || state == 1) {
					state = 3;
				} else if (state == 2 || state == 5) {
					state = 8;
				} else if (state == 4 || state == 8) {
					state = 7;
				} else if ((state == 6 && (newParameter.timestamp.getTime() - ruleResult.timestamp.getTime() >= 2000))
						|| state == 9) {
					state = 10;
				} 
				if(personsTS.getTime() > spo2TS.getTime() + 20000 && !(state == 9 || state == 10)) {
					state = 10;
					ruleResult.timestamp = newParameter.timestamp;
				}
			}
		}

		// what happens when the Parameter is spo2
		else if (newParameter.parameterType.equals("spo2")) {
			spo2TS = newParameter.timestamp;
			// spo2 is normal
			if (newParameter.parameterValue.equals("low")) {
				if (state == 0 || state == 4) {
					state = 2;
				} else if (state == 1 || state == 8) {
					state = 5;
					ruleResult.timestamp = newParameter.timestamp;
				} else if (state == 3 || state == 7) {
					state = 6;
					ruleResult.timestamp = newParameter.timestamp;
				} else if ((state == 5 && (newParameter.timestamp.getTime() - ruleResult.timestamp.getTime() >= 2000))) {
					state = 9;
				}
				if(spo2TS.getTime() > personsTS.getTime() + 20000 && !(state == 9 || state == 10)) {
					state = 10;
				}
			}
			// spo2 is low
			else if (newParameter.parameterValue.equals("normal")) {
				if (state == 0 || state == 2) {
					state = 4;
				} else if (state == 1 || state == 5 || state == 9) {
					state = 8;
				} else if (state == 3 || state == 6 || state == 10) {
					state = 7;
				}
				if(spo2TS.getTime() > personsTS.getTime() + 20000 && !(state == 9 || state == 10)) {
					state = 10;
					ruleResult.timestamp = newParameter.timestamp;
				}
			}
			if (state == 10 && (newParameter.timestamp.getTime() > ruleResult.timestamp.getTime() + 20000)) {
				state = 9;
			}		
		}
		System.out.println("PSpO2-State: " + state);
		evaluateStateMachine();
	}

	// method to evaluate the state machine
	@Override
	void evaluateStateMachine() {
		if (state == 9) {
			ruleResult.parameterValue = "hnr";
		} else if (state == 10) {
			ruleResult.parameterValue = "local";
		} else {
			ruleResult.parameterValue = "none";
		}
		System.out.println("PSpO2-Alarm: " + ruleResult.parameterValue);
		InferenceControll.handleNewAlarm(ruleResult);
	}

	@Override
	void initializeRule() {
		this.listOfParametersNeeded.add("persons");
		this.listOfParametersNeeded.add("spo2");
	}
	
	@Override
	String getOutputType() {
		return ruleResult.parameterType;
	}
	
	// getter / setter

}
