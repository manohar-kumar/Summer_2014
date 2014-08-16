import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.io.Console;

 class FindRank extends JPanel {
   ArrayList<Integer> v= new ArrayList<Integer>();
   int n ;
   int am;
   public FindRank(ArrayList<Integer> v1,int n1,int am1){
      v=v1;
      n=n1;
      am=am1;
   }

   public void printRect(ArrayList<Integer> v2,Graphics g,int y){
      for (int i=0;i<v2.size();i++){
      g.drawRect(45+30*i,y,30,30);
      g.drawString(Integer.toString(v2.get(i)),60+30*i,y+15);
   }
   }
   
   public void paintComponent(Graphics g) {
   super.paintComponent(g);
   printRect(v,g,200);
   ArrayList<Integer> v1 = new ArrayList<Integer>();
   ArrayList<Integer> v2 = new ArrayList<Integer>();
   Color c1 = new Color(0,0,255);
   Color c2 = new Color(0,255,0);
   Console c = System.console();
         
   
      for(int i=0;i<v.size();i++){
        String readString = c.readLine();
         if(v.get(i) < am){
            v1.add(v.get(i));
            g.setColor(c1);
            g.fillRect(45+30*i,100,30,30);
            g.setColor(c2);
            g.drawString(Integer.toString(v.get(i)),60+30*i,100+15);
            
         }
         else if (v.get(i) > am){
            v2.add(v.get(i));
            g.setColor(c2);
            g.fillRect(45+30*i,300,30,30);
            g.setColor(c1);
            g.drawString(Integer.toString(v.get(i)),60+30*i,300+15);
         
         }

      }
      
   }

      }





 class ApproxMedian extends JPanel {
   ArrayList<Integer> v = new ArrayList<Integer>();

public ApproxMedian(ArrayList<Integer> v1){
   v=v1;
}


public void printRect(ArrayList<Integer> v2,Graphics g,int y){
      for (int i=0;i<v2.size();i++){
      g.drawRect(45+30*i,y,30,30);
      g.drawString(Integer.toString(v2.get(i)),60+30*i,y+15);
   }
   }


public void paintComponent(Graphics g) {
   super.paintComponent(g);
      Color c1 = new Color(0,0,255);
      Color c2 = new Color(0,255,0);
      
       printRect(v,g,30);
      ArrayList<Integer> a = new ArrayList<Integer>();
      ArrayList<Integer> temp = new ArrayList<Integer>();
      if (v.size()==1) {
         g.drawRect(45,200,30,30);
      }
      else if (v.size()<=5) {
         printRect(v,g,200);
         Collections.sort(v);
         int t=v.size()/2;
         g.setColor(c1);
         g.drawString("Median   =",200,215);
         g.setColor(c2);
         g.drawRect(280,200,30,30);
         g.setColor(c1);
         g.drawString(Integer.toString(v.get(t)),295,215);
         g.setColor(Color.black);
      }
      else if (v.size()>5) {
      for(int i=0;i<v.size();i=i+5){
         if (i+5>=v.size()) {
            for (int j=0;j<v.size()-i;j++){
               a.add(v.get(i+j));
            }
         }
         else{
            for (int j=0;j<5;j++){
               a.add(v.get(i+j));
            }
         }
         
            printRect(a,g,100+i*10);
         
         Collections.sort(a);
         int med=(a.size()-1)/2;
         g.setColor(c1);
         g.drawString("Median   =",250,115+i*10);
         g.setColor(c2);
         g.fillRect(340,100+i*10,30,30);
         g.setColor(c1);
         g.drawString(Integer.toString(a.get(med)),355,115+i*10);
         g.setColor(Color.black);
         temp.add(a.get(med));
         a.clear();

      }
      g.setColor(Color.blue);
      printRect(temp,g,320);
   }

}
}
      





public class Main extends JPanel {

   public static void main(String[] a) {
     ArrayList<Integer> v = new ArrayList<Integer>();
     v.add(2);v.add(4);v.add(5);
     v.add(7);v.add(8);v.add(3);
      JFrame k = new JFrame();
      k.setSize(400, 400);
      JButton okButton = new JButton("Next");
      FindRank h = new FindRank(v,1,4);

      h.add(okButton);

      k.add(h);

      k.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      k.setVisible(true);


      JFrame f = new JFrame();

      f.setSize(400, 400);
      f.add(new ApproxMedian(v));
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
     

   }
   public void paint(Graphics g) {
      Color myColor = new Color(255,0,0);
      g.setColor(myColor);
      g.drawRect (5, 15, 50, 75);
      Color myc = new Color(0,255,0);
      g.setColor(myc);
      g.fillOval(250, 250, 50, 50);

   }
}