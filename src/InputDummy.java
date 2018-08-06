import java.util.*;
public class InputDummy {
	

	static ArrayList<Parameter> listOfParameterValues = new ArrayList<Parameter>();
	
	static void fillList() {
		Date date0 = new Date();
		Parameter parameterValue0 = new Parameter("persons", date0, "many");
		Parameter parameterValue1 = new Parameter("spo2", date0, "normal");

		Date date1 = new Date();
		Parameter parameterValue2 = new Parameter("persons", date1, "many");
		Parameter parameterValue3 = new Parameter("spo2", date1, "normal");
		
		Date date2 = new Date();
		Parameter parameterValue4 = new Parameter("persons", date2, "many");
		Parameter parameterValue5 = new Parameter("spo2", date2, "low");
		
		Date date3 = new Date();
		Parameter parameterValue6 = new Parameter("persons", date3, "none");
		Parameter parameterValue7 = new Parameter("spo2", date3, "low");
		
		Date date4 = new Date();
		Parameter parameterValue8 = new Parameter("persons", date4, "many");		
		Parameter parameterValue9 = new Parameter("spo2", date4, "low");


		listOfParameterValues.add(parameterValue0);
		listOfParameterValues.add(parameterValue1);
		listOfParameterValues.add(parameterValue2);
		listOfParameterValues.add(parameterValue3);
		listOfParameterValues.add(parameterValue4);
		listOfParameterValues.add(parameterValue5);
		listOfParameterValues.add(parameterValue6);
		listOfParameterValues.add(parameterValue7);
		listOfParameterValues.add(parameterValue8);
		listOfParameterValues.add(parameterValue9);
		
		forwardParameter();
	}
	
	
	static void forwardParameter() {
		while(!listOfParameterValues.isEmpty()) {
			InferenceControll.handleNewParameterValue(listOfParameterValues.get(0));
			listOfParameterValues.remove(0);
		}
	}
}
