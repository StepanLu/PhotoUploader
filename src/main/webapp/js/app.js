/**
 * Created by StepLuch on 02.09.15.
 */
'use strict';

// Declare app level module which depends on filters, and services

var app = angular.module('photoUploader',['ngResource','ngRoute', 'ngCookies'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/login', {templateUrl: 'login.html', controller: 'loginCtrl'});
        $routeProvider.when('/user', {templateUrl: 'user.html', controller: 'userCtrl'});
        //$routeProvider.when('/register', {templateUrl: 'register.html', controller: 'registerCtrl'});
    }]);;

app.run(function($rootScope, $location, $cookieStore){
    console.error("run!");
    console.error($cookieStore.get('logined'));
    if($cookieStore.get('logined') == true){
        $location.path('/user');
    }else{
        $location.path('/login');
    }
    $rootScope.$on('$routeChangeStart', function(){
        console.error($location.path());
    });
});