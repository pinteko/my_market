angular.module('market-front').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders')
            .then(function (response) {
                $scope.ordersList = response.data;
            });
    }

    $scope.loadOrders();
});