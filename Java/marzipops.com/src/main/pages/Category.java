package main.pages;

import java.util.ArrayList;
import java.util.List;

import main.helpers.Utility;
import main.interfaces.Item;
import main.interfaces.Page;

public class Category extends Page implements Item {

	//////////////////////////
	////INSTANCE VARIABLES////
	//////////////////////////
	
	/**
	 * The category that is the parent of this category.
	 */
	private Category parent;
	
	/**
	 * The list of items that are in this category.
	 */
	private List<Item> listOfItems;
	
	
	////////////////////
	////CONSTRUCTORS////
	////////////////////
	
	/**
	 * The main constructor for a Category object. Used for non-HomePage Categories.
	 * Uses parent.getChildLocation() to find the location.
	 * @param rawName The name of the page as it would appear on the name of the file. 
	 * @param parent The parent Category of this Category.
	 */
	public Category(String rawName, Category parent){
		super(rawName, parent.getChildLocation(), PageType.CATEGORY);
		this.parent = parent;
		listOfItems = new ArrayList<Item>();
	}
	
	/**
	 * Another constructor for a Category object. Used for HomePage Categories.
	 * Assumes that the location is "Categories/".
	 * @param rawName The name of the page as it would appear on the name of the file. 
	 */
	public Category(String rawName){
		super(rawName, "", PageType.CATEGORY);
		parent = null;
		listOfItems = new ArrayList<Item>();
	}
	
	/**
	 * A helper method to Category(String, Category).
	 * @return the location of any child Categories of this Category.
	 */
	public String getChildLocation(){
		return getLocation() + getRawName() + "/";
	}
	
	/**
	 * Adds an Item to the end of the list of Items that this Category contains.
	 * @param item An Item to add to the list of Items that this Category contains.
	 */
	public void addToList(Item item){
		addToList(listOfItems.size(), item);
	}
	
	/**
	 * Inserts an Item into the list of Items that this Category contains.
	 * @param index The index to insert the Item into.
	 * @param item An Item to add to the list of Items that this Category contains.
	 */
	public void addToList(int index, Item item){
		listOfItems.add(index, item);
	}
	
	
	//////////////////////////////////
	////buildContent() AND HELPERS////
	//////////////////////////////////
	
	/**
	 * Allows the use of a default method to fulfill an abstract one.
	 */
	public String buildTopLine(){ return Item.super.buildTopLine();}
	
	
	/**
	 * Abstract method of Page.java
	 * @return The part of the HTML file that contains the main content of the page.
	 */
	public String buildContent(){
		String write = "";	
		
		//Grid
		write += Utility.buildGrid(this, listOfItems);

		//Bottom Forum
		write += buildBottomForum();

		return write;
	}
	
	/**
	 * This method is intentionally blank. Index objects will overwrite it,
	 * because they are the only objects that have a forum at the bottom of the webpage.
	 * @return The part of the HTML file that contains the forum at the bottom of the page.
	 */
	public String buildBottomForum(){
		return "";
	}
	
	
	////////////////
	////GETTERS/////
	////////////////
	
	/**
	 * Abstract method of Item.java
	 * @return The name of the page as it would appear in the name of the image. (Capitalized in some cases).
	 */
	public String getImageName(){
		return getRawName().toUpperCase();
	}
	
	/**
	 * Abstract method of Item.java
	 * @return The default parent Category to this item. (The one that would appear in the top filepath bar.
	 */
	public Category getParent(){
		return parent;
	}
	
	/**
	 * @return The list of items in this Category.
	 */
	public List<Item> getListOfItems(){
		return listOfItems;
	}
	
	/**
	 * A list of all of the Categories that are the children, grandchildren, etc, of this Category.
	 * This works by calling this method on all of this method's children and then adding up all of their lists.
	 * The casting is legal because this only gets called before Categories are populated with Products.
	 * @return A list of all of the Categories that are the children, grandchildren, etc, of this Category.
	 */
	public List<Category> getListOfAllChildCategories(){
		List<Category> returnList = new ArrayList<Category>();
		returnList.add(this);
		for(Item item : listOfItems){
			returnList.addAll(((Category) item).getListOfAllChildCategories());
		}
		return returnList;
	}
}
