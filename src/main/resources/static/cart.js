angular.module('app', []).controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    let map = new Map();

    $scope.loadNovels = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.cartList = response.data;
            });
    };

    $scope.loadNovelsDto = function () {
        $http.get(contextPath + '/cart/dto')
            .then(function (response) {
                $scope.cartList = response.data;
                console.log($scope.cartList);
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

    // $scope.loadNovels();
    $scope.loadNovelsDto();

});