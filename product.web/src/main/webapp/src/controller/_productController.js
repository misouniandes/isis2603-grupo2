define(['model/productModel'], function(productModel) {
    App.Controller._ProductController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#product').html());
            this.listTemplate = _.template($('#productList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'product-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'product-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'product-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'product-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-product-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'product-save', function(params) {
                self.save(params);
            });
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-product-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-product-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-product-create', {view: this});
                this.currentProductModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-product-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-product-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-product-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-product-list', {view: this, data: data});
                var self = this;
				if(!this.productModelList){
                 this.productModelList = new this.listModelClass();
				}
                this.productModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-product-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'product-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-product-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-product-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-product-edit', {view: this, id: id, data: data});
                if (this.productModelList) {
                    this.currentProductModel = this.productModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-product-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentProductModel = new this.modelClass({id: id});
                    this.currentProductModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-product-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'product-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-product-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-product-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-product-delete', {view: this, id: id});
                var deleteModel;
                if (this.productModelList) {
                    deleteModel = this.productModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-product-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'product-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-productForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-product-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-product-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-product-save', {view: this, model : model});
                this.currentProductModel.set(model);
                this.currentProductModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-product-save', {model: self.currentProductModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'product-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({products: self.productModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({product: self.currentProductModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ProductController;
});