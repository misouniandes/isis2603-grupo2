define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/clientModel', 'controller/clientController'], function() {
    App.Component.ClientComponent = App.Component._CRUDComponent.extend({
        name: 'client',
        model: App.Model.ClientModel,
        listModel: App.Model.ClientList,
        controller : App.Controller.ClientController
    });
    return App.Component.ClientComponent;
});