//	$(function() {
//	$('.productsList').empty();
//	
//	var offsettedPage = 0;
//	var query = $(".searchBar").val();
//
//	$.ajax({
//		type : "GET",
//		url : "getPaginatedProducts?page=" + offsettedPage + "&query=" + query,
//		dataType : 'json',
//		beforeSend : console.log("Enviando dados pro serv"),
//		success : function(data) {
//			var count = data[0].count;
//			var pages = count/10;
//			
//			$.each(data, function(index) {
//				console.log("COunt:L: +  "+ data[index].count);
//				$('.productsList').append('<li>Nome: <a href="productForm?id='+data[index].id+'">'+data[index].name+' Count: '+data[index].count+'</a></li>');	
//			});
//			
//			$('#pagination').append('<a href="#">first</a>');
//			
//			while(pages) {
//				$('#pagination').append('<a href="'+ pages +'">'+ pages +'</a>');
//				pages--;
//			}
//			
//			$('#pagination').append('<a href="#">last</a>');
//		}
//	});
//});
var cachedCategoryOptions = null;

$(function() {
	
	 $('#productContainer').jtable({
         title: 'Produtos',
         paging: true,
         pageSize: 10,
         actions: {
        	 listAction: function (postData, jtParams) {
                 console.log("Loading from custom function...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                         url: '/EstagioEstoque/web/getPaginatedProducts?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize,
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                        	 if (data.length > 0) {
                        		 var count = data[0].count;
                        	 } else {
                        		 count = 0;
                        	 }
                        	 var result = {Result: "OK", Records: data, TotalRecordCount: count};
                             $dfd.resolve(result);
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             },
             createAction: '/GettingStarted/CreatePerson',
             updateAction: '/GettingStarted/UpdatePerson',
             deleteAction: '/GettingStarted/DeletePerson'
         },
         fields: {
             id: {
                 key: true,
                 list: false
             },
             categoryId: {
                 title: 'Categoria',
//                 options: '/EstagioEstoque/web/getCategories',
                 options: function () {
                     
                     if (cachedCategoryOptions) { //Check for cache
                         return cachedCategoryOptions;
                     }

                     var options = new Object();

                     $.ajax({ //Not found in cache, get from server
                         url: '/EstagioEstoque/web/getCategories',
                         type: 'POST',
                         dataType: 'json',
                         async: false,
                         success: function (data) {
                        	 var categories = new Object();
                        	 $.each(data, function(index) {
                        		 var id = data[index].id;
                            	 categories[id] = data[index].name;
                             });
                        	 console.log("Gambi: " + categories);
                             options = categories;
                         }
                     });
                      
                     return cachedCategoryOptions = options; //Cache results and return options
                 },
                 list: false
             },
             subcategoryId: {
                 title: 'Subcategoria',
                 dependsOn: 'categoryId', //Countries depends on continentals. Thus, jTable builds cascade dropdowns!
                 options: function (data) {
                     if (data.source == 'list') {
                         //Return url of all countries for optimization. 
                         //This method is called for each row on the table and jTable caches options based on this url.
                         return '/EstagioEstoque/web/selectSubcategories?id=0';
                     }

                     //This code runs when user opens edit/create form or changes continental combobox on an edit/create form.
                     //data.source == 'edit' || data.source == 'create'
                     var options = new Object();

                     $.ajax({ //Not found in cache, get from server
                         url: '/EstagioEstoque/web/selectSubcategories?id=' + data.dependedValues.categoryId,
                         type: 'POST',
                         dataType: 'json',
                         async: false,
                         success: function (data) {
                        	 var subcategories = new Object();
                        	 $.each(data, function(index) {
                        		 var id = data[index].id;
                            	 subcategories[id] = data[index].name;
                             });
                        	 console.log("Gambi: " + subcategories);
                             options = subcategories;
                         }
                     });
                      
                     return options; //Cache results and return options
                 },
                 list: false
             },
             name: {
                 title: 'Nome do Produto',
                 width: '40%'
             },
             brand: {
                 title: 'Marca',
                 width: '10%'
             },
             model: {
                 title: 'Modelo',
                 width: '10%'
             },
             active: {
            	 list: false,
            	 edit: false,
            	 create: false
             },
             items: {
            	 list: false,
            	 edit: false,
            	 create: false
             },
             links: {
            	 list: false,
            	 edit: false,
            	 create: false
             },
             longDescription: {
            	 title: 'Descrição longa',
            	 type: 'textarea',
            	 list: false
             },
             shortDescription: {
            	 title: 'Descrição curta',
            	 list: false
             },
             rank: {
            	 title: 'Ranking',
            	 width: '5%'
             },
             weight: {
            	 title: 'Peso',
            	 list: false
             },
             warranty: {
            	 title: 'Garantia',
            	 width: '5%'
             }
         }
     });
	 
	 $('#productContainer').jtable('load');
	 //Re-load records when user click 'load records' button.
     $('.btn-search').click(function (e) {
         e.preventDefault();
         $('#productContainer').jtable('load', {
             query: $('.searchBar').val()
         });
     });

     //Load all records when page is first shown
     $('.btn-search').click();
 });