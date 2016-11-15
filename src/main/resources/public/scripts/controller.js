/**
 * 
 */

var myModule = angular.module('AngularIssues',[]);

myModule.controller('controller', function($scope, $http) {
	$scope.date = new Date();
	$scope.lastWeek = new Date(
			$scope.date.getFullYear(),
			$scope.date.getMonth(),
			$scope.date.getDate() - 7,
			$scope.date.getMinutes(),
			$scope.date.getSeconds(),
			$scope.date.getMilliseconds()
	);

	var angws = "https://api.github.com/repos/angular/angular/issues?since=" + $scope.lastWeek.toISOString();
	$http.get(angws)
		.then(function(response) {
			$scope.gitData = response.data;
//			$scope.title = gitData.title;
//			$scope.body = gitData.body;
			//var userData = gitData.user;
//			$scope.user = gitData.user;
//			$scope.assignee = gitData.assignee  ? gitData.assignee : "Unassigned";
		},
		function gitError(response) {
			$scope.gitError = response.statusText;
		});
});