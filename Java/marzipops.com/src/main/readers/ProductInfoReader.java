package main.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import main.pages.Category;

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

	private int numberOfProducts;

	private String[][] sheetContents;

	private ArrayList<Category> listOfCategories;


	public ProductInfoReader(String fileLocation, ArrayList<Category> listOfCategories) throws BiffException, IOException{
		File file = new File(fileLocation);
		Workbook w = Workbook.getWorkbook(file);
		Sheet sheet = w.getSheet(0);

		numberOfProducts = Integer.parseInt(sheet.getCell(0, 0).getContents());

		sheetContents = new String[numberOfProducts][Info.values().length];

		for(int i=0; i<numberOfProducts; i++){
			for(int j=0; j<Info.values().length; j++){
				sheetContents[i][j] = sheet.getCell(Info.values()[j].getColumn(), i + START).getContents();
			}
		}

		this.listOfCategories = listOfCategories;
	}
	
	public int getNumberOfProducts(){
		return numberOfProducts;
	}
	
	public ArrayList<Category> getListOfCategories(){
		return listOfCategories;
	}
	
	public String getInfo(Info info, int id){
		 return sheetContents[id][info.ordinal()];
	}
	
	











}