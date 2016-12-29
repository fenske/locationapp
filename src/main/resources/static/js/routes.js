(function () {
'use strict';

angular.module('LocationApp')
.config(RoutesConfig);

RoutesConfig.$inject = ['$urlRouterProvider', '$stateProvider', '$httpProvider'];
function RoutesConfig($httpProvider, $urlRouterProvider, $stateProvider) {

    // TODO Describe what for
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    // Redirect to home page if no other URL matches
    $urlRouterProvider.otherwise('/');

    // *** Set up UI states ***
    $stateProvider

    // Home page
    .state('home', {
        url: '/',
        templateUrl: '../templates/home.template.html',
        controller: 'HomeController as homeCtrl'
    })

    .state('marker', {
        url: '/',
        templateUrl: '../templates/home.template.html',
        controller: 'MarkerController as markerCtrl'
    });
}

})();