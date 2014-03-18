define(['model/_suministroModel'], function() {
    App.Model.SuministroModel = App.Model._SuministroModel.extend({

    });

    App.Model.SuministroList = App.Model._SuministroList.extend({
        model: App.Model.SuministroModel
    });

    return  App.Model.SuministroModel;

});