/**
 * Created by StepLuch on 02.09.15.
 */

app.factory('UserFactory', ['$resource', function ($resource) {
    var serviceObject = {userInfo: function (user_id){
        var LoginResource = $resource("/PhotoUploader/rest/user/:user_id", {user_id:user_id},{
            get:{method: "GET"}
        });
        return LoginResource.get().$promise; //this promise will be fulfilled when the response is retrieved for this call
    }};
    return serviceObject;
}]);

app.factory('UserListFactory', ['$resource', function ($resource) {
    var serviceObject = {userList: function (user_id){
        var UserListResource = $resource("/PhotoUploader/rest/user/userList", {},{
            'query':{method: "GET", params:{user_id:user_id}, isArray:true}
        });
        return UserListResource.query().$promise; //this promise will be fulfilled when the response is retrieved for this call
    }};
    return serviceObject;
}]);

app.factory('PhotoFactory', ['$resource', function ($resource) {
    var serviceObject = {photoList: function (user_id){
        var PhotoResource = $resource("/PhotoUploader/rest/photo/imageList", {},{
            'query':{method: "GET", params:{user_id:user_id}, isArray:true,}
        });
        return PhotoResource.query().$promise; //this promise will be fulfilled when the response is retrieved for this call
    }};
    return serviceObject;
}]);

app.factory('PhotoOneFactory', ['$resource', function ($resource) {
    var photoObject = {photo: function (photoId){
        var PhotoGetResource = $resource("/PhotoUploader/rest/photo/:photoId", {photoId:photoId},{
            get:{method: "GET"}
        });
        return PhotoGetResource.get().$promise; //this promise will be fulfilled when the response is retrieved for this call
    }};
    return photoObject;
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