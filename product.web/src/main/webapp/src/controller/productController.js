define(['controller/_productController'], function() {
    App.Controller.ProductController = App.Controller._ProductController.extend({
      initialize: function(params) {
                
        //-------------------------RF5---------------------------------
        this.searchTemplate = _.template($('#product-search').html());
        //-------------------------------------------------------------
        
         //-------------------------RF5---------------------------
        Backbone.on(this.componentId + '-' + 'product-search', function(params) {
             self.search(params);
        });
            //--------------------------------------------------------
       },
       
       search: function(params) {
            var query = $("#query").val();
            //TODO 
        },
        
       viewImage: function(params) {
            var id = params.id;
            var self = this;
            var data = params.data;
            
        },
        
        _renderSearch: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.searchTemplate({componentId: self.componentId
                
                                }));
                self.$el.slideDown("fast");
            });
        },
        
        _renderImage: function() {
            //TODO
        }
        
    });
    return App.Controller.ProductController;
}); 