import java.sql.Timestamp;
import java.util.ArrayList;
import java.awt.Color;

public class PersonsSpO2Rule extends Rule {

	Timestamp personsTS = new Timestamp(System.currentTimeMillis());
	Timestamp spo2TS = new Timestamp(System.currentTimeMillis());
	Timestamp stateTS = new Timestamp(System.currentTimeMillis());

	PersonsSpO2Rule() {
		setRuleName("PersonsSpO2Rule");
		ruleResult = new Parameter("psResult", null, null, null);
		initializeRule();
		InferenceControl.addAvailableParameter(getOutputType());
		InferenceControl.addAvailableParameterValues(listOfOutputs);
		RuleEgg ruleEgg = new RuleEgg(this);
		ConfigurationUI.forwardRuleEgg(ruleEgg);
	}

	// Method to update the state based on newParameter
	@Override
	void updateState(Parameter newParameter) {
		prevState = state;
		ruleResult.timestamp = newParameter.timestamp;
		// what happens when the Parameter is of the type persons
		if (newParameter.parameterType.equals("persons")) {
			personsTS = newParameter.timestamp;
			// persons is none
			if (newParameter.parameterValue.equals("none")) {
				if (state == 0 || state == 1 || state == 2 || state == 3) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 1;
					} else {
						state = 17;
					}
				} else if (state == 4) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 5) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 6) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 7) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 7;
					} else {
						state = 17;
					}
				} else if (state == 8) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 8;
					} else {
						state = 17;
					}
				} else if (state == 9) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 9;
					} else {
						state = 17;
					}
				} else if (state == 10) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 11) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 12) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 13) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 7;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 14) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 8;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 15) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 9;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 16) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("critical")) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("low")) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("normal")) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("critical")) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("low")) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("normal")) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 17) {
						state = 17;
					}
				}
			}
			// persons is one
			else if (newParameter.parameterValue.equals("one")) {
				if (state == 0 || state == 1 || state == 2 || state == 3) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 2;
					} else {
						state = 17;
					}
				} else if (state == 4) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 5) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 6) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 7) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 8) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 9) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 10) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 10;
					} else {
						state = 17;
					}
				} else if (state == 11) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 11;
					} else {
						state = 17;
					}
				} else if (state == 12) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 12;
					} else {
						state = 17;
					}
				} else if (state == 13) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 10;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 14) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 11;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 15) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 12;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 16) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("critical")) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("low")) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("normal")) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("critical")) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("low")) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("normal")) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 17) {
						state = 17;
					}
				}
			}
			// persons is many
			else if (newParameter.parameterValue.equals("many")) {
				if (state == 0 || state == 1 || state == 2 || state == 3) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 3;
					} else {
						state = 17;
					}
				} else if (state == 4) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 13;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 5) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 6) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 15;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 7) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 13;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 8) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 9) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 5;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 10) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 13;
					} else {
						state = 17;
					}
				} else if (state == 11) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 14;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 12) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 15;
						stateTS = new Timestamp(System.currentTimeMillis());
					} else {
						state = 17;
					}
				} else if (state == 13) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 13;
					} else {
						state = 17;
					}
				} else if (state == 14) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 14;
					} else {
						state = 17;
					}
				} else if (state == 15) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						state = 15;
					} else {
						state = 17;
					}
				} else if (state == 16) {
					if (personsTS.getTime() - spo2TS.getTime() < 1000) {
						if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("critical")) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("low")) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(0).parameterType.equals("spo2")
								&& listOfLastInputs.get(0).parameterValue.equals("normal")) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("critical")) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("low")) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else if (listOfLastInputs.get(1).parameterType.equals("spo2")
								&& listOfLastInputs.get(1).parameterValue.equals("normal")) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						}
					} else if (state == 17) {
						state = 17;
					}
				}
			}
			// what happens when the Parameter is of the type spo2
			else if (newParameter.parameterType.equals("spo2")) {
				spo2TS = newParameter.timestamp;
				// spo2 is critical
				if (newParameter.parameterValue.equals("critical")) {
					if (state == 0 || state == 4 || state == 5 || state == 6) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 4;
						} else {
							state = 16;
						}
					} else if (state == 1) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 2) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 3) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 7) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 7;
						} else {
							state = 16;
						}
					} else if (state == 8) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 9) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 7;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 10) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 10;
						} else {
							state = 16;
						}
					} else if (state == 11) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 12) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 10;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 13) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 13;
						} else {
							state = 16;
						}
					} else if (state == 14) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 15) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 13;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 17) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
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
				// spo2 is low
				else if (newParameter.parameterValue.equals("low")) {
					if (state == 0 || state == 4 || state == 5 || state == 6) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 5;
						} else {
							state = 16;
						}
					} else if (state == 1) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 2) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 3) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 7) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 8) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 8;
						} else {
							state = 16;
						}
					} else if (state == 9) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 8;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 10) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 11) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 11;
						} else {
							state = 16;
						}
					} else if (state == 12) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 11;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 13) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 14) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 14;
						} else {
							state = 16;
						}
					} else if (state == 15) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 14;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 17) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
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
				// spo2 is normal
				else if (newParameter.parameterValue.equals("normal")) {
					if (state == 0 || state == 4 || state == 5 || state == 6) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 6;
						} else {
							state = 16;
						}
					} else if (state == 1) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 2) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 3) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 7) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 8) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 9;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 9) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 9;
						} else {
							state = 16;
						}
					} else if (state == 10) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 11) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 12;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 12) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 12;
						} else {
							state = 16;
						}
					} else if (state == 13) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 14) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 15;
							stateTS = new Timestamp(System.currentTimeMillis());
						} else {
							state = 16;
						}
					} else if (state == 15) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
							state = 15;
						} else {
							state = 16;
						}
					} else if (state == 17) {
						if (spo2TS.getTime() - personsTS.getTime() < 1000) {
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
		}
		evaluateStateMachine();
	}

	@Override
	void fillStateOutputList() {
		ArrayList<String> tempStateList0 = new ArrayList<String>();
		tempStateList0.add("init");
		tempStateList0.add("none");
		ArrayList<String> tempStateList1 = new ArrayList<String>();
		tempStateList1.add("persons == none && spo2 == critical");

		tempStateList1.add("hnr");
		ArrayList<String> tempStateList2 = new ArrayList<String>();
		tempStateList2.add("persons == none && spo2 == low");
		tempStateList2.add("none");
		ArrayList<String> tempStateList3 = new ArrayList<String>();
		tempStateList3.add("persons == none && spo2 == normal");
		tempStateList3.add("none");
		ArrayList<String> tempStateList4 = new ArrayList<String>();
		tempStateList4.add("persons == one && spo2 == critical");
		tempStateList4.add("hnr");
		ArrayList<String> tempStateList5 = new ArrayList<String>();
		tempStateList5.add("persons == one && spo2 == low");
		tempStateList5.add("none");
		ArrayList<String> tempStateList6 = new ArrayList<String>();
		tempStateList6.add("persons == one && spo2 == normal");
		tempStateList6.add("none");
		ArrayList<String> tempStateList7 = new ArrayList<String>();
		tempStateList7.add("persons == many && spo2 == critical");
		tempStateList7.add("local");
		ArrayList<String> tempStateList8 = new ArrayList<String>();
		tempStateList8.add("persons == many && spo2 == low");
		tempStateList8.add("local");
		ArrayList<String> tempStateList9 = new ArrayList<String>();
		tempStateList9.add("persons == many && spo2 == normal");
		tempStateList9.add("none");
		ArrayList<String> tempStateList10 = new ArrayList<String>();
		tempStateList10.add("personsMissing");
		tempStateList10.add("local");
		ArrayList<String> tempStateList11 = new ArrayList<String>();
		tempStateList11.add("spo2Missing");
		tempStateList11.add("local");

		stateOutputList.add(tempStateList0);
		stateOutputList.add(tempStateList1);
		stateOutputList.add(tempStateList2);
		stateOutputList.add(tempStateList3);
		stateOutputList.add(tempStateList4);
		stateOutputList.add(tempStateList5);
		stateOutputList.add(tempStateList6);
		stateOutputList.add(tempStateList7);
		stateOutputList.add(tempStateList8);
		stateOutputList.add(tempStateList9);
		stateOutputList.add(tempStateList10);
		stateOutputList.add(tempStateList11);
	}

	@Override
	void showStateMachine() {

	}

	// method to evaluate the state machine
	@Override
	void evaluateStateMachine() {
		Timestamp tempTS = new Timestamp(System.currentTimeMillis());
		if (state > 6) {
			if (state == 13 || state == 14 || state == 16 || state == 17) {
				ruleResult.parameterValue = "local";
			} else if (state == 7 || state == 10) {
				ruleResult.parameterValue = "hnr";
			}
		} else {
			ruleResult.parameterValue = "none";
		}

		System.out.println("psResult: " + ruleResult.parameterValue);
		InferenceControl.handleNewAlarm(ruleResult);
	}

	@Override
	void initializeRule() {
		this.listOfParametersNeeded.add("persons");
		this.listOfParametersNeeded.add("spo2");
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
