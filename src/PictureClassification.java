import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class PictureClassification {
	public JFrame frame;
	public Paint paint;
	Perceptron pct, pct2, pct3;

	public PictureClassification()
	{	
		pct=new Perceptron(Paint.DEFAULT_SIZE.width, Paint.DEFAULT_SIZE.height);
		pct2 = new Perceptron(Paint.DEFAULT_SIZE.width, Paint.DEFAULT_SIZE.height);
		pct3 = new Perceptron(Paint.DEFAULT_SIZE.width, Paint.DEFAULT_SIZE.height);
		
		this.frame = new JFrame("No ron nhan tao!");
		this.frame.setSize(600 ,300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(null);
		
		paint= new Paint();
		paint.setBounds(50, 0, Paint.DEFAULT_SIZE.width, Paint.DEFAULT_SIZE.height);
		this.frame.getContentPane().add(paint);
		
		
		
		
		final JLabel l1=new JLabel("0");
		l1.setBounds(540, 30, 100, 25);
		this.frame.add(l1);
		
		JButton Learn=new JButton("Class 1");
		Learn.setBounds(410, 30, 100, 25);
		Learn.addActionListener(new ActionListener(){    // actionPerformed duoc goi khi 1 action xuat hien

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pct.learning(paint.image, 1);
				
				
				pct2.learning(paint.image, 0);
				pct3.learning(paint.image, 0);
				//paint.clear();
				l1.setText(""+ pct.class2);
				
			}
		});
		this.frame.add(Learn);
		
		
		
		
		
		
		final JLabel l2=new JLabel("0");
		l2.setBounds(540, 70, 100, 25);
		this.frame.add(l2);
		
		JButton Learn2=new JButton("Class 2");
		Learn2.setBounds(410, 70, 100, 25);
		Learn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pct2.learning(paint.image, 1);
				
				
				
				
				pct.learning(paint.image, 0);
				pct3.learning(paint.image, 0);
				//paint.clear();
				l2.setText(""+pct2.class2);
			}
		});
		this.frame.add(Learn2);
		
		
		
		
		
		final JLabel l3=new JLabel("0");
		l3.setBounds(540, 110, 100, 25);
		this.frame.add(l3);
		
		JButton Learn3=new JButton("Class 3");
		Learn3.setBounds(410, 110, 100, 25);
		Learn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pct3.learning(paint.image, 1);
				
				
				
				pct2.learning(paint.image, 0);
				pct.learning(paint.image, 0);
				//paint.clear();
				l3.setText(""+pct3.class2);
			}
		});
		this.frame.add(Learn3);
	
		
		
		
		
		
		
		final JLabel l4=new JLabel("0");
		l4.setBounds(540, 150, 200, 25);
		this.frame.add(l4);
		
		JButton Test=new JButton("Classification");
		Test.setBounds(410, 150, 125, 25);
		Test.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				double o1=pct.output(paint.image);
				double o2=pct2.output(paint.image);
				double o3=pct3.output(paint.image);
				
				
				System.out.println("o1: "+o1+"o2: "+o2+"o3: "+o3);
				
//				System.out.println(o);
				//l3.setText(""+o);
//				if (o<0.5) l4.setText("Class 1!");
//				else l4.setText("Class 2!");
				
				
				if(o1>0.5) l4.setText("Class 1!");
				else {
					if(o2>0.5) l4.setText("Class 2!");
					else {
						if(o3>0.5) l4.setText("Class 3!");
						else
						{
							double m = max(max(o1,o2),o3);
							if(m == o1) l4.setText("Class 1!");
							else if (m == o2) l4.setText("Class 2!");
							else	l4.setText("Class 3!");
						}
					}
				}

			}
		});
		this.frame.add(Test);
		
		
		
		
		
		
		JButton Cl=new JButton("Clear");
		Cl.setBounds(410, 200, 75, 25);
		Cl.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				paint.clear();
				l4.setText("");
			}
		});
		this.frame.add(Cl);
		
		frame.setVisible(true);
	}
	static double max(double a,double b){
	    while(a>b) return a;
	    return b;
	}

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new PictureClassification();

	}
}
