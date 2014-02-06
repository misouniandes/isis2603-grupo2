define(['model/clientModel'], function(clientModel) {
    App.Controller._ClientController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#client').html());
            this.listTemplate = _.template($('#clientList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'client-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'client-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'client-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'client-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-client-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'client-save', function(params) {
                self.save(params);
            });
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-client-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-client-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-client-create', {view: this});
                this.currentClientModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-client-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-client-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-client-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-client-list', {view: this, data: data});
                var self = this;
				if(!this.clientModelList){
                 this.clientModelList = new this.listModelClass();
				}
                this.clientModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-client-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'client-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-client-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-client-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-client-edit', {view: this, id: id, data: data});
                if (this.clientModelList) {
                    this.currentClientModel = this.clientModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-client-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentClientModel = new this.modelClass({id: id});
                    this.currentClientModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-client-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'client-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-client-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-client-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-client-delete', {view: this, id: id});
                var deleteModel;
                if (this.clientModelList) {
                    deleteModel = this.clientModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-client-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'client-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-clientForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-client-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-client-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-client-save', {view: this, model : model});
                this.currentClientModel.set(model);
                this.currentClientModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-client-save', {model: self.currentClientModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'client-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({clients: self.clientModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({client: self.currentClientModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ClientController;
});