/**
 * Created by StepLuch on 02.09.15.
 */
/*var app = angular.module('ngdemo.controllers', []);*/

app.controller('loginCtrl', function ($scope, LoginFactory, $cookieStore, $location) {
    $scope.sendInfo = function (username, password) {
        console.error(username, password);
        LoginFactory.loginUser(username, password).then(
            function(loginResult){
                console.log(loginResult);
                //$cookieStore.put('user_id', loginResult.user_id);
                //$cookieStore.put('logined', loginResult.logined);
                $location.path('/user');
            },
            function(err){
                console.error(err);
            }
        );
    }
});

app.controller('userCtrl', ['$scope', 'UserFactory', function ($scope, LoginFactory) {
    console.error("ololo user logined");
}]);