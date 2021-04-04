(function() {
	var app = angular.module('role', []);

	app.directive("role", function() {
		return {
			restrict:'E',
			templateUrl:"role.html",
			controller:function($http) {
				var self = this;
				self.roles = [];
				self.role = null;
				self.roleValues = [];
				self.users = [];
				self.user = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/role'
					}).then(function(response) {
						self.roles = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					$http({
						method : 'GET',
						url : 'api/user'
					}).then(function(response) {
						self.users = response.data; 
					}, function(response) {

					});
					self.role = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/user'
					}).then(function(response) {
						self.users = response.data; 
					}, function(response) {

					});
					$http({
						method : 'GET',
						url : 'api/role/'+id
					}).then(function(response) {
						self.role = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.role.id) {
						$http({
							method : 'PUT',
							url : 'api/role/'+self.role.id,
							data : self.role
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/role/'+self.user.id,
							data : self.role
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
						url : 'api/role/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.role = null;
				};
				
				self.listRoles = function() {
					$http({
						method : 'GET',
						url : 'api/role/enum'
					}).then(function(response) {
						self.roleValues = response.data; 
					}, function(response) {

					});
				};
				
				self.list();
				self.listRoles();
				
			}, 
			controllerAs:'roleCtrl'
		};
	});
})();
