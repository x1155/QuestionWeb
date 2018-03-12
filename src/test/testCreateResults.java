package test;
import utils.CreateResults;

public class testCreateResults {
	public static void main(String[] args){
		int[][][] results;
		int P_NUM = 3; 
		int Q_NUM = 5;
		int T_NUM = 8;
		int C_NUM = 3;
		
		CreateResults cr = new CreateResults();
		results = cr.randResults(P_NUM, Q_NUM, T_NUM, C_NUM);
		
		for(int i=0; i<P_NUM; i++){
			System.out.println("******" + "第" + (i+1) + "个人的测评结果" + "******");
			for(int j=0; j<Q_NUM; j++){
				for(int k=0; k<C_NUM; k++){
					System.out.print(results[i][j][k] + "    ");
				}
				System.out.print("\n");
			}			
		}
		
		System.out.println("\n程序运行结束!");
	}
}
