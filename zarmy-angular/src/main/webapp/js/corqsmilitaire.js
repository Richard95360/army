(function() {
	var app = angular.module('corqsmilitaire', []);
	
	app.directive("corqsmilitaire", function() {
		return {
			restrict:'E',
			templateUrl:"corqsmilitaire.html",
			controller:function($http) {
				var self = this;
				self.corqsmilitaires = [];
				self.corqsmilitaire = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/corqsmilitaire'
					}).then(function(response) {
						self.corqsmilitaires = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					self.corqsmilitaire = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/corqsmilitaire/'+id
					}).then(function(response) {
						self.corqsmilitaire = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.corqsmilitaire.id) {
						console.log(self.corqsmilitaire);
						$http({
							method : 'PUT',
							url : 'api/corqsmilitaire/'+self.corqsmilitaire.id,
							data : self.corqsmilitaire
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/corqsmilitaire/',
							data : self.corqsmilitaire
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
						url : 'api/corqsmilitaire/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.corqsmilitaire = null;
				};
				
				self.list();
				
			}, 
			controllerAs:'corqsmilitaireCtrl'
		};
	});
})();
