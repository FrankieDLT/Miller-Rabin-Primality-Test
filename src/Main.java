import java.math.BigInteger;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Random ran = new Random(); 
		 
		 //FastExpon(143, 10, 230); //works
		 //FastExpon(212,50,307); //works
		 //FastExpon(380,65537,401); //WORKS LIKE A CHARM
		 
		 
		int n = 0;//we assume that n is an odd integer greater than 2. 147141353
		
		while(n<2)
			n=ran.nextInt();
		
		if(n%2 == 0)
			n++;
	
		//n = 147141353;
		
		int s = 3;  //Como se dijo en clase, pues es suficiente
		int a = 0;
		String text = "PRIMO";

		for (int j = 1; j <= s; j++) {
			a = ran.nextInt(n-1)+1;
			if (witness(a, n))
			text =  "COMPUESTO"; // definitely
		}
		System.out.println("El número: " + n + " es " + text);  // almost surely

		
	}
	
	
	public static boolean witness(int a, int n) {
		Random r = new Random(); 	
		int  s = n-1;
		int t=0;		
		while(s%2 == 0) {
			
			if(s%2 ==0)
				t++;
			s = s/2;			
		}	
		
		int u= (int) ((n - 1) / (int)Math.pow(2, t));	
		//System.out.println(u);
		long x[] = new long[t+1];
		
		x[0] = FastExpon(a,u,n);

		 //System.out.println(a+"\t"+u+"\t"+n+"\n"+x[0]);  FastExpon works with large numbers
		
		/*if(((int)Math.pow(2, t)*u)+1==n)
		System.out.println(n+"\t"+(int)(Math.pow(2, t)*u+1));*/


		 for(int i = 1; i <= t; i++) {
			 
			 x[i] = x[i-1] * x[i-1] % n;
			// System.out.println(x[i]);
			 
			 if(x[i] == 1 && x[i-1] != 1 && x[i-1] != (n - 1)) {
				// System.out.println("************"); 
				 return true;
			 }			

		 }		 
			 
		 if (x[t] != 1) {
			// System.out.println("----------------"); 
			 return true; 
		 } 
		 
		 return false;

	}//Fin de witness
	

	public static int FastExpon(int a, int b, int n) {
		//Descomentado puede resolver los problemas de la tarea anterior paso a paso
		
		//System.out.println("("+a+"^"+b+") mod "+n+" = \n");
		
		String c = Integer.toBinaryString(b); 
		BigInteger d;
		char[] binar = c.toCharArray();
		
		int x = 0;
		for (char output : binar) {
			binar[x] = output;
			x++;
		}
		x=0;
		
		BigInteger  finnum = BigInteger.ONE;
		
		d = BigInteger.valueOf(a);
		
		
		for(int i = 0; i<c.length();i++) {
			
			//System.out.print(binar[binar.length-i-1]+"\t"+d);
			
			if(binar[binar.length-i-1]=='1') {
				finnum = finnum.multiply(d);
			}
			
			d = d.multiply(d).mod(BigInteger.valueOf(n));
			
			//System.out.println("\t"+finnum);
		}
		
		//System.out.println("\nResultado Final: " 
		//+ (finnum.mod(BigInteger.valueOf(n)).intValue()));
		
		return  (finnum.mod(BigInteger.valueOf(n)).intValue());
	}

}
