import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class RuleEgg extends JPanel {

	Ellipse2D circle;
	Rule eggRule;
	RuleOverview ro;
	boolean isMarked = false;


	public RuleEgg(Rule rule) {
		circle = new Ellipse2D.Double(0, 0, 200, 200);
		eggRule = rule;

		ro = new RuleOverview(rule);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (circle.contains(e.getX(), e.getY())) {
					//System.out.println("click " + eggRule.ruleName);
					ro.showOverview();
					isMarked = !isMarked;
						repaint();
				}
			}
		});

	}

	@Override
	protected void paintComponent(final Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(!isMarked) {
			g2d.setColor(Color.WHITE);
			g2d.fill(circle);
		} else {
			g2d.setColor(Color.RED);
			g2d.fill(circle);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.draw(circle);
	}
}
