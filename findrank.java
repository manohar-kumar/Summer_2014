
import java.util.* ; 


public class findrank {

	public static int approx_median(ArrayList<Integer> v ) {
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		if (v.size()==1) return v.get(0);
		if (v.size()<=5) {
			Collections.sort(v);
			int t=v.size()/2;
			
			return v.get(t);

		}
		for(int i=0;i<v.size();i=i+5){
			if (i+5>=v.size()) {
				for (int j=0;j<v.size()-i;j++){
			a.add(v.get(i+j));
			}
			}
			else{
			for (int j=0;j<5;j++){
			a.add(v.get(i+j));
			}}
			Collections.sort(a);
			int med=(a.size()-1)/2;
			temp.add(a.get(med));
			a.clear();

		}
		int c=(temp.size()-1)/2;
		return find_rank(c,temp);
		}

	

	public static int find_rank(int n, ArrayList <Integer> v){
		int am = approx_median(v);
		ArrayList<Integer> v1 = new ArrayList<Integer>();
		ArrayList<Integer> v2 = new ArrayList<Integer>();
		for(int i=0;i<v.size();i++){
			if(v.get(i) < am){
				v1.add(v.get(i));
			}
			else if (v.get(i) > am){
				v2.add(v.get(i));
			}
		}
		int r=v1.size();
		if(r==n){
			return am;
		}
		else if(r>n){
			return find_rank(n,v1);
		}
		else if(r<n){
			return find_rank(n-r-1,v2);
		}
		return 0;
		
	}
	
	public static void main(String[] args){
		ArrayList<Integer> v = new ArrayList<Integer>();
		v.add(3);
		v.add(2);	
		v.add(5);	
		v.add(1);		
		//int b=approx_median(v);
		int m = (v.size()-1)/2;
		int a = find_rank(m,v);
		System.out.println(a);
	
}
}
