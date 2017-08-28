package main.pages.specials;

import main.helpers.Constants;
import main.pages.Special;
import main.readers.FAQReader;

public class FAQ extends Special{

	/**
	 * The Object that reads the list of questions and answers from the excel file.
	 */
	private FAQReader reader;
	
	/**
	 * Constructor for an Details object.
	 */
	public FAQ(FAQReader reader){
		super("FAQ");
		this.reader = reader;
	}

	/**
	 * Abstract method of Page.java
	 * @return The part of the HTML file that contains the main content of the page.
	 */
	public String buildContent() {
		String write = "";

		//Questions
		write = "<div class='row'><div class='small-12 columns'>"
				+ "<div class='row'><div class='small-12 columns'>";

		for(int index=0; index<reader.getNumberOfQuestions(); index++){
			if(index == 0){
				write += writeHeader(true, "About our Products");
			}else if(index == reader.getBreakingPoint()){
				write += writeHeader(true, "Placing an Order");
			}
			String question = reader.getQuestion(index);
			write += "<p class='question'><a href='#" + index + "'>" + question + "</a></p>";
		}

		write += "</div></div>";

		//Answers		
		write += "<div class='row'><div class='small-12 columns'>";

		for(int index=0; index<reader.getNumberOfQuestions(); index++){
			if(index == 0){
				write += writeHeader(false, "About our Products");
			}else if(index == reader.getBreakingPoint()){
				write += writeHeader(false, "Placing an Order");
			}

			String question = reader.getQuestion(index);
			String answer = reader.getAnswer(index);

			write += "<div class='row'><div class='small-12 columns'><a name='" + index + "'>&nbsp;</a>"
					+ "<p class='question'><a href='#" + index + "' onClick='boldStuff(" + index + ");' '>" + question + "</a></p>"
					+ "<p class='answer'>" + answer + "</p></div></div>";
		}

		write += "</div></div>"
				+ "</div></div>";


		//Blanks at the bottom to leave room for #names
		for(int i=0; i<35; i++){
			write += Constants.BLANK;
		}
		
		return write;
	}

	/**
	 * Helper method to buildContent(). Builds the part of the HTML file that contains the section headers on the details page.
	 * @param first Whether this is the first time that the question header has a appeared in the page.
	 * @param text The name of the section.
	 * @return The HTML file that contains the section headers on the details page.
	 */
	private String writeHeader(boolean first, String text){
		String blank = Constants.BLANK;
		if(text.equals("About our Products") && first) blank = "";
		
		String name = "";
		if(!first) name = "name=" + text.charAt(0);
		
		String firstInWords = "first";
		if(!first) firstInWords = "second";
		
		return blank + "<div class='row'><div class='small-12 columns'><p class='" + firstInWords + "QuestionHeader'><a " + name + " href='#" + text.charAt(0) + "'>" + text + "</a></p></div></div>";
	}
}
