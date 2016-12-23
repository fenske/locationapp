(function () {
    "use strict";

    angular.module("app", ['ngMap'])
        .config(function ($httpProvider) {
            $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        })
        .controller("home", function ($http, $location) {
            var self = this;

            $http.get("/user").success(function (data) {
                self.user = data.name;
                self.authenticated = true;
            }).error(function () {
                self.user = "N/A";
                self.authenticated = false;
            });

            self.logout = function () {
                $http.post('logout', {}).success(function () {
                    self.authenticated = false;
                    $location.path("/");
                }).error(function (data) {
                    console.log("Logout failed")
                    self.authenticated = false;
                });
            };
        })
        .controller('MarkerRemoveCtrl', function ($http, $location) {
            var vm = this;
            $http.get("/marker").success(function (data) {
                self.user = name;
                vm.positions = [{lat: data.lat, lng: data.lng}];
            });
            vm.addMarker = function (event) {
                var ll = event.latLng;
                vm.positions = [];
                vm.positions.push({lat: ll.lat(), lng: ll.lng()});
            }
            vm.saveMarker = function () {
                $http.post('/savemarker', vm.positions[0], {}).success(function () {
                    $location.path("/");
                });
            };
        });
})();