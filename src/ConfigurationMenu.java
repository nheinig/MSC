import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ConfigurationMenu extends JPanel {
	
	Box menuBox = Box.createVerticalBox();
	
	static Box overviewBox = Box.createVerticalBox();
	Box menuButtonBox = Box.createVerticalBox();	
	
	JButton editButton = new JButton("Toggle Editmode");


    public ConfigurationMenu()
    {
    	setSize(300,800);
        createMenu();
        add(menuBox);
    }
    
	//creates the Menu (fills the menuBox)
	public void createMenu() {
		menuButtonBox.add(editButton);	
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConfigurationUI.editMode = !ConfigurationUI.editMode;
				if(ConfigurationUI.editMode) {
					editButton.setBackground(Color.green);
				} else {
					editButton.setBackground(null);
				}
			}
		});
		
		menuBox.setSize(200,800);
		
		menuBox.add(overviewBox);
		menuBox.add(menuButtonBox);	

        overviewBox.setSize(250, 600);
        menuButtonBox.setSize(250,200);

        overviewBox.setAlignmentX(LEFT_ALIGNMENT);
        menuButtonBox.setAlignmentX(LEFT_ALIGNMENT);
	}
}