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
            var query = $("#query").val();
            //TODO 
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