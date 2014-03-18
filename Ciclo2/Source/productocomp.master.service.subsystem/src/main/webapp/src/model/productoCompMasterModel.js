define(['model/_productoCompMasterModel'], function() { 
    App.Model.ProductoCompMasterModel = App.Model._ProductoCompMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('productoComp-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.ProductoCompModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.productoCompEntity,options);
            }
        }
    });

    App.Model.ProductoCompMasterList = App.Model._ProductoCompMasterList.extend({
        model: App.Model.ProductoCompMasterModel
    });

    return  App.Model.ProductoCompMasterModel;

});