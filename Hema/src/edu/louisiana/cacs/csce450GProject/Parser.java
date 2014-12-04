package edu.louisiana.cacs.csce450GProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;








public class Parser{
	/*
	 * YOUR CODE GOES HERE
	 * 
	 * You must implement two methods
	 * 1. parse
	 * 2. printParseTree

	 * Print the intermediate states of the parsing process,
	 * including the intermediate states of the parse tree,make
	 * as specified in the class handout.
	 * If the input is legal according to the grammar,
	 * print ACCEPT, else UNGRAMMATICAL.
	 * If the parse is successful, print the final parse tree.

	 * You can modify the input and output of these function but not the name
	 */

	//public static String equation="id+id*id$";
    public String fileName="";
	private ParseExpression parseExpr=null;
	private String parsetree=null;
	private PrintParseTree printpareTree=null;
	public String[] specialSymbols={"+","*"};
	public Parser(String fileName){
		this.fileName=fileName;
		System.out.println("File to parse : "+fileName);
		Vector<String> datainfo=ExpressionInput.getExpression(fileName);
		for(int i=0;i<datainfo.size();i++)
		{
			parseExpr=new ParseExpression(datainfo.get(i));
			parsetree=parseExpr.parseTrees();
		
		}
	}
	
	
	
	
	public void printParseTree(){
		
		printpareTree=new PrintParseTree();
		printpareTree.setparsetree(parsetree);
		printpareTree.treeView();

	}

	
	public void parse(){
		printParseTree();
	}
}
class DataModeloutput {
	
	private String stack=" ";
	private String inputtokens=" ";
	private String actionlookup=" ";
	private String actionvalue=" ";
	private String valueofLHS=" ";
	private String lengthofRHS=" ";
	private String tempstack=" ";
	private String gotolookup=" ";
	private String gootovalue=" ";
	private String stackaction=" ";
	private String parsetreestack=" ";
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getInputtokens() {
		return inputtokens;
	}
	public void setInputtokens(String inputtokens) {
		this.inputtokens = inputtokens;
	}
	public String getActionlookup() {
		return actionlookup;
	}
	public void setActionlookup(String actionlookup) {
		this.actionlookup = actionlookup;
	}
	public String getActionvalue() {
		return actionvalue;
	}
	public void setActionvalue(String actionvalue) {
		this.actionvalue = actionvalue;
	}
	public String getValueofLHS() {
		return valueofLHS;
	}
	public void setValueofLHS(String valueofLHS) {
		this.valueofLHS = valueofLHS;
	}
	public String getLengthofRHS() {
		return lengthofRHS;
	}
	public void setLengthofRHS(String lengthofRHS) {
		this.lengthofRHS = lengthofRHS;
	}
	public String getTempstack() {
		return tempstack;
	}
	public void setTempstack(String tempstack) {
		this.tempstack = tempstack;
	}
	public String getGotolookup() {
		return gotolookup;
	}
	public void setGotolookup(String gotolookup) {
		this.gotolookup = gotolookup;
	}
	public String getGootovalue() {
		return gootovalue;
	}
	public void setGootovalue(String gootovalue) {
		this.gootovalue = gootovalue;
	}
	public String getStackaction() {
		return stackaction;
	}
	public void setStackaction(String stackaction) {
		this.stackaction = stackaction;
	}
	public String getParsetreestack() {
		return parsetreestack;
	}
	public void setParsetreestack(String parsetreestack) {
		this.parsetreestack = parsetreestack;
	}

}
class ExpressionInput {
	
	public static String expression="id+id*id$";
	
	public static String getExpression()
	{
		return expression;
	}

	public static Vector<String> getExpression(String file)
	{
		
		BufferedReader br=null;
		Vector<String> fileinfo=new Vector<String>();
		String stline=null;
		try
		{
			br=new BufferedReader(new FileReader(file));
			while((stline=br.readLine())!=null)
			{
				fileinfo.add(stline);
			}
		}
		catch (Exception e) {
		  e.printStackTrace();
		}
		finally
		{
			try
			{
			if(br!=null)
			{
				br.close();
			}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return fileinfo;

	}
	
	
}
class ExpressionStore {
	public Hashtable<String, String> getExpressionData()
	{
		Hashtable<String,String> expressiondata=new Hashtable<String,String>();
		expressiondata.put("E+T", "E");
		expressiondata.put("T", "E");
		expressiondata.put("T*F", "T");
		expressiondata.put("F", "T");
		expressiondata.put("(E)", "F");
		expressiondata.put("id", "F");
		return expressiondata;
	}
	
	
	public Hashtable<String,Hashtable<String, String>> getExpressionDataRules()
	{
		
		Hashtable<String,Hashtable<String, String>> expressiondata=new Hashtable<String,Hashtable<String, String>>();
		Hashtable<String,String> expressiondata1=new Hashtable<String,String>();
		Hashtable<String,String> expressiondata2=new Hashtable<String,String>();
		Hashtable<String,String> expressiondata3=new Hashtable<String,String>();
		Hashtable<String,String> expressiondata4=new Hashtable<String,String>();
		Hashtable<String,String> expressiondata5=new Hashtable<String,String>();
		Hashtable<String,String> expressiondata6=new Hashtable<String,String>();
		Hashtable<String,String> expressiondata7=new Hashtable<String,String>();
		expressiondata1.put("E", "E+T");
		expressiondata2.put("E", "T");
		expressiondata3.put("T", "T*F");
		expressiondata4.put("T", "F");
		expressiondata5.put("F", "(E)");
		expressiondata6.put("F", "d");
		expressiondata7.put("accept", "F");
		expressiondata.put("R1", expressiondata1);
		expressiondata.put("R2", expressiondata2);
		expressiondata.put("R3", expressiondata3);
		expressiondata.put("R4", expressiondata4);
		expressiondata.put("R5", expressiondata5);
		expressiondata.put("R6", expressiondata6);
		expressiondata.put("accept", expressiondata7);
		return expressiondata;
	}

}
class ParseExpression {


	public static String expression="";
	public String[] specialchars={"id","+","*","(",")","$"};
	public String[] specialruleschar={"*","+"};
	String initialstack="0";
	String stacklastindex="0";
	Hashtable <String,String> getExpression=null;
	Hashtable <String,Hashtable <String,String>> getExpressionList=null;
	TableInfo tbin=null;
	String previouspus=null;
	String previouslookup="0";
	String previouslhs="0";
	String parsetree="";
	private TreeWithChar twc=new TreeWithChar();
	
	public ParseExpression(String expression) {
		
		this.expression=expression;

		// TODO Auto-generated constructor stub
	}
	
	
	public String parseTrees()
	{
		ExpressionStore expstore=new ExpressionStore();
		getExpression=expstore.getExpressionData();
		getExpressionList=expstore.getExpressionDataRules();
		DataModeloutput dmoutput=new DataModeloutput();
		dmoutput.setStack(""+initialstack);
		tbin=new TableInfo();
		tbin.tabledata();
		boolean flag=true;;
		ArrayList<String> getstrings=null;
		String getdata="";
		ArrayList<DataModeloutput> displaydata=new ArrayList<DataModeloutput>();
		while(!getdata.equals("accept")&&flag)
		{
			if(!getdata.contains("R"))	
				getstrings=getRemaingString(expression);
			getdata=tbin.getvalue(stacklastindex, getstrings.get(0));
			DataModeloutput dm=new DataModeloutput();
			if(getdata.toLowerCase().startsWith("s"))
			{
				scase(dm, getdata, getstrings.get(0),expression,initialstack);	
				displaydata.add(dm);
				expression=getstrings.get(1);
			}
			else
			{
				rcase(dm, getdata, getstrings.get(0),expression,initialstack);	
				displaydata.add(dm);
			}
			
			if(getstrings.get(0).equals(getstrings.get(1)))
			{
				flag=false;
			}
		}
		for(int i=0;i<displaydata.size();i++)
		{
			displayModelData(i,displaydata.get(i));
		}
		
		return parsetree;
	
		
	}

	public void scase(DataModeloutput dm,String tablevalue,String token,String expression,String stack)
	{
		dm.setStack(stack);
		dm.setInputtokens(expression);
		dm.setActionlookup("["+stack.substring(stack.length()-1,stack.length())+","+token+"]");
		dm.setActionvalue(tablevalue);
		dm.setStackaction("push"+token+tablevalue.replace("S",""));
		initialstack+=token+tablevalue.replace("S","");
		stacklastindex=initialstack.substring(initialstack.length()-1,initialstack.length());
		previouspus="push"+token+tablevalue.replace("S","");
		String temptree=null;
		if(token.equals("id"))
			temptree="id"+parsetree;
		else
			temptree=parsetree;
	    	dm.setParsetreestack(temptree);

	}
	public void rcase(DataModeloutput dm,String tablevalue,String token,String expression,String stack)
	{

		
		String makeparsertree="";
		dm.setStack(stack);
		dm.setInputtokens(expression);
		dm.setActionlookup("["+previouslookup+","+token+"]");	
		dm.setActionvalue(tablevalue);
		String temp=stack.replace("0", "").replace(stack.substring(stack.length()-1,stack.length()), "");
		if(getExpression.get(temp)!=null)
		{

			dm.setValueofLHS(getExpression.get(temp));
		}
		else
		{
			temp=previouspus.substring(4,previouspus.length()-1);	
			if(getExpression.get(temp)!=null)	
				dm.setValueofLHS(getExpression.get(temp));
			else
			{
				temp=previouslhs;
				dm.setValueofLHS(getExpression.get(temp));
			}
		}
		
		if(dm.getValueofLHS()!=null)
		{
			initialstack=initialstack.replace(temp, "").replace(stack.substring(stack.length()-previouslookup.length(),stack.length()),"");
			String temdis=initialstack.replace(dm.getValueofLHS(),"");
			if(!tablevalue.equals("$"))
			{
				String ruledata=getExpressionList.get(tablevalue).get(dm.getValueofLHS());
				int length=ruledata.length();
				dm.setLengthofRHS(""+length);
				if(length>2)
				{
				dm.setTempstack(temdis.substring(0,temdis.length()-length));
				String getexprchar=getexprchar(ruledata);
				makeparsertree=twc.treeMaker(ruledata, parsetree,dm.getValueofLHS(),getexprchar);
				}
				else
					dm.setTempstack(temdis.substring(0,temdis.length()));	
				initialstack=dm.getTempstack();
			}
			if(makeparsertree.length()<=0)
			{
			if(temp.equals("id"))
			{
			 parsetree="["+dm.getValueofLHS()+","+temp+"]"+parsetree;
			}
			else 
			parsetree="["+dm.getValueofLHS()+parsetree+"]";
			}
			else
			{
				parsetree=makeparsertree;
			}
			String tempval=dm.getTempstack().substring(dm.getTempstack().length()-1,dm.getTempstack().length());
			String value=tbin.getvalue(tempval, dm.getValueofLHS());
			dm.setGotolookup("["+tempval+","+dm.getValueofLHS()+"]");
			dm.setGootovalue(value);
			dm.setStackaction("push"+dm.getValueofLHS()+dm.getGootovalue());
			dm.setParsetreestack(parsetree);
			initialstack+=dm.getValueofLHS()+dm.getGootovalue();
			stacklastindex=dm.getGootovalue();
			previouspus="push"+dm.getValueofLHS()+dm.getGootovalue();
			previouslookup=dm.getGootovalue();
			previouslhs=dm.getValueofLHS();
		}
	}
	



	public void displayModelData(int stackno,DataModeloutput datamodel)
	{

		System.out.print((stackno+1)+"\t");
		System.out.format("%20s",datamodel.getStack());
		System.out.format("%20s",datamodel.getInputtokens());
		System.out.format("%20s",datamodel.getActionlookup());
		System.out.format("%20s",datamodel.getActionvalue());
		if(datamodel.getValueofLHS()!=null)
		System.out.format("%20s",datamodel.getValueofLHS());
		System.out.format("%20s",datamodel.getLengthofRHS());
		System.out.format("%20s",datamodel.getTempstack());
		System.out.format("%20s",datamodel.getGotolookup());
		System.out.format("%20s",datamodel.getGootovalue());
		System.out.format("%20s",datamodel.getStackaction());
		System.out.format("%20s",datamodel.getParsetreestack());
		System.out.println("");	
	}



	public ArrayList<String> getRemaingString(String data)
	{
		String remain=null;
		ArrayList<String> getStrings=new ArrayList<String>();
		int index=0;
		int temp=0;
		String checkString="";
		for(int i=0;i<specialchars.length;i++)
		{
			if(data.contains(specialchars[i]))
			{
				index=data.indexOf(specialchars[i]);
				if(temp>=index)
				{
					checkString=specialchars[i];
				}
				temp=index;
			}
		}
		String sub=data.substring(0,checkString.length());
		remain=data.substring(checkString.length(),data.length());
		getStrings.add(sub);
		getStrings.add(remain);
		data.substring(0,checkString.length());
		return getStrings;
	}
	
	
	public String getexprchar(String data)
	{
		String exprchar="";
		for(int i=0;i<specialruleschar.length;i++)
		if(data.contains(specialruleschar[i]))
		{
			exprchar=specialruleschar[i];
			break;
		}
		return exprchar;
	}
}
class TableInfo {

    public String[] headerArray={"id","+","*","(",")","$","E","T","F"};
	String[][] dataMatrix=null;

	public void tabledata()
	{
		int columns=12;
		int rows=9;
		dataMatrix=new String[columns][rows];
		String[] row1={"S5","","","S4","","","1","2","3"};
		String[] row2={"","S6","","","","accept","","",""};
		String[] row3={"","R2","S7","","R2","R2","","",""};
		String[] row4={"","R4","R4","","R4","R4","","",""};
		String[] row5={"S5","","","S4","","","8","2","3"};
		String[] row6={"","R6","R6","","R6","R6","","",""};
		String[] row7={"S5","","","S4","","","","9","3"};
		String[] row8={"S5","","","S4","","","","","10"};
		String[] row9={"","S6","","","s11","","","",""};
		String[] row10={"","R1","S7","","R1","R1","","",""};
		String[] row11={"","R3","R3","","R3","R3","","",""};
		String[] row12={"","R5","R5","","R5","R5","","",""};
		ArrayList<String[]> rowsdata=new ArrayList<String[]>();
		rowsdata.add(row1);
		rowsdata.add(row2);
		rowsdata.add(row3);
		rowsdata.add(row4);
		rowsdata.add(row5);
		rowsdata.add(row6);
		rowsdata.add(row7);
		rowsdata.add(row8);
		rowsdata.add(row9);
		rowsdata.add(row10);
		rowsdata.add(row11);
		rowsdata.add(row12);
		for(int i=0;i<columns;i++)
		{
			dataMatrix[i]=rowsdata.get(i);
		}
		printMatrix(dataMatrix);
	}

	public static void main(String[] args)
	{
		TableInfo tinfo=new TableInfo();
		tinfo.tabledata();
		//System.out.println(tinfo.getvalue("0", "id"));
	}


	public void printMatrix(String[][] data)
	{
		int columns=data.length;
		int rows=data[data.length-1].length;
		for(int i=0;i<columns;i++)
		{
			for(int j=0;j<rows;j++)
			{
				if(data[i][j].length()<=0)
				{
					System.out.format("%10s"," ");
				}
				else
					System.out.format("%10s",data[i][j]);
			}
			System.out.println("");
		}
	}
	public int getheaderIndex(String key)
	{
		for(int i=0;i<headerArray.length;i++)
		{
			if(key.equals(headerArray[i]))
			{
				return i;
			}
		}
		return 0;
	}

	public String getvalue(String id,String key)
	{
		int index=getheaderIndex(key);
	
		String value="";
		try
		{
		value=dataMatrix[Integer.parseInt(id)][index];
		}
		catch (Exception e) {
		return "$";
		}
		return value;                           
	}

}
class PrintParseTree {

	private String parseTree=null;
	public String[] specialchars={"+","*"};
	public ArrayList<String> specialcharsList=new ArrayList<String>();
	public void setparsetree(String parsetree)
	{
		this.parseTree=parsetree;
		parseTreechars(parsetree);
	}
	public void parseTreechars(String parsetree)
	{
		for(int i=0;i<specialchars.length;i++)
		{
			if(parsetree.contains(specialchars[i]))
			{
				specialcharsList.add(specialchars[i]);
			}
		}
	}

	public void treeView()
	{
		ArrayList<String> parestreeStore=new ArrayList<String>();
		String[] parser=null;
		for(int i=0;i<specialcharsList.size();i++)
		{
			parser=parseTree.split("\\"+specialcharsList.get(i));
			if(parser[0].length()>0)
			{
				parestreeStore.add(parser[0]);
				parseTree=specialcharsList.get(i)+parser[1];
			}
		}
		parestreeStore.add(parseTree);
		treeDispayView(parestreeStore);
	}

	

	public void treeDispayView(ArrayList<String> splittreedata)
	{
		int space=0;
		String[] splitdata=null;
		String customizeddata=null;
		String spacer="";
		String keyspacer="";
		for(int i=0;i<splittreedata.size();i++)
		{
			keyspacer="";
			for(int spa=0;spa<=space;spa++)
			{
				spacer+="  ";
			}
			int openbraces=countString(splittreedata.get(i), '[');
			int closebraces=countString(splittreedata.get(i), ']');
			space+=(openbraces-closebraces);
			customizeddata=replace(splittreedata.get(i));
			splitdata=customizeddata.split(",");
			for(int keys=0;keys<splitdata.length;keys++)
			{
				if(keys==0)
					System.out.println(spacer+splitdata[keys]);
				else
				{
					keyspacer+=spacer;
					System.out.println(keyspacer+splitdata[keys]);
					keyspacer+="  ";
				}
			}
		}
	}


	public String replace(String data)
	{
		data=data.replace("[", ",").replace("]","").replace(" ",",");
		return data;
	}


	public int countString(String data,char charcter)
	{
		int count=0;
		for(int i=0;i<data.length();i++)
		{
			if(data.charAt(i)==charcter)
				count++;
		}
		return count;

	}

	public ArrayList<String> getRemaingString(String data)
	{
		String remain=null;
		ArrayList<String> getStrings=new ArrayList<String>();
		int index=0;
		int temp=0;
		String checkString="";
		for(int i=0;i<specialchars.length;i++)
		{
			if(data.contains(specialchars[i]))
			{
				index=data.indexOf(specialchars[i]);
				if(temp>=index)
				{
					checkString=specialchars[i];
					break;
				}
				temp=index;
			}
		}
		String sub=data.substring(0,checkString.length());
		remain=data.substring(checkString.length(),data.length());
		getStrings.add(sub);
		getStrings.add(remain);
		data.substring(0,checkString.length());
		return getStrings;
	}
}
class TreeWithChar {

	public String treeMaker(String data,String parsetree,String pushdata,String splitchar)
	{
		String[] rulespliter=data.split("\\"+splitchar);
		String tempString=null;
		ArrayList<String> dataPrepare=new ArrayList<String>();
		for(int i=0;i<rulespliter.length;i++)
		{
			
			tempString=parsetree.substring(parsetree.indexOf("["+rulespliter[i]),parsetree.length());
			tempString=tempString.substring(0,tempString.indexOf("]")+1);
			parsetree =parsetree.substring(0,parsetree.indexOf("["+rulespliter[i]))+parsetree.substring(parsetree.indexOf("["+rulespliter[i])+tempString.length(),parsetree.length());
			if(i==0)
			{
			tempString="["+pushdata+tempString+"]";
			}
			
			dataPrepare.add(tempString);
		}
		
	    String pudataPrepare="";
		for(int i=0;i<dataPrepare.size();i++)
		{
			pudataPrepare+=dataPrepare.get(i);
		   if(i<dataPrepare.size()-1)
			   pudataPrepare+=splitchar;
		}
		pudataPrepare+=parsetree;
		return pudataPrepare;
		}
}