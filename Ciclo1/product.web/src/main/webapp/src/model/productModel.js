define(['model/_productModel'], function() {
    App.Model.ProductModel = App.Model._ProductModel.extend({
          initialize:function(parameters){
            this.on('invalid',function(model,error){
                Backbone.trigger('product-model-error', {error: error});
            });
        },
        validate: function(attrs, options) {
            var img=new String(attrs.imagen);
      
            if (isNaN(attrs.value)||attrs.value<0) {
                return "El valor tiene que ser un numero valido";
            }
            else if (attrs.name == "")
                return "El nombre no puede ser vacio";
            else if(img.length<=4 || (img.indexOf(".jpg")!=(img.length-4) && (img.indexOf(".png")!=img.length-4) && (img.indexOf(".gif")!=img.length-4)))
                return "La imagen debe ser .jpg, .gif o .png";
        }
    });

    App.Model.ProductList = App.Model._ProductList.extend({
        model: App.Model.ProductModel
    });

    return  App.Model.ProductModel;

});