define(['delegate/_ClientDelegate'], function() {
    App.Delegate.ClientDelegate = App.Delegate._ClientDelegate.extend({
       search: function(client, callback, callbackError) {
            console.log('Client Search: ');
            $.ajax({
                url: '/client.service.subsystem.web/webresources/Client/search',
                type: 'POST',
                data: JSON.stringify(client),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        } 
    });
});