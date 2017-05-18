package main.helpers;

import java.util.List;

import main.pages.Category;

public class Utility {
	
	public static String convertToText(String text){
		text = text.replaceAll("1", "&lsquo;");
		text = text.replaceAll("2", "&rsquo;");
		text = text.replaceAll("3", "&ldquo;");
		text = text.replaceAll("4", "&rdquo;");
		text = text.replaceAll("-", "&dash;");
		text = text.replaceAll("6", "&ntilde");
		text = text.replaceAll("7", "&hellip;");
		text = text.replaceAll("9", "<br>");
		text = text.replaceAll("…", "&hellip;");

		text = text.replaceAll("~&lsquo;", "1");
		text = text.replaceAll("~&rsquo;", "2");
		text = text.replaceAll("~&ldquo;", "3");
		text = text.replaceAll("~&rdquo;", "4");
		text = text.replaceAll("~5", "5");
		text = text.replaceAll("~&ntilde", "6");
		text = text.replaceAll("~&hellip;", "7");
		text = text.replaceAll("~8", "8");
		text = text.replaceAll("~<br>", "9");
		text = text.replaceAll("~0", "0");

		return text;
	}
	
	
	public static Category getCategory(String str, List<Category> listOfCategories){
		for(Category c : listOfCategories){
			if(str.equals(c.getRawName())){
				return c;
			}
		}

		return null;
	}
}
