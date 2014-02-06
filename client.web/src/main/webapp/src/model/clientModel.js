define(['model/_clientModel'], function() {
    App.Model.ClientModel = App.Model._ClientModel.extend({

    });

    App.Model.ClientList = App.Model._ClientList.extend({
        model: App.Model.ClientModel
    });

    return  App.Model.ClientModel;

});