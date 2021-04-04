(function() {
	var app = angular.module('civil', []);
	
	app.directive("civil", function() {
		return {
			restrict:'E',
			templateUrl:"civil.html",
			controller:function($http) {
				var self = this;
				self.civils = [];
				self.civil = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/civil'
					}).then(function(response) {
						self.civils = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					self.civil = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/civil/'+id
					}).then(function(response) {
						self.civil = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.civil.id) {
						console.log(self.civil);
						$http({
							method : 'PUT',
							url : 'api/civil/'+self.civil.id,
							data : self.civil
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/civil/',
							data : self.civil
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					}
				};
				
				self.remove = function(id) {
					$http({
						method : 'DELETE',
						url : 'api/civil/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.civil = null;
				};
				
				self.list();
				
			}, 
			controllerAs:'civilCtrl'
		};
	});
})();
