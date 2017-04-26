package nl.ru.ai.hci.paint;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ButtonPanel(DrawPanel dp) {
		super();
		// Add a button to the panel . The argument to the JButton constructor
		// will become the text on the button .
		InputHandler IH = new InputHandler(dp);
        ImageIcon myIcon = new ImageIcon("images/icons/undoIcon.png");
        
		JButton add = new JButton(myIcon);
		add(add);
		add.addActionListener(IH);
		JButton mod = new JButton("mod");
		add(mod);
		mod.addActionListener(IH);
		JButton rec = new JButton("rectangle");
		add(rec);
		rec.addActionListener(IH);
		JButton line = new JButton("line");
		add(line);
		line.addActionListener(IH);
		JButton ell = new JButton("ellipse");
		add(ell);
		ell.addActionListener(IH);
		JButton del = new JButton("delete");
		add(del);
		del.addActionListener(IH);
		JButton und = new JButton("undo");
		add(und);
		und.addActionListener(IH);
		JButton col = new JButton("color");
		add(col);
		col.addActionListener(IH);
	}

}
