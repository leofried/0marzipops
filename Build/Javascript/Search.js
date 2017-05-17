function buildSearch(){

	var search = midSearch.toLowerCase();
	var fullLength = fullSearchList.length;
	var shortList = [];
	var blank = "<div class='row'>&nbsp</div>";
	var write = "";

	write += "<div class='row'><div class='small-12 columns'><form action='javascript:searchFunction(&quot&quot, true)'name='bigSearch' method='GET'>";
		write += "<div>";
			write += "<img id='bigSearch' onClick='searchFunction(&quot&quot, true)' src='Images/Click Images/Search.jpg'>";
		write += "<div id='searchCover'><input type='text' name='bigSearch'></div></div>";																													//Ends the text
	write += "</form></div></div>";

	

	var searchList = search.split(" ");																//Splits the Search by words
	var searchListLength = searchList.length;														//Length of the spearch list

	for(i=0; i<fullLength; i++){																	//Rotates through the full List
		smallLength = fullSearchList[i][1].length;														//smallLength is the length of the miniList
		var breakAgain = false;
		for(j=0; j<smallLength; j++){																//Rotates thorugh j from 0 to small length
			for(splitSearch=0; splitSearch<searchListLength; splitSearch++){						//Rotates through the different item to be serched	
				var searchItem = searchList[splitSearch];											//Makes searchItem the actual search
				if(((fullSearchList[i][1][j] + 's').toLowerCase()).indexOf(searchItem) != -1){			//If the searchItem is the keyword or the keyword witha n s at the end then:
					shortList.push(fullSearchList[i][0]);													//Add the item to smallList
					breakAgain = true;
					break;
				}
			}
			if(breakAgain == true){break;}
		}
	}

	write += "<div class='row'>";
	write += "<div class='small-12 columns'>" + buildGrid("", "Products/", shortList) + "</div>";														//start the row fo the items


	if(shortList.length == 0){
		write += "<div class='small-12 columns'><p class='picName'>";
		write += "We were not able to find any matches to your search. Try searching again, or browse our categories.";
		write += "</p></div>";
		write += "<div class='small-12 columns'>" + buildGrid("", "Categories/", categoriesList) + "</div>";
	}
	write += "</div>";	

	document.getElementById('content').innerHTML = write;
	
	var element = document.getElementById('searchWrapper');
	element.innerHTML = '&nbsp;'
	var element = document.getElementById('notIfSearch');
	element.innerHTML = '';
	
	addAsIs();																					//Edits the form to diplay the search query

}

function addAsIs(){		//Edits the form to display the search query
	document.forms.bigSearch.elements['bigSearch'].value = midSearch;
}

var midSearch = decodeURIComponent(window.location.href.substring(window.location.href.indexOf("Search.html?bigSearch=") + 22));
console.log(midSearch);

function buildGrid(imageDir, linkDir, picList){	
	var listLength =  picList.length															//list length is the amount of picks in the grid

	nWrite = "<div class='row'>";														//start the row fo the items
	for(i=0; i<listLength; i++){																//rotates through the list
		nWrite += "<div class='small-6 medium-4 large-3 columns end'><div class='row'><div class='small-12 columns'>"	//Add the with of the item, and then add another row for increased space b/t items
		nWrite += "<a href='" + imageDir + linkDir + picList[i].toLowerCase() + ".html#'><img src='" + imageDir + "Images/" + linkDir + "SQB " + picList[i] + " Marzipan Lollipops Marzipops.jpg'></img></a>";	//Add the pic of the item and make it a link
		nWrite += "<p class='picName'>" + (picList[i].toLowerCase()) + "</p>";				//Add the text for the item
		nWrite += "</div></div></div>";													//finish the item
	}
	nWrite += "</div>";																	//finishes the row

	return nWrite;
}