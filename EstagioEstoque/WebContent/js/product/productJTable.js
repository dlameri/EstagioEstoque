var cachedCategoryOptions = null;

$(function() {
	
	 $('#productContainer').jtable({
         title: 'Produtos',
         paging: true,
         pageSize: 10,
         pageSizes: [10, 20, 35, 50],
         sorting: true,
         defaultSorting: 'id asc',
         deleteConfirmation: function(data) {
         	
        	 $.ajax({
     			type : "GET",
     			url : '/EstagioEstoque/web/checkProductBeforeDeleting?id=' + data.record.id,
     			dataType : 'json',
     			async: false,
     			beforeSend : console.log("Enviando dados pro serv"),
     			success : function(response) {
     				if (response.length > 0 ) {
     					data.deleteConfirmMessage = "Este produto tem itens atrelados a ele.<br/> Você tem certeza que deseja deletá-lo?";
     				} else {
     					data.deleteConfirmMessage = "Você tem certeza que deseja deletar o produto?<br/> ";
     				}
     			}
        	 });
         },
         actions: {
        	 listAction: function (postData, jtParams) {
                 console.log("Recebendo lista do server...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                         url: '/EstagioEstoque/web/getPaginatedProducts?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize + '&jtSorting=' + jtParams.jtSorting,
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
                         url: '/EstagioEstoque/web/saveProduct',
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
                    	 url: '/EstagioEstoque/web/saveProduct',
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
             deleteAction: function (postData) {
                 console.log("Enviando produto para deleção...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                    	 url: '/EstagioEstoque/web/deleteProduct',
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
             items: {
                 title: '',
                 width: '5%',
                 sorting: false,
                 edit: false,
                 create: false,
                 display: function (productData) {
                     //Create an image that will be used to open child table
                     var $img = $('<img class="child-opener-image" src="/EstagioEstoque/css/jQuery/jTable/themes/metro/list_metro.png" title="Editar itens" />');
                     $img.closest('td').addClass("child-opener-image-column");
                     //Open child table when user clicks the image
                     $img.click(function () {
                    	 $('#productContainer').jtable('openChildTable',
                                 $img.closest('tr'), //Parent row
                                 {
                                 title: 'Itens de: ' + productData.record.name,
                                 paging: true,
                                 pageSize: 10,
                                 pageSizes: [10, 20, 35, 50],
                                 sorting: true,
                                 defaultSorting: 'id asc',
                                 actions: {
                                	 listAction: function (postData, jtParams) {
                                         console.log("Recebendo lista do server...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/getItemsByProductId?productId=' + productData.record.id + '&jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize + '&jtSorting=' + jtParams.jtSorting,
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
                                         console.log("Enviando item para criação...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/saveItem',
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
                                         console.log("Enviando item para atualização...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/saveItem',
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
                                     deleteAction: function (postData) {
                                         console.log("Enviando item para deleção...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/deleteItem',
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
                                 },
                                 fields: {
                                     productId: {
                                         type: 'hidden',
                                         defaultValue: productData.record.id
                                     },
                                     images: {
                                    	 title: '',
                                         width: '5%',
                                         sorting: false,
                                         edit: false,
                                         create: false,
                                         display: function (itemData) {
                                        	//Create an image that will be used to open child table
                                             var $imgsub = $('<img class="child-opener-image" src="/EstagioEstoque/css/jQuery/jTable/themes/metro/list_metro.png" title="Editar itens" />');
                                             $imgsub.closest('td').addClass("child-opener-image-column");
                                             //Open child table when user clicks the image
                                             $imgsub.click(function () {
                                            	 $('#productContainer').jtable('openChildTable',
                                                         $imgsub.closest('tr'), //Parent row
                                                         {
                                                         title: 'Imagens de: ' + itemData.record.sku,
                                                         paging: true,
                                                         pageSize: 10,
                                                         pageSizes: [10, 20, 35, 50],
                                                         actions: { 
                                                        	 listAction:'/EstagioEstoque/web/getImagesByItemId?itemId=' + itemData.record.id, 
                                                        	 createAction:'/EstagioEstoque/web/saveImage', 
                                                        	 updateAction:'/EstagioEstoque/web/saveImage', 
                                                        	 deleteAction:'/EstagioEstoque/web/deleteImage' 
                                                         },
                                                         fields: { 
                                                        	 itemId: {
                                                        		 list: false,
                                                        		 input: function (data) {
                                                                     return '<input type="hidden" name="itemId" value="'+itemData.record.id+'"/>';
                                                                 }
                                                        	 },
                                                        	 id: {
                                                                 key: true,
                                                                 create: false,
                                                                 list: false,
                                                                 edit: true,
                                                                 type: 'hidden'
                                                             },
                                                        	 showcaseUrl: {
                                                        		title: 'Vitrine',
                                                        		width: '10%',
                                                        		input: function (data) {
                                                                    if (data.record) {
                                                                        return '<input type="text" name="image.showcaseUrl" class="showcaseUrl" value="' + data.record.showcaseUrl + '" placeholder="Digite a URL da imagem" />';
                                                                    } else {
                                                                        return '<input type="text" name="image.showcaseUrl" class="showcaseUrl" placeholder="Digite a URL da imagem" />';
                                                                    }
                                                                },
                                                                display: function (data) {
                                                                	if (data.record) {
                                                               		 	if (data.record.showcaseUrl) {
                                                               		 		return '<a href = "javascript:void(0)" onclick = "summonLightBox(\''+data.record.showcaseUrl+'\')">Clique aqui</a>';
		                                                                } else {
		                                                       			 return 'N/A';
		                                                                }
                                                                	}
		                                                        }
                                                        	 },
                                                        	 productUrl: {
                                                        		title: 'Pág. Produto',
                                                         		width: '10%',
                                                         		input: function (data) {
                                                                     if (data.record) {
                                                                         return '<input type="text" name="image.productUrl" class="productUrl" value="' + data.record.productUrl + '" placeholder="Digite a URL da imagem" />';
                                                                     } else {
                                                                         return '<input type="text" name="image.productUrl" class="productUrl" placeholder="Digite a URL da imagem" />';
                                                                     }
                                                                 },
                                                                 display: function (data) {
                                                                	 if (data.record) {
                                                                		 if (data.record.productUrl) {
                                                                			 return '<a href = "javascript:void(0)" onclick = "summonLightBox(\''+data.record.productUrl+'\')">Clique aqui</a>';
                                                                		 } else {
		                                                        			 return 'N/A';
		                                                                 }
		                                                        	 }
		                                                         }
                                                        	 },
                                                        	 superzoomUrl: {
                                                        		 title: 'Superzoom',
                                                         		width: '10%',
                                                         		input: function (data) {
                                                                     if (data.record) {
                                                                         return '<input type="text" name="image.superzoomUrl" class="superzoomUrl" value="' + data.record.superzoomUrl + '" placeholder="Digite a URL da imagem" />';
                                                                     } else {
                                                                         return '<input type="text" name="image.superzoomUrl" class="superzoomUrl" placeholder="Digite a URL da imagem" />';
                                                                     }
                                                                 },
                                                                 display: function (data) {
                                                                	 if (data.record) {
                                                                		 if (data.record.superzoomUrl) {
                                                                			 return '<a href = "javascript:void(0)" onclick = "summonLightBox(\''+data.record.superzoomUrl+'\')">Clique aqui</a>';
		                                                                 } else {
		                                                        			 return 'N/A';
		                                                                 }
		                                                        	 }
		                                                         }
                                                        	 },
                                                        	 shoppingCartUrl: {
                                                        		 title: 'Carrinho',
                                                         		width: '10%',
                                                         		input: function (data) {
                                                                     if (data.record) {
                                                                         return '<input type="text" name="image.shoppingCartUrl" class="shoppingCartUrl" value="' + data.record.shoppingCartUrl + '" placeholder="Digite a URL da imagem" />';
                                                                     } else {
                                                                         return '<input type="text" name="image.shoppingCartUrl" class="shoppingCartUrl" placeholder="Digite a URL da imagem" />';
                                                                     }
                                                                 },
                                                                 display: function (data) {
                                                                	 if (data.record) {
                                                                		 if (data.record.shoppingCartUrl) {
                                                                			 return '<a href = "javascript:void(0)" onclick = "summonLightBox(\''+data.record.shoppingCartUrl+'\')">Clique aqui</a>';
                                                                		 } else {
		                                                        			 return 'N/A';
		                                                                 }
		                                                        	 }
		                                                         }
                                                        	 },
                                                        	 androidShowcaseUrl: {
                                                        		 title: 'Android Vitrine',
                                                         		width: '10%',
                                                         		input: function (data) {
                                                                     if (data.record) {
                                                                         return '<input type="text" name="image.androidShowcaseUrl" class="androidShowcaseUrl" value="' + data.record.androidShowcaseUrl + '" placeholder="Digite a URL da imagem" />';
                                                                     } else {
                                                                         return '<input type="text" name="image.androidShowcaseUrl" class="androidShowcaseUrl" placeholder="Digite a URL da imagem" />';
                                                                     }
                                                                 },
                                                                 display: function (data) {
                                                                	 if (data.record) {
                                                                		 if (data.record.androidShowcaseUrl) {
                                                                			 return '<a href = "javascript:void(0)" onclick = "summonLightBox(\''+data.record.androidShowcaseUrl+'\')">Clique aqui</a>';
		                                                                 } else {
		                                                        			 return 'N/A';
		                                                                 }
		                                                        	 }
		                                                         }
                                                        	 },
                                                        	 androidProductUrl: {
                                                        		 title: 'Android Produto',
                                                         		width: '10%',
                                                         		input: function (data) {
                                                                     if (data.record) {
                                                                         return '<input type="text" name="image.androidProductUrl" class="androidProductUrl" value="' + data.record.androidProductUrl + '" placeholder="Digite a URL da imagem" />';
                                                                     } else {
                                                                         return '<input type="text" name="image.androidProductUrl" class="androidProductUrl" placeholder="Digite a URL da imagem" />';
                                                                     }
                                                                 },
                                                                 display: function (data) {
                                                                	 if (data.record) {
                                                                		 if (data.record.androidProductUrl) {
                                                                			 return '<a href = "javascript:void(0)" onclick = "summonLightBox(\''+data.record.androidProductUrl+'\')">Clique aqui</a>';
		                                                                 } else {
		                                                        			 return 'N/A';
		                                                                 }
                                                                	 }
                                                                 }
                                                        	 },
                                                        	 promo: {
                                                        		 title: 'URL Promo',
                                                         		width: '10%',
                                                         		input: function (data) {
                                                                     if (data.record) {
                                                                         return '<input type="text" name="image.promo" class="promo" value="' + data.record.promo + '" placeholder="Digite a URL da imagem"/>';
                                                                     } else {
                                                                         return '<input type="text" name="image.promo" class="promo" placeholder="Digite a URL da imagem" />';
                                                                     }
                                                                 },
                                                                 display: function (data) {
                                                                	 if (data.record) {
                                                                		 if (data.record.promo) {
                                                                			 return '<a href = "javascript:void(0)" onclick = "summonLightBox(\''+data.record.promo+'\')">Clique aqui</a>';
                                                                		 } else {
                                                                			 return 'N/A';
                                                                		 }
                                                                	 }
                                                                 }
                                                        	 },
                                                        	 main: {
                                                        		 input: function (data) {
                                                            		 if (data.record) {
                                                            			 if (data.record.main)
                                                            				 return '<input class="" type="checkbox" name="image.main" value="true" checked="checked"><span class="jtable-option-text-clickable">Principal</span>';
                                                            			 else
                                                            				 return '<input class="" type="checkbox" name="image.main" value="true"><span class="jtable-option-text-clickable">Principal</span>';
                                                                     } else {
                                                                    		 return '<input class="" type="checkbox" name="image.main" value="true" checked="checked" /><span class="jtable-option-text-clickable">Principal</span>';
                                                                     }
                                                                 }
                                                        	 }
                                                         }
                                                     }, function (data) { //opened handler
                                                         data.childTable.jtable('load');
                                                     });
                                             });
                                             return $imgsub;
                                         }
                                     },
                                     id: {
                                         key: true,
                                         create: false,
                                         list: false,
                                         edit: true,
                                         type: 'hidden'
                                     },
                                     sku: {
                                    	 title: 'SKU',
                                         width: '10%',
                                         create: false,
                                         edit: false,
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="item.sku" class="sku" value="' + data.record.sku + '" />';
                                             } else {
                                                 return '<input type="text" name="item.sku" class="sku" placeholder="Digite o SKU" />';
                                             }
                                         }
                                     },
                                     priceFrom: {
                                         title: 'Preço De',
                                         list: false,
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="item.priceFrom" class="priceFrom" value="' + data.record.priceFrom + '" />';
                                             } else {
                                                 return '<input type="text" name="item.priceFrom" class="priceFrom" placeholder="Digite o preço de" />';
                                             }
                                         }
                                     },
                                     priceFor: {
                                         title: 'Preço Por',
                                         list: false,
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="item.priceFor" class="priceFor" value="' + data.record.priceFor + '" />';
                                             } else {
                                                 return '<input type="text" name="item.priceFor" class="priceFor" placeholder="Digite o preço por" />';
                                             }
                                         }
                                     },
                                     formattedPriceFrom: {
                                         title: 'Preço De',
                                         width: '20%',
                                         create: false,
                                         edit: false
                                     },
                                     formattedPriceFor: {
                                         title: 'Preço Por',
                                         width: '20%',
                                         create: false,
                                         edit: false
                                     },
                                     optionName: {
                                         title: 'Opção',
                                         width: '10%',
                                         sorting: false,
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="item.optionName" class="optionName" value="' + data.record.optionName + '" />';
                                             } else {
                                                 return '<input type="text" name="item.optionName" class="optionName" placeholder="Digite o nome da opção" />';
                                             }
                                         }
                                     },
                                     optionValue: {
                                         title: 'Valor',
                                         width: '10%',
                                         sorting: false,
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="item.optionValue" class="optionValue" value="' + data.record.optionValue + '" />';
                                             } else {
                                                 return '<input type="text" name="item.optionValue" class="optionValue" placeholder="Digite a opção" />';
                                             }
                                         }
                                     },
                                     rank: {
                                    	 title: 'Ranking',
                                    	 width: '6%',
                                    	 create: false,
                                    	 edit: false
                                     },
                                     stock: {
                                         title: 'Estoque',
                                         width: '5%',
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="item.stock" class="stock" value="' + data.record.stock + '" />';
                                             } else {
                                                 return '<input type="text" name="item.stock" class="stock" placeholder="Digite a quantidade de estoque" />';
                                             }
                                         }
                                     },
                                     active: {
                                    	 list: false,
                                    	 input: function (data) {
                                    		 if (data.record) {
                                    			 if (data.record.active)
                                    				 return '<input class="" type="checkbox" name="item.active" value="true" checked="checked"><span class="jtable-option-text-clickable">Ativo</span>';
                                    			 else
                                    				 return '<input class="" type="checkbox" name="item.active" value="true"><span class="jtable-option-text-clickable">Ativo</span>';
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
             subcategoryId: {
                 title: 'Subcategoria',
                 dependsOn: 'categoryId',
                 options: function (data) {
                     if (data.source == 'list') {
                         //This method is called for each row on the table and jTable caches options based on this url.
                         return '/EstagioEstoque/web/selectSubcategories?id=0';
                     }

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
                             options = subcategories;
                         }
                     });
                      
                     return options;
                 },
                 list: false
             },
             name: {
                 title: 'Nome do Produto',
                 width: '40%',
                 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="product.name" class="productName" value="' + data.record.name + '" />';
                     } else {
                         return '<input type="text" name="product.name" class="productName" placeholder="Digite um nome" />';
                     }
                 }
             },
             brand: {
                 title: 'Marca',
                 width: '8%',
                 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="product.brand" class="brand" value="' + data.record.brand + '" />';
                     } else {
                         return '<input type="text" name="product.brand" class="brand" placeholder="Digite a marca" />';
                     }
                 }
             },
             model: {
                 title: 'Modelo',
                 width: '8%',
                 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="product.model" class="model" value="' + data.record.model + '" />';
                     } else {
                         return '<input type="text" name="product.model" class="model" placeholder="Digite o modelo" />';
                     }
                 }
             },
             shortDescription: {
            	 title: 'Descrição curta',
            	 list: false,
            	 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="product.shortDescription" class="shortDescription" value="' + data.record.shortDescription + '" />';
                     } else {
                         return '<input type="text" name="product.shortDescription" class="shortDescription" placeholder="Digite a descrição" />';
                     }
                 }
             },
             rank: {
            	 title: 'Ranking',
            	 width: '6%',
            	 create: false,
            	 edit: false
             },
             weight: {
            	 title: 'Peso',
            	 width: '4%',
            	 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="product.weight" class="weight" value="' + data.record.weight + '" />';
                     } else {
                         return '<input type="text" name="product.weight" class="weight" placeholder="Digite o peso" />';
                     }
                 }
             },
             warranty: {
            	 title: 'Garantia',
            	 width: '6%',
            	 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="product.warranty" class="warranty" value="' + data.record.warranty + '" />';
                     } else {
                         return '<input type="text" name="product.warranty" class="warranty" placeholder="Digite a garantia" />';
                     }
                 }
			 },
			 width: {
				 title: 'Largura',
            	 list: false,
            	 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="dimensions.width" class="width" value="' + data.record.width + '" />';
                     } else {
                         return '<input type="text" name="dimensions.width" class="width" placeholder="Digite a largura" />';
                     }
                 }
			 },
			 height: {
				 title: 'Altura',
            	 list: false,
            	 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="dimensions.height" class="height" value="' + data.record.height + '" />';
                     } else {
                         return '<input type="text" name="dimensions.height" class="height" placeholder="Digite a altura" />';
                     }
                 }
			 },
			 depth: {
				 title: 'Profundidade',
            	 list: false,
            	 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="dimensions.depth" class="depth" value="' + data.record.depth + '" />';
                     } else {
                         return '<input type="text" name="dimensions.depth" class="depth" placeholder="Digite a profundidade" />';
                     }
                 }
			 },
			 longDescription: {
            	 title: 'Descrição longa',
            	 list: false,
            	 input: function (data) {
            		 if (data.record) {
                         return '<textarea name="product.longDescription" class="longDescription" rows=4 cols=75>' + data.record.longDescription + '</textarea>';
                     } else {
                         return '<textarea name="product.longDescription" class="longDescription" rows=4 cols=75 placeholder="Descrição longa pode conter tags HTML"></textarea>';
                     }
                 }
             },
             active: {
            	 list: false,
            	 input: function (data) {
            		 if (data.record) {
            			 if (data.record.active)
            				 return '<input class="" type="checkbox" name="product.active" value="true" checked="checked"><span class="jtable-option-text-clickable">Ativo</span>';
            			 else
            				 return '<input class="" type="checkbox" name="product.active"  value="true"><span class="jtable-option-text-clickable">Ativo</span>';
                     } else {
                    	 return '<input class="" type="checkbox" name="product.active" value="true" checked="checked" /><span class="jtable-option-text-clickable">Ativo</span>';
                     }
                 }
             },
             promo: {
            	 list: false,
            	 input: function (data) {
            		 if (data.record) {
            			 if (data.record.promo)
            				 return '<input class="" type="checkbox" name="product.promo" value="true" checked="checked"><span class="jtable-option-text-clickable">Destaque</span>';
            			 else
            				 return '<input class="" type="checkbox" name="product.promo" value="true"><span class="jtable-option-text-clickable">Destaque</span>';
                     } else {
                    	 return '<input class="" type="checkbox" name="product.promo" value="true" /><span class="jtable-option-text-clickable">Destaque</span>';
                     }
                 }
             }
         },
       //Initialize validation logic when a form is created
         formCreated: function (event, data) {
             data.form.find('input[name="product.name"]').addClass('validate[required]');
             data.form.find('input[name="product.brand"]').addClass('validate[required]');
             data.form.find('input[name="product.model"]').addClass('validate[required]');
             data.form.find('input[name="product.shortDescription"]').addClass('validate[required]');
             data.form.find('input[name="product.shortDescription"]').addClass('validate[required]');
             data.form.find('input[name="product.weight"]').addClass('validate[required]');
             data.form.find('input[name="product.warranty"]').addClass('validate[required]');
             data.form.find('input[name="dimensions.width"]').addClass('validate[required,custom[number]]');
             data.form.find('input[name="dimensions.height"]').addClass('validate[required,custom[number]]');
             data.form.find('input[name="dimensions.depth"]').addClass('validate[required,custom[number]]');
             data.form.validationEngine();
         },
         //Validate form when it is being submitted
         formSubmitting: function (event, data) {
             return data.form.validationEngine('validate');
         },
         //Dispose validation logic when form is closed
         formClosed: function (event, data) {
             data.form.validationEngine('hide');
             data.form.validationEngine('detach');
         }
     });
	 
	 //Re-load records when user click 'load records' button.
     $('.btn-search').click(function (e) {
         e.preventDefault();
         $('#productContainer').jtable('load', {
             query: $('.searchBar').val(),
             status: $('.statusSelector').val()
         });
     });

     //Load all records when page is first shown
     $('.btn-search').click();
     
     $('.btn-clean').click(function (e) {
         e.preventDefault();
         $('.searchBar').val("");
         $('.statusSelector').val("true");
         $('.btn-search').click();
     });
 });