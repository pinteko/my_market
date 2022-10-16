angular.module('app', []).controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';


    $scope.loadNovels = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.cartList = response.data;
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
            $scope.loadNovels();
        });
    };

    $scope.loadNovels();

});