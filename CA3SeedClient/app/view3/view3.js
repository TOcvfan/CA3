'use strict';
var isRoleSet;

var app = angular.module('myApp.view3', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view3', {
      templateUrl: 'view3/view3.html',
      controller: 'View3Ctrl',
      controllerAs:'ctrl'
    });
  }]).
      
controller('View3Ctrl',['HttpService2', function (HttpService2) {
  
  this.allInfo = HttpService2.getInfo();
}]);

app.service('HttpService2',['$http', function($http){
    
        
        this.getInfo = function(){
           var info=[]; 
            $http({
                method:'GET',
                url:'api/demouser/all'   
            })
                    .then(function succesCallBack(response){
                        
                        console.log("this was httpservice2"+response.data);
//                    .then(function succesCallBack(response){
//                for (var i = 0; i < response.data.length; i++) {
//                    info.push(response.data[i]);
//                }return info;

//                    var x  = $.parseJSON( response.data.toString());
                    
                    

//
                for (var prop in response.data) {
//                    console.log("this was prop"+response.data[prop]);
                   
                    
                    info.push(response.data[prop]);
                    Object.keys(response.data[prop]);
                    
                    var str=response.data[prop]+":"+Object.keys(response.data[prop]).toString();
                }

                console.log("this was info: "+info.toString());
                    
//                info === response.data;
                return info;    
                
            },function errorCallBack(response){
                console.log("there was an error : "+response.status);
            });
            console.log("this was before return: "+info.toString());
            return info;
        };
        
}]);