import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class RuleEgg extends JPanel {

	Ellipse2D circle;
	Rule eggRule;
	RuleOverview ro;


	public RuleEgg(Rule er) {
		circle = new Ellipse2D.Double(0, 0, 100, 200);
		eggRule = er;

		ro = new RuleOverview(er);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (circle.contains(e.getX(), e.getY())) {
					//System.out.println("click " + eggRule.ruleName);
					ro.showOverview();
				}
			}
		});

	}

	@Override
	protected void paintComponent(final Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fill(circle);
		g2d.setColor(Color.BLACK);
		g2d.draw(circle);
	}
}
