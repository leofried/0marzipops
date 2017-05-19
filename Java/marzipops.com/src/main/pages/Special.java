package main.pages;

import main.helpers.Constants;
import main.interfaces.Page;

public abstract class Special extends Page {
	
	/**
	 * Constructor for a Special Page
	 * @param rawName The raw name of the page, as it would appear on the name of the file.
	 */
	public Special(String rawName){
		super(rawName, "", Type.SPECIAL);
	}
	
	/**
	 * @return The part of the HTML file that contains the top line.
	 */
	public String buildTopLine(){
		return "<div class='row'><div class='small-12 columns'><p class='fullUnderline zeroMargin popBlue'>"
				+ getTextName() + "</p></div></div><br>";
	}
	
	/**
	 * Creates a Formspree form with a name, email, phone, and message box.
	 * @param str The text to be written above the form
	 * @param destination The place to go once the form is submitted
	 * @param isText Whether the text above the form should use the "text" class
	 * @return A string containing the build form
	 */
	public String buildForm(String destination){		
		String write = "";
		write += "<div class='small-12 columns'><form action='//formspree.io/" + Constants.EMAIL + "' method='POST' name='sendNote1' onSubmit='return emailList(false)'>";
		write += "<input type='text' placeholder='Name' name='name'>";
		write += "<input type='text' placeholder='Email' name='_replyto'>";
		write += "<input type='text' placeholder='Phone (optional)' name='phone'>";
		write += "<textarea rows='5' placeholder='Message' name='message'></textarea>";
		write += "<input type='hidden' name='_subject' value='Note'>";
		write += "<input type='hidden' name='_next' value='" + destination + "'>";
		write += "<button type='submit' id='contactButton' class='backBlue'><p class='fontWhite'>Send</p></button></form></div>";
		return write;
	}	
	
}
