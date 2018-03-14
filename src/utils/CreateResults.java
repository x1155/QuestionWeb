package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Result;

public class CreateResults {
	private List<Result> results;	
	
	/*
	 * 功能：根据测评对象人数和参加测评人数生成结果矩阵
	 * 参数：P_NUM 测评对象人数；Q_NUM 题目数量；T_NUM 参加测评人数 ；C_NUM 题目选项数。
	 */
	public List<Result> randResults(int P_NUM, int Q_NUM, int T_NUM, int C_NUM){
		//初始化结果矩阵，results.get(i)表示 第i个参加测评人对测评结果。
		//注：两个测评对象的测评结果用 '#'号隔开，如："02……120#11……000#……#"。
		results = new ArrayList<Result>();
		Random random = new Random();
		
		
		for(int i=0; i<T_NUM; i++){
			Result r = new Result();
			StringBuffer str = new StringBuffer();
			for(int j=0; j<P_NUM; j++){				
				for(int k=0; k<Q_NUM; k++)
				{
					str.append(random.nextInt(C_NUM-1));					
				}
				str.append('#');
			}
			
			r.setId(String.valueOf(i));
			r.setScoreStr(str.toString());
			results.add(r);
		}
		
		return results;
	}	
}