function javascript(add, name, product){
	normalPageTop(add, name);
	if(product){productAdds(name);}
}


function normalPageTop(add, name){
	
//	document.getElementById('full').style.cssText += "hidden !important";

	var write = "";
	write += "<div class='row'>";
		write += "<div class='show-for-medium-up medium-3 columns'>&nbsp</div>";
		write += "<div class='small-12 medium-6 columns'><a href=" + add + "index.html#><img id='logo' src='" + add + "Images/Logo.jpg'></imgs></a></div>";
		write += "<div class='small-12 medium-3 columns' id='searchWrapper'><form action='javascript:searchFunction(&quot" + add + "&quot, false)' name='search' method='POST'>";
			write += "<br><div>";
				write += "<img id='search' onClick='searchFunction(&quot" + add + "&quot, false)' src='" + add + "Images/Click Images/Search.jpg'>";
			write += "<div id='searchCover'><input type='text' name='search'></div></div>";
		write += "</form></div>";
	write += "</div><br id='notIfSearch'>";

	navList = ['shop', 'custom', 'about', 'details', 'contact'];
	media = ['Facebook', 'Twitter', 'Instagram', 'Pinterest'];

	write += "<div class='row'><div class='small-12 columns'><ul id='nav'>";
		for(i=0; i<navList.length; i++){
			linkName = capitalize(navList[i]);
			write += "<li><a href='" + add + linkName + ".html#'>" + navList[i] + "</a></li>";
			if(i != navList.length-1){
				write += "<li class='popBlue'>|</li>";
			}
		}

		for(i=0; i<media.length; i++){
			write = write + "<li class='show-for-medium-up " + media[i] + "' id='socialNav'><a href='http://www." + media[i] + ".com/marzipops' target='_blank'><img src='" + add + "Images/Social Media/Orange " + media[i] + ".jpg'></img></a></li>";
		}
	write += "</ul></div></div>";

	document.getElementById('header').innerHTML = write;

	if(name == "Search"){buildSearch();}
}

function productAdds(name){
	for(var i=0; i<extraPicList.length; i++){
		if(extraPicList[i][0] == name){
			if(extraPicList[i][1] != 0){
				var item = i;
				break;
			}else{
				return;
			}
		}
	}
	
	

	var picture = "<div class='small-2 medium-1 columns zeroLeftPadding'><ul id='extraImages'>";
	for(var i=0; i<=extraPicList[item][1]; i++){
		if(i == 0){var text = "";}else{var text = " " + i;}
		link = "'../Images/Products/SQB " + name + text + " Marzipan Lollipops Marzipops.jpg'";
		picture += "<li><img src=" + link + " onClick='changeImg(\"" + name + text + "\");' class='pointer'></img></li><br>";
	}
	picture += "</ul></div>";

	document.getElementById('productPic').className = 'small-4 medium-5 columns';

	var currentText = document.getElementById('picDiscWrapper').innerHTML;
	var index = currentText.indexOf('</div>') + 6;
	var newText = currentText.slice(0, index) + picture + currentText.slice(index);

	document.getElementById('picDiscWrapper').innerHTML = newText;

}

function changeImg(text){
	var element = document.getElementById('mainPic');
	element.src = "../Images/Products/SQB " + text + " Marzipan Lollipops Marzipops.jpg";
	console.log("lalal");
}


function searchFunction(add, isBig){
	var form = document.forms.search;													//When the search button is activated var form = document.forms.search;														//The form is the searchForm
	var item = 'search';																	//Item is search
	if(isBig == true){form=document.forms.bigSearch; item='bigSearch';}						//Unlsess isBig is true... then item is bigSearch

	window.open(add + "Search.html?bigSearch=" + form.elements[item].value, "_self");		//Opens a new page with the requested search
}

function emailList(isEmailList){												//This function checks to see if the email is real (Is this the email LIst form)

	if(isEmailList == true){													//If it is the email list
		var email = document.forms.emailList1.elements['email'].value;			//Email is the item with the name 'email' in the form emailList1
	}else{																		///If not
		var email = document.forms.sendNote1.elements['_replyto'].value;			//Email is the item with the name 'email' in the formsendNote1
	}
	var length = email.length													//Lenght is the length of the string emial

	if (email.length == 0){														//If the string as no lenght
		alert('Please enter your e-mail address. Thank you!');					//Alert to send an email
		return false;
	}

	var atYet = false;															//atYet is false This means there is no @ yet.
	for (var i=1; i<length; i++){												//For i in length (cycles through all of the characters in email except for the first letter bc that should not be the @ sign)
		var letter = email.charAt(i);											//letter is the curent letter
		if (atYet==true){														//If there is was an a@ sign prevously then it is valid
			return true;														//the email is valid
		}
		if (letter=='@'){atYet = true;}											//If there is an @ sign then make atYet true
	}

	alert('Please enter your e-mail address in the form of email@example.');	//Say to correctly add email
	return false;																//Email is invalid
}



function capitalize(str){ //capitalizes a string based on sentense case
	return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}