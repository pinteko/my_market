angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadBooks = function () {
        $http.get(contextPath + '/books')
            .then(function (response) {
                $scope.bookList = response.data;
            });
    };

    $scope.changeRating = function (bookId, delta){
        $http({
            url: contextPath + '/books/change_rating',
            method: 'GET',
            params: {
                bookId: bookId,
                delta: delta
            }
        }).then(function (response){
            $scope.loadBooks();
        });
    };

    $scope.deleteBook = function (bookId){
        $http({
            url: contextPath + '/books/delete_book',
            method: 'DELETE',
            params: {
                bookId: bookId,
            }
        }).then(function (response){
            $scope.loadBooks();
        });
    };

    document.getElementById('newBook').onclick = function() {
        window.location.href = contextPath + '/newBook.html';
    };


    $scope.addBook = function (title, author, rating, price){
        $http({
            url: contextPath + '/books/form_book',
            method: 'POST',
            params: {
                // title: document.getElementById('title').getAttribute('title'),
                // author: document.getElementById('author').getAttribute('author'),
                // rating: document.getElementById('rating').getAttribute('rating'),
                // price: document.getElementById('price').getAttribute('price')
            },
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
                window.location.href = contextPath + '/books.html'
        );
    };

    $scope.loadBooks();

});