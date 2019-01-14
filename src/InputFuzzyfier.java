import java.util.ArrayList;

public class InputFuzzyfier {

	//list of all parameterTypes of the fuzzyfied Inputs
	static ArrayList<String> listOfOutputs = new ArrayList<String>();
	static ArrayList<ArrayList<String>> listOfOutputValues = new ArrayList<ArrayList<String>>();
	
	FuzzyfierEgg fe;
	
	//lower and upper bounds for all InputTypes
	static double personsLB = 0;
	static double personsUB = 2;
	
	static double spo2LB = 90;
	static double spo2UB = 94;
	
	static double tubeLB = 96;
	static double tubeUB = 97;
	
	
	InputFuzzyfier(){
		ArrayList<String> personsValues = new ArrayList<String>();
		personsValues.add("persons");
		personsValues.add("none");
		personsValues.add("one");
		personsValues.add("many");		

		ArrayList<String> spo2Values = new ArrayList<String>();
		spo2Values.add("spo2");
		spo2Values.add("critical");
		spo2Values.add("low");
		spo2Values.add("normal");		

		ArrayList<String> tubeValues = new ArrayList<String>();
		tubeValues.add("tube");
		tubeValues.add("disconnected");
		tubeValues.add("unknown");
		tubeValues.add("connected");
		
		listOfOutputs.add("persons");
		listOfOutputs.add("spo2");
		listOfOutputs.add("tube");
		

		listOfOutputValues.add(listOfOutputValues.size(), personsValues);
		listOfOutputValues.add(listOfOutputValues.size(), spo2Values);
		listOfOutputValues.add(listOfOutputValues.size(), tubeValues);	
		initializeFuzzyfier();
		
		fe = new FuzzyfierEgg(this);
		ConfigurationUI.forwardFuzzyfier(fe);
	}
	
	
	//Method that adds all entries from the listOfOutputTypes to the listOfAvailableParameters in the InferenceControll
	void initializeFuzzyfier() {
		for(int i = 0; i < listOfOutputs.size(); i++) {
			InferenceControll.addAvailableParameter(listOfOutputs.get(i));
			InferenceControll.addAvailableParameterValues(listOfOutputValues.get(i));
			System.out.println("HERERERERER   " + listOfOutputValues.get(i));
		}
	}
	
	
	//method that handles new Inputs and forwards them to the right fuzzyfier based on the inputType
	void handleInput(Input in) {
		switch (in.inputType) {
		case "persons":
			handlePersonsInput(in);
			break;
		case "spo2":
			handleSpO2Input(in);
			break;
		case "tube":
			handleTubeInput(in);
			break;
		default:
			System.out.println("Unknown InputType!");
			break;
		}	
	}
		

	//Method that fuzzyfies Inputs with the inputType "persons"
	Parameter handlePersonsInput(Input in) {
		Parameter newPersonsParameter = new Parameter("persons", in.timestamp, null, null);
		if(in.inputValue <= personsLB) {
			newPersonsParameter.parameterValue = "none";
		} else if(in.inputValue > personsLB && in.inputValue < personsUB) {
			newPersonsParameter.parameterValue = "one";
		} else if(in.inputValue > personsUB) {
			newPersonsParameter.parameterValue = "many";
		}	
		newPersonsParameter.parameterState = "persons = " + newPersonsParameter.parameterValue;
		return newPersonsParameter;				
	}

	//Method that fuzzyfies Inputs with the inputType "spo2"
	Parameter handleSpO2Input(Input in) {
		Parameter newSpO2Parameter = new Parameter("spo2", in.timestamp, null, null);
		if(in.inputValue <= spo2LB) {
			newSpO2Parameter.parameterValue = "critical";
		} else if(in.inputValue > spo2LB && in.inputValue < spo2UB) {
			newSpO2Parameter.parameterValue = "low";
		} else if(in.inputValue > spo2UB) {
			newSpO2Parameter.parameterValue = "normal";
		}		
		newSpO2Parameter.parameterState = "persons = " + newSpO2Parameter.parameterValue;	
		return newSpO2Parameter;		
	}
	
	//Method that fuzzyfies Inputs with the inputType "tube"
	Parameter handleTubeInput(Input in) {
		Parameter newTubeParameter = new Parameter("tube", in.timestamp, null, null);
		if(in.inputValue <= tubeLB) {
			newTubeParameter.parameterValue = "disconnected";
		} else if(in.inputValue > tubeLB && in.inputValue < tubeUB) {
			newTubeParameter.parameterValue = "unknown";
		} else if(in.inputValue > tubeUB) {
			newTubeParameter.parameterValue = "connected";
		}		
		newTubeParameter.parameterState = "tube = " + newTubeParameter.parameterValue;	
		return newTubeParameter;		
	}
	
	
	
	//---------------
	//getter/ setter
	//---------------

	static void setPersonsLB(double newlb) {
		personsLB = newlb;
	}
	
	static void setPersonsUB(double newub) {
		personsUB = newub;
	}
	
	static void setSpO2LB(double newlb) {
		spo2LB = newlb;
	}
	
	static void setSpO2UB(double newub) {
		spo2UB = newub;
	}
	
	static void setTubeLB(double newlb) {
		tubeLB = newlb;
	}
	
	static void setTubeUB(double newub) {
		tubeUB = newub;
	}
	
	static double getPersonsLB() {
		return personsLB;
	}
	
	static double getPersonsUB() {
		return personsUB;
	}
	
	static double getSpO2LB() {
		return spo2LB;
	}
	
	static double getSpO2UB() {
		return spo2UB;
	}
	
	static double getTubeLB() {
		return tubeLB;
	}
	
	static double getTubeUB() {
		return tubeUB;
	}
	
}
