import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class RuleEgg extends JPanel {
	
	double xPos;
	double yPos;
	Ellipse2D circle;

	public RuleEgg(double x, double y) {
		this.xPos = x;
		this.yPos = y;
		circle = new Ellipse2D.Double(xPos, yPos, 100, 200);
		
		addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	if (circle.contains(e.getX(), e.getY())) {
        			System.out.println("click");
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
