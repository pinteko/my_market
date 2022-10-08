angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.title = null;
    $scope.author = null;
    $scope.rating = null;
    $scope.price = null;

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
        let data = {title: title, author: author,  rating: rating, price: price};
        alert(title.toString());
        $http.post(contextPath + '/books/form_book', data)
            .success(function (response){
                alert("пришло после поста");
            window.location.href ='http://localhost:8189/app/books.html';
        });
    };

    document.getElementById('newBook').onclick = function() {
        window.location.href = contextPath + '/newBook.html';
    };



    $scope.loadBooks();

});