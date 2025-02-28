package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders{ 
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path ="C:\\Users\\codilar\\eclipse-workspace\\TGRFramework\\testData\\test11.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalrowswithvalue = totalrows-1;
		System.out.println(totalrowswithvalue);
		//System.out.println(totalrows);
	//	int totalrows = 6;
		int totalcols = xlutil.getCellcount("Sheet1", 1);
		
		String logindata[][] = new String[totalrowswithvalue] [totalcols];
		for(int i=1; i<=totalrowswithvalue;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
		}
		System.out.println(logindata);
		return logindata;
		
	}

}
