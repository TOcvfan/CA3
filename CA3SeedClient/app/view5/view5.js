'use strict';
var isRoleSet;

var app = angular.module('myApp.view5', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/view5', {
              templateUrl: 'view5/view5.html',
              controller: 'View5Ctrl',
              controllerAs:'ctrl'
            });
          }])

        .controller('View5Ctrl',['HttpService', function (HttpService, $scope) {
            
            this.allUsers = HttpService.getUsers();
//            this.HAHA = "JAJAJAJA";
//          $http({
//            method: 'GET',
//            url: 'api/demoadmin'
//          }).then(function successCallback(res) {
//            $scope.data = res.data.message;
//            isRoleSet = true;
//          }, function errorCallback(res) {
//            $scope.error = res.status + ": "+ res.data.statusText;
//            isRoleSet = false;
//          });

        }]);
        
app.service('HttpService',['$http', function($http){
    
        this.getUsers = function(){
            var users=[];
            
            $http({
                method:'GET',
                url:'api/demoadmin/all'
            }).then(function succesCallBack(response){
                for (var i = 0; i < response.data.length; i++) {
                    users.push(response.data[i]);
                    console.log("from console: "+response.data[i]);
                }return users;
            },function errorCallBack(response){
                console.log("there was an error : "+response.status);
            });
            return users;
        };
        
        
}]);