import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class EggMovement implements MouseListener, MouseMotionListener {

	int x, y;

	public EggMovement(Component... cmps) {
		for (Component component : cmps) {
			component.addMouseListener(this);
			component.addMouseMotionListener(this);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (ConfigurationUI.editMode) {
			e.getComponent().setLocation((e.getX() + e.getComponent().getX()) - x,
					(e.getY() + e.getComponent().getY()) - y);
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
