define(['controller/selectionController', 'model/cacheModel', 'model/productoCompMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/productoCompComponent',
 'component/necesidadComponent'
 
 ],function(SelectionController, CacheModel, ProductoCompMasterModel, CRUDComponent, TabController, ProductoCompComponent,
 NecesidadComponent
 ) {
    App.Component.ProductoCompMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('productoCompMaster');
            var uComponent = new ProductoCompComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-productoComp-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-productoComp-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-productoComp-list', function() {
                self.hideChilds();
            });
            Backbone.on('productoComp-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'productoComp-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-productoComp-save', function(params) {
                self.model.set('productoCompEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var necesidadModels = self.necesidadComponent.componentController.necesidadModelList;
                self.model.set('listNecesidad', []);
                self.model.set('createNecesidad', []);
                self.model.set('updateNecesidad', []);
                self.model.set('deleteNecesidad', []);
                for (var i = 0; i < necesidadModels.models.length; i++) {
                    var m = necesidadModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createNecesidad').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateNecesidad').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < necesidadModels.deletedModels.length; i++) {
                    var m = necesidadModels.deletedModels[i];
                    self.model.get('deleteNecesidad').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'productoComp-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Necesidad", name: "necesidad", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.ProductoCompMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.necesidadComponent = new NecesidadComponent();
                    self.necesidadModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.NecesidadModel), self.model.get('listNecesidad'));
                    self.necesidadComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.NecesidadModel),
                        listModelClass: App.Utils.createCacheList(App.Model.NecesidadModel, App.Model.NecesidadList, self.necesidadModels)
                    });
                    self.necesidadComponent.render(self.tabs.getTabHtmlId('necesidad'));
                    Backbone.on(self.necesidadComponent.componentId + '-post-necesidad-create', function(params) {
                        params.view.currentNecesidadModel.setCacheList(params.view.necesidadModelList);
                    });
                    self.necesidadToolbarModel = self.necesidadComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.necesidadComponent.setToolbarModel(self.necesidadToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'productoComp-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.ProductoCompMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.ProductoCompMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.ProductoCompMasterComponent;
});