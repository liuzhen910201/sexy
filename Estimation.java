package new1;

import java.util.*;




/*
main:　メインメソッド
read:　   time配列を処理する：1,0などの結果を配列に入れる
compute:  time配列の中の一列の1の数量を計算
putIn:   computeの結果をpoint配列に入れる
check:   結果(point配列)をプリンド

呼び出す関係:
		main()
		 |
		 |
		 |
		 |
		read()——————check()
		 |
		 |
		 |
		 |
		compute()
		 |
		 |
		 |
		 |
		putIn()


*/
public class Estimation {
	 final static int LONG=30;
	 public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
	　　　　　System.out.print("Input in >");
		//String input = scanner.nextLine();
		try{
			int n=scanner.nextInt();
			if(n==0){											System.out.println("プログラム終了");
			}
			else{
				read(n);//输入结果至数组time
			}
			}catch(InputMismatchException e){
				//例外処理
				System.out.println("Please input a number");
			}
			
	 }

	public static void read(int n) {
		//time配列を処理する：1,0などの結果を配列に入れる
		Scanner scanner1 = new Scanner(System.in);
		System.out.print("Input in >");
		int[][] time = new int[n][LONG];
		int[] point=new int[n];
		String[] name=new String[n];
		for(int i=0;i<n;i++){
		  	  name[i]=scanner1.next();
		      int scene=scanner1.nextInt();
		   	  //二つ目の引数でそのキャラがいくつの時刻に登場したか分かるので、
		   	  //登場した時刻の分だけfor文を回して行列の特定の要素に１を入れる
		   	  for(int j=0;j<scene;j++){
		    	  time[i][scanner1.nextInt()] = 1;
	    	  }  
		}

		compute(time,point);//计算point 并存入数组point
		//output(time);//打印数组time 用于测试
		check(point,name);
	}
	
	public static void compute(int[][] time,int[] point) {
		//time配列の中の一列の1の数量を計算
		int score=0;//一列の1の数量
		int i,j;
		for(j=0;j<LONG;j++){
			for(i=0;i<time.length;i++){
				if(time[i][j]==1){
					score++;
				}
			}
			if(score!=0){
				putIn(time.length-score+1,point,time,j);
			}
			score=0;
		}
	}
	
	public static void putIn(int total,int[] point,int[][] time,int j){
		//computeの結果をpoint配列に入れる
		for(int x=0;x<time.length;x++){
			if(time[x][j]==1){
				point[x]+=total;
			}
		}
	}
	
	public static void check(int[] point,String[] name){
		//結果(point配列)をプリンド
		int temp=point[0];
		int min=0;
		for(int y=0;y<point.length;y++){
			if(temp>point[y]){
				min=y;
				temp=point[y];
			}
		}
		System.out.println(name[min]+"   "+point[min]);	
	}
	
	// for test
	public static void output(int[][] time){
		for(int i=0;i<time.length;i++){
			for(int j=0;j<30;j++){
				System.out.print(time[i][j]);
			}
			System.out.println();
		}	
	}
}
