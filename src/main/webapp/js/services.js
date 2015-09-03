/**
 * Created by StepLuch on 02.09.15.
 */

app.factory('UserFactory', ['$resource', function ($resource) {
    var serviceObject = {loginUser: function (user_id){
        var LoginResource = $resource("/PhotoUploader/rest/user", {},{
            get:{method: "GET", params: {user_id: user_id}}
        });
        return LoginResource.get().$promise; //this promise will be fulfilled when the response is retrieved for this call
    }};
    return serviceObject;
}]);

app.factory('LoginFactory', ['$resource', function ($resource) {
    var serviceObject = {loginUser: function (username, password){
        console.info(username);
        console.info(password);
        var LoginResource = $resource("/PhotoUploader/rest/login", {},{
            save:{method: "POST", params: {username: username, password: password}}
        });
        return LoginResource.save().$promise; //this promise will be fulfilled when the response is retrieved for this call
    }};
    return serviceObject;
}]);