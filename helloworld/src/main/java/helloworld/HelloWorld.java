package helloworld;

public class HelloWorld {
	
	public static void main(String[] args) {
		int a;
		int b;
		
		while(true) {
			a = (int) (Math.random() * 6) + 1;
			b = (int) (Math.random() * 6) + 1;
			
			if((a>=1 && a<=6) && (b>=1 && b<=1)) {
				if((a+b) == 5 ) { 
					break;
				}
				
				System.out.println("(" + a + ", " + b + ")" );
				
				
			}
			
			
			
		}
		
		
		
	}	

}