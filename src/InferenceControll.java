

import java.io.*;
import java.util.*;


public class InferenceControll {

	public static void main(String[] args) {
		
		UserInterface.createUI();
		processInput();
		MLM.updateMLM(0);
		
		
	}
	
	
	//method for reading new Input: parameterType and parameterValue
	static boolean processInput() {
		String parameterType = null;
		int parameterValue = -1;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Parametertyp eingeben (end für Abbruch): ");
		
		//read parameterType
		while(parameterType == null) {
			try {
				parameterType = reader.readLine();
				System.out.println(parameterType);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		Scanner in = new Scanner(System.in);
		//action when parameterType is end to end reading Input
		if(parameterType.equals("end")) {
			in.close();
			return false;
		}		
		//action when parameterType is persons
		if(parameterType.equals("persons")) {		
				try {
					System.out.println("Anzahl an Personen im Raum: ");
					parameterValue = in.nextInt();	
					System.out.println(parameterValue);
				} catch(NoSuchElementException e) {
					e.printStackTrace();
				}

			KnowledgeBase.setPersons(parameterValue);
		}
		//action when parameterType is 
		if(parameterType.equals("spo2")) {
				try {
					System.out.println("SpO2-Wert: ");
					parameterValue = in.nextInt();	
					System.out.println(parameterValue);
				} catch(NoSuchElementException e) {
					e.printStackTrace();
				}
			KnowledgeBase.setSpO2(parameterValue);
		}
		in.close();
		return true;
	}
	
	static void updateAlarmLabel(String alarm) {
		UserInterface.updateAlarm(alarm);
	}
	
	//forwards Parameters to UI
	static void forwardParametersToUI(int parameter) {
		UserInterface.updatePersons(parameter);
	}
	
	//forwards ParameterChange to RuleBase
	static void forwardCallToKnowledgeBase(String parameterType, int parameterValue) {
		RuleBase.runBaseRules(parameterType, parameterValue);
	}
}
