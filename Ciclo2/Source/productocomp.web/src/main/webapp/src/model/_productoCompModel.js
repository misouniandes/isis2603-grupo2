define([], function() {
    App.Model._ProductoCompModel = Backbone.Model.extend({
        defaults: {
 
		 'terminado' : ''
 ,  
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

    App.Model._ProductoCompList = Backbone.Collection.extend({
        model: App.Model._ProductoCompModel,
        initialize: function() {
        }

    });
    return App.Model._ProductoCompModel;
});