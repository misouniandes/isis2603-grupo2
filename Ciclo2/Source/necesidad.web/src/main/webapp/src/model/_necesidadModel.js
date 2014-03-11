define([], function() {
    App.Model._NecesidadModel = Backbone.Model.extend({
        defaults: {
 
		 'cantidad' : ''
 ,  
		 'name' : ''
 ,  
		 'productoId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
			 if(name=='productoId'){  
                 var value = App.Utils.getModelFromCache('productoComponent',this.get('productoId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model._NecesidadList = Backbone.Collection.extend({
        model: App.Model._NecesidadModel,
        initialize: function() {
        }

    });
    return App.Model._NecesidadModel;
});