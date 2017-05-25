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
			input=upper(input);//アンダーラインの次の文字を大文字に変換
			input=removeUnderLine(input);//アンダーラインを削除
			System.out.println(input);
		}
		else if(command=='D'){
			input=input.substring(0, input.length()-2);
			input=lowerFirst(input);//頭文字を小文字に変換
			input=underLine(input);//大文字の前にアンダーラインを埋め込む
			input=lower(input);//大文字を小文字に変換
			System.out.println(input);
		}
		else if(command=='U'){
			input=input.substring(0, input.length()-2);
			input=upFirst(input);//頭文字を大文字に変換
			input=upper(input);//アンダーラインの次の文字を大文字に変換
			System.out.println(input);
		}
	}
	
	public static String removeUnderLine(String input){//アンダーラインを削除
		input=input.replaceAll("_", "");
		return input;
	}
	
	public static String upper(String input){//アンダーラインの次の文字を大文字に変換
		for(int i=0;i<input.length();i++){
			if(input.substring(i, i+1).equals("_")){
				String temp=input.substring(i+1, i+2);
				input=input.replaceFirst(temp, temp.toUpperCase());	
			}
		}
		return input;
	}
	
	public static String lower(String input){//大文字を小文字に変換
		for (int i=1;i<input.length();i++){
			if(input.substring(i, i+1).equals(input.substring(i, i+1).toUpperCase())){
				String temp=input.substring(i, i+1);
				input=input.replaceAll(temp, temp.toLowerCase());
			}
		}
		return input;
	}
	
	public static String underLine(String input){//大文字の前にアンダーラインを埋め込む
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
	
	public static String upFirst(String input){//頭文字を大文字に変換
		if(input.substring(0, 1).equals(input.substring(0,1).toLowerCase())){
			String temp=input.substring(0,1).toUpperCase();
			input=input.replaceFirst(input.substring(0,1),temp);
		}
		return input;
	}
	
	public static String lowerFirst(String input){//頭文字を小文字に変換
		if(input.substring(0, 1).equals(input.substring(0,1).toUpperCase())){
			String temp=input.substring(0,1).toLowerCase();
			input=input.replaceFirst(input.substring(0,1),temp);
		}
		return input;
	}
}
