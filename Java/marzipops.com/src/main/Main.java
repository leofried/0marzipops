package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.read.biff.BiffException;
import main.helpers.Constants;
import main.helpers.Utility;
import main.interfaces.Item;
import main.interfaces.Page;
import main.pages.Category;
import main.pages.HomePage;
import main.pages.Product;
import main.pages.Special;
import main.pages.specials.About;
import main.pages.specials.Contact;
import main.pages.specials.Custom;
import main.pages.specials.FAQ;
import main.pages.specials.Search;
import main.pages.specials.old.Details;
import main.readers.FAQReader;
import main.readers.ProductInfoReader;

public class Main {
	
	public static void main(String[] args) throws BiffException, IOException{
		System.out.println("i can prin to the screen :)");
		
		Utility.clearDirectory(new File(Constants.WEB_LOCATION + "Categories"), false);	
		Utility.clearDirectory(new File(Constants.WEB_LOCATION + "Products"), false);
		
		ArrayList<Page> pages = new ArrayList<Page>();
		List<Category> categories = makeListOfCategories();
		List<Product> products = makeListOfProducts(categories);
		
		pages.addAll(categories);
		pages.addAll(products);
		pages.addAll(makeListOfSpecials(categories));
		
		writePages(pages);
		writeJSLists(products);
		changePictures();
	}	
	
	/**
	 * Creates the list of Categories that are needed for this website.
	 * This method has four steps:<br>
	 * 1) Create the index page and create and fill it with all of it's children categories.
	 * Children Categories that have children themselves will follow the same process,
	 * With their children being created and then filled and so on and so forth.<br>
	 * 2) Add the current first holiday to the index Page's list of Items.<br>
	 * 3) Create the three other HomePages (indexSignedUp, Shop, and ShopSignedUp).<br>
	 * 4) Get a list of all of the Categories that were created and add them to a list which then gets returned.
	 * @return a list of all of the Categories.
	 */
	private static List<Category> makeListOfCategories(){		
		//The list of all of the Categories. This will get returned.
		List<Category> listOfCategories = new ArrayList<Category>();
		
		//PART 1
		//Create the Index Category
		Category shop = new HomePage("shop");
		//Fill with Categories. As this is a recursive function, all of the non-home-page categories will be created here.
		fillCategory(shop, Constants.LIST_OF_CATEGORIES);
		//Add the shop category to the listOfCategories
		listOfCategories.add(shop);

		//Part 2
		//Find the Category that has the name of the first Holiday and add that one to the index Page's list of items.
		shop.addToList(2, Utility.getCategory(Utility.sortHolidays()[0], shop.getListOfAllChildCategories()));
		
		//Part 3
		//Create and fill the other three HomePages. Also add these Categories to the listOfCategories.
		String[] otherHomePageNames = {"shopSignedUp", "index", "indexSignedUp"};
		for(String pageName : otherHomePageNames){
			Category otherHomePage = new HomePage(pageName);
			listOfCategories.add(otherHomePage);
			for(Item item : shop.getListOfItems()){
				otherHomePage.addToList(item);
			}
		}
		
		//Part 4
		//Get a list of all of the children and children of children, etc, of the index Category. Add these to the listOfCategories.
		//This works because all of the items in the listOfItems of Categories are Categories at the moment.
		listOfCategories.addAll(shop.getListOfAllChildCategories());
		return listOfCategories;
	}
	
	/**
	 * Helper method to makeListOfCategories().
	 * A recursive function that fills a given Category by creating and filling it's children Categories,
	 * before adding those Categories to the original Category.
	 * @param target The Category that should be filled.
	 * @param listOfCategoryNames The list of names for the target's children Category.
	 */
	private static void fillCategory(Category target, String[] listOfCategoryNames){
		
		for(int i=0; i<listOfCategoryNames.length; i++){
			Category newCategory = new Category(listOfCategoryNames[i], target);

			for(String[][] infoList : Constants.LIST_OF_MIDDLE_CATEGORIES){
				if(infoList[0][0].equals(newCategory.getRawName())){
					fillCategory(newCategory, infoList[1]);
					new File(Constants.WEB_LOCATION + newCategory.getLocation() + newCategory.getRawName()).mkdir();
				}
			}
			target.addToList(newCategory);
		}
	}
	
	/**
	 * Creates all of the Products and then returns a list of them.
	 * @param listOfCategories The list of Categories that could be the parent of the Products that are being created.
	 * @return A list of all of the Product for the website.
	 */
	private static List<Product> makeListOfProducts(List<Category> listOfCategories) throws BiffException, IOException{
		ProductInfoReader reader = new ProductInfoReader(Constants.EXCEL_FILE, listOfCategories);
		
		List<Product> returnList = new ArrayList<Product>();
		for(int i=0; i<reader.getNumberOfProducts(); i++){
			Product product = new Product(i, reader);
			for(Category category : product.getCategories()){
				category.addToList(product);
			}
			returnList.add(product);
		}
		
		return returnList;
	}
	
	/**
	 * Creates all of the Specials and then returns a list of them.
	 * @return A list of all of the Special Pages for the website.
	 */
	private static List<Special> makeListOfSpecials(List<Category> categories) throws BiffException, IOException{
		List<Special> returnList = new ArrayList<Special>();
		returnList.add(new About());
		returnList.add(new Contact());
		returnList.add(new Custom());
		returnList.add(new FAQ(new FAQReader(Constants.EXCEL_FILE)));
		returnList.add(new Search(categories.get(0).getListOfItems()));
		returnList.add(new Details());
		return returnList;
	}
	
	/**
	 * Writes the pages into HTML files.
	 * @param pages The list of pages to write into an actual file.
	 */
	private static void writePages(List<Page> pages) throws IOException{
		for(Page page : pages){
			FileWriter writer = new FileWriter(Constants.WEB_LOCATION + page.getLocation() + page.getRawName() + ".html" , false);
			writer.write(page.buildHTML());
			writer.close();
		}
	}
	
	/**
	 * Writes the list that are needed for the javascript program to function.
	 * Currently there are only two lists: fullSearchList and extraPicList
	 * @param products The list of products that are on the website.
	 */
	private static void writeJSLists(List<Product> products) throws IOException {
		FileWriter writer = new FileWriter(Constants.WEB_LOCATION + "Javascript/Lists.js" , false);
		
		//List of search terms.
		writer.write("fullSearchList = [\n");
		for(Product product : products){
			writer.write("['" + product.getRawName() + "',[");
			for(String searchTerm : product.getSearchTerms()){
				writer.write("'" + searchTerm.replace("'", "\\'") + "',");
			}
			writer.write("]],\n");
		}
		writer.write("];\n\n\n");

		//List of how many extra pictures each product has.
		writer.write("extraPicList = [\n");
		for(Product product : products){
			writer.write("['" + product.getRawName() + "',");
			writer.write(" " + product.getNumberOfExtraPhotos() + ",");
			writer.write("],\n");
		}
		writer.write("];\n\n\n");
		
		//List of categories.
		writer.write("categoriesList = [");
		for(String category : Constants.LIST_OF_CATEGORIES){
			writer.write("'" + category.toUpperCase() + "', ");
		}
		writer.write("];");
		writer.close();
	}
	
	/**
	 * Changes any pictures that should be automatically changed.
	 * There is currently only one picture that needs changing: The holidays picture changes to match whichever the first holiday is.
	 */
	private static void changePictures() throws IOException{
		File source = new File(Constants.WEB_LOCATION + "Images/Categories/holidays/SQB " + Utility.sortHolidays()[0] + " Marzipan Lollipops Marzipops.jpg");
		File dest   = new File(Constants.WEB_LOCATION + "Images/Categories/SQB " + Utility.sortHolidays()[0] + " Marzipan Lollipops Marzipops.jpg");
		Utility.copyFile(source, dest);
	}
	
}
