// Java Program to create a text editor using java
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener {
	// Text component
	JTextArea t;

	// Frame
	JFrame f;
	
	Font fb;
	String filename,st,fn="untitled";
	int style=Font.PLAIN;
	int fsize=12;

	// Constructor
	editor()
	{
		
		fb=new Font("Courier",style,fsize);
		setLayout(new GridLayout(1,1));
		
		// Create a frame
		f = new JFrame("editor");

		try {
			// Set metal look and feel
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			// Set theme to ocean
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (Exception e) {
		}

		// Text component
		t = new JTextArea();

		// Create a menubar
		JMenuBar mb = new JMenuBar();

		// Create amenu for menu
		JMenu m1 = new JMenu("File");

		// Create menu items
		JMenuItem mi1 = new JMenuItem("New");
		JMenuItem mi2 = new JMenuItem("Open");
		JMenuItem mi3 = new JMenuItem("Save");
		JMenuItem mi9 = new JMenuItem("Print");

		// Add action listener
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi9.addActionListener(this);

		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi9);

		// Create amenu for menu
		JMenu m2 = new JMenu("Edit");

		// Create menu items
		JMenuItem mi4 = new JMenuItem("cut");
		JMenuItem mi5 = new JMenuItem("copy");
		JMenuItem mi6 = new JMenuItem("paste");

		// Add action listener
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);

		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);
		//create menu for font
		JMenu m3=new JMenu("Font");
		
		JMenuItem mi10 = new JMenuItem("Bold");
		JMenuItem mi11 = new JMenuItem("Plain");
		JMenuItem mi12 = new JMenuItem("Italic");
		
		mi10.addActionListener(new FM());
		mi11.addActionListener(new FM());
		mi12.addActionListener(new FM());
		
		m3.add(mi10);
		m3.add(mi11);
		m3.add(mi12);
		
		JMenu m4=new JMenu("FontType");
		
		JMenuItem mi13 =new JMenuItem("TimesRoman");
		JMenuItem mi14 =new JMenuItem("Helvetica");
		JMenuItem mi15 =new JMenuItem("Courier");
		JMenuItem mi16 =new JMenuItem("Arial");
		JMenuItem mi17 =new JMenuItem("Arial Black");
		JMenuItem mi18 =new JMenuItem("Century");
		
		mi13.addActionListener(new Type());
		mi14.addActionListener(new Type());
		mi15.addActionListener(new Type());
		mi16.addActionListener(new Type());
		mi17.addActionListener(new Type());
		mi18.addActionListener(new Type());
		
		m4.add(mi13);
		m4.add(mi14);
		m4.add(mi15);
		m4.add(mi16);
		m4.add(mi17);
		m4.add(mi18);
		
		JMenu size=new JMenu("Size");
		
		JMenuItem s1=new JMenuItem("10");
		JMenuItem s2=new JMenuItem("12");
		JMenuItem s3=new JMenuItem("14");
		JMenuItem s4=new JMenuItem("16");
		JMenuItem s5=new JMenuItem("18");
		JMenuItem s6=new JMenuItem("20");
		JMenuItem s7=new JMenuItem("22");
		JMenuItem s8=new JMenuItem("24");
		JMenuItem s9=new JMenuItem("26");
		JMenuItem s10=new JMenuItem("28");
		
		s1.addActionListener(new Size());
		s2.addActionListener(new Size());
		s3.addActionListener(new Size());
		s4.addActionListener(new Size());
		s5.addActionListener(new Size());
		s6.addActionListener(new Size());
		s7.addActionListener(new Size());
		s8.addActionListener(new Size());
		s9.addActionListener(new Size());
		s10.addActionListener(new Size());
		
		size.add(s1);
		size.add(s2);
		size.add(s3);
		size.add(s4);
		size.add(s5);
		size.add(s6);
		size.add(s7);
		size.add(s8);
		size.add(s9);
		size.add(s10);
		
		size.addActionListener(new FM());
		m3.add(size);
		
		JMenu color=new JMenu("Color");
		JMenuItem Bg=new JMenuItem("Background");
		JMenuItem Fg=new JMenuItem("Foreground");
		Bg.addActionListener(new BC());
		Fg.addActionListener(new BC());
		color.add(Bg);
		color.add(Fg);

		JMenuItem mc = new JMenuItem("close");

		mc.addActionListener(this);

		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		//mb.add(size);
		mb.add(m4);
		mb.add(color);
		mb.add(mc);

		f.setJMenuBar(mb);
		f.add(t);
		f.setSize(500, 500);
		f.show();
	}

	// If a button is pressed
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();

		if (s.equals("cut")) {
			t.cut();
		}
		else if (s.equals("copy")) {
			t.copy();
		}
		else if (s.equals("paste")) {
			t.paste();
		}
		else if (s.equals("Save")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsSaveDialog function to show the save dialog
			int r = j.showSaveDialog(null);

			if (r == JFileChooser.APPROVE_OPTION) {

				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// Create a file writer
					FileWriter wr = new FileWriter(fi, false);

					// Create buffered writer to write
					BufferedWriter w = new BufferedWriter(wr);

					// Write
					w.write(t.getText());

					w.flush();
					w.close();
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(f, "the user cancelled the operation");
		}
		else if (s.equals("Print")) {
			try {
				// print the file
				t.print();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		else if (s.equals("Open")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsOpenDialog function to show the save dialog
			int r = j.showOpenDialog(null);

			// If the user selects a file
			if (r == JFileChooser.APPROVE_OPTION) {
				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// String
					String s1 = "", sl = "";

					// File reader
					FileReader fr = new FileReader(fi);

					// Buffered reader
					BufferedReader br = new BufferedReader(fr);

					// Initialize sl
					sl = br.readLine();

					// Take the input from the file
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}

					// Set the text
					t.setText(sl);
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(f, "the user cancelled the operation");
		}
		else if (s.equals("New")) {
			t.setText("");
			
		}
		else if (s.equals("close")) {
			f.setVisible(false);
		}
		
		
	}
	
	class FM extends Applet implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String b=e.getActionCommand();
			if(b=="Bold") {
				fb=new Font("Courier",Font.BOLD,fsize);
				style=fb.getStyle();
				t.setFont(fb);
			}
			
			if(b=="Plain") {
				fb=new Font("Courier",Font.PLAIN,fsize);
				style=fb.getStyle();
				t.setFont(fb);
			}
			if(b=="Italic") {
				fb=new Font("Courier",Font.ITALIC,fsize);
				style=fb.getStyle();
				t.setFont(fb);
			}
			
			
		}
	}
	
	class Type implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String lbl=e.getActionCommand();
			if(lbl=="TimesRoman") {
				fb=new Font("TimesRoman",style,fsize);
				t.setFont(fb);
			}
			if(lbl=="Courier") {
				fb=new Font("Courier",style,fsize);
				t.setFont(fb);
			}
			if(lbl=="Helvetica") {
				fb=new Font("Helvetica",style,fsize);
				t.setFont(fb);
			}
			if(lbl=="Arial") {
				fb=new Font("Arial",style,fsize);
				t.setFont(fb);
			}
			if(lbl=="Arial Black") {
				fb=new Font("Arial Black",style,fsize);
				t.setFont(fb);
			}
			if(lbl=="Century") {
				fb=new Font("Century",style,fsize);
				t.setFont(fb);
			}
			
			repaint();
			
		}
	}
	
	class Size implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int style= fb.getStyle();
			String w=e.getActionCommand();
			if(w=="10") {
				fb=new Font("Courier",style,10);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			
			if(w=="12") {
				fb=new Font("Courier",style,12);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			
			if(w=="14") {
				fb=new Font("Courier",style,14);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			if(w=="16") {
				fb=new Font("Courier",style,16);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			if(w=="18") {
				fb=new Font("Courier",style,18);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			if(w=="20") {
				fb=new Font("Courier",style,20);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			if(w=="22") {
				fb=new Font("Courier",style,22);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			if(w=="24") {
				fb=new Font("Courier",style,24);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			if(w=="26") {
				fb=new Font("Courier",style,26);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
			if(w=="28") {
				fb=new Font("Courier",style,28);
				t.setFont(fb);
				fsize=fb.getSize();
				repaint();
			}
		}
	}
	
	class BC implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			st=e.getActionCommand();
			JFrame jf=new JFrame("JColorChooser");
			colorChooser c=new colorChooser();
			c.setSize(400,300);
			c.setVisible(true);
		}
	}
	
	class colorChooser extends JFrame{
		Button ok;
		JColorChooser jcc;
		public colorChooser() {
			setTitle("JColorChooser");
			jcc=new JColorChooser();
			JPanel content=(JPanel)getContentPane();
			content.setLayout(new BorderLayout());
			content.add(jcc,"Center");
			ok=new Button("OK");
			content.add(ok,"South");
			ok.addActionListener(new B());
		}
		
		class B implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.out.println("Color Is:"+jcc.getColor().toString());
				if(st.equals("Background"))
					t.setBackground(jcc.getColor());
				if(st.equals("Foreground"))
					t.setForeground(jcc.getColor());
				setVisible(false);
			}
		}
	}

	// Main class
	public static void main(String args[])
	{
		editor e = new editor();
	}
}

