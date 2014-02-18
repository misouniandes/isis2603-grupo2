define([], function() {
    App.Delegate._PymeDelegate = App.Delegate.PymeDelegate || {
         search: function(user, callback, callbackError) {
            console.log('User Search: ');
            $.ajax({
                url: '/user.service.subsystem.web/webresources/User/search',
                type: 'POST',
                data: JSON.stringify(user),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        }
    };
});