define(['model/_pymeModel'], function() {
    App.Model.PymeModel = App.Model._PymeModel.extend({
        defaults: {
            'email' : '',
            'dirContacto' : '',
        }
    });

    App.Model.PymeList = App.Model._PymeList.extend({
        model: App.Model.PymeModel
    });

    return  App.Model.PymeModel;

});