package main.pages.specials;

import java.util.List;

import main.helpers.Utility;
import main.interfaces.Item;
import main.pages.Special;

public class Search extends Special{

	/**
	 * The list of items that should be in the grid if JS is disabled.
	 */
	private List<Item> listOfItems;
	
	/**
	 * Constructor for an Search object.
	 * @param listOfItems The list of items that should be in the grid if JS is disabled.
	 */
	public Search(List<Item> listOfItems){
		super("Search");
		this.listOfItems = listOfItems;
	}
	
	/**
	 * Abstract method of Page.java.
	 * This is what will show up if Javascript is disabled. If JS is enabled, this code will be overwritten.
	 * @return The part of the HTML file that contains the main content of the page.
	 */
	public String buildContent(){
		String write = "";
		
		write += "<div class='row'><div class='small-12 columns'><p>The best way to search toady is by looking through our categories.</p></div></div>";
		
		write += Utility.buildGrid(this, listOfItems);
		
		return write;
	}
	
	/**
	 * What javascript function should be called when the page is called
	 */
	public String getOnLoadCall(){
		return "buildSearchPage()";
	}
}
