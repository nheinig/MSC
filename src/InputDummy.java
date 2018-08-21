import java.sql.Timestamp;
import java.util.*;
public class InputDummy {
	

	static ArrayList<Parameter> listOfParameterValues = new ArrayList<Parameter>();
	
	void InptDummy() {
		fillList();
	}
	
	void fillList() {
		Timestamp date0 =  new Timestamp(System.currentTimeMillis());
		Parameter parameterValue0 = new Parameter("persons", date0, "many");
		Parameter parameterValue1 = new Parameter("spo2", date0, "normal");

		try        
		{
		    Thread.sleep(1000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		
		Timestamp date1 =  new Timestamp(System.currentTimeMillis());
		Parameter parameterValue2 = new Parameter("persons", date1, "many");
		Parameter parameterValue3 = new Parameter("spo2", date1, "normal");
		
		try        
		{
		    Thread.sleep(1000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		
		Timestamp date2 =  new Timestamp(System.currentTimeMillis());
		Parameter parameterValue4 = new Parameter("persons", date2, "many");
		Parameter parameterValue5 = new Parameter("spo2", date2, "low");
		
		try        
		{
		    Thread.sleep(1000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		
		Timestamp date3 =  new Timestamp(System.currentTimeMillis());
		Parameter parameterValue6 = new Parameter("persons", date3, "none");
		Parameter parameterValue7 = new Parameter("spo2", date3, "low");
		
		try        
		{
		    Thread.sleep(1000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		
		Timestamp date4 =  new Timestamp(System.currentTimeMillis());
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
