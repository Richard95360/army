(function() {
	var app = angular.module('commande', []);

	app.directive("commande", function() {
		return {
			restrict:'E',
			templateUrl:"commande.html",
			controller:function($http) {
				var self = this;
				self.commandes = [];
				self.commande = null;
				self.armes = [];
				self.civils = [];
								
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/commande'
					}).then(function(response) {
						self.commandes = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					$http({
						method : 'GET',
						url : 'api/arme'
					}).then(function(response) {
						self.armes = response.data; 
					}, function(response) {

					});
					$http({
						method : 'GET',
						url : 'api/civil'
					}).then(function(response) {
						self.civils = response.data; 
					}, function(response) {

					});
					self.commande = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/arme'
					}).then(function(response) {
						self.armes = response.data; 
					}, function(response) {

					});
					$http({
						method : 'GET',
						url : 'api/civil'
					}).then(function(response) {
						self.clients = response.data; 
					}, function(response) {

					});
					$http({
						method : 'GET',
						url : 'api/commande/'+id
					}).then(function(response) {
						self.commande = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.commande.id) {
						$http({
							method : 'PUT',
							url : 'api/commande/'+self.commande.id,
							data : self.commande
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/commande/',
							data : self.commande
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
						url : 'api/commande/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.commande = null;
				};
				
				self.list();
				
			}, 
			controllerAs:'commandeCtrl'
		};
	});
})();
