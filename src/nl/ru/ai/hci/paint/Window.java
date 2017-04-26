package nl.ru.ai.hci.paint;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	public Window() {
		// ‘super ’ calls a function inherited from the parent class ( JFrame )
		super();
		setTitle(" Callbacks ");
		setSize(new Dimension(1000, 630));
		// Make sure the window appears in the middle of your screen
		setLocationRelativeTo(null);
		// Determines what should happen when the frame is closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Chooses a certain layout type for the elements in this frame
		getContentPane().setLayout(new BorderLayout());
		DrawPanel dp = new DrawPanel();
		ButtonPanel bp = new ButtonPanel(dp);
		// Places the DrawPanel in the center of the frame
		getContentPane().add(dp, BorderLayout.CENTER);
		// Places the ButtonPanel in the top of the frame
		getContentPane().add(bp, BorderLayout.NORTH);

		// Set the window to visible ! Yup ... This is necessary
		setVisible(true);
	}
	
	
}
