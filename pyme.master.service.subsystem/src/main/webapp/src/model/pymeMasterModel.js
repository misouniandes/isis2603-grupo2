define(['model/_pymeMasterModel'], function() { 
    App.Model.PymeMasterModel = App.Model._PymeMasterModel.extend({

    });

    App.Model.PymeMasterList = App.Model._PymeMasterList.extend({
        model: App.Model.PymeMasterModel
    });

    return  App.Model.PymeMasterModel;

});