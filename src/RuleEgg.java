import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.management.remote.JMXConnector;
import javax.swing.*;

public class RuleEgg extends JPanel {

	RuleOverview ro;
	boolean isMarked = false;
	String eggName;
	
	Box labelBox = Box.createVerticalBox();
	JLabel nameLabel;
	static JLabel stateLabel;
	static JLabel prevStateLabel;
	static JLabel input1Label;
	static JLabel input2Label;

	JLabel stateValueLabel;
	JLabel prevStateValueLabel;
	JLabel input1ValueLabel;
	JLabel input2ValueLabel;
	
	Box nameBox = Box.createHorizontalBox();
	Box stateBox = Box.createHorizontalBox();
	Box prevStateBox = Box.createHorizontalBox();
	Box input1Box = Box.createHorizontalBox();
	Box input2Box = Box.createHorizontalBox();

	public RuleEgg(Rule rule) {

		eggName = rule.getRuleName();
		// circle = new Ellipse2D.Double(0, 0, 200, 200);
	
		ro = new RuleOverview(rule);

		setSize(200, 200);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		nameLabel = new JLabel(eggName);
		stateLabel = new JLabel("State: ");
		prevStateLabel = new JLabel("Previous State: ");
		input1Label = new JLabel("Input1: ");
		input2Label = new JLabel("Input2: ");


		stateValueLabel = new JLabel(Integer.toString(rule.state));
		prevStateValueLabel = new JLabel(Integer.toString(rule.prevState));
		input1ValueLabel = new JLabel("null");
		input2ValueLabel = new JLabel("null");
		
		nameBox.add(nameLabel);
		
		stateBox.add(stateLabel);
		stateBox.add(stateValueLabel);
		
		prevStateBox.add(prevStateLabel);
		prevStateBox.add(prevStateValueLabel);
		
		input1Box.add(input1Label);
		input1Box.add(input1ValueLabel);
		
		input2Box.add(input2Label);
		input2Box.add(input2ValueLabel);
		
		labelBox.add(nameBox);
		labelBox.add(stateBox);
		labelBox.add(prevStateBox);
		labelBox.add(input1Box);
		labelBox.add(input2Box);

		add(labelBox);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (contains(e.getX(), e.getY())) {
					if (!ConfigurationUI.editMode) {
						// System.out.println("click " + eggRule.ruleName);
						ro.showOverview();
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
	
	//method that updates the eggLabels when a new input was handled
	void updateLabels(int s, int ps, Parameter p1, Parameter p2, Parameter result) {
		stateValueLabel.setText(Integer.toString(s));
		prevStateValueLabel.setText(Integer.toString(ps));
		if(p1 != null) {
			input1ValueLabel.setText(p1.parameterType + " = " + p1.parameterValue);
		}
		if(p2 != null) {
			input2ValueLabel.setText(p2.parameterType + " = " + p2.parameterValue);
		}
		
		//System.out.println(RuleEgg.eggName+ "    " + s);
		ConfigurationUI.ui.setVisible(true);
	}
	

}
