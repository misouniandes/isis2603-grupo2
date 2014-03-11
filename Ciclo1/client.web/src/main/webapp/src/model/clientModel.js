define(['model/_clientModel'], function() {
    App.Model.ClientModel = App.Model._ClientModel.extend({
        initialize:function(parameters){
            this.on('invalid',function(model,error){
                Backbone.trigger('client-model-error', {error: error});
            });
        },
 
        validate: function(attrs, options) {
            var ced= new String(attrs.cc);
            var pass = new String (attrs.password);
            var em = new String (attrs.email);
            
            if(em.indexOf("@")==-1 || em.split("@").length!=2 || em.split("@")[1].indexOf(".")==-1){
                return "El email no es valido";
            //    return em;
            }
            if(isNaN(attrs.cc)||attrs.cc==""){
                return "La cedula no es valida";}
            if(pass.length<=6 || (pass.indexOf('0')==-1 && pass.indexOf('1')==-1 && pass.indexOf('2')==-1 && pass.indexOf('3')==-1 
                    && pass.indexOf('4')==-1 && pass.indexOf('5')==-1 && pass.indexOf('6')==-1 && pass.indexOf('7')==-1 
                    && pass.indexOf('8')==-1 && pass.indexOf('9')==-1 )){
                return "La contrasenia debe tener por lo menos 6 caracteres y un numero";}
        }
    });

    App.Model.ClientList = App.Model._ClientList.extend({
        model: App.Model.ClientModel
    });

    return  App.Model.ClientModel;

});