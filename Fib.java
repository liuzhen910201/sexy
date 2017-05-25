import java.util.Scanner;

public class Fib {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input in >");
		int a=scanner.nextInt();
		for(int b=1;b<=a;b++){
			int x=fib(b);
			//System.out.println(x);
		}
		
		for(int b=1;b<=a;b++){
			int x=refib(b,1,1);
			System.out.println(x);
		}
		
	}
	public static int fib(int n){
		if(n==1)
			return 1;
		else if(n==2)
			return 1;
		else{
			return fib(n-1)+fib(n-2);
		}	
	}
	//末尾再帰
	public static int refib(int n,int x,int y){
		if(n<2)
			return x;
		else{
			return refib(n-1,y,x+y);
		}
	}
}
