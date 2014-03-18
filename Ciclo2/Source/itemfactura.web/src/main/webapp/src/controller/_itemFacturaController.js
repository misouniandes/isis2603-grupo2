define(['model/itemFacturaModel'], function(itemFacturaModel) {
    App.Controller._ItemFacturaController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#itemFactura').html());
            this.listTemplate = _.template($('#itemFacturaList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'itemFactura-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'itemFactura-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'itemFactura-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'itemFactura-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-itemFactura-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'itemFactura-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-itemFactura-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-itemFactura-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-itemFactura-create', {view: this});
                this.currentItemFacturaModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-itemFactura-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-itemFactura-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-itemFactura-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-itemFactura-list', {view: this, data: data});
                var self = this;
				if(!this.itemFacturaModelList){
                 this.itemFacturaModelList = new this.listModelClass();
				}
                this.itemFacturaModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-itemFactura-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'itemFactura-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-itemFactura-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-itemFactura-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-itemFactura-edit', {view: this, id: id, data: data});
                if (this.itemFacturaModelList) {
                    this.currentItemFacturaModel = this.itemFacturaModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-itemFactura-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentItemFacturaModel = new this.modelClass({id: id});
                    this.currentItemFacturaModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-itemFactura-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'itemFactura-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-itemFactura-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-itemFactura-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-itemFactura-delete', {view: this, id: id});
                var deleteModel;
                if (this.itemFacturaModelList) {
                    deleteModel = this.itemFacturaModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-itemFactura-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'itemFactura-delete', view: self, error: error});
                    }
                });
            }
        },
		_loadRequiredComponentsData: function(callBack) {
            var self = this;
            var listReady = _.after(1, function(){
                callBack();
            }); 
            var listDataReady = function(componentName, model){
                self[componentName] = model;
                listReady();
            };
				App.Utils.getComponentList('productoComponent',listDataReady);
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-itemFacturaForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-itemFactura-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-itemFactura-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-itemFactura-save', {view: this, model : model});
                this.currentItemFacturaModel.set(model);
                this.currentItemFacturaModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-itemFactura-save', {model: self.currentItemFacturaModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'itemFactura-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({itemFacturas: self.itemFacturaModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({itemFactura: self.currentItemFacturaModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				    ,producto: self.productoComponent
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ItemFacturaController;
});