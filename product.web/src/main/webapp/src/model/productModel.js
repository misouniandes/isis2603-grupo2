define(['model/_productModel'], function() {
    App.Model.ProductModel = App.Model._ProductModel.extend({

    });

    App.Model.ProductList = App.Model._ProductList.extend({
        model: App.Model.ProductModel
    });

    return  App.Model.ProductModel;

});