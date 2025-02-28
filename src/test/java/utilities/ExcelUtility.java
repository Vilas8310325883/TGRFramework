package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType; 
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility{
    String path;
	public  XSSFSheet sheet;
	public  FileInputStream inputStream;
	public static FileOutputStream outputStream;
	public static XSSFWorkbook wb;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public ExcelUtility(String path)
	{
		this.path = path;
	}
/*	public static  XSSFSheet Data_Provider1() throws IOException 
	{
		//File file =    new File("C:\\Users\\codilar\\eclipse-workspace\\TGR\\SeedsmanCreateaccount.xlsx");
		file =    new File(path);
		inputStream = new FileInputStream(file);
			wb=new XSSFWorkbook(inputStream);
			  sheet=wb.getSheet("Sheet2");
			 row= sheet.getRow(0);
				return sheet;			
	}*/
	public int getRowCount(String sheetname) throws IOException
	{
		System.out.println(path);
		inputStream = new FileInputStream(path);
		wb=new XSSFWorkbook(inputStream);
		sheet = wb.getSheet(sheetname);
		int rowcount = sheet.getPhysicalNumberOfRows();
		int rowvalue = 0;
		for(int i=0;i<rowcount;i++)
		{
			try {
			XSSFRow row =sheet.getRow(i);
			XSSFCell cell = row.getCell(0);
			if(cell.getCellType() != CellType.BLANK)
			{
				rowvalue++;
			}
			}
			catch(NullPointerException e)
			{
				i++;
			}
		}
		System.out.println(rowvalue);
		wb.close();
		inputStream.close();
		return rowvalue;	
	}
	public int getCellcount(String sheetname,int rowNum) throws IOException
	{
		inputStream = new FileInputStream(path);
		wb=new XSSFWorkbook(inputStream);
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rowNum);
		int cellcount = row.getLastCellNum();
		wb.close();
		inputStream.close();
		return cellcount;	
	}
	public String getCellData(String sheetname, int rownum, int colnum) throws IOException 
	{
		inputStream = new FileInputStream(path);
		wb=new XSSFWorkbook(inputStream);
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rownum);
	    cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data = "";
		}
		wb.close();
		inputStream.close();
		return data;
	}
	public void setCellData(String sheetname,int rownum,int colnum, String data) throws IOException
	{
		File xlfile = new File(path);
		if(!xlfile.exists())
		{
			wb=new XSSFWorkbook();
			outputStream = new FileOutputStream(path);
			wb.write(outputStream);
		}
			inputStream = new FileInputStream(path);
			wb = new XSSFWorkbook (inputStream);
			
			if(wb.getSheetIndex(sheetname)==-1)
          {
	
			wb.createSheet(sheetname);
			sheet = wb.getSheet(sheetname);		
		  }
			if(sheet.getRow(rownum)==null)
			{
				sheet.createRow(rownum);
				row = sheet.getRow(rownum);
				cell = row.createCell(colnum);
				cell.setCellValue(data);
				outputStream = new FileOutputStream(path);
				wb.write(outputStream);
				wb.close();
				inputStream.close();
				outputStream.close();
			}
	}
       public void fillRedColor(String sheetname, int rownum, int colnum) throws IOException
       {
    	   inputStream = new FileInputStream(path);
   		wb=new XSSFWorkbook(inputStream);
   		sheet = wb.getSheet(sheetname);
   		row = sheet.getRow(rownum);
   	    cell = row.getCell(colnum);
   	     style = wb.createCellStyle();
   	     style.setFillForegroundColor(IndexedColors.RED.getIndex());
   	     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
   	     cell.setCellStyle(style);
   	  wb.write(outputStream);
		wb.close();
		inputStream.close();
		outputStream.close();
   	     
       }
}
