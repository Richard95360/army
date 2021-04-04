(function() {
	var app = angular.module('pacifiste', []);
	
	app.directive("pacifiste", function() {
		return {
			restrict:'E',
			templateUrl:"pacifiste.html",
			controller:function($http) {
				var self = this;
				self.pacifistes = [];
				self.pacifiste = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/pacifiste'
					}).then(function(response) {
						self.pacifistes = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					self.pacifiste = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/pacifiste/'+id
					}).then(function(response) {
						self.pacifiste = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.pacifiste.id) {
						console.log(self.pacifiste);
						$http({
							method : 'PUT',
							url : 'api/pacifiste/'+self.pacifiste.id,
							data : self.pacifiste
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/pacifiste/',
							data : self.pacifiste
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
						url : 'api/pacifiste/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.pacifiste = null;
				};
				
				self.list();
				
			}, 
			controllerAs:'pacifisteCtrl'
		};
	});
})();
