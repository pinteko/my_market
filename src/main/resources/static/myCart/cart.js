angular.module('market-front').controller('cartController', function ($scope, $rootScope, $http) {
    const contextPath = 'http://localhost:8189/app';

    // $scope.loadNovels = function () {
    //     $http.get(contextPath + '/cart')
    //         .then(function (response) {
    //             $scope.cartList = response.data;
    //         });
    // };

    // $scope.loadCart = function () {
    //     $http({
    //         url: contextPath + '/cart',
    //         method: 'GET'
    //     }).then(function (response) {
    //         $scope.cart = response.data;
    //     });
    // };

    $scope.loadNovelsDto = function () {
        $http.get(contextPath + '/cart/dto')
            .then(function (response) {
                $scope.cartList = response.data;
                console.log($scope.cartList);
            });
    };

    $scope.findStudents = function () {
        $http.get(contextPath + '/students')
            .then(function (response) {
                $scope.studentList = response.data;
            });
    };

    $scope.deleteNovel = function (novel_id){
        $http({
            url: contextPath + '/cart/delete_novel',
            method: 'DELETE',
            params: {
                novel_id: novel_id,
            }
        }).then(function (response){
            $scope.loadNovelsDto();
        });
    };

    $scope.addNovel = function (novel_id){
        $http({
            url: contextPath + '/cart/add_novel',
            method: 'GET',
            params: {
                novel_id: novel_id,
            }
        }).then(function (response){
            $scope.loadNovelsDto();
        });
    };

    $scope.clearCart = function (){
        $http({
            url: contextPath + '/novels/cart/clear_cart',
            method: 'DELETE'
        }).then(function (response){
            // $scope.loadBooks();
        });
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.createOrder = function (){
        $http({
            url: contextPath + '/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response){
            $scope.loadOrders();
            $scope.loadNovelsDto();
            $scope.orderDetails = null;
        });
    };

    $scope.loadOrders = function (){
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.ordersList = response.data;
            });
    };

    $scope.loadNovelsDto();
    $scope.loadOrders();

});