define(['model/productoCompModel'], function(productoCompModel) {
    App.Controller._ProductoCompController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#productoComp').html());
            this.listTemplate = _.template($('#productoCompList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'productoComp-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'productoComp-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'productoComp-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'productoComp-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-productoComp-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'productoComp-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-productoComp-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-productoComp-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-productoComp-create', {view: this});
                this.currentProductoCompModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-productoComp-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-productoComp-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-productoComp-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-productoComp-list', {view: this, data: data});
                var self = this;
				if(!this.productoCompModelList){
                 this.productoCompModelList = new this.listModelClass();
				}
                this.productoCompModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-productoComp-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'productoComp-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-productoComp-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-productoComp-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-productoComp-edit', {view: this, id: id, data: data});
                if (this.productoCompModelList) {
                    this.currentProductoCompModel = this.productoCompModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-productoComp-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentProductoCompModel = new this.modelClass({id: id});
                    this.currentProductoCompModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-productoComp-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'productoComp-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-productoComp-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-productoComp-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-productoComp-delete', {view: this, id: id});
                var deleteModel;
                if (this.productoCompModelList) {
                    deleteModel = this.productoCompModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-productoComp-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'productoComp-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-productoCompForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-productoComp-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-productoComp-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-productoComp-save', {view: this, model : model});
                this.currentProductoCompModel.set(model);
                this.currentProductoCompModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-productoComp-save', {model: self.currentProductoCompModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'productoComp-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({productoComps: self.productoCompModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({productoComp: self.currentProductoCompModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ProductoCompController;
});