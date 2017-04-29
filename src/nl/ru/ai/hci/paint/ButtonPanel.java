package nl.ru.ai.hci.paint;

import java.awt.Color;
import java.awt.Dimension;

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
		dp.addIH(IH);
        ImageIcon myIcon = new ImageIcon("images/icons/undoIcon.png");
        
		JButton add = new JButton(myIcon);
		add.setPreferredSize(new Dimension(40,40));
		add.setBackground(Color.WHITE);
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
		JButton outl = new JButton("outline color");
		add(outl);
		outl.addActionListener(IH);
		JButton fill = new JButton("fill color");
		add(fill);
		fill.addActionListener(IH);
		JButton trans = new JButton("transform");
		add(trans);
		trans.addActionListener(IH);
	}

}
