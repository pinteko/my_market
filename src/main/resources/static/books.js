angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.title = null;
    $scope.author = null;
    $scope.rating = null;
    $scope.price = null;
    $scope.msg = null;

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

    // document.getElementById('backMain').onclick = function() {
    //     window.location.href = contextPath + '/books.html';
    // };

    $scope.addBook = function (title, author, rating, price){
        let book = {title: title, author: author,  rating: rating, price: price};
        $http({
            url: contextPath + '/books/form_book',
            method: 'POST',
            data: JSON.stringify(book),
            contentType: 'application/json'
        }).then(function (response){
            if (response.data)
                $scope.msg = "Post Data Submitted Successfully!";
        }, function (response) {
            $scope.msg = "Service not Exists";
            window.location.href ='http://localhost:8189/app/books.html';
        });
    };

    document.getElementById('newBook').onclick = function() {
        window.location.href = contextPath + '/newBook.html';
    };



    $scope.loadBooks();

});