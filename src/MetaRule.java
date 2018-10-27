import java.sql.Timestamp;

public class MetaRule extends Rule {

	Parameter pvAlarm = new Parameter("pvAlarm", null, "none");
	Parameter psAlarm = new Parameter("psAlarm", null, "none");;

	int state = 0;

	MetaRule() {
		setRuleName("MetaRule");
		ruleResult = new Parameter("GlobalAlarm", null, "none");
		initializeRule();
		RuleEgg ruleEgg = new RuleEgg(this);
		ConfigurationUI.forwardRuleEgg(ruleEgg);
	}

	// Method to update the state based on new alarm from other rules
	@Override
	void updateState(Parameter newAlarm) {
		prevState = state;
		if (newAlarm.parameterType.equals("pvAlarm")) {
			pvAlarm = newAlarm;
		} else if (newAlarm.parameterType.equals("psAlarm")) {
			psAlarm = newAlarm;
		}
		if (pvAlarm.parameterValue.equals("hnr") || psAlarm.parameterValue.equals("hnr")) {
			if (state != 1) {
				ruleResult.timestamp = new Timestamp(System.currentTimeMillis());
			}
			state = 1;
		} else if ((pvAlarm.parameterValue.equals("local") || psAlarm.parameterValue.equals("local"))) {
			if (state != 2) {
				ruleResult.timestamp = new Timestamp(System.currentTimeMillis());
			}
			state = 2;
		} else if (pvAlarm.parameterValue.equals("none") && psAlarm.parameterValue.equals("none")) {
			state = 0;
		}
		System.out.println("Meta-State: " + state);
		evaluateStateMachine();
	}

	// Method to evaluate the state machine output
	@Override
	void evaluateStateMachine() {
		if (state == 0) {
			ruleResult.parameterValue = "none";
		} else if (state == 1) {
			ruleResult.parameterValue = "hnr";
		} else if (state == 2) {
			ruleResult.parameterValue = "local";
		}
		System.out.println("Meta-Alarm: " + ruleResult.parameterValue + " since " + ruleResult.timestamp);
	}

	// method to forward the globalAlarm
	Parameter forwardGlobalAlarm() {
		return ruleResult;
	}

	// OLD
	@Override
	void initializeRule() {
		this.listOfParametersNeeded.add("pvAlarm");
		this.listOfParametersNeeded.add("psAlarm");
		this.listOfOutputs.add(ruleResult.parameterType);
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
