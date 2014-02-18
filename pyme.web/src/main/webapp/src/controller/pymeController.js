define(['controller/_pymeController'], function() {
    App.Controller.PymeController = App.Controller._PymeController.extend({
           postInit: function(options){
            var self = this;
            Backbone.on('pyme-model-error', function(params) {
                var error = params.error;
                Backbone.trigger(self.componentId + '-' + 'error', 
                         {event: 'pyme-model', view: self, error:{ responseText: error}});
            });
        } 
    });
    return App.Controller.PymeController;
}); 