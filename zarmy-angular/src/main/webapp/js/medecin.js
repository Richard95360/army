(function() {
	var app = angular.module('medecin', []);
	
	app.directive("medecin", function() {
		return {
			restrict:'E',
			templateUrl:"medecin.html",
			controller:function($http) {
				var self = this;
				self.medecins = [];
				self.medecin = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/medecin'
					}).then(function(response) {
						self.medecins = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					self.medecin = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/medecin/'+id
					}).then(function(response) {
						self.medecin = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.medecin.id) {
						console.log(self.medecin);
						$http({
							method : 'PUT',
							url : 'api/medecin/'+self.medecin.id,
							data : self.medecin
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/medecin/',
							data : self.medecin
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
						url : 'api/medecin/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.medecin = null;
				};
				
				self.list();
				
			}, 
			controllerAs:'medecinCtrl'
		};
	});
})();
