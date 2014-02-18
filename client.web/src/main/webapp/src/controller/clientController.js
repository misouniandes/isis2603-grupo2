define(['controller/_clientController'], function() {
    App.Controller.ClientController = App.Controller._ClientController.extend({
        postInit: function(options){
            var self = this;
            Backbone.on('client-model-error', function(params) {
                var error = params.error;
                Backbone.trigger(self.componentId + '-' + 'error', 
                         {event: 'client-model', view: self, error:{ responseText: error}});
            });
        } 
    });
    return App.Controller.ClientController;
}); 