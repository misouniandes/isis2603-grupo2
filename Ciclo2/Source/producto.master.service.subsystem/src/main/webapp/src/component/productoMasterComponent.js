define(['controller/selectionController', 'model/cacheModel', 'model/productoMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/productoComponent',
 'component/documentoComponent'
 ,
 'component/documentoComponent'
 
 ],function(SelectionController, CacheModel, ProductoMasterModel, CRUDComponent, TabController, ProductoComponent,
 DocumentoComponent
 ,
 DocumentoComponent
 ) {
    App.Component.ProductoMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('productoMaster');
            var uComponent = new ProductoComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-producto-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-producto-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-producto-list', function() {
                self.hideChilds();
            });
            Backbone.on('producto-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'producto-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-producto-save', function(params) {
                self.model.set('productoEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var documentoModels = self.documentoComponent.componentController.documentoModelList;
                self.model.set('listDocumento', []);
                self.model.set('createDocumento', []);
                self.model.set('updateDocumento', []);
                self.model.set('deleteDocumento', []);
                for (var i = 0; i < documentoModels.models.length; i++) {
                    var m = documentoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createDocumento').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateDocumento').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < documentoModels.deletedModels.length; i++) {
                    var m = documentoModels.deletedModels[i];
                    self.model.get('deleteDocumento').push(m.toJSON());
                }
                var documentoModels = self.documentoComponent.componentController.documentoModelList;
                self.model.set('listDocumento', []);
                self.model.set('createDocumento', []);
                self.model.set('updateDocumento', []);
                self.model.set('deleteDocumento', []);
                for (var i = 0; i < documentoModels.models.length; i++) {
                    var m = documentoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createDocumento').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateDocumento').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < documentoModels.deletedModels.length; i++) {
                    var m = documentoModels.deletedModels[i];
                    self.model.get('deleteDocumento').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Documento", name: "documento", enable: true},
                            ,
                            {label: "Documento", name: "documento", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.ProductoMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.documentoComponent = new DocumentoComponent();
                    self.documentoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.DocumentoModel), self.model.get('listDocumento'));
                    self.documentoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.DocumentoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.DocumentoModel, App.Model.DocumentoList, self.documentoModels)
                    });
                    self.documentoComponent.render(self.tabs.getTabHtmlId('documento'));
                    Backbone.on(self.documentoComponent.componentId + '-post-documento-create', function(params) {
                        params.view.currentDocumentoModel.setCacheList(params.view.documentoModelList);
                    });
					self.documentoComponent = new DocumentoComponent();
                    self.documentoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.DocumentoModel), self.model.get('listDocumento'));
                    self.documentoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.DocumentoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.DocumentoModel, App.Model.DocumentoList, self.documentoModels)
                    });
                    self.documentoComponent.render(self.tabs.getTabHtmlId('documento'));
                    Backbone.on(self.documentoComponent.componentId + '-post-documento-create', function(params) {
                        params.view.currentDocumentoModel.setCacheList(params.view.documentoModelList);
                    });
                    self.documentoToolbarModel = self.documentoComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.documentoComponent.setToolbarModel(self.documentoToolbarModel);                    
                    self.documentoToolbarModel = self.documentoComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.documentoComponent.setToolbarModel(self.documentoToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.ProductoMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.ProductoMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.ProductoMasterComponent;
});