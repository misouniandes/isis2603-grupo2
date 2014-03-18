define(['model/necesidadModel'], function(necesidadModel) {
    App.Controller._NecesidadController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#necesidad').html());
            this.listTemplate = _.template($('#necesidadList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'necesidad-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'necesidad-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'necesidad-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'necesidad-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-necesidad-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'necesidad-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-necesidad-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-necesidad-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-necesidad-create', {view: this});
                this.currentNecesidadModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-necesidad-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-necesidad-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-necesidad-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-necesidad-list', {view: this, data: data});
                var self = this;
				if(!this.necesidadModelList){
                 this.necesidadModelList = new this.listModelClass();
				}
                this.necesidadModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-necesidad-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'necesidad-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-necesidad-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-necesidad-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-necesidad-edit', {view: this, id: id, data: data});
                if (this.necesidadModelList) {
                    this.currentNecesidadModel = this.necesidadModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-necesidad-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentNecesidadModel = new this.modelClass({id: id});
                    this.currentNecesidadModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-necesidad-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'necesidad-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-necesidad-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-necesidad-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-necesidad-delete', {view: this, id: id});
                var deleteModel;
                if (this.necesidadModelList) {
                    deleteModel = this.necesidadModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-necesidad-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'necesidad-delete', view: self, error: error});
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
            var model = $('#' + this.componentId + '-necesidadForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-necesidad-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-necesidad-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-necesidad-save', {view: this, model : model});
                this.currentNecesidadModel.set(model);
                this.currentNecesidadModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-necesidad-save', {model: self.currentNecesidadModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'necesidad-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({necesidads: self.necesidadModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({necesidad: self.currentNecesidadModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				    ,producto: self.productoComponent
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._NecesidadController;
});