define(['model/_documentoModel'], function() {
    App.Model.DocumentoModel = App.Model._DocumentoModel.extend({

    });

    App.Model.DocumentoList = App.Model._DocumentoList.extend({
        model: App.Model.DocumentoModel
    });

    return  App.Model.DocumentoModel;

});