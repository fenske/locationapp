(function () {
'use strict';

angular.module('LocationApp')
.controller('MarkerController', MarkerController);

function MarkerController($http, $location) {
  var markerCtrl = this;
  $http.get("/marker").success(function (data) {
    self.user = name;
    markerCtrl.positions = [{lat: data.lat, lng: data.lng}];
  });
  markerCtrl.addMarker = function (event) {
    var ll = event.latLng;
    markerCtrl.positions = [];
    markerCtrl.positions.push({lat: ll.lat(), lng: ll.lng()});
  }
  markerCtrl.saveMarker = function () {
    $http.post('/savemarker', markerCtrl.positions[0], {}).success(function () {
      $location.path("/");
    });
  };
}

})();
