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
				if (state == 0 || state == 1 || state == 2 || state == 3) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 1;
					} else {
						state = 17;
					}
				} else if (state == 4) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 5) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 6) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 7) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 7;
					} else {
						state = 17;
					}
				} else if (state == 8) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 8;
					} else {
						state = 17;
					}
				} else if (state == 9) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 9;
					} else {
						state = 17;
					}
				} else if (state == 10) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 11) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 12) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 13) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 14) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 15) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 16) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("disconnected")) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("unknown")) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("connected")) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("disconnected")) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("unknown")) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("connected")) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 17) {
						state = 17;
					}
				}
			}

			else if (newParameter.parameterValue.equals("one")) {

				if (state == 0 || state == 1 || state == 2 || state == 3) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 2;
					} else {
						state = 17;
					}
				} else if (state == 4) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 5) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 6) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 7) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 8) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 9) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 10) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 10;
					} else {
						state = 17;
					}
				} else if (state == 11) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 11;
					} else {
						state = 17;
					}
				} else if (state == 12) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 12;
					} else {
						state = 17;
					}
				} else if (state == 13) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 14) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 15) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 16) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("disconnected")) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("unknown")) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("connected")) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("disconnected")) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("unknown")) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("connected")) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} 
					} else if (state == 17) {
						state = 17;
					}
				}
			}

			// many persons in the room
			else if (newParameter.parameterValue.equals("many")) {

				if (state == 0 || state == 1 || state == 2 || state == 3) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 3;
					} else {
						state = 17;
					}
				} else if (state == 4) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 13;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 5) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 6) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 15;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 7) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 13;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 8) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 9) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 5;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 10) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 13;
					} else {
						state = 17;
					}
				} else if (state == 11) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 12) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 15;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 13) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 13;
					} else {
						state = 17;
					}
				} else if (state == 14) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 14;
					} else {
						state = 17;
					}
				} else if (state == 15) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						state = 15;
					} else {
						state = 17;
					}
				} else if (state == 16) {
					if (personsTS.getTime() - tubeTS.getTime() < 2000) {
						if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("disconnected")) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("unknown")) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("tube")
								&& listOfLastInputs.get(0).parameterValue.equals("connected")) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("disconnected")) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("unknown")) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("tube")
								&& listOfLastInputs.get(1).parameterValue.equals("connected")) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 17) {
						state = 17;
					}
				}
			}
		}
		// what happens when the Parameter is tube
		else if (newParameter.parameterType.equals("tube")) {
			tubeTS = newParameter.timestamp;
			// tube is disconnected
			if (newParameter.parameterValue.equals("disconnected")) {
				if (state == 0 || state == 4 || state == 5 || state == 6) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 4;
					} else {
						state = 16;
					}
				} else if (state == 1) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 2) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 3) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 13;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 7) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 7;
					} else {
						state = 16;
					}
				} else if (state == 8) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 9) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 10) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 10;
					} else {
						state = 16;
					}
				} else if (state == 11) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 12) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 13) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 13;
					} else {
						state = 16;
					}
				} else if (state == 14) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 13;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 15) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 13;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 17) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("none")) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("one")) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("many")) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("none")) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("one")) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("many")) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 16) {
						state = 16;
					}
				}

			}

			// tube is unknown
			else if (newParameter.parameterValue.equals("unknown")) {
				if (state == 0 || state == 4 || state == 5 || state == 6) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 5;
					} else {
						state = 16;
					}
				} else if (state == 1) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 2) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 3) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 7) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 8) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 8;
					} else {
						state = 16;
					}
				} else if (state == 9) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 10) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 11) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 11;
					} else {
						state = 16;
					}
				} else if (state == 12) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 13) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 14) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 14;
					} else {
						state = 16;
					}
				} else if (state == 15) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 17) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("none")) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("one")) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("many")) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("none")) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("one")) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("many")) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 16) {
						state = 16;
					}
				}
			}
			// tube is connected
			else if (newParameter.parameterValue.equals("connected")) {
				if (state == 0 || state == 4 || state == 5 || state == 6) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 6;
					} else {
						state = 16;
					}
				} else if (state == 1) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 2) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 3) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 15;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 7) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 8) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 9) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 9;
					} else {
						state = 16;
					}
				} else if (state == 10) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 11) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 12) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 12;
					} else {
						state = 16;
					}
				} else if (state == 13) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 15;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 14) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 15;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 16;
					}
				} else if (state == 15) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						state = 15;
					} else {
						state = 16;
					}
				} else if (state == 17) {
					if (tubeTS.getTime() - personsTS.getTime() < 2000) {
						if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("none")) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("one")) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("persons")
								&& listOfLastInputs.get(0).parameterValue.equals("many")) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("none")) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("one")) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("persons")
								&& listOfLastInputs.get(1).parameterValue.equals("many")) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 16) {
						state = 16;
					}
				}
			}
		}
		System.out.println("PVT-State: " + state);

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
