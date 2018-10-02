import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class RulePanel extends JPanel {

	Path2D connector;
	JPopupMenu popup = new JPopupMenu();
	JMenuItem newRule = new JMenuItem("Create new Rule");
	
	RuleCreator ruleCreator = new RuleCreator();
	
	public RulePanel() {
		setSize(900, 800);
		setLayout(null);
		setBackground(Color.WHITE);
		
		popup.add(newRule);
		
		EggMovement rm = new EggMovement(getComponents());
		
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					doPop(e);
				}
			
			}
			
		});
		
		newRule.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ruleCreator.show();
			}
		});
		
	}

	// creates a new RuleEgg and adds it to the eggPanel
	public void addNewEgg(Rule rule) {
		RuleEgg ruleEgg = new RuleEgg(rule);
		
		ruleEgg.setSize(200, 200);
		ruleEgg.setName(rule.ruleName);

		ConfigurationUI.addRuleEggToList(ruleEgg);
		this.add(ruleEgg);
		
		ConfigurationUI.ui.setVisible(true);
	}
	
	public void doPop(MouseEvent e) {
		if(ConfigurationUI.editMode) {
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	
	}
}
