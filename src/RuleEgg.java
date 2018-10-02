import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.management.remote.JMXConnector;
import javax.swing.*;

public class RuleEgg extends JPanel {

	Ellipse2D circle;
	static Rule eggRule;
	RuleOverview ro;
	boolean isMarked = false;

	Box labelBox = Box.createVerticalBox();
	JLabel nameLabel;
	static JLabel stateLabel;
	static JLabel prevState;
	static JLabel input1Label;
	static JLabel input2Label;

	public RuleEgg(Rule rule) {

		// circle = new Ellipse2D.Double(0, 0, 200, 200);
		eggRule = rule;

		ro = new RuleOverview(rule);

		setSize(100, 100);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		nameLabel = new JLabel(eggRule.ruleName);
		stateLabel = new JLabel("State: " + Integer.toString(eggRule.state));
		prevState = new JLabel("Previous State: " + Integer.toString(eggRule.prevState));
		input1Label = new JLabel("Input1: ");
		input2Label = new JLabel("Input2: ");

		labelBox.add(nameLabel);
		labelBox.add(stateLabel);
		labelBox.add(prevState);
		labelBox.add(input1Label);
		labelBox.add(input2Label);

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

	void markEgg() {
		if(isMarked) {
			this.setBackground(Color.GRAY);
		} else {
			this.setBackground(Color.WHITE);
		}
	}
	
	static void updateLabels(Rule rule) {
		eggRule = rule;
		stateLabel.setText("State: " + Integer.toString(eggRule.state));
		prevState.setText("Previous State: " + Integer.toString(eggRule.prevState));
		if (eggRule.listOfLastInputs.size() > 0) {
			input1Label.setText("Input1: " + eggRule.listOfLastInputs.get(0).parameterType + " = "
					+ eggRule.listOfLastInputs.get(0).parameterValue);
		}
		if (eggRule.listOfLastInputs.size() > 1) {
			input2Label.setText("Input2: " + eggRule.listOfLastInputs.get(1).parameterType + " = "
					+ eggRule.listOfLastInputs.get(1).parameterValue);
		}
		System.out.println(eggRule.state);
		ConfigurationUI.ui.setVisible(true);
	}

}
