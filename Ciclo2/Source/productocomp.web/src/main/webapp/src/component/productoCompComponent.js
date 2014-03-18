define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/productoCompModel', 'controller/productoCompController'], function() {
    App.Component.ProductoCompComponent = App.Component._CRUDComponent.extend({
        name: 'productoComp',
        model: App.Model.ProductoCompModel,
        listModel: App.Model.ProductoCompList,
        controller : App.Controller.ProductoCompController
    });
    return App.Component.ProductoCompComponent;
});