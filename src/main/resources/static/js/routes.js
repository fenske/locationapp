(function () {
'use strict';

angular.module('LocationApp')
.config(RoutesConfig);

// NOTE Apparently the order of the injections matters
RoutesConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$httpProvider'];
function RoutesConfig($stateProvider, $urlRouterProvider, $httpProvider) {

    // Redirect to home page if no other URL matches
    $urlRouterProvider.otherwise('/');

    // *** Set up UI states ***
    $stateProvider

    // Home page
    .state('home', {
        url: '/',
        templateUrl: '../templates/home.template.html',
        controller: 'HomeController as homeCtrl'
    });

    // TODO Describe what for
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}

})();