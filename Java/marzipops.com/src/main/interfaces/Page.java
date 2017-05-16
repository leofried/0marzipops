package main.interfaces;

public abstract class Page {
	
	public enum Type{PRODUCT, CATEGORY, SPECIAL};
	
	private String rawName;
	private String adjName;
	
	private String location;
	
	private Type pageType;
	
	
	public Page(String rawName, String location, Type pageType){
		this.rawName = rawName;
		this.adjName = rawName.replaceAll("-", "&dash;")
							.replaceAll("'", "&#39;");

		this.location = location;
		
		this.pageType = pageType;
	}
	
	public Page(String rawName, String adjName, String location, Type pageType){
		this.rawName = rawName;
		this.adjName = adjName;
		
		this.location = location;
		
		this.pageType = pageType;
	}
	
	
	public String getHTML(){
		return buildHead() + buildTop() + buildContent() + buildFooter();
	}
	
	private String buildHead(){
		String pageTitle;
		if(rawName.equals("index")){pageTitle = "marzipops";}else{pageTitle = "marzipops: " + adjName;}
		
		String write = "<!doctype html><html>" +
				"<head>" +
				"<title>" + pageTitle + "</title>" +
				"<meta name='p:domain_verify' content='b556a9473bc0ff0cfd08b0608c15ffa3'/>" +
				"<link rel='stylesheet' type='text/css' href='" + getReverseLocation() + "Foundation/css/foundation.css'></link>" +
				"<link rel='stylesheet' type='text/css' href='" + getReverseLocation() + "Foundation/css/style.css'></link>" +
				"<script src='" + getReverseLocation() + "Javascript/Javascript.js'></script>" +
				"<script src='" + getReverseLocation() + "Javascript/Lists.js'></script>" +
				"<script src='" + getReverseLocation() + "Javascript/Search.js'></script>" +
				"</head>" +
				"<body onload='javascript(\"" + rawName + "\", \"" + getReverseLocation() + "\", " + (pageType == Type.PRODUCT) + ")'>";
		
		//Website Wrapper
		write += "<div id='full' style='visibility: hidden;'>";	

		return write;
	}
	
	public String buildTop(){
		//Logo and search box
		String write = "<div id='header'>" + Constants.BLANK + "<div class='row id='logo'>" +
				"<div class='show-for-medium-up medium-3 columns'>&nbsp</div>" +
				"<div class='small-12 medium-6 columns'><a href=" + getReverseLocation() + "index.html><img id='logo' src='" + getReverseLocation() + "Images/Logo.jpg'></imgs></a></div>" +
				"<div class='small-12 medium-3 columns' id='searchWrapper'>&nbsp;</div>" +
				"</div><br>";

		//Navagation bar
		write += "<div class='row'><div class='small-12 columns'><ul id='nav'>";
		for(int i=Constants.SPECIALS.length-1; i>=0; i--){
			write = write + "<li><a href='" + getReverseLocation() + Constants.SPECIALS[i] + ".html#'>" + Constants.SPECIALS[i].toLowerCase() + "</a></li>";
			if(i != Constants.SPECIALS.length-1){
				write = write + "<li class='popBlue'>|</li>";
			}
		}

		//Social Media pop-ups
		for(int i=0; i<Constants.MEDIA.length; i++){
			write = write + "<li class='show-for-medium-up socialNav' id='" + Constants.MEDIA[i] + "'><a href='http://www." + Constants.MEDIA[i] + ".com/marzipops' target='_blank'><img src='" + getReverseLocation() + "Images/Social Media/Orange " + Constants.MEDIA[i] + ".jpg'></img></a></li>";
		}
		write += "</ul></div></div>";

		write += Constants.BLANK + "</div>";
		
		write += "<div id='content'>";

		return write;
	}
	
	public abstract String buildContent();
	
	public String buildFooter(){
		String write = "";	
		
		//Bottom social media links
		write += "</div><div id='footer'><div class='row show-for-small-only'><div class='small-6 small-centered columns'><div class='row'>";
		for(int i=0; i<Constants.MEDIA.length; i++){
			write += "<div class='small-3 columns'><a href='http://www." + Constants.MEDIA[i] + ".com/marzipops' target='_blank'><img src='" + getReverseLocation() + "Images/Social Media/Orange " + Constants.MEDIA[i] + ".jpg'></img></a></div>";
		}
		write += "</div></div></div></div></div>" ;


		//Pintrist
		if(pageType == Type.PRODUCT){
			write += "<script async defer src='http://assets.pinterest.com/js/pinit.js'></script>";
		}

		
		//Foundation
		write += "</div><script src='" + getReverseLocation() + "Foundation/js/vendor/jquery.js'></script>" +
				"<script src='" + getReverseLocation() + "Foundation/js/vendor/fastclick.js'></script>" +
				"<script src='" + getReverseLocation() + "Foundation/js/foundation.min.js'></script>" +
				"<script>jQuery.noConflict();$(document).foundation();</script>";

		
		//Google Analytics
		write += "<script>" +
					"(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){" +
						"(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o)," +
						"m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)" +
					"})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');" +
					"ga('create', 'UA-78064797-1', 'auto');" +
					"ga('send', 'pageview');" +
  				"</script>";

		write += "</body></html>";

		return write;
	}
	
	public String getRawName(){
		return rawName;
	}
	
	public String getAdjNam(){
		return adjName;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getReverseLocation(){
		int numberOfFolders = location.length() - location.replace(".", "").length();
		
		String str = "";
		for(int i=0; i<numberOfFolders; i++){
			str += "../";
		}
		
		return str;
	}
}
