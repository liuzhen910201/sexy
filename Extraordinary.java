package new1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Extraordinary {

	public static void main(String[] args) {


		Scanner scanner = new Scanner(System.in);
		System.out.print("Input in >");
		String input = scanner.nextLine();
		check(input);

		scanner.close();
	}
	public static void check(String input) {
		String b1="',.!?";
		String b2="abcABC";
		String b3="defDEF";
		String b4="ghiGHI";
		String b5="jklJKL";
		String b6="mnoMNO";
		String b7="pqrsPQRS";
		String b8="tuvTUV";
		String b9="wxyzWXYZ";
		ArrayList<String> list = new ArrayList<String>();

		list.add(b1);
		list.add(b2);
		list.add(b3);
		list.add(b4);
		list.add(b5);
		list.add(b6);
		list.add(b7);
		list.add(b8);
		list.add(b9);
		try{
			int temp=Integer.parseInt(String.valueOf(input.charAt(0)));
			decrypt(input,list);//数字转文字 解密
		}catch(NumberFormatException e){
			encrypt(input,list);//文字转数字 加密
		}

	}
	public static void decrypt(String input, ArrayList<String> list) {//数字转文字 解密
		char x1=input.charAt(0);
		int n=0;
		for(int i=0;i<input.length();i++){
			if(x1==input.charAt(i)){
				n++;
			}
			else{
				x1=input.charAt(i);
			}
		}
		int y=x1-'0'-1;
		switch(x1){
			case '1':
				System.out.print(list.get(y).charAt(n%5));
				break;
			case '2':
				System.out.print(list.get(y).charAt(n%5));
				break;
			case '3':
				System.out.print(list.get(y).charAt(n%5));
				break;
			case '4':
				System.out.print(list.get(y).charAt(n%5));
				break;
			case '5':
				System.out.print(list.get(y).charAt(n%5));
				break;
			case '6':
				System.out.print(list.get(y).charAt(n%5));
				break;
			case '7':
				System.out.print(list.get(y).charAt(n%6));
				break;
			case '8':
				System.out.print(list.get(y).charAt(n%5));
				break;
			case '9':
				System.out.print(list.get(y).charAt(n%6));
				break;
			default:
				for(int count=1;count<n;count++)
					System.out.print(" ");

		}
	}
	public static void encrypt(String input, ArrayList<String> list){//文字转数字 加密

	}
}
