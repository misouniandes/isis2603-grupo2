define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/suministroModel', 'controller/suministroController'], function() {
    App.Component.SuministroComponent = App.Component._CRUDComponent.extend({
        name: 'suministro',
        model: App.Model.SuministroModel,
        listModel: App.Model.SuministroList,
        controller : App.Controller.SuministroController
    });
    return App.Component.SuministroComponent;
});