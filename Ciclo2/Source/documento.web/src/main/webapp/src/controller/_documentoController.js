define(['model/documentoModel'], function(documentoModel) {
    App.Controller._DocumentoController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#documento').html());
            this.listTemplate = _.template($('#documentoList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'documento-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'documento-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'documento-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'documento-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-documento-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'documento-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-documento-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-documento-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-documento-create', {view: this});
                this.currentDocumentoModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-documento-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-documento-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-documento-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-documento-list', {view: this, data: data});
                var self = this;
				if(!this.documentoModelList){
                 this.documentoModelList = new this.listModelClass();
				}
                this.documentoModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-documento-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'documento-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-documento-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-documento-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-documento-edit', {view: this, id: id, data: data});
                if (this.documentoModelList) {
                    this.currentDocumentoModel = this.documentoModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-documento-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentDocumentoModel = new this.modelClass({id: id});
                    this.currentDocumentoModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-documento-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'documento-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-documento-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-documento-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-documento-delete', {view: this, id: id});
                var deleteModel;
                if (this.documentoModelList) {
                    deleteModel = this.documentoModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-documento-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'documento-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-documentoForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-documento-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-documento-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-documento-save', {view: this, model : model});
                this.currentDocumentoModel.set(model);
                this.currentDocumentoModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-documento-save', {model: self.currentDocumentoModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'documento-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({documentos: self.documentoModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({documento: self.currentDocumentoModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._DocumentoController;
});