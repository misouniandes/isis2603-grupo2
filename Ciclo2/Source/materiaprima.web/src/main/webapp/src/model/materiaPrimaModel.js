define(['model/_materiaPrimaModel'], function() {
    App.Model.MateriaPrimaModel = App.Model._MateriaPrimaModel.extend({

    });

    App.Model.MateriaPrimaList = App.Model._MateriaPrimaList.extend({
        model: App.Model.MateriaPrimaModel
    });

    return  App.Model.MateriaPrimaModel;

});