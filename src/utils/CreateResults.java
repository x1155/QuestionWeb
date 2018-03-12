package utils;

import java.util.Random;

public class CreateResults {
	private int[][][] results;	
	
	/*
	 * 功能：根据测评对象人数和参加测评人数生成结果矩阵
	 * 参数：P_NUM 测评对象人数；Q_NUM 题目数量；T_NUM 参加测评人数 
	 */
	public int[][][] randResults(int P_NUM, int Q_NUM, int T_NUM, int C_NUM){
		//初始化结果矩阵，result[i][j][k]表示 第i个受测评对象的第j个评价项的第k个选项的得票数
		//注：result[i][Q_NUM][k]记录的为总评结果
		results = new int[P_NUM][Q_NUM + 1][C_NUM];
		Random random = new Random();
		
		for(int i=0; i<P_NUM; i++){
			for(int j=0; j<Q_NUM + 1; j++){
				for(int k=0; k<C_NUM; k++)
				{
					if(k == C_NUM-1){
						results[i][j][k] = T_NUM - resultSum(results[i][j], k-1);
					}else if(k>0){
						results[i][j][k] = Math.abs(random.nextInt() % (T_NUM - resultSum(results[i][j], k-1)));;
					}else{
						results[i][j][k] = Math.abs(random.nextInt() % T_NUM);
					}
				}
			}			
		}
		
		return results;
	}
	
	//递归求和
	public int resultSum(int[] result, int k){		
		if(k>0){
			return result[k] + resultSum(result, k-1);
		}else{
			return result[0];
		}	
	}
}