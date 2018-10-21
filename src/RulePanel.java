import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class RulePanel{
	
	static JPanel eggPanel = new JPanel();

	Path2D connector;
	JPopupMenu popup = new JPopupMenu();
	JMenuItem newRule = new JMenuItem("Create new Rule");
	
	static ArrayList<RuleEgg> listOfEggs = new ArrayList<RuleEgg>();
	
	RuleCreator ruleCreator = new RuleCreator();
	
	public RulePanel() {
		eggPanel.setSize(900, 800);
		eggPanel.setLayout(null);
		eggPanel.setBackground(Color.WHITE);
		
		popup.add(newRule);
		
		EggMovement rm = new EggMovement(eggPanel.getComponents());
				
		//drawArrow();
		
		//adds a mouseListener thats opens a popup menu when right clicked on RulePanel
		eggPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					doPop(e);
				}
			
			}
		});
		//adds a mouseListener that opens the RuleCreator if clicked on newRule on popupmenu
		newRule.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ruleCreator.fillInputComboBox();
				ruleCreator.show();
			}
		});
		
	}

	// creates a new RuleEgg and adds it to the eggPanel
	static void addNewEgg(RuleEgg re) {
		eggPanel.add(re);
		listOfEggs.add(re);
		
		ConfigurationUI.ui.setVisible(true);
	}
	
	//Method to show the popup menu
	public void doPop(MouseEvent e) {
		if(ConfigurationUI.editMode) {
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}	
	
	static void forwardEggLabelUpdate(String name, int s, int ps, Parameter p1, Parameter p2, Parameter result) {
		for(int i = 0; i < listOfEggs.size(); i++) {
			String ern = listOfEggs.get(i).eggRuleName;
			if(ern.equals(name)) {
				RuleEgg.updateLabels(s, ps, p1, p2, result);				
			}
		}
	}
	
}
