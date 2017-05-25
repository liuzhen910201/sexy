import java.util.Scanner;


/*
例:n=6
再帰
fib(6)
=fib(5)+fib(4)
=fib(4)+fib(3) +fib(3)+fib(2)
=fib(3)+fib(2) +fib(2)+fib(1) +fib(2)+fib(1) +fib(1)+fib(0)
=fib(2)+fib(1) +fib(1)+fib(0) +fib(1)+fib(0) +fib(1) +fib(1)+fib(0)  +fib(1) +fib(1) +fib(0)
=fib(1)+fib(0) +fib(1) +fib(1)+fib(0) +fib(1)+fib(0) +fib(1) +fib(1)+fib(0) +fib(1) +fib(1) +fib(0)
=1+0+1+1+0+1+0+1+1+0+1+1+0
=8
Nがとても大きいの場合，スタック(stack)がオーバーロード
末尾再帰
fib(n,x,y)
fib(6,1,1)
=fib(5,1,2)
=fib(4,2,3)
=fib(3,3,5)
=fib(2,5,8)
=fib(1,8,13)
if (n<2) return x; (8)

スタック(stack)の容量を考える必要がない
*/







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
	//再帰
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
