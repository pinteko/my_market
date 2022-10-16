angular.module('app', []).controller('novelController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.title = null;
    $scope.author = null;
    $scope.rating = null;
    $scope.price = null;
    $scope.msg = null;

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

    // $scope.addBook = function (title, author, rating, price){
    //     let book = {title: title, author: author,  rating: rating, price: price};
    //     $http({
    //         url: contextPath + '/books/form_book',
    //         method: 'POST',
    //         data: JSON.stringify(book),
    //         contentType: 'application/json'
    //     }).then(function (response){
    //         if (response.data)
    //             $scope.msg = "Post Data Submitted Successfully!";
    //     }, function (response) {
    //         $scope.msg = "Service not Exists";
    //         window.location.href ='http://localhost:8189/app/books.html';
    //     });
    // };
    document.getElementById('cart').onclick = function() {
        window.location.href = contextPath + '/cart.html';
    };

    document.getElementById('newNovel').onclick = function() {
        window.location.href = contextPath + '/newNovel.html';
    };





    $scope.loadBooks();

});