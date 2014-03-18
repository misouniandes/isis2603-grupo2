define(['model/_necesidadModel'], function() {
    App.Model.NecesidadModel = App.Model._NecesidadModel.extend({

    });

    App.Model.NecesidadList = App.Model._NecesidadList.extend({
        model: App.Model.NecesidadModel
    });

    return  App.Model.NecesidadModel;

});