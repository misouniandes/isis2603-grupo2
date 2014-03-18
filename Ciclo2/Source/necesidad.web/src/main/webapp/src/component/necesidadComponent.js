define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/necesidadModel', 'controller/necesidadController'], function() {
    App.Component.NecesidadComponent = App.Component._CRUDComponent.extend({
        name: 'necesidad',
        model: App.Model.NecesidadModel,
        listModel: App.Model.NecesidadList,
        controller : App.Controller.NecesidadController
    });
    return App.Component.NecesidadComponent;
});