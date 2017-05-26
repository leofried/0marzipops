function javascript(name, add, isProduct){
	updateHeader(name, add);
	if(isProduct){
		updateProductPage(name);
	}
	if(name == "Search"){
		buildSearch();
	}
}

function updateHeader(name, add){

	//Add the search box.

	var searchForm = document.createElement("form");
		searchForm.action = "javascript:searchFunction(&quot" + add + "&quot, false)";
		searchForm.name = "search";
		searchForm.method = "POST";

		var br = document.createElement("br");

		searchForm.appendChild(br);


		var container = document.createElement("div");

			var picture = document.createElement("img");
				picture.id = "search";
				picture.onClick = "searchFunction(&quot" + add + "&quot, false)";
				picture.src = add + "Images/Click Images/Search.jpg";

			container.appendChild(picture);


			var searchCover = document.createElement("div");
				searchCover.id = "searchCover";

				var input = document.createElement("input");
					input.type = "text";
					input.name = "search";

				searchCover.appendChild(input);

			container.appendChild(searchCover);

		searchForm.appendChild(container);


	var searchBoxContainer = document.getElementById("searchWrapper");
	searchBoxContainer.appendChild(searchForm);
}

function updateProductPage(name){
	
	//Check if there are any secondary photos for this name.
	for(var i=0; i<extraPicList.length; i++){
		if(extraPicList[i][0] == name){
			if(extraPicList[i][1] != 0){
				addExtraPhotos(name, i);
			}
		}
	}
}

function addExtraPhotos(name, index){

	var verticalPictures = document.createElement("div");
		verticalPictures.className = "small-2 medium-1 columns zeroLeftPadding";

		var list = document.createElement("ul");
			list.id = "extraImages";

			for(var i=0; i<extraPicList[index][i]; i++){
				var text = "";
				if(i != 0) text = " " + i;

				var listElement = document.createElement("li");

					var image = document.createElement("img");
						image.src = "../Images/Products/SQB " + name + text + " Marzipan Lollipops Marzipops.jpg";
						image.onClick = "changeImg(\"" + name + text + "\");";
						image.className = "pointer";

					listElement.appendChild(image);

				list.appendChild(listElement);
			}

		verticalPictures.appendChild(list);

	document.getElementById("productPic").class = "small-4 medium-5 columns";

	document.getElementById("picDiscWrapper").insertBefore(verticalPictures, document.getElementById("productDescription"));

/*
	var picture = "<div class='small-2 medium-1 columns zeroLeftPadding'><ul id='extraImages'>";
	for(var i=0; i<=extraPicList[index][1]; i++){
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
*/
}

function changeImg(text){
	var element = document.getElementById('mainPic');
	element.src = "../Images/Products/SQB " + text + " Marzipan Lollipops Marzipops.jpg";
}

function searchFunction(add, isBig){
	var form = document.forms.search;
	var item = 'search';
	if(isBig == true){form=document.forms.bigSearch; item='bigSearch';}
	window.open(add + "Search.html?bigSearch=" + form.elements[item].value, "_self");
}

function emailList(isEmailList){

	if(isEmailList == true){
		var email = document.forms.emailList1.elements['email'].value;
	}else{
		var email = document.forms.sendNote1.elements['_replyto'].value;
	}
	var length = email.length;

	if (email.length == 0){	
		alert('Please enter your e-mail address. Thank you!');
		return false;
	}

	var atYet = false;
	for (var i=1; i<length; i++){
		var letter = email.charAt(i);
		if (atYet==true){
			return true;
		}
		if (letter=='@'){
			atYet = true;
		}
	}

	alert('Please enter your e-mail address in the form of email@example.');
	return false;
}
