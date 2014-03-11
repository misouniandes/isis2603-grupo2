define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/documentoModel', 'controller/documentoController'], function() {
    App.Component.DocumentoComponent = App.Component._CRUDComponent.extend({
        name: 'documento',
        model: App.Model.DocumentoModel,
        listModel: App.Model.DocumentoList,
        controller : App.Controller.DocumentoController
    });
    return App.Component.DocumentoComponent;
});