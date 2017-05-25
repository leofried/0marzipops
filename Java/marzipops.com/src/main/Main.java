package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.helpers.Constants;
import main.helpers.Utility;
import main.pages.Category;
import main.pages.Index;

public class Main {
	
	public static void main(String[] args){
		Utility.clearDirectory(new File(Constants.WEB_LOCATION + "Categories"), false);	
		Utility.clearDirectory(new File(Constants.WEB_LOCATION + "Products"), false);
		List<Category> categories = makeListOfCategories();		
	}

	
	public static List<Category> makeListOfCategories(){		
		//Create the Shop Category and fill it with categories.
		Category shop = new Index("Shop");
		Category specialHoliday = fillCategory(shop, Constants.LIST_OF_CATEGORIES);
		shop.addToList(2, specialHoliday);
		
	}
	
	public Category fillCategory(Category target, String[] listOfCategoryNames){		
		Category specialHoliday = null;
		
		for(int i=0; i<listOfCategoryNames.length; i++){
			Category newCategory = new Category(listOfCategoryNames[i], target);
			for(String[][] infoList : Constants.LIST_OF_SPECIAL_CATEGORIES){
				if(infoList[0][0].equals(newCategory.getRawName())){
					fillCategory(newCategory, infoList[0]);
				}
			}
			target.addToList(newCategory);
			
			if(target.getRawName().equals("Holiday") && i==0){
				specialHoliday = newCategory;
			}
		}
		
		return specialHoliday;
	}
	
	
}
