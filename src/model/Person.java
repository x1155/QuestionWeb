package model;

import java.util.Date;

public class Person {	
	private String id;             //编号
	private String name;            //姓名
	private String department;      //部憋
	private String duty;            //职别
	private String sex;             //性别
	private Date birthday;          //出生日期
	private Date mil_rank_time;     //军衔时间
	private Date rank_time;         //职级时间
	private Date mil_time;          //入伍时间
	private Date par_time;          //入党时间
	private String deg_of_edu;      //文化程度	

	//set and get method
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getMil_rank_time() {
		return mil_rank_time;
	}
	public void setMil_rank_time(Date mil_rank_time) {
		this.mil_rank_time = mil_rank_time;
	}
	public Date getRank_time() {
		return rank_time;
	}
	public void setRank_time(Date rank_time) {
		this.rank_time = rank_time;
	}
	public Date getMil_time() {
		return mil_time;
	}
	public void setMil_time(Date mil_time) {
		this.mil_time = mil_time;
	}
	public Date getPar_time() {
		return par_time;
	}
	public void setPar_time(Date par_time) {
		this.par_time = par_time;
	}
	public String getDeg_of_edu() {
		return deg_of_edu;
	}
	public void setDeg_of_edu(String deg_of_edu) { 
		this.deg_of_edu = deg_of_edu; 
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) { 
		this.sex = sex; 
	}
}
