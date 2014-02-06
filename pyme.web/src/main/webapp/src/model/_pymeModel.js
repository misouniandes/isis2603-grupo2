define([], function() {
    App.Model._PymeModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'description' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._PymeList = Backbone.Collection.extend({
        model: App.Model._PymeModel,
        initialize: function() {
        }

    });
    return App.Model._PymeModel;
});