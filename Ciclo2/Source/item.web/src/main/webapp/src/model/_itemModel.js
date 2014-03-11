define([], function() {
    App.Model._ItemModel = Backbone.Model.extend({
        defaults: {
 
		 'fechaLlegada' : ''
 ,  
		 'fechaExpiracion' : ''
 ,  
		 'costo' : ''
 ,  
		 'name' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
             if(name=='fechaLlegada'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaLlegada'), this);
             }
             if(name=='fechaExpiracion'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaExpiracion'), this);
             }
         return this.get(name);
        }
    });

    App.Model._ItemList = Backbone.Collection.extend({
        model: App.Model._ItemModel,
        initialize: function() {
        }

    });
    return App.Model._ItemModel;
});