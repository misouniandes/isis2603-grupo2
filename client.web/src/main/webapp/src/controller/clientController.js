define(['controller/_clientController'], function() {
    App.Controller.ClientController = App.Controller._ClientController.extend({
        initialize: function(params) {
            //----------RF 5----------------------------------------------
            this.searchTemplate = _.template($('#client-search').html());
           
            Backbone.on(this.componentId + '-' + 'client-search', function(params) {
                self.search(params);
            });
            //------------------------------------------------------------
        },
        
        //---------------------------RF5----------------------------
        search: function(params) {
            var self = this;
            var model = $('#' + this.componentId + '-searchForm').serializeObject();
            this.currentClientModel.set(model);
            App.Delegate.ClientDelegate.search(self.currentClientModel, function(data) {
                self.clientModelList=new App.Model.ClientList();
                _.each(data,function(d){
                    var model=new App.Model.ClientModel(d);
                    self.clientModelList.models.push(model);
                });
                self._renderSearch(params);
            }, function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'client-search', view: self, id: '', data: data, error: 'Error in client search'});
            });
        },
        //-----------------------------------------------------------


        //-----------------------RF5---------------------------
        _renderSearch: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.searchTemplate({componentId: self.componentId
                
                                }));
                self.$el.slideDown("fast");
            });
        }
        //------------------------------------------------------
    });
    return App.Controller.ClientController;
}); 