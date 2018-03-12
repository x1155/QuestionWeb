package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import outPut.DB2Word;
import utils.CreateResults;
import utils.CreateZip;
import model.Person;

public class testZip {	
	
	public static void main(String[] args){
		
		List<Person> persons = new ArrayList<Person>();		
		DB2Word d2w = new DB2Word();
		persons = d2w.getPersons();
		
		int[][][] results = null;
		int num_of_joined = 20;
		int num_of_questions = 14;
		int num_of_choices = 3; 
		CreateResults cr = new CreateResults();
		results = cr.randResults(persons.size(), num_of_questions, num_of_joined, num_of_choices);
		
		String folderPath = "D:/" + new SimpleDateFormat("yyyy").format(new Date()) + "测评/";
		String ftlTemplatePath = "/outPut/Template.ftl";	
		
		d2w.db2Word(persons, results, ftlTemplatePath, folderPath);		
		
		String zipFilePath = "D:/" + new SimpleDateFormat("yyyy").format(new Date()) + "测评.zip";
		CreateZip.toZip(folderPath, zipFilePath, true);	
		
		System.out.println("程序运行结束！");
	}
}
