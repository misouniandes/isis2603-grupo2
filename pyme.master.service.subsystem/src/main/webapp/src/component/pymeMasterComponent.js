define(['controller/selectionController', 'model/cacheModel', 'model/pymeMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/pymeComponent',
 'component/clientComponent'
 ,
 'component/productComponent'
 
 ],function(SelectionController, CacheModel, PymeMasterModel, CRUDComponent, TabController, PymeComponent,
 ClientComponent
 ,
 ProductComponent
 ) {
    App.Component.PymeMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('pymeMaster');
            var uComponent = new PymeComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-pyme-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-pyme-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-pyme-list', function() {
                self.hideChilds();
            });
            Backbone.on(uComponent.componentId + '-instead-pyme-save', function(params) {
                self.model.set('pymeEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var clientModels = self.clientComponent.componentController.clientModelList;
                self.model.set('listClient', []);
                self.model.set('createClient', []);
                self.model.set('updateClient', []);
                self.model.set('deleteClient', []);
                for (var i = 0; i < clientModels.models.length; i++) {
                    var m = clientModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createClient').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateClient').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < clientModels.deletedModels.length; i++) {
                    var m = clientModels.deletedModels[i];
                    self.model.get('deleteClient').push(m.toJSON());
                }
                var productModels = self.productComponent.componentController.productModelList;
                self.model.set('listProduct', []);
                self.model.set('createProduct', []);
                self.model.set('updateProduct', []);
                self.model.set('deleteProduct', []);
                for (var i = 0; i < productModels.models.length; i++) {
                    var m = productModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createProduct').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateProduct').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < productModels.deletedModels.length; i++) {
                    var m = productModels.deletedModels[i];
                    self.model.get('deleteProduct').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'pyme-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Client", name: "client", enable: true},
                            ,
                            {label: "Product", name: "product", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.PymeMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.clientComponent = new ClientComponent();
                    self.clientModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ClientModel), self.model.get('listClient'));
                    self.clientComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ClientModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ClientModel, App.Model.ClientList, self.clientModels)
                    });
                    self.clientComponent.render(self.tabs.getTabHtmlId('client'));
                    Backbone.on(self.clientComponent.componentId + '-post-client-create', function(params) {
                        params.view.currentClientModel.setCacheList(params.view.clientModelList);
                    });
					self.productComponent = new ProductComponent();
                    self.productModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ProductModel), self.model.get('listProduct'));
                    self.productComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ProductModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ProductModel, App.Model.ProductList, self.productModels)
                    });
                    self.productComponent.render(self.tabs.getTabHtmlId('product'));
                    Backbone.on(self.productComponent.componentId + '-post-product-create', function(params) {
                        params.view.currentProductModel.setCacheList(params.view.productModelList);
                    });
                    self.clientToolbarModel = self.clientComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.clientComponent.setToolbarModel(self.clientToolbarModel);                    
                    self.productToolbarModel = self.productComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.productComponent.setToolbarModel(self.productToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'pyme-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.PymeMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.PymeMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.PymeMasterComponent;
});