window.onload = function() {
	var categoryList = document.getElementById('categoryList');
	var newCategory = document.getElementById('newCategory');
	var radios = document.getElementsByName('selector');
	
	radios[0].onclick = function() {
		newCategory.disabled="disabled";
		categoryList.removeAttribute("disabled");
	};
	
	radios[1].onclick = function() {
		categoryList.disabled="disabled";
		newCategory.removeAttribute("disabled");
	};
};