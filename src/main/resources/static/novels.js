angular.module('app', ['ngStorage']).controller('novelController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app';

    if ($localStorage.springWebUser) {
        console.log($localStorage.springWebUser);
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

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

    $scope.changeRating = function (novel_id, delta){
        $http({
            url: contextPath + '/novels/edit/change_rating',
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
            url: contextPath + '/novels/edit/delete_novel',
            method: 'DELETE',
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

    document.getElementById('newNovel').onclick = function() {
        window.location.href = contextPath + '/newNovel.html';
    };

    document.getElementById('cart').onclick = function() {
        window.location.href = contextPath + '/cart.html';
    };

    document.getElementById('students').onclick = function() {
        window.location.href = contextPath + '/students.html';
    };

    $scope.tryToAuth = function () {
        console.log($scope.user);
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    // $scope.inspectAuth = function () {
    //     $localStorage.springWebUser = {username: 'pinteko', token: 123};
    //     console.log($localStorage.springWebUser);
    // };

        $scope.loadBooks();
    // $scope.inspectAuth();



});