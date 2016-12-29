(function () {
'use strict';

angular.module('LocationApp')
.controller('HomeController', HomeController);

function HomeController($http, $location) {
  var homeCtrl = this;
  $http.get("/user").success(function (data) {
    homeCtrl.user = data.name;
    homeCtrl.authenticated = true;
  }).error(function () {
    homeCtrl.user = "N/A";
    homeCtrl.authenticated = false;
  });

  homeCtrl.logout = function () {
    $http.post('logout', {}).success(function () {
      homeCtrl.authenticated = false;
      $location.path("/");
    }).error(function (data) {
      console.log("Logout failed")
      homeCtrl.authenticated = false;
    });
  };
}

})();
