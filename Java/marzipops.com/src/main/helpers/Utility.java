package main.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.interfaces.Item;
import main.interfaces.Page;
import main.pages.Category;

public class Utility {
	
	/**
	 * Convert a String into HTML language by adding &lsquo;, etc.
	 * @param text The string to convert into HTML.
	 * @return The converted String.
	 */
	public static String convertToText(String text){
		return text.replaceAll("1", "&lsquo;")
				.replaceAll("2", "&rsquo;")
				.replaceAll("3", "&ldquo;")
				.replaceAll("4", "&rdquo;")
				.replaceAll("-", "&dash;")
				.replaceAll("6", "&ntilde")
				.replaceAll("7", "&hellip;")
				.replaceAll("9", "<br>")
				.replaceAll("…", "&hellip;")

				.replaceAll("~&lsquo;", "1")
				.replaceAll("~&rsquo;", "2")
				.replaceAll("~&ldquo;", "3")
				.replaceAll("~&rdquo;", "4")
				.replaceAll("~5", "5")
				.replaceAll("~&ntilde", "6")
				.replaceAll("~&hellip;", "7")
				.replaceAll("~8", "8")
				.replaceAll("~<br>", "9")
				.replaceAll("~0", "0");
	}
	
	
	/**
	 * Find the Category with a certain name given a list of Categories.
	 * @param rawName The name of Category to search for.
	 * @param listOfCategories The list of Categories to search through.
	 * @return The Category with the name rawName.
	 */
	public static Category getCategory(String rawName, List<Category> listOfCategories){
		for(Category c : listOfCategories){
			if(rawName.equals(c.getRawName())){
				return c;
			}
		}

		return null;
	}
	
	
	/**
	 * Deletes a folder and all of its contents.
	 * @param folder The folder to be cleared
	 * @param deleteFolder Whether or not to delete the folder itself.
	 */
	public static void clearDirectory(File folder, boolean deleteFolder) {
		File[] files = folder.listFiles();
		if(files != null) {
			for(File f: files) {
				if(f.isDirectory()) {
					clearDirectory(f, true);
				} else {
					f.delete();
				}
			}
		}
		if(deleteFolder == true){
			folder.delete();
		}
	}
	
	/**
	 * Sorts the list of holidays.
	 * @return The list of sorted holidays.
	 */
	public static String[] sortHolidays(){
		Map<Integer, String> holidayMap = new TreeMap<Integer, String>();
		
		int currentDate = LocalDateTime.now().getMonthValue() * 100 + LocalDateTime.now().getDayOfMonth();
		
		for(int i=0; i<Constants.LIST_OF_HOLIDAYS.length; i++){
			int daysAway = Constants.LIST_OF_HOLIDAY_DATES[i] - currentDate;
			if(daysAway < 0) daysAway += 10000;
			
			String name = Constants.LIST_OF_HOLIDAYS[i];
			
			holidayMap.put(daysAway, name);
		}
		
		List<String> sortedList = new ArrayList<String>();
		for(Map.Entry<Integer, String> entry: holidayMap.entrySet()){
			sortedList.add(entry.getValue());
		}
		
		String[] returnList = new String[sortedList.size()];
		sortedList.toArray(returnList);
		return returnList;
	}
	
	
	/**
	 * Copies a file from source to destination. Overwrites destination.
	 * This is mostly a black box.
	 * @param source The file to copy from.
	 * @param dest The file to copy to.
	 */
	public static void copyFile(File source, File dest) throws IOException {
		FileInputStream inputStream = new FileInputStream(source);
		FileChannel inputChannel = inputStream.getChannel();
		
		FileOutputStream outputStream = new FileOutputStream(dest);
		FileChannel outputChannel = outputStream.getChannel();

		outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		
		inputStream.close();
		outputStream.close();
	}
	
	/**
	 * Builds a grid of items onto the page. This is used by categories and by the search (for when JS is disabled).
	 * @param page The page that the grid will be added to. Necessary for getReverseLocation().
	 * @param listOfItems The list of items that should be in the grid.
	 * @return A String with the code for the grid.
	 */
	public static String buildGrid(Page page, List<Item> listOfItems){
		String write = "";
		write += "<div class='row grid'>";
		for(int i=0; i<listOfItems.size(); i++){
			Item item = listOfItems.get(i);

			write += "<div class='small-6 medium-4 large-3 columns end'><div class='row'><div class='small-12 columns'>"
					+ "<a href=\"" + page.getReverseLocation() + item.getLocation() + item.getRawName() + ".html#\"><img src=\"" + page.getReverseLocation() + "Images/" + item.getLocation() + "SQB " + item.getImageName() + " Marzipan Lollipops Marzipops.jpg\"></img></a>"
					+ "<p class='grid-itemName'>" + item.getTextName() + "</p>"
					+ "</div></div></div>";

			//Space between rows

			if((i + 1) % 2 == 0){
				write += "<div class='small-12 show-for-small-only columns'>&nbsp;</div>";
			}
			if((i + 1) % 3 == 0){
				write += "<div class='small-12 show-for-medium-only columns'>&nbsp;</div>";
			}
			if((i + 1) % 4 == 0){
				write += "<div class='small-12 show-for-large columns'>&nbsp;</div>";
			}
		}
		write += "</div>";
		
		return write;
	}
	
	
}
