

public class RuleBase {
	static String alarmType = "No Alarm";
	static String persons = "one";
	static String spo2 = "none";
	
	//Method called with parameterType and parameterValue if KB is updated 
	static void runBaseRules(String parameterType, int parameterValue) {
		//action when parameterType is persons
		if(parameterType.equals("persons")) {	
			personRules(parameterValue);
			if(persons.equals("zero")) {
				alarmType = "Kein Alarm!";	
				InferenceControll.updateAlarmLabel(alarmType);
				return;
			} else if(persons.equals("one")) {	
				if(spo2.equals("normal")) {
					alarmType = "Kein Alarm!";	
					InferenceControll.updateAlarmLabel(alarmType);
					return;
				} else if(spo2.equals("low")){
					alarmType = "Globaler Alarm!";
					InferenceControll.updateAlarmLabel(alarmType);
				}
			} else if(persons.equals("multiple")) {
				if(spo2.equals("normal")) {
					alarmType = "Kein Alarm!";	
					InferenceControll.updateAlarmLabel(alarmType);
					return;
				} else if(spo2.equals("low")){
					alarmType = "Lokaler Alarm";
					InferenceControll.updateAlarmLabel(alarmType);
				}
			}	
			return;
					
		//action when parameterType is spo2
		} else if(parameterType.equals("spo2")) {
			spo2Rules(parameterValue);
			if(spo2.equals("normal")) {
				alarmType = "Kein Alarm!";	
				InferenceControll.updateAlarmLabel(alarmType);
			} else if(spo2.equals("low")) {
				if (persons.equals("zero")) {
					alarmType = "Kein Alarm!";	
					InferenceControll.updateAlarmLabel(alarmType);
				} else if (persons.equals("one")) {
					alarmType = "Globaler Alarm!";	
					InferenceControll.updateAlarmLabel(alarmType);
				}else if (persons.equals("multiple")) {
					alarmType = "Lokaler Alarm!";	
					InferenceControll.updateAlarmLabel(alarmType);
				}
			}
			
		}
		return;
	}
	
	
	//Methods to fuzzyfy Parameter Values
	
	//fuzzyfies persons
	static void personRules(int i) {
		if(i == 0) {
			persons = "zero";
		} else if(i == 1){
			persons = "one";
		} else if (i >= 2){
			persons = "multiple";
		} 
	}

	//fuzzyfies spo2
	static void spo2Rules(int i) {
		if(i < 90) {
			spo2 = "low";
		} else {
			spo2 = "normal";
		}
	}
	
}
