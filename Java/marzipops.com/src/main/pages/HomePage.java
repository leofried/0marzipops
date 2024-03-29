package main.pages;

import main.helpers.Constants;

public class HomePage extends Category {
	
	/**
	 * The main constructor for an Index object
	 * @param rawName The name of the page as it would appear on the name of the file. 
	 */
	public HomePage(String rawName){
		super(rawName);
	}
	
	/**
	 * @return The name of the page as it would appear in HTML text or in the title of a webpage.
	 */
	public String getTextName(){
		return getRawName().replaceAll("SignedUp", "");
	}

	/**
	 * Children of HomePages have different locations.
	 * @return the location of any child Categories of this Category.
	 */
	public String getChildLocation(){
		return "Categories/";
	}
	
	/**
	 * 
	 */
	public String buildTopLine(){
		if(getRawName().startsWith("shop")){
			return super.buildTopLine();
		}else{
			return "<div class='row'>"
					+ "<div class='small-12 columns'><a id='SlideshowLink' href='Products/emoji.html'><img id='SlideshowImage' src='Images/Slideshow/Slideshow 1.jpg'></img></a></div>"
					+ "</div>"
					+ Constants.BLANK;
		}		
	}
	
	/**
	 * @return The part of the HTML file that contains the forum at the bottom of the page.
	 */
	public String buildBottomForum(){
		String write = "";

		if(!getRawName().contains("SignedUp")){				
			write += "<div class='row'>"
					+ "<div class='small-12 medium-6 large-4 small-centered columns'><div class='row'><form action='//formspree.io/" + Constants.EMAIL + "' method='POST' name='emailList1' onSubmit='return emailList(true)'>"
						+ "<div class='small-12 columns'><p>Stay up to date: Sign up for our email!</p></div>"
						+ "<input type='hidden' name='_subject' value='For Email List'>"
						+ "<input type='hidden' name='_next' value='" + getRawName() + "signedup.html#signedUp'>"						
						+ "<div class='small-10 columns'><input type='text' name='email' placeholder='Email'></div>"
						+ "<div class='small-2 columns'><button type='submit' class='shopButton'><p>Send</p></button></div></form>"
					+ "</div></div>"
					+ "</div>";
		}else{
			write += "<div class='row'>"
					+ "<div class='small-12 medium-6 large-4 small-centered columns'>"
						+ "<p class='center'>Sweet, you're on the list!</p>"
					+ "</div>"
					+ "</div>";
		}
		
		return write;
	}
	
}
