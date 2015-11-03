'use strict';
var isRoleSet;

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/view5', {
              templateUrl: 'view5/view5.html',
              controller: 'View5Ctrl'
            });
          }])

        .controller('View5Ctrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/demoadmin'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
            isRoleSet = true;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
            isRoleSet = false;
          });

        });