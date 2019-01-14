import java.sql.Timestamp;
import java.util.ArrayList;

public class PersonVentilationTubeRule extends Rule {

	Timestamp personsTS = new Timestamp(System.currentTimeMillis());
	Timestamp tubeTS = new Timestamp(System.currentTimeMillis());

	PersonVentilationTubeRule() {
		setRuleName("PersonVentilationTubeRule");
		ruleResult = new Parameter("pvAlarm", null, "none", null);
		initializeRule();
		InferenceControll.addAvailableParameter(getOutputType());
		InferenceControll.addAvailableParameterValues(listOfOutputs);
		RuleEgg ruleEgg = new RuleEgg(this);
		ConfigurationUI.forwardRuleEgg(ruleEgg);
	}

	// Method to update the state based on newParameter
	@Override
	void updateState(Parameter newParameter) {
		prevState = state;
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
				} else if ((state == 5 && (newParameter.timestamp.getTime() - ruleResult.timestamp.getTime() >= 3000))
						|| state == 10) {
					state = 9;
				}
				if (personsTS.getTime() > tubeTS.getTime() + 20000 && !(state == 9 || state == 10)) {
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
				} else if ((state == 6 && (newParameter.timestamp.getTime() - ruleResult.timestamp.getTime() >= 3000))
						|| state == 9) {
					state = 10;
				}
				if (personsTS.getTime() > tubeTS.getTime() + 20000 && !(state == 9 || state == 10)) {
					state = 10;
					ruleResult.timestamp = newParameter.timestamp;
				}
			}
		}
		// what happens when the Parameter is spo2
		else if (newParameter.parameterType.equals("tube")) {
			tubeTS = newParameter.timestamp;
			// spo2 is normal
			if (newParameter.parameterValue.equals("disconnected")) {
				if (state == 0 || state == 4) {
					state = 2;
				} else if (state == 1 || state == 8) {
					state = 5;
					ruleResult.timestamp = newParameter.timestamp;
				} else if (state == 3 || state == 7) {
					state = 6;
					ruleResult.timestamp = newParameter.timestamp;
				} else if ((state == 5
						&& (newParameter.timestamp.getTime() - ruleResult.timestamp.getTime() >= 3000))) {
					state = 9;
				} else if ((state == 6
						&& (newParameter.timestamp.getTime() - ruleResult.timestamp.getTime() >= 3000))) {
					state = 10;
				}
				if (tubeTS.getTime() > personsTS.getTime() + 20000 && !(state == 9 || state == 10)) {
					state = 10;
				}
			}
			// spo2 is low
			else if (newParameter.parameterValue.equals("connected")) {
				if (state == 0 || state == 2) {
					state = 4;
				} else if (state == 1 || state == 5 || state == 9) {
					state = 8;
				} else if (state == 3 || state == 6 || state == 10) {
					state = 7;
				}
				if (tubeTS.getTime() > personsTS.getTime() + 20000 && !(state == 9 || state == 10)) {
					state = 10;
					ruleResult.timestamp = newParameter.timestamp;
				}
			}
			if (state == 10 && (newParameter.timestamp.getTime() > ruleResult.timestamp.getTime() + 2000)) {
				state = 9;
			}
		}
		System.out.println("PVT-State: " + state);
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
		System.out.println("PVT-Alarm: " + ruleResult.parameterValue);
		InferenceControll.handleNewAlarm(ruleResult);
	}

	@Override
	void initializeRule() {
		this.listOfParametersNeeded.add("persons");
		this.listOfParametersNeeded.add("tube");
		listOfOutputs.add(ruleResult.parameterType);
		listOfOutputs.add("none");
		listOfOutputs.add("local");
		listOfOutputs.add("hnr");
	}

	@Override
	String getOutputType() {
		return ruleResult.parameterType;
	}

	@Override
	void updateEggLabels() {
		if (listOfLastInputs.size() > 1) {
			RulePanel.forwardEggLabelUpdate(ruleName, state, prevState, listOfLastInputs.get(0),
					listOfLastInputs.get(1), ruleResult);
		} else if (listOfLastInputs.size() == 1) {
			RulePanel.forwardEggLabelUpdate(ruleName, state, prevState, listOfLastInputs.get(0), null, ruleResult);
		} else if (listOfLastInputs.size() == 0) {
			RulePanel.forwardEggLabelUpdate(ruleName, state, prevState, null, null, ruleResult);
		}
	}
}
