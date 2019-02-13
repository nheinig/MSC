import java.sql.Timestamp;
import java.util.*;

public class InputDummy {

	static ArrayList<Parameter> listOfParameterValues = new ArrayList<Parameter>();

	void InptDummy() {
		fillList();
	}

	// creates dummyData and fills listOfPArameterValues with dummyData
	void fillList() {
		Timestamp ts0 = new Timestamp(new Date().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ts0.getTime());

		Parameter parameterValue00 = new Parameter("persons", ts0, "none", null);
		Parameter parameterValue01 = new Parameter("tube", ts0, "connected", null);

		calendar.add(Calendar.SECOND, 2);
		ts0 = new Timestamp(calendar.getTime().getTime());
		Parameter parameterValue0 = new Parameter("persons", ts0, "many", null);
		Parameter parameterValue1 = new Parameter("spo2", ts0, "normal", null);

		calendar.add(Calendar.SECOND, 2);
		ts0 = new Timestamp(calendar.getTime().getTime());
		Parameter parameterValue02 = new Parameter("tube", ts0, "disconnected", null);
		Parameter parameterValue2 = new Parameter("persons", ts0, "many", null);
		Parameter parameterValue3 = new Parameter("spo2", ts0, "normal", null);

		calendar.add(Calendar.SECOND, 2);
		ts0 = new Timestamp(calendar.getTime().getTime());
		Parameter parameterValue4 = new Parameter("persons", ts0, "many", null);
		Parameter parameterValue5 = new Parameter("spo2", ts0, "low", null);

		calendar.add(Calendar.SECOND, 2);
		ts0 = new Timestamp(calendar.getTime().getTime());
		Parameter parameterValue6 = new Parameter("persons", ts0, "none", null);
		Parameter parameterValue7 = new Parameter("spo2", ts0, "low", null);

		calendar.add(Calendar.SECOND, 2);
		ts0 = new Timestamp(calendar.getTime().getTime());
		Parameter parameterValue8 = new Parameter("persons", ts0, "many", null);
		Parameter parameterValue9 = new Parameter("spo2", ts0, "low", null);
		Parameter parameterValue03 = new Parameter("tube", ts0, "disconnected", null);

		calendar.add(Calendar.SECOND, 3);
		ts0 = new Timestamp(calendar.getTime().getTime());
		Parameter parameterValue08 = new Parameter("persons", ts0, "none", null);
		Parameter parameterValue09 = new Parameter("spo2", ts0, "low", null);
		Parameter parameterValue04 = new Parameter("tube", ts0, "disconnected", null);

		listOfParameterValues.add(parameterValue00);
		listOfParameterValues.add(parameterValue01);
		listOfParameterValues.add(parameterValue0);
		listOfParameterValues.add(parameterValue1);
		listOfParameterValues.add(parameterValue02);
		listOfParameterValues.add(parameterValue2);
		listOfParameterValues.add(parameterValue3);
		listOfParameterValues.add(parameterValue4);
		listOfParameterValues.add(parameterValue5);
		listOfParameterValues.add(parameterValue6);
		listOfParameterValues.add(parameterValue7);
		listOfParameterValues.add(parameterValue8);
		listOfParameterValues.add(parameterValue9);
		listOfParameterValues.add(parameterValue03);
		listOfParameterValues.add(parameterValue08);
		listOfParameterValues.add(parameterValue09);
		listOfParameterValues.add(parameterValue04);

		forwardParameter();
	}

	// forwards listOfParameterValues with dummyData to InferenceControll
	static void forwardParameter() {
		while (!listOfParameterValues.isEmpty()) {
			InferenceControl.handleNewParameterValue(listOfParameterValues.get(0));
			listOfParameterValues.remove(0);
		}
	}
}
