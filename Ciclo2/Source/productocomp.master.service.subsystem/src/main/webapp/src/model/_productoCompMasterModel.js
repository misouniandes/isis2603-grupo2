define([], function() {
    App.Model._ProductoCompMasterModel = Backbone.Model.extend({
     
    });

    App.Model._ProductoCompMasterList = Backbone.Collection.extend({
        model: App.Model._ProductoCompMasterModel,
        initialize: function() {
        }

    });
    return App.Model._ProductoCompMasterModel;
    
});