define([], function() {
    App.Model._SuministroModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'cantidadDisp' : ''
 ,  
		 'canitdadEnProc' : ''
 ,  
		 'tiempoEspera' : ''
 ,  
		 'costoPromedio' : ''
 ,  
		 'lugar' : ''
 ,  
		 'cantidadMin' : ''
 ,  
		 'cantidadMax' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._SuministroList = Backbone.Collection.extend({
        model: App.Model._SuministroModel,
        initialize: function() {
        }

    });
    return App.Model._SuministroModel;
});