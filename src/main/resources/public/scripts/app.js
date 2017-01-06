var app = angular.module('angularissues', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
	var parse_link_header = function (header) {
		if (header.length == 0) return {};

		// Split parts by comma
		var parts = header.split(',');
		var links = {};
			  
		// Parse each part into a named link
		parts.forEach(function(d){
			var section = d.split(';');
			if (section.length != 2) {
				throw new Error("section could not be split on ';'");
			}
			var url = section[0].replace(/<(.*)>/, '$1').trim();
			var name = section[1].replace(/rel="(.*)"/, '$1').trim();
			links[name] = url;
		});

		return links;
	};
	
	var gotoPage = function (url, page){
		$http.get(url).then(function (data) {
	        $scope.links = parse_link_header(data.headers().link);
	        $scope.prevDisabled = $scope.links.prev ? '' : 'disabled';
	        $scope.nextDisabled = $scope.links.next ? '' : 'disabled';
	    	$scope.issues = data.data;
	    	$scope.currentPage += page;
	    }).catch(function (data, status) {
	        console.log('Error ' + data)
	    })
	};
	
	var weekAgo = new Date();
	weekAgo.setDate(weekAgo.getDate() - 7);
	var firstPageUrl = 'https://api.github.com/repos/angular/angular/issues?sort=created&per_page=10&page=1&since=' + weekAgo.toISOString()
	$scope.currentPage = 0;
	gotoPage(firstPageUrl, 1);
	
	$scope.prevPage = function () {
		if ($scope.links.prev) gotoPage($scope.links.prev, -1);
	};
	
	$scope.nextPage = function () {
		if ($scope.links.next) gotoPage($scope.links.next, 1);
	};
});