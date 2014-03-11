define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/itemFacturaModel', 'controller/itemFacturaController'], function() {
    App.Component.ItemFacturaComponent = App.Component._CRUDComponent.extend({
        name: 'itemFactura',
        model: App.Model.ItemFacturaModel,
        listModel: App.Model.ItemFacturaList,
        controller : App.Controller.ItemFacturaController
    });
    return App.Component.ItemFacturaComponent;
});