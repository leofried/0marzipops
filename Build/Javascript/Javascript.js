function javascript(name, add, isProduct, index){
	updateEveryPage(add);
//	if(isProduct) updateProductPage(name, index);
//	if(name == "index") updateIndexPage();
}


function updateEveryPage(add){

	/////////////////////////
	////Build Search Form////
	/////////////////////////

	var searchForm = document.createElement("form");
		searchForm.action = "javascript:searchFunction('" + add + "', false)";
		searchForm.name = "search";
		searchForm.method = "POST";

		var br = document.createElement("br");

		searchForm.appendChild(br);


		var container = document.createElement("div");

			var picture = document.createElement("input");
				picture.id = "saveForm";
				picture.type = "image";
				picture.className = "searchImage";
				picture.src = add + "Images/Click Images/Search.jpg";
//				picture.onClick = function(){alert("yeah");};

			container.appendChild(picture);


			var searchCover = document.createElement("div");
				searchCover.className = "searchInputWrapper";

				var input = document.createElement("input");
					input.type = "text";
					input.name = "search";

				searchCover.appendChild(input);

			container.appendChild(searchCover);

		searchForm.appendChild(container);


	var searchBoxContainer = document.getElementById("searchWrapper");
	searchBoxContainer.appendChild(searchForm);
}

function updateProductPage(name, index){


	////////////////////////
	////Add Extra Photos////
	////////////////////////

	if(extraPicList[index][1] != 0){

		var verticalPictures = document.createElement("div");
			verticalPictures.className = "small-2 medium-1 columns productInfo-altImageWrapper";

			var list = document.createElement("ul");
				list.id = "extraImages";

				for(var i=0; i<extraPicList[index][1]; i++){

					var text = "";
					if(i != 0) text = " " + i;

					var listElement = document.createElement("li");

						var image = document.createElement("img");
							image.src = "../Images/Products/SQB " + name + text + " Marzipan Lollipops Marzipops.jpg";
							image.onclick = function(name, text){
								var element = document.getElementById('productImage');
								element.src = "../Images/Products/SQB " + name + text + " Marzipan Lollipops Marzipops.jpg";
							};

						listElement.appendChild(image);


					list.appendChild(listElement);
				}

			verticalPictures.appendChild(list);

		document.getElementById("productPic").className = "small-4 medium-5 columns";

		document.getElementById("productInfoWrapper").insertBefore(verticalPictures, document.getElementById("productDescription"));
	
	}
}

function changeImg(name){
	var element = document.getElementById('productImage');
	element.src = "../Images/Products/SQB " + name + " 1 Marzipan Lollipops Marzipops.jpg";

	console.log(element.src);
	console.log(element.src.substring(22 + name, element.src.length - 34));

	return false;
}













//Needs work
function searchFunction(add, isBig){
	var form = document.forms.search;
	var item = "search";
	if(isBig == true){
		form=document.forms.bigSearch;
		item="bigSearch";
	}
	window.open(add + "Search.html?bigSearch=" + form.elements[item].value, "_self");
}

//Will be replaced by PHP.
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
