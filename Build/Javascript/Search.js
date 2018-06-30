function buildSearchPage(){

	var searchedTerm = decodeURIComponent(window.location.href.substring(window.location.href.indexOf("Search.html?bigSearch=") + 22));

	var write = "<div class='row'><div class='small-12 columns searchWrapper'><form action='javascript:searchFunction(&quot;&quot;, true)'name='bigSearch' method='GET'>"
					+ "<div>"
						+ "<img class='searchImage' onClick='searchFunction(&quot;&quot;, true)' src='Images/Click Images/Search.jpg'>"
						+ "<div class='searchInputWrapper'><input type='text' name='bigSearch'></div>"
					+ "</div>"
				+ "</form></div></div>";

	
	var listOfSearchTerms = searchedTerm.split(" ");

	var listOfProducts = [];
	
	
	for(var i=0; i<fullSearchList.length; i++){
		var product = fullSearchList[i];

		var nextProduct = false;
		for(var j=0; j < listOfSearchTerms.length; j++){
			var searchTerm = listOfSearchTerms[j];

			for(var k=0; k<product[1].length; k++){
				var productAttribute = product[1][k];

				if((productAttribute + "s").toLowerCase().includes(searchTerm)){
					listOfProducts.push(product[0]);
					nextProduct = true;
					break;
				}

			}

			if(nextProduct) break;
		}
	}



	write += "<div class='row grid'><div class='small-12 columns'>";

	if(listOfProducts.length > 0){
		write += buildGrid("Products/", listOfProducts);
	}else{
		write += "<p>We were not able to find any matches to your search. Try searching again, or browse our categories.</p></div>";
		write += "<div class='small-12 columns'>" + buildGrid("Categories/", categoriesList);
	}

	write += "</div></div>";

	document.getElementById('content').innerHTML = write;
	document.getElementById('searchWrapper').innerHTML =  '&nbsp;'

	document.forms.bigSearch.elements['bigSearch'].value = searchedTerm;
}


function buildGrid(location, listOfItems){
	write = "<div class='row'>";
		for(i=0; i<listOfItems.length; i++){
			write += "<div class='small-6 medium-4 large-3 columns end'><div class='row'><div class='small-12 columns'>"
				+ "<a href='" + location + listOfItems[i].toLowerCase() + ".html#'><img src='Images/" + location + "SQB " + listOfItems[i] + " Marzipan Lollipops Marzipops.jpg'></img></a>"
				+ "<p class='grid-itemName'>" + listOfItems[i].toLowerCase() + "</p>"
			+ "</div></div></div>";

			if((i + 1) % 2 == 0){
				write += "<div class='small-12 show-for-small-only columns'>&nbsp;</div>";
			}
			if((i + 1) % 3 == 0){
				write += "<div class='small-12 show-for-medium-only columns'>&nbsp;</div>";
			}
			if((i + 1) % 4 == 0){
				write += "<div class='small-12 show-for-large columns'>&nbsp;</div>";
			}
		}
	write += "</div>";

	return write;	
}