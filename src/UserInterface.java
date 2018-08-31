import javax.swing.*;

public class UserInterface {
	// create Labels
	static JLabel personsLabel = new JLabel("Personen: Kein Wert vorhanden!");		
	static JLabel spo2Label = new JLabel("SpO2: Kein Wert vorhanden!");
	static JLabel alarmLabel = new JLabel("No Warning!");
	
	
	//creates the UI
	public static void createUI(){
		//create a new Frame for the UI
		JFrame ui = new JFrame("Inference Engine");
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create Box
		Box box = Box.createVerticalBox();
		ui.add(box);
		
		//customize  labels	
		personsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		box.add(personsLabel);
		
		spo2Label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		box.add(spo2Label);
		
		alarmLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		box.add(alarmLabel);
		
		ui.setSize(400, 400);
		//ui.pack();
		ui.setVisible(true);		
	}
	
	
	//Methods to update the label texts 
	
	static void updatePersons(int i) {
		personsLabel.setText("Personen: " + i);
	}
	
	static void updateSpO2(int i) {
		spo2Label.setText("SpO2: " + i);
	}
	
	static void updateAlarm(String alarm) {
		alarmLabel.setText(alarm);
	}
}
