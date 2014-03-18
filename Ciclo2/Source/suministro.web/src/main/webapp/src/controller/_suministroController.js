define(['model/suministroModel'], function(suministroModel) {
    App.Controller._SuministroController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#suministro').html());
            this.listTemplate = _.template($('#suministroList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'suministro-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'suministro-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'suministro-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'suministro-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-suministro-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'suministro-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-suministro-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-suministro-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-suministro-create', {view: this});
                this.currentSuministroModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-suministro-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-suministro-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-suministro-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-suministro-list', {view: this, data: data});
                var self = this;
				if(!this.suministroModelList){
                 this.suministroModelList = new this.listModelClass();
				}
                this.suministroModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-suministro-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'suministro-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-suministro-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-suministro-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-suministro-edit', {view: this, id: id, data: data});
                if (this.suministroModelList) {
                    this.currentSuministroModel = this.suministroModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-suministro-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentSuministroModel = new this.modelClass({id: id});
                    this.currentSuministroModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-suministro-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'suministro-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-suministro-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-suministro-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-suministro-delete', {view: this, id: id});
                var deleteModel;
                if (this.suministroModelList) {
                    deleteModel = this.suministroModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-suministro-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'suministro-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-suministroForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-suministro-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-suministro-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-suministro-save', {view: this, model : model});
                this.currentSuministroModel.set(model);
                this.currentSuministroModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-suministro-save', {model: self.currentSuministroModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'suministro-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({suministros: self.suministroModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({suministro: self.currentSuministroModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._SuministroController;
});