(function() {
	var app = angular.module('lignecommande', []);

	app.directive("lignecommande", function() {
		return {
			restrict:'E',
			templateUrl:"lignecommande.html",
			controller:function($http) {
				var self = this;
				self.lignecommandes = [];
				self.lignecommande = null;
				self.armes = [];
				self.commandes = [];
				self.alertQuantite = false;
								
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/lignecommande'
					}).then(function(response) {
						self.lignecommandes = response.data; 
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
						url : 'api/commande'
					}).then(function(response) {
						self.commandes = response.data; 
					}, function(response) {

					});
					self.lignecommande = {};
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
						url : 'api/commande'
					}).then(function(response) {
						self.commandes = response.data; 
					}, function(response) {

					});
					$http({
						method : 'GET',
						url : 'api/lignecommande/'+id
					}).then(function(response) {
						self.lignecommande = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.lignecommande.id) {
						$http({
							method : 'PUT',
							url : 'api/lignecommande/'+self.lignecommande.id,
							data : self.lignecommande
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/lignecommande/',
							data : self.lignecommande
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
						url : 'api/lignecommande/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.lignecommande = null;
				};
				
				self.changeQuantite = function() {
					if (self.lignecommande.quantite <= self.lignecommande.arme.stock) {
						self.lignecommande.montant = self.lignecommande.quantite * self.lignecommande.arme.prix;
						self.alertQuantite = false;
					}
					else
						self.alertQuantite = true;
				}
				
				self.list();
				
			}, 
			controllerAs:'lignecommandeCtrl'
		};
	});
})();
