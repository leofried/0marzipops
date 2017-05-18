package main.readers;

import java.io.File;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class ProductInfoReader {


	public enum Info {

		RAW_NAME(0),
		DESCRIPTION(2),
		AVAILABLE(3),
		DEFAULT_CATEGORY(7),
		CATEGORIES(13),
		SEARCH_TERMS(14),
		ETSY(16),
		EXTRA_PHOTOS(17),
		SOLD_OUT(18);		

		
		private int column;
		private Info(final int column){
			this.column = column;
		}
		public int getColumn(){
			return column;
		}
	}
	
	private static final int START = 2;
	
	private ArrayList<ArrayList<String>> sheetContents;
	
	private ArrayList<String> listOfCategories;

	
	public ProductInfoReader(String fileLocation, ArrayList<String> listOfCategories){
		File file = new File(fileLocation);
		Workbook w = Workbook.getWorkbook(file);
		Sheet sheet = w.getSheet(0);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
