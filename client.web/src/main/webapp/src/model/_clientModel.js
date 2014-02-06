define([], function() {
    App.Model._ClientModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'cc' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._ClientList = Backbone.Collection.extend({
        model: App.Model._ClientModel,
        initialize: function() {
        }

    });
    return App.Model._ClientModel;
});