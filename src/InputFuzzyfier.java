import java.util.ArrayList;

public class InputFuzzyfier {

	//list of all parameterTypes of the fuzzyfied Inputs
	static ArrayList<String> listOfOutputs = new ArrayList<String>();
	static ArrayList<ArrayList<String>> listOfOutputValues = new ArrayList<ArrayList<String>>();
	
	FuzzyfierEgg fe;
	
	//lower and upper bounds for all InputTypes
	float personsLB = 0;
	float personsUB = 2;
	
	float spo2LB = 90;
	float spo2UB = 94;
	
	float tubeLB = 96;
	float tubeUB = 97;
	
	
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
		return newSpO2Parameter;		
	}
	
	//Method that fuzzyfies Inputs with the inputType "tube"
	Parameter handleTubeInput(Input in) {
		Parameter newtubeParameter = new Parameter("tube", in.timestamp, null, null);
		if(in.inputValue <= tubeLB) {
			newtubeParameter.parameterValue = "disconnected";
		} else if(in.inputValue > tubeLB && in.inputValue < tubeUB) {
			newtubeParameter.parameterValue = "unknown";
		} else if(in.inputValue > tubeUB) {
			newtubeParameter.parameterValue = "connected";
		}			
		return newtubeParameter;		
	}
	
	
	
	//---------------
	//getter/ setter
	//---------------

	void setPersonsLB(float newlb) {
		personsLB = newlb;
	}
	
	void setPersonsUB(float newub) {
		personsUB = newub;
	}
	
	void setSpO2LB(float newlb) {
		spo2LB = newlb;
	}
	
	void setSpO2UB(float newub) {
		spo2UB = newub;
	}
	
	void setTubeLB(float newlb) {
		tubeLB = newlb;
	}
	
	void setTubeUB(float newub) {
		tubeUB = newub;
	}
	
}
