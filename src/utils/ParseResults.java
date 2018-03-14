package utils;

import java.util.List;

import model.Result;

public class ParseResults {
	
	/*存在问题（BUG）：maxOptionNum应由resultStr分析得出。*/
	public int doStatistics(List<Result> results, int[][][] count, boolean validate){		
		/*存在问题（BUG）：resultStr需要至少记录一次测评结果。*/
		int num_of_val = 0;
		
		// T_NUM 参加测评人数 ；P_NUM 测评对象人数；Q_NUM 题目数量；C_NUM 题目选项数。
		int T_NUM = results.size();
		int P_NUM = count.length;
		int Q_NUM = count[0].length;		
		FindInvalidVote fiv = new FindInvalidVote();
		
		for(int i=0; i<T_NUM; i++){			
			if(validate && !fiv.isValidVote(results.get(i).getScoreStr())){				
				//若进行不合法票筛选并且所筛选票不合法，则不对该票进行统计。
			}else{
				num_of_val++;
				for(int j=0; j<P_NUM; j++){
					for(int k=0; k<Q_NUM; k++){
						int score = results.get(i).getScoreStr().charAt(j*(Q_NUM + 1) + k) - '0';
						count[j][k][score]++;
					}
				}
			}			
		}
		System.out.println("有效票数：" + num_of_val);
		
		return num_of_val;
	}
}
