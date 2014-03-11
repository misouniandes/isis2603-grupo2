define(['controller/selectionController', 'model/cacheModel', 'model/inventarioMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/inventarioComponent',
 'component/facturaComponent'
 ,
 'component/productoComponent'
 
 ],function(SelectionController, CacheModel, InventarioMasterModel, CRUDComponent, TabController, InventarioComponent,
 FacturaComponent
 ,
 ProductoComponent
 ) {
    App.Component.InventarioMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('inventarioMaster');
            var uComponent = new InventarioComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-inventario-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-inventario-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-inventario-list', function() {
                self.hideChilds();
            });
            Backbone.on('inventario-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'inventario-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-inventario-save', function(params) {
                self.model.set('inventarioEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var facturaModels = self.facturaComponent.componentController.facturaModelList;
                self.model.set('listFactura', []);
                self.model.set('createFactura', []);
                self.model.set('updateFactura', []);
                self.model.set('deleteFactura', []);
                for (var i = 0; i < facturaModels.models.length; i++) {
                    var m = facturaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createFactura').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateFactura').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < facturaModels.deletedModels.length; i++) {
                    var m = facturaModels.deletedModels[i];
                    self.model.get('deleteFactura').push(m.toJSON());
                }
                var productoModels = self.productoComponent.componentController.productoModelList;
                self.model.set('listProducto', []);
                self.model.set('createProducto', []);
                self.model.set('updateProducto', []);
                self.model.set('deleteProducto', []);
                for (var i = 0; i < productoModels.models.length; i++) {
                    var m = productoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createProducto').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateProducto').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < productoModels.deletedModels.length; i++) {
                    var m = productoModels.deletedModels[i];
                    self.model.get('deleteProducto').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'inventario-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Factura", name: "factura", enable: true},
                            ,
                            {label: "Producto", name: "producto", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.InventarioMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.facturaComponent = new FacturaComponent();
                    self.facturaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.FacturaModel), self.model.get('listFactura'));
                    self.facturaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.FacturaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.FacturaModel, App.Model.FacturaList, self.facturaModels)
                    });
                    self.facturaComponent.render(self.tabs.getTabHtmlId('factura'));
                    Backbone.on(self.facturaComponent.componentId + '-post-factura-create', function(params) {
                        params.view.currentFacturaModel.setCacheList(params.view.facturaModelList);
                    });
					self.productoComponent = new ProductoComponent();
                    self.productoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ProductoModel), self.model.get('listProducto'));
                    self.productoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ProductoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ProductoModel, App.Model.ProductoList, self.productoModels)
                    });
                    self.productoComponent.render(self.tabs.getTabHtmlId('producto'));
                    Backbone.on(self.productoComponent.componentId + '-post-producto-create', function(params) {
                        params.view.currentProductoModel.setCacheList(params.view.productoModelList);
                    });
                    self.facturaToolbarModel = self.facturaComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.facturaComponent.setToolbarModel(self.facturaToolbarModel);                    
                    self.productoToolbarModel = self.productoComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.productoComponent.setToolbarModel(self.productoToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'inventario-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.InventarioMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.InventarioMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.InventarioMasterComponent;
});