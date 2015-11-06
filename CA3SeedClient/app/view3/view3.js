'use strict';
var isRoleSet;

var app = angular.module('myApp.view3', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view3', {
      templateUrl: 'view3/view3.html',
      controller: 'View3Ctrl',
      controllerAs:'ctrl'
    });
  }]);

app.controller('View3Ctrl',['HttpService', function (HttpService) {
  
  this.allInfo = HttpService.getInfo();
        this.jens = "the jens";
//    $http({
//    method: 'GET',
//    url: 'api/demouser'
//  }).then(function successCallback(res) {
//    $scope.data = res.data.message;
//  }, function errorCallback(res) {
//    $scope.error = res.status + ": "+ res.data.statusText;
//  });

}]);

app.service('HttpService',['$http', function($http){
    
    
        this.getInfo = function(){
            var info=[];
            
            $http({
                method:'GET',
                url:'api/demoadmin/all'//'http://cvrapi.dk/api?vat=3167%208021&country=dk'        //'//cvrapi.dk/api?search="   vat   "&country=dk'
            }).then(function succesCallBack(response){
                for (var i = 0; i < response.data.length; i++) {
                    
                    info.push(response.data[i]);
                    console.log("from console: "+response.data[i]);
                }return info;
            },function errorCallBack(response){
                console.log("there was an error : "+response.status);
            });
            return info;
        };
        
        
}]);