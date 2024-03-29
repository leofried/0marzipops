package main.pages.specials;

import main.helpers.Constants;
import main.pages.Special;

public class Contact extends Special{	
	
	/**
	 * Constructor for an Contact object.
	 */
	public Contact (){
		super("Contact");
	}
	
	/**
	 * Abstract method of Page.java
	 * @return The part of the HTML file that contains the main content of the page.
	 */
	public String buildContent(){
		String write = "";
		
		//The words at the top.
		write += "<div class='row'><div class='small-12 small-centered columns'><p class='contactMessage'>"
				+ "We want to hear from you!<br>hello@marzipops.com<br>(917)&nbsp658&ndash;0726</p></div></div>";
	
		//The marzipop photos of the various social media sites.
		write += "<div class='row'><div class='small-10 medium-6 large-4 small-centered columns'><div class='row'>";
			for(int i=0; i<Constants.LIST_OF_MEDIA.length; i++){
				write +=  "<div class='small-3 columns'><a href='http://www." + Constants.LIST_OF_MEDIA[i] + ".com/marzipops' target='_blank'><img src='Images/Social Media/" + Constants.LIST_OF_MEDIA[i] + ".jpg'></img></a></div>";
			}
		write += "</div></div></div><br>";
		
		//The two contact us forums.
		write += "<div class='row' id='Feedback'>"
					+ "<div class='small-12 medium-8 small-centered columns'><div class='row'>"
						+ "<div class='small-12 columns'><form action='//formspree.io/" + Constants.EMAIL + "' method='POST' name='emailList1' onSubmit='return emailList(true)'>"
							+ "<p>Stay up to date: sign up for sweet and special offers!</p>"
							+ "<input type='text' name='email' placeholder='Email'>"
							+ "<input type='hidden' name='_subject' value='For Email List'>"
							+ "<input type='hidden' name='_next' value='ContactSignedUp1'>"
							+ "<button type='submit' class='contactButton'><p>Send</p></button>"
						+ "</form></div>"
						+ "<div class='small-12 columns'>" + Constants.BLANK + "</div>"
						+ "<div class='small-12 columns'><p>Send us a note!</p></div>"
						+ buildForm("Contact")
					+ "</div>"
				+ "</div>";
		
		write += Constants.BLANK;
		
		return write;

	}
}
