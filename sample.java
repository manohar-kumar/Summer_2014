import java.awt.Graphics;
  import java.awt.Font;
  import java.util.*;
  import java.awt.*;
  import java.util.Date;
  import javax.swing.*;
  import java.io.Console;

class Ganga extends JPanel{

	public void paint(Graphics g){
		g.drawRect(0,0,50,50);
	}
}


class Manohar extends JPanel{

	public void paint(Graphics g){
		g.drawRect(100,100,50,50);
	}
}
 
  public class sample extends JPanel 
    {

    	

    	public static void main(String args[]){
		JFrame f = new JFrame();
      f.setSize(400, 400);
      f.add(new Manohar());
      f.setVisible(true);
      f.add(new Ganga());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);

    	}


    }
