define(['model/_pymeModel'], function() {
    App.Model.PymeModel = App.Model._PymeModel.extend({
            initialize:function(parameters){
            this.on('invalid',function(model,error){
                Backbone.trigger('pyme-model-error', {error: error});
            });
        },
 
        validate: function(attrs, options) {
            var em = new String(attrs.email);
            if (attrs.name == '') {
                return "El nombre no puede ser nulo";
            }
            if(em.indexOf("@")==-1 || em.split("@").length!=2 || em.split("@")[1].indexOf(".")==-1)
                return "El email no es valido";
        }
    });

    App.Model.PymeList = App.Model._PymeList.extend({
        model: App.Model.PymeModel
    });

    return  App.Model.PymeModel;

});