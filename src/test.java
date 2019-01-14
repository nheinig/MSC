import java.sql.Timestamp;
import java.util.ArrayList;

public class test extends Rule {

	Timestamp personsTS = new Timestamp(System.currentTimeMillis());
	Timestamp pvAlarmTS = new Timestamp(System.currentTimeMillis());
	Timestamp stateTS = new Timestamp(System.currentTimeMillis());

test() {
		setRuleName("test");
		ruleResult = new Parameter("abc", null, "none", null);
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
		// what happens when the Parameter is of the typepersons
		if (newParameter.parameterType.equals("persons")) {
       personsTS = newParameter.timestamp;
           // persons is none
			if (newParameter.parameterValue.equals("none")) {
			}
			// persons is one
			else if (newParameter.parameterValue.equals("one")) {
			}
           // persons is one
           else if (newParameter.parameterValue.equals("many")) {
           }
		// what happens when the Parameter is of the type pvAlarm
		else if (newParameter.parameterType.equals("pvAlarm")) {
			pvAlarmTS = newParameter.timestamp;
			// pvAlarm is none
			if (newParameter.parameterValue.equals("none")) {
			}
			// pvAlarm is local
			else if (newParameter.parameterValue.equals("local")) {
			}
			// pvAlarm is hnr
			else if (newParameter.parameterValue.equals("hnr")) {
			}
		}
		evaluateStateMachine();
	}

	
	
	@Override
	void fillStateOutputList() {
		
	}
	
	@Override
	void showStateMachine() {
		
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
		listOfOutputs.add("jkl");
		listOfOutputs.add("ghi");
		listOfOutputs.add("def");
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
