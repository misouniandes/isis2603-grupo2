define(['model/_productoCompModel'], function() {
    App.Model.ProductoCompModel = App.Model._ProductoCompModel.extend({

    });

    App.Model.ProductoCompList = App.Model._ProductoCompList.extend({
        model: App.Model.ProductoCompModel
    });

    return  App.Model.ProductoCompModel;

});