(function() {
	var app = angular.module('user', []);

	app.directive("user", function() {
		return {
			restrict:'E',
			templateUrl:"user.html",
			controller:function($http) {
				var self = this;
				self.users = [];
				self.user = null;
//				self.roles = [];
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/user'
					}).then(function(response) {
						self.users = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					self.user = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/user/'+id
					}).then(function(response) {
						self.user = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.user.id) {
						$http({
							method : 'PUT',
							url : 'api/user/'+self.user.id,
							data : self.user
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/user/',
							data : self.user
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
						url : 'api/user/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.user = null;
				};
				
//				self.listRoles = function() {
//					$http({
//						method : 'GET',
//						url : 'api/role'
//					}).then(function(response) {
//						self.roles = response.data; 
//					}, function(response) {
//
//					});
//				};
				
				self.list();
//				self.listRoles();
				
			}, 
			controllerAs:'userCtrl'
		};
	});
})();
