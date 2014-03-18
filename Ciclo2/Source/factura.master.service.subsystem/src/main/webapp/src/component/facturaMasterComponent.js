define(['controller/selectionController', 'model/cacheModel', 'model/facturaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/facturaComponent',
 'component/itemFacturaComponent'
 
 ],function(SelectionController, CacheModel, FacturaMasterModel, CRUDComponent, TabController, FacturaComponent,
 ItemFacturaComponent
 ) {
    App.Component.FacturaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('facturaMaster');
            var uComponent = new FacturaComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-factura-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-factura-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-factura-list', function() {
                self.hideChilds();
            });
            Backbone.on('factura-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'factura-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-factura-save', function(params) {
                self.model.set('facturaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var itemFacturaModels = self.itemFacturaComponent.componentController.itemFacturaModelList;
                self.model.set('listItemFactura', []);
                self.model.set('createItemFactura', []);
                self.model.set('updateItemFactura', []);
                self.model.set('deleteItemFactura', []);
                for (var i = 0; i < itemFacturaModels.models.length; i++) {
                    var m = itemFacturaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createItemFactura').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateItemFactura').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < itemFacturaModels.deletedModels.length; i++) {
                    var m = itemFacturaModels.deletedModels[i];
                    self.model.get('deleteItemFactura').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "ItemFactura", name: "itemFactura", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.FacturaMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.itemFacturaComponent = new ItemFacturaComponent();
                    self.itemFacturaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ItemFacturaModel), self.model.get('listItemFactura'));
                    self.itemFacturaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ItemFacturaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ItemFacturaModel, App.Model.ItemFacturaList, self.itemFacturaModels)
                    });
                    self.itemFacturaComponent.render(self.tabs.getTabHtmlId('itemFactura'));
                    Backbone.on(self.itemFacturaComponent.componentId + '-post-itemFactura-create', function(params) {
                        params.view.currentItemFacturaModel.setCacheList(params.view.itemFacturaModelList);
                    });
                    self.itemFacturaToolbarModel = self.itemFacturaComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.itemFacturaComponent.setToolbarModel(self.itemFacturaToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.FacturaMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.FacturaMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.FacturaMasterComponent;
});