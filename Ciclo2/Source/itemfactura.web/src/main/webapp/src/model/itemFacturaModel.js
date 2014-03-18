define(['model/_itemFacturaModel'], function() {
    App.Model.ItemFacturaModel = App.Model._ItemFacturaModel.extend({

    });

    App.Model.ItemFacturaList = App.Model._ItemFacturaList.extend({
        model: App.Model.ItemFacturaModel
    });

    return  App.Model.ItemFacturaModel;

});