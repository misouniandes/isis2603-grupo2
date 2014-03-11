define([], function() {
    App.Model._MateriaPrimaModel = Backbone.Model.extend({
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

    App.Model._MateriaPrimaList = Backbone.Collection.extend({
        model: App.Model._MateriaPrimaModel,
        initialize: function() {
        }

    });
    return App.Model._MateriaPrimaModel;
});