define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/productModel', 'controller/productController'], function() {
    App.Component.ProductComponent = App.Component._CRUDComponent.extend({
        name: 'product',
        model: App.Model.ProductModel,
        listModel: App.Model.ProductList,
        controller : App.Controller.ProductController
    });
    return App.Component.ProductComponent;
});