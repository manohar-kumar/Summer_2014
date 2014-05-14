import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.io.Console;

class printRect extends JPanel{
	ArrayList<Integer> v=new ArrayList<Integer>();
	int y;
	public printRect(ArrayList<Integer> v1,int y1){
		v=v1;
		y=y1;
	}
	public void paint(Graphics g){
		for (int i=0;i<v.size();i++){
      g.drawRect(45+30*i,y,30,30);
      g.drawString(Integer.toString(v.get(i)),60+30*i,y+15);
   		}
   	}
}

class c1 extends JPanel{
	int val;
	int x; int y;
	public c1(int val1,int x1,int y1){
		val=val1;
		x=x1;
		y=y1;
	}

	public void paint(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(x,y,30,30);
		g.setColor(Color.green);
		g.drawString(Integer.toString(val),x+15,y+15);
	}
}

class writeMedian extends JPanel{
	int value;
	int y;
	public writeMedian(int value1,int y1){
		value=value1;
		y=y1;
	}
	public void paint(Graphics g){
		g.setColor(Color.green);
         g.drawString("Median   =",250,y);
         g.setColor(Color.blue);
         g.drawRect(340,y-15,30,30);
         g.setColor(Color.green);
         g.drawString(Integer.toString(value),355,y);
         g.setColor(Color.black);
	}

}

public class Bomi extends JPanel{

	public static int approxMedian(ArrayList<Integer> v){
		Console c=System.console();
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		JFrame k=new JFrame();
		k.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		k.setSize(400, 400);
		String p=c.readLine();
		if (p.equals("")){
     		k.add(new printRect(v,30));
     		k.setVisible(true);
		}
		String p2=c.readLine();

		if (v.size()==1) {
			if (p2.equals("")){
        	k.add(new printRect(v,200));
        	k.setVisible(true);
        	return v.get(0);
      		}
      }
       else if (v.size()<=5) {
       	if (p2.equals("")){
       		k.add(new printRect(v,200));}
       		Collections.sort(v);
         int t=v.size()/2;
         
         String p3=c.readLine();
         if (p3.equals("")){
         	k.add(new writeMedian(v.get(t),215));
     		}
       	
			return v.get(t);
      }

        else if (v.size()>5) {
        	for(int i=0;i<v.size();i=i+5){
         		if (i+5>=v.size()) {
           			for (int j=0;j<v.size()-i;j++){
              			 a.add(v.get(i+j));
            		}
        	 }
         else {
            for (int j=0;j<5;j++){
               a.add(v.get(i+j));
            }
         }
         String p4=c.readLine();
         if (p4.equals("")){
         k.add(new printRect(a,100+i*10));
         	}
         String p5=c.readLine();
         Collections.sort(a);
         int med=(a.size()-1)/2;
         if (p5.equals("")){
         k.add(new writeMedian(a.get(med),115+i*10));
     		}
         temp.add(a.get(med));
         a.clear();
     	}
     	String p6=c.readLine();
     	if (p6.equals("")){
     	k.add(new printRect(temp,320));
     	k.setVisible(true);
     }
	}
	int h=(temp.size()-1)/2;
	String p7=c.readLine();
	if (p7.equals("")){
	k.setVisible(false);}
	return findRank(temp,h);
}


	


	public static int findRank(ArrayList<Integer> v,int n){
		Console c=System.console();
		int am;
		am=approxMedian(v);
		ArrayList<Integer> v1 = new ArrayList<Integer>();
   		ArrayList<Integer> v2 = new ArrayList<Integer>();
		JFrame k=new JFrame();
		k.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		k.setSize(400, 400);
		String p=c.readLine();
		if (p.equals("")){
     	k.add(new printRect(v,200));
     	k.setVisible(true);
		}
		for(int i=0;i<v.size();i++){
			String s=c.readLine();
			if (v.get(i)<am){
				v1.add(v.get(i));
				if (s.equals("")){
					k.add(new c1(v.get(i),45+30*i,100));
					k.setVisible(true);
				}
			}
			else if (v.get(i) > am){
				v2.add(v.get(i));
				if (s.equals("")){
					k.add(new c1(v.get(i),45+30*i,300));
					k.setVisible(true);
				}

			}
		}
		int r=v1.size();
		if(r==n){
			return am;
		}
		else if(r>n){
			return findRank(v1,n);
		}
		else if(r<n){
			return findRank(v2,n-r-1);
		}
		String d=c.readLine();
		if (d.equals("")){
		k.setVisible(false);}
		return 0;
	}



	public static void main(String[] args){
		
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(2);a.add(3);a.add(4);
		int b=findRank(a,4);
	}
}