package outPut;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Person;
import utils.AgeCalculate;
import connectDB.ConnectMySQL;

public class DB2Word {	
	private static final String[] answers ={"A", "B", "C"};	
	
	public void db2Word(List<Person> persons, int[][][] results, String ftlTemplatePath, String folderPath){
		long start = System.currentTimeMillis();
		
		Map<String, Object> date = new HashMap<String, Object>();		
		String fileName = null;			
	
		Person p = new Person();
		AgeCalculate ac = new AgeCalculate();
		int age = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM"); 
		
		//循环写出word
		for(int i=0; i<persons.size(); i++)	{			
			//根据出生日期计算计算年龄
			p = persons.get(i);
			date.put("name", p.getName());
			date.put("sex", p.getSex());			
			date.put("department", p.getDepartment());			
			date.put("deg_of_edu", p.getDeg_of_edu());
			
			//格式化日期输出格式   yyyy.MM
			String empty = "";
			date.put("birthday", p.getBirthday() == null? empty : sdf.format(p.getBirthday()));
			date.put("rank_time", p.getRank_time() == null? empty : sdf.format(p.getRank_time()));			
			date.put("mil_rank_time", p.getMil_rank_time() == null? empty : sdf.format(p.getMil_rank_time()));
			date.put("mil_time", p.getMil_time() == null? empty : sdf.format(p.getMil_time()));
			date.put("par_time", p.getPar_time() == null? empty : sdf.format(p.getPar_time()));		
			
			//根据生日计算年龄			
			age = ac.getAgeByBirth(p.getName(), p.getBirthday());
			//如果birthday值为空或者不合法 （-1），则age值为空（-2）。
			if(age<0){
				date.put("age", empty);
				if(age == -1){
					System.out.println("错误：" + p.getName() + "出生日期不合法，晚于当前日期！");
				}else{
					System.out.println("错误：" + p.getName() + "出生日期为空！");
				}					
			}else{
				date.put("age", age);
			}		
			
			//传入参加人数和有效票数
			/*存在问题（BUG）：逻辑上有效票数小于等于参加人数，需要进行程序分析*/			
			int num = 0;
			for(int j=0; j<results[0][0].length; j++){
				num += results[0][0][j];
			}			
			date.put("num_of_ent", num);
			date.put("num_of_val", num);
			
			//传入各单项优秀、及格、不合格票数及比例
			/*存在问题（BUG）：为增强程序鲁棒性应对results值的合法性（值非负）进行判断。*/
			DecimalFormat df = new DecimalFormat("0.00%");
			for(int k=0; k<results[0].length; k++){
				for(int t=0; t<results[0][0].length; t++){					
					//注：1~N题代表变量尾号为1~N，总评代号变量尾号为0.
					if(k == results[0].length-1){
						date.put("num"+answers[t]+0, results[i][k][t]);
						date.put("prop"+answers[t]+0, df.format(results[i][k][t]/(num*1.0)));
					}else{
						date.put("num"+answers[t]+(k+1), results[i][k][t]);
						date.put("prop"+answers[t]+(k+1), df.format(results[i][k][t]/(num*1.0)));
					}										
				}				
			}			
			
			String finalPath = "";
			//将不同部门的人员分文件夹存储
			/*存在问题（BUG）：同一部门同名人员将无法同时输出*/			
			if(p.getDepartment() == null || p.getDepartment() == ""){
				finalPath = folderPath + "其他/";
			}else{
				finalPath = folderPath + p.getDepartment() + "/";
			}				
			fileName = p.getName() + ".doc";
			CreateWord cw = new CreateWord(ftlTemplatePath, finalPath, fileName, date);
			cw.createDoc();
		}				
		long end = System.currentTimeMillis();
		
		System.out.println("测评表写出完成,耗时" + (end - start) +" ms");
	}
	
	public List<Person> getPersons(){
		List<Person> persons = new ArrayList<Person>(); 
		ConnectMySQL cm = new ConnectMySQL();		
		try{
			String sql = "SELECT * FROM person";
			ResultSet rs = cm.executeQuery(sql);		
						
			while(rs.next()){
				Person p = new Person();
				//数据准备				
				p.setName(rs.getString("name"));				
				p.setSex(rs.getString("sex"));				
				p.setBirthday(rs.getDate("birthday"));				
				p.setDepartment(rs.getString("department"));				
				p.setRank_time(rs.getDate("rank_time"));				
				p.setMil_rank_time(rs.getDate("mil_rank_time"));
				p.setDeg_of_edu(rs.getString("deg_of_edu"));
				p.setMil_time(rs.getDate("mil_time"));
				p.setPar_time(rs.getDate("par_time"));	
				
				persons.add(p);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			cm.close();
		}	
		
		return persons;
	}
}