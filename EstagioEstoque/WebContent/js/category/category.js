var cachedCategoryOptions = null;

$(function() {
	
	 $('#categoryContainer').jtable({
         title: 'Produtos',
         paging: true,
         pageSize: 10,
         pageSizes: [10, 20, 35, 50],
         deleteConfirmation: function(datas) {
        	
        	 datas.deleteConfirmMessage = "Esta categoria tem as seguintes subcategorias atreladas a ela: <br/> ";
        	 $.ajax({
     			type : "GET",
     			url : '/EstagioEstoque/web/requestDeleteCategory?id=' + datas.record.id,
     			dataType : 'json',
     			async: false,
     			beforeSend : console.log("Enviando dados pro serv"),
     			success : function(data) {
     				console.log("yay: " + data);
     				if (data.length > 0) {
     					$("#toBeDeleted").append('<ul class="subcategories"></ul>');
     					$.each(data, function(index) {
     						var name = data[index].name;
     						var subcategoryId = data[index].id;
     						datas.deleteConfirmMessage += subcategoryId + " - " + name + "<br/>";
     						var products = data[index].products;
     						$("ul.subcategories").append('<li class="subcategory'+subcategoryId+'">' + name + '</li>');
     						$('li.subcategory'+subcategoryId).append('<ul class="subcategory'+subcategoryId+'"></ul>');
			 					$.each(products, function(index) {
			 							var name = products[index].name;
			 							var productId = products[index].id;
			 							var items = products[index].items;
			 							$('ul.subcategory'+subcategoryId).append('<li class="product'+productId+'">' + name + '</li>');
			 							$('li.product'+productId).append('<ul class="product'+productId+'"></ul>');
			 							$.each(items, function(index) {
			 								var name = items[index].sku;
			 								$('ul.product'+productId).append('<li>' + name + '</li>');
			 							});
			 						});
			 					});
			 					$("#toBeDeleted").append('<button id="/EstagioEstoque/web/deleteCategory?id=1&confirmation=ok" type="button" class="btn btn-xs btn-danger btn-delete">OK</button>');
			 					$("#toBeDeleted").append('<button type="button" class="btn-cancel btn btn-xs btn-warning">Cancelar</button>');
			 				}
     				else {
//     					window.location.replace("/EstagioEstoque/web/subcategorias?deleted=true");
     				}
     			}
     		});
         },
         actions: {
        	 listAction: function (postData, jtParams) {
                 console.log("Recebendo lista do server...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                         url: '/EstagioEstoque/web/getPaginatedCategories?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize,
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                             $dfd.resolve(data);
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             },
             createAction: function (postData) {
                 console.log("Enviando produto para criação...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                         url: '/EstagioEstoque/web/saveCategory',
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                        	 $dfd.resolve(data);
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             },
             updateAction: function (postData) {
                 console.log("Enviando produto para atualização...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                    	 url: '/EstagioEstoque/web/saveCategory',
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                        	 if (data) {
                                 $dfd.resolve(data);
                        	 } else {
                        		 $dfd.reject();
                        	 }
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             },
             deleteAction: function (postData) {
                 console.log("Enviando produto para deleção...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                    	 url: '/EstagioEstoque/web/deleteCategory?confirmation=ok',
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                        	 $dfd.resolve(data);
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             }
         },
         fields: {
             id: {
                 key: true,
                 list: false,
                 edit: true,
                 type: 'hidden'
             },
             subcategories: {
                 title: '',
                 width: '5%',
                 sorting: false,
                 edit: false,
                 create: false,
                 display: function (productData) {
                     //Create an image that will be used to open child table
                     var $img = $('<img src="/EstagioEstoque/css/jQuery/jTable/themes/metro/list_metro.png" title="Editar itens" />');
                     //Open child table when user clicks the image
                     $img.click(function () {
                    	 $('#categoryContainer').jtable('openChildTable',
                                 $img.closest('tr'), //Parent row
                                 {
                                 title: 'Itens de - ' + productData.record.name,
                                 paging: true,
                                 pageSize: 10,
                                 pageSizes: [10, 20, 35, 50],
                                 actions: {
                                	 listAction: function (postData, jtParams) {
                                         console.log("Recebendo lista do server...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/getSubcategoriesByCategoryId?categoryId=' + productData.record.id + '&jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize,
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
                                     createAction: function (postData) {
                                         console.log("Enviando item para criação...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/saveSubcategory',
                                                 type: 'POST',
                                                 dataType: 'json',
                                                 data: postData,
                                                 success: function (data) {
                                                	 if (data) {
                                                		 var result = {Result: "OK", Record: data};
                                                         $dfd.resolve(result);
                                                	 } else {
                                                		 $dfd.reject();
                                                	 }
                                                 },
                                                 error: function () {
                                                     $dfd.reject();
                                                 }
                                             });
                                         });
                                     },
                                     updateAction: function (postData) {
                                         console.log("Enviando item para atualização...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/saveSubcategory',
                                                 type: 'POST',
                                                 dataType: 'json',
                                                 data: postData,
                                                 success: function (data) {
                                                	 if (data) {
                                                		 var result = {Result: "OK"};
                                                         $dfd.resolve(result);
                                                	 } else {
                                                		 $dfd.reject();
                                                	 }
                                                 },
                                                 error: function () {
                                                     $dfd.reject();
                                                 }
                                             });
                                         });
                                     },
                                     deleteAction: function (postData) {
                                         console.log("Enviando item para deleção...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/deleteSubcategory',
                                                 type: 'POST',
                                                 dataType: 'json',
                                                 data: postData,
                                                 success: function (data) {
                                                	 if (data) {
                                                		 var result = {Result: "OK"};
                                                         $dfd.resolve(result);
                                                	 } else {
                                                		 $dfd.reject();
                                                	 }
                                                 },
                                                 error: function () {
                                                     $dfd.reject();
                                                 }
                                             });
                                         });
                                     },
                                 },
                                 fields: {
                                	 categoryId: {
                                         title: 'Categoria',
                                         options: function () {
                                             
                                             if (cachedCategoryOptions) {
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
                                                     options = categories;
                                                 }
                                             });
                                              
                                             return cachedCategoryOptions = options; //Cache results and return options
                                         },
                                         list: false
                                     },
                                     id: {
                                         key: true,
                                         create: false,
                                         list: false,
                                         edit: true,
                                         type: 'hidden'
                                     },
                                     name: {
                                         title: 'Nome da subcategoria',
                                         width: '40%',
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="subcategory.name" class="subcategoryName" value="' + data.record.name + '" />';
                                             } else {
                                                 return '<input type="text" name="subcategory.name" class="subcategoryName" placeholder="Digite um nome" />';
                                             }
                                         }
                                     },
                                     active: {
                                    	 list: false,
                                    	 input: function (data) {
                                    		 if (data.record) {
                                    			 if (data.record.active)
                                    				 return '<input class="" type="checkbox" name="item.active" value="' + data.record.active + '" checked="checked"><span class="jtable-option-text-clickable">Ativo</span>';
                                    			 else
                                    				 return '<input class="" type="checkbox" name="item.active" value="' + data.record.active + '"><span class="jtable-option-text-clickable">Ativo</span>';
                                             } else {
                                            	 return '<input class="" type="checkbox" name="item.active" value="true" checked="checked" /><span class="jtable-option-text-clickable">Ativo</span>';
                                             }
                                         }
                                     }
                                 }
                             }, function (data) { //opened handler
                                 data.childTable.jtable('load');
                             });
                     });
                     //Return image to show on the person row
                     return $img;
                 }
             },
             name: {
                 title: 'Nome da categoria',
                 width: '40%',
                 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="category.name" class="categoryName" value="' + data.record.name + '" />';
                     } else {
                         return '<input type="text" name="category.name" class="categoryName" placeholder="Digite um nome" />';
                     }
                 }
             },
             active: {
            	 list: false,
            	 input: function (data) {
            		 if (data.record) {
            			 if (data.record.active)
            				 return '<input class="" type="checkbox" name="category.active" value="' + data.record.active + '" checked="checked"><span class="jtable-option-text-clickable">Ativo</span>';
            			 else
            				 return '<input class="" type="checkbox" name="category.active" value="' + data.record.active + '"><span class="jtable-option-text-clickable">Ativo</span>';
                     } else {
                    	 return '<input class="" type="checkbox" name="category.active" value="true" checked="checked" /><span class="jtable-option-text-clickable">Ativo</span>';
                     }
                 }
             }
         }
     });
	 
	 //Re-load records when user click 'load records' button.
     $('.btn-search').click(function (e) {
         e.preventDefault();
         $('#categoryContainer').jtable('load', {
             query: $('.searchBar').val(),
             status: $('.statusSelector').val()
         });
     });

     //Load all records when page is first shown
     $('.btn-search').click();
 });