define(['model/materiaPrimaModel'], function(materiaPrimaModel) {
    App.Controller._MateriaPrimaController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#materiaPrima').html());
            this.listTemplate = _.template($('#materiaPrimaList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'materiaPrima-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'materiaPrima-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'materiaPrima-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'materiaPrima-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-materiaPrima-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'materiaPrima-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-materiaPrima-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-materiaPrima-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-materiaPrima-create', {view: this});
                this.currentMateriaPrimaModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-materiaPrima-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-materiaPrima-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-materiaPrima-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-materiaPrima-list', {view: this, data: data});
                var self = this;
				if(!this.materiaPrimaModelList){
                 this.materiaPrimaModelList = new this.listModelClass();
				}
                this.materiaPrimaModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-materiaPrima-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'materiaPrima-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-materiaPrima-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-materiaPrima-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-materiaPrima-edit', {view: this, id: id, data: data});
                if (this.materiaPrimaModelList) {
                    this.currentMateriaPrimaModel = this.materiaPrimaModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-materiaPrima-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentMateriaPrimaModel = new this.modelClass({id: id});
                    this.currentMateriaPrimaModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-materiaPrima-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'materiaPrima-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-materiaPrima-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-materiaPrima-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-materiaPrima-delete', {view: this, id: id});
                var deleteModel;
                if (this.materiaPrimaModelList) {
                    deleteModel = this.materiaPrimaModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-materiaPrima-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'materiaPrima-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-materiaPrimaForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-materiaPrima-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-materiaPrima-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-materiaPrima-save', {view: this, model : model});
                this.currentMateriaPrimaModel.set(model);
                this.currentMateriaPrimaModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-materiaPrima-save', {model: self.currentMateriaPrimaModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'materiaPrima-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({materiaPrimas: self.materiaPrimaModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({materiaPrima: self.currentMateriaPrimaModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._MateriaPrimaController;
});