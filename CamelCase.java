package new1;
import java.util.Scanner;




/*

メソッド1 アンダーラインの次の文字を大文字に変換
メソッド2　アンダーラインを削除
メソッド3　頭文字を小文字に変換
メソッド4　大文字の前にアンダーラインを埋め込む
メソッド5　大文字を小文字に変換
メソッド6　頭文字を大文字に変換

L	1	2
D	3	4	5
U	6	1

if アンダーラインを削除，頭文字を小文字に変換というBを作ると，
	if(command==‘B’){
		メソッド2を呼び出す;
		メソッド3を呼び出す;
	}
*/
public class CamelCase {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input in >");
		String input = scanner.nextLine();
		check(input);
	}
	
	public static void check(String input){
		char command = input.charAt(input.length()-1);
		if(command=='X'){
			System.out.println("EndOfInput");
		}
		else if(command=='L'){
			input=input.substring(0, input.length()-2);
			input=upper(input);
			input=removeUnderLine(input);
			System.out.println(input);
		}
		else if(command=='D'){
			input=input.substring(0, input.length()-2);
			input=lowerFirst(input);
			input=underLine(input);
			input=lower(input);
			System.out.println(input);
		}
		else if(command=='U'){
			input=input.substring(0, input.length()-2);
			input=upFirst(input);
			input=upper(input);
			System.out.println(input);
		}
	}
	
	public static String removeUnderLine(String input){//删除下划线
		input=input.replaceAll("_", "");
		return input;
	}
	
	public static String upper(String input){//下划线之后 小写转大写
		for(int i=0;i<input.length();i++){
			if(input.substring(i, i+1).equals("_")){
				String temp=input.substring(i+1, i+2);
				input=input.replaceFirst(temp, temp.toUpperCase());	
			}
		}
		return input;
	}
	
	public static String lower(String input){//大写转小写
		for (int i=1;i<input.length();i++){
			if(input.substring(i, i+1).equals(input.substring(i, i+1).toUpperCase())){
				String temp=input.substring(i, i+1);
				input=input.replaceAll(temp, temp.toLowerCase());
			}
		}
		return input;
	}
	
	public static String underLine(String input){//插入下划线
		String s="_";
		for(int i=0;i<input.length();i++){
			if(input.substring(i, i+1).equals(input.substring(i, i+1).toUpperCase())){
				String temp=input.substring(i, i+1);
				input=input.replaceFirst(temp, s+temp);
				i++;
			}
		}
		return input;
	}
	
	public static String upFirst(String input){//头文字转大写
		if(input.substring(0, 1).equals(input.substring(0,1).toLowerCase())){
			String temp=input.substring(0,1).toUpperCase();
			input=input.replaceFirst(input.substring(0,1),temp);
		}
		return input;
	}
	
	public static String lowerFirst(String input){//头文字转小写
		if(input.substring(0, 1).equals(input.substring(0,1).toUpperCase())){
			String temp=input.substring(0,1).toLowerCase();
			input=input.replaceFirst(input.substring(0,1),temp);
		}
		return input;
	}
}
