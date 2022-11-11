angular.module('app', []).controller('novelController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    let current_page = 1;
    $scope.count_pages = 2;

    $scope.loadBooks = function (page, min_rating, max_rating,
                                 min_price, max_price, title_part, names, surname) {
        $http({
            url: contextPath + '/novels',
            method: 'GET',
            params: {
                p: page,
                min_rating: min_rating,
                max_rating: max_rating,
                min_price: min_price,
                max_price: max_price,
                title_part: title_part,
                names: names,
                surname: surname
            }
        }).then(function (response) {
                $scope.bookList = response.data.content;
                console.log($scope.bookList);
            });
    };

    $scope.prevPage = function ()
    {
        if (current_page > 1) {
            current_page--;
            $scope.changePage(current_page);
        }
    };

    $scope.goNextPage = function ()
    {
        if (current_page < $scope.count_pages) {
            current_page++;
            $scope.changePage(current_page);
        }
    };

    $scope.changePage = function (page)
    {
        $scope.loadBooks(page, null, null, null, null, null, null);
        current_page = page;
        console.log(current_page);
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