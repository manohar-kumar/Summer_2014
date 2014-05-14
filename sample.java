import java.awt.Graphics;
  import java.awt.Font;
  import java.util.*;
  import java.awt.*;
  import java.util.Date;
  import javax.swing.*;
  import java.io.Console;

class Ganga extends JPanel{

	public void paint(Graphics g){
		Console c = System.console();
		String read = c.readLine();
		if(read.equals("")){
		g.drawRect(0,0,50,50);
	}}
}


class Manohar extends JPanel{

	public void paint(Graphics g){
		
		g.drawRect(100,100,50,50);
	}
}
 
  public class sample extends JPanel 
    {

	public static void main(String args[]){
//Console c = System.console();

    			JFrame f = new JFrame();
      f.setSize(400, 400);
      
      f.add(new Manohar());
      
      //String read = c.readLine();

      f.add(new Ganga());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);

    	}


    }
