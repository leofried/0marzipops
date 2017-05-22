package main.pages.specials;

import main.helpers.Constants;
import main.helpers.Utility;
import main.pages.Special;
import main.readers.DetailsReader;

public class Details extends Special{

	public Details(){
		super("Details");
	}
	
	/**
	 * Page superclass 
	 */
	public String buildContent() {
		
		DetailsReader reader = new DetailsReader(Constants.EXCEL_FILE);
		
		//Questions
		String write = "<div class='row'><div class='small-12 columns'>"
					+ "<div class='row'><div class='small-12 columns'>";
		
			for(int index=0; index<reader.getArray().length; index++){
				if(index == 0){
					write += writeHeader(false, "About our Products");
				}else if(index == reader.getBreakingPoint()){
					write += writeHeader(false, "Placing an Order");
				}
				String question = Utility.convertToText(reader.getArray().get(index)[0]);
				write += "<p class='bold pointer'><a href='#" + index + "'>" + question + "</a></p>";
			}
			
		write += "</div></div>";
		
		//Answers		
		write += "<div class='row'><div class='small-12 columns'>";
			
		for(int index=0; index<reader.getArray().length; index++){
			if(index == 0){
				write += writeHeader(true, "About our Products");
			}else if(index == reader.getBreakingPoint()){
				write += writeHeader(true, "Placing an Order");
			}
			
			String question = Utility.convertToText(reader.getArray().get(index)[0]);
			String answer = Utility.convertToText(reader.getArray().get(index)[1]);
			
			write += "<a name='" + index + "'>&nbsp;</a><br>"
					+ "<div class='row'><div class='small-12 columns'><p class='bold pointer'><a id='PopBlue' href='#" + index + "' onClick='boldStuff(" + index + ");' '>" + question + "</a></p></div></div>"
					+ "<div class='row'><div class='small-12 columns'><p>" + answer + "</p></div></div>";
		}
		
		write += "</div></div>"
				+ "</div></div>";
		
		
		//Blanks at the bottom to leave room for #names
		for(int i=0; i<35; i++){
			write += Constants.BLANK;
		}	
		
		return write;
	}
	
	private String writeHeader(boolean haveName, String text){
		String name = "";
		if(haveName==true){name = "name=" + text.charAt(0);}
		return Constants.BLANK + "<h3><a class='bold popBlue' " + name + " href='#" + text.charAt(0) + "'>" + text + "</a></h3>";
	}
}