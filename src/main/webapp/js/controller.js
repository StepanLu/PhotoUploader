/**
 * Created by StepLuch on 02.09.15.
 */
/*var app = angular.module('ngdemo.controllers', []);*/

app.controller('loginCtrl', function ($scope, LoginFactory, $cookieStore, $location) {
    $scope.sendInfo = function (username, password) {
        console.error(username, password);
        LoginFactory.loginUser(username, password).then(
            function (loginResult) {
                console.log(loginResult);
                $cookieStore.put('user_id', loginResult.user_id);
                //$cookieStore.put('logined', loginResult.logined);
                $location.path('/user');
            },
            function (err) {
                console.error(err);
            }
        );
    }
});

app.controller('userCtrl', ['$scope', 'UserFactory', 'PhotoFactory', 'PhotoOneFactory', 'UserListFactory', '$cookieStore', '$http', function ($scope, UserFactory, PhotoFactory, PhotoOneFactory, UserListFactory, $cookieStore, $http) {
    UserFactory.userInfo($cookieStore.get('user_id')).then(
        function (infoResult) {
            console.log(infoResult);
            $scope.userName = infoResult.firstName + " " + infoResult.userEmail;
        },
        function (err) {
            console.error(err);
        }
    );

    PhotoFactory.photoList($cookieStore.get('user_id')).then(
        function (infoResult) {
            $scope.userPhotos = [];
            $scope.userImages = [];
            console.log(infoResult);
            for(var i in infoResult){
                if(infoResult[i].photo_id!=null){
                    $scope.userPhotos.push(infoResult[i]);
                }
            }
        },
        function (err) {
            console.error(err);
        }
    );

    UserListFactory.userList($cookieStore.get('user_id')).then(
        function (infoResult) {
            $scope.userList = [];
            console.log(infoResult);
            for(var i in infoResult){
                if(infoResult[i]._id!=null){
                    $scope.userList.push(infoResult[i]);
                }
            }
        },
        function (err) {
            console.error(err);
        }
    );


    $scope.uploadPhoto = function(){
        console.error($scope.fileToUpload);
        $http({
            method: 'POST',
            url: 'rest/photo/upload',
            //data: $.param({fkey: $scope.fileToUpload}),
            //data: ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data, status, headers, config){
            console.error(data);
            console.error(status);
            console.error(headers);
            //deferred.resolve(data);
        });
    };

    $scope.goToUserProfile = function(user){
        PhotoFactory.photoList(user._id).then(
            function (infoResult) {
                $scope.userPhotos = [];
                $scope.userImages = [];
                console.log(infoResult);
                for(var i in infoResult){
                    if(infoResult[i].photo_id!=null){
                        $scope.userPhotos.push(infoResult[i]);
                    }
                }
            },
            function (err) {
                console.error(err);
            }
        );
    };

    $scope.upload = function(flow){
        console.error(flow);
        flow.opts.query.user_id = $cookieStore.get('user_id');
        flow.upload();
    };



}]);