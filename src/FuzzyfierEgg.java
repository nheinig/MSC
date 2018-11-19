import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FuzzyfierEgg extends JPanel {

	boolean isMarked = false;
	String eggName = "Fuzzyfier";
	
	Box labelBox = Box.createVerticalBox();
	JLabel nameLabel;
	static JLabel input1Label;
	static JLabel input2Label;
	static JLabel input3Label;

	JLabel input1ValueLabel;
	JLabel input2ValueLabel;
	JLabel input3ValueLabel;
	
	Box nameBox = Box.createHorizontalBox();
	Box stateBox = Box.createHorizontalBox();
	Box prevStateBox = Box.createHorizontalBox();
	Box input1Box = Box.createHorizontalBox();
	Box input2Box = Box.createHorizontalBox();
	Box input3Box = Box.createHorizontalBox();

	public FuzzyfierEgg(InputFuzzyfier fuzzyfier) {

		setSize(200, 200);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		nameLabel = new JLabel(eggName);
		input1Label = new JLabel("Input1: ");
		input2Label = new JLabel("Input2: ");
		input3Label = new JLabel("Input3: ");


		input1ValueLabel = new JLabel(fuzzyfier.listOfOutputs.get(0));
		input2ValueLabel = new JLabel(fuzzyfier.listOfOutputs.get(1));
		input3ValueLabel = new JLabel(fuzzyfier.listOfOutputs.get(2));
		
		nameBox.add(nameLabel);
		input1Box.add(input1Label);
		input1Box.add(input1ValueLabel);

		input2Box.add(input2Label);
		input2Box.add(input2ValueLabel);
		
		input3Box.add(input3Label);
		input3Box.add(input3ValueLabel);
		
		labelBox.add(nameBox);;
		labelBox.add(input1Box);
		labelBox.add(input2Box);
		labelBox.add(input3Box);

		add(labelBox);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (contains(e.getX(), e.getY())) {
					if (!ConfigurationUI.editMode) {
						if(!ConfigurationUI.editMode) {
							isMarked = !isMarked;
							markEgg();
						}
						ConfigurationUI.ui.setVisible(true);
					}
				}
			}
		});

	}

	//(un-)marks an egg when clicked on
	void markEgg() {
		if(isMarked) {
			this.setBackground(Color.GRAY);
		} else {
			this.setBackground(Color.WHITE);
		}
	}
	
	

}
