package test;
import java.util.List;

import model.Result;
import utils.CreateResults;

public class testCreateResults {
	public static void main(String[] args){
		List<Result> results;
		int P_NUM = 3; 
		int Q_NUM = 5;
		int T_NUM = 8;
		int C_NUM = 3;
		
		CreateResults cr = new CreateResults();
		results = cr.randResults(P_NUM, Q_NUM, T_NUM, C_NUM);
		
		for(int i=0; i<T_NUM; i++){
			System.out.println("******" + "第" + (i+1) + "个参评人的投票结果" + "******");
			System.out.println(results.get(i).getScoreStr());			
		}
		
		System.out.println("\n程序运行结束!");
	}
}
