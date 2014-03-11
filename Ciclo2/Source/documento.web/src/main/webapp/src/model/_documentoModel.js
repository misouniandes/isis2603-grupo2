define([], function() {
    App.Model._DocumentoModel = Backbone.Model.extend({
        defaults: {
 
		 'type' : ''
 ,  
		 'fecha' : ''
 ,  
		 'autor' : ''
 ,  
		 'descripcion' : ''
 ,  
		 'name' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
             if(name=='fecha'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fecha'), this);
             }
         return this.get(name);
        }
    });

    App.Model._DocumentoList = Backbone.Collection.extend({
        model: App.Model._DocumentoModel,
        initialize: function() {
        }

    });
    return App.Model._DocumentoModel;
});