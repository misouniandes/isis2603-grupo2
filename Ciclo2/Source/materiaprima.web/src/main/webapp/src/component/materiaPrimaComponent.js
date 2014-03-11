define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/materiaPrimaModel', 'controller/materiaPrimaController'], function() {
    App.Component.MateriaPrimaComponent = App.Component._CRUDComponent.extend({
        name: 'materiaPrima',
        model: App.Model.MateriaPrimaModel,
        listModel: App.Model.MateriaPrimaList,
        controller : App.Controller.MateriaPrimaController
    });
    return App.Component.MateriaPrimaComponent;
});