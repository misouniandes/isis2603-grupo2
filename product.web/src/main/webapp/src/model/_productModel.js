define([], function() {
    App.Model._ProductModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'value' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._ProductList = Backbone.Collection.extend({
        model: App.Model._ProductModel,
        initialize: function() {
        }

    });
    return App.Model._ProductModel;
});