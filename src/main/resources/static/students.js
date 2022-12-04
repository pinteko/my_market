angular.module('market-front').controller('studentsController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';



    $scope.findStudents = function () {
        $http.get(contextPath + '/students')
            .then(function (response) {
                $scope.studentList = response.data;
            });
    };


    $scope.findStudents();

});