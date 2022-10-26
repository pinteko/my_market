angular.module('app', []).controller('novelController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadBooks = function () {
        $http.get(contextPath + '/novels')
            .then(function (response) {
                $scope.bookList = response.data;
            });
    };

    $scope.changeRating = function (novel_id, delta){
        $http({
            url: contextPath + '/novels/change_rating',
            method: 'GET',
            params: {
                novel_id: novel_id,
                delta: delta
            }
        }).then(function (response){
            $scope.loadBooks();
        });
    };

    $scope.deleteNovel = function (novel_id){
        $http({
            url: contextPath + '/novels/delete_novel',
            method: 'DELETE',
            params: {
                novel_id: novel_id,
            }
        }).then(function (response){
            $scope.loadBooks();
        });
    };

    $scope.addNovelInCart = function (novel_id){
        $http({
            url: contextPath + '/novels/add_cart',
            method: 'GET',
            params: {
                novel_id: novel_id,
            }
        }).then(function (response){
            $scope.loadBooks();
        });
    };

    $scope.createNovel = function () {
        console.log($scope.newNovel);
        console.log($scope.author);
        $http.post(contextPath + '/novels', $scope.newNovel)
            .then(function (response) {
                window.location.href = contextPath + '/novels.html';
                $scope.loadBooks();
            });
    };

    document.getElementById('cart').onclick = function() {
        window.location.href = contextPath + '/cart.html';
    };

    document.getElementById('newNovel').onclick = function() {
        window.location.href = contextPath + '/newNovel.html';
    };





    $scope.loadBooks();

});