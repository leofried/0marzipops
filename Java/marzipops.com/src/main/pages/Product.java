package main.pages;

import main.helpers.Constants;
import main.interfaces.Item;
import main.interfaces.Page;


public class Product extends Page implements Item{

	public Product(String rawName){
		super(rawName, "Products/", Type.PRODUCT);
	}
	
	
	public String buildContent(){
		
		String write = "";
		
		//Location Line
		write += buildTopLine();
		
		
		//Picture Text
		String picture = "<div class='small-6 columns' id='productPic'><img src='../Images/Products/SQB " + getImageName() + " Marzipan Lollipops Marzipops.jpg' id='mainPic'></img>"
						+ "<p class='text'>Click <a class='popBlue' href='../Custom.html#'>here</a> for custom&nbsp;orders.</p>"
						+ "</div>";
		
		
		//Buy Now Text
		String buyNow = "";
		if(!getSoldOut()){
			buyNow += "<div class='small-6 medium-3 large-2 columns end zeroPadding'><a href='http://www.etsy.com/listing/" + getEtsyCode() + "' target='_blank'><img src='../Images/Click Images/Buy Now.jpg'></img></a></div>";
		}
		else{
			buyNow += "<div class='small-12 columns'>"
						+ "<p class='text bold fontRed'>SOLD OUT</p>"
						+ "<p class='text'>Sorry, we are sold out of " + getTextName() + ".<br>"
						+ "Browse our <a href='../index.html' class='popBlue'>other products,</a>" +"or visit our <a href='www.etsy.com/shop/marzipops' class='popBlue'>Etsy shop.</a><br>"
						+ "You can also check back later.</p>"
					+ "</div>";
		}
		
		
		//Build Picture and Description
		write += "<div class='row' id='picDiscWrapper'>"
					+ picture
					+ "<div class='small-6 columns' id='productDescription'>"
						+ "<div class='row'><div class='small-12 columns'><p class='text bold'>" + getTextName() + "</p></div></div>"
						+ "<div class='row'><div class='small-12 columns'><p class='text'>" + getDesciption() + "</p></div></div>"
						+ "<div class='row'><div class='small-12 columns'><p class='text'>" + getAvailable() + ".</p></div></div>"
						+ "<div class='row'><div class='small-12 columns'>"
							+ "<a data-pin-do='buttonPin' data-pin-count='above' href='http://www.pinterest.com/pin/create/button/?url=http://www.marzipops.com/Products/" + getRawName() + ".html&media=http://www.marzipops.com/Images/Products/SQB " + getImageName() + " Marzipan Lollipops Marzipops.jpg&description=" + getRawName() + " by marzipops.com'>"
								+ "<img src='http://assets.pinterest.com/images/pidgets/pinit_fg_en_rect_gray_20.png'/>"
							+ "</a>&nbsp;"
						+ "</div></div><br>"
						+ "<div class='row'>" + buyNow + "</div>"
					+ "</div>"
				+ "</div>" + Constants.BLANK;
		
		return write;
	}
	
	
}