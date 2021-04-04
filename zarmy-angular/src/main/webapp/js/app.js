(function() {
var app = angular.module('army', ['connexion','arme','armes','civil','user','role','commande','corqsmilitaire','guerrier','guerriers','lignecommande','medecin','pacifiste','filesInput']); 

app.controller("MenuController", function($http,$rootScope) {
	this.menu = 'accueil';
	$rootScope.connecteObj = {
			"connecte": false,
			"role": ""
				};
	
	this.setMenu = function(page) {
		this.menu = page;
	};
	
	this.isMenu = function(page) {
		return this.menu == page;
	};
		
	this.isConnecte = function() {
		return $rootScope.connecteObj.connecte == true;
	};
	
	this.isAdminGue = function() {
		return $rootScope.connecteObj.role == "AdminGue";
	};
	
	this.isAdminArm = function() {
		return $rootScope.connecteObj.role == "AdminArm";
	};
	
	var self = this;

	self.cancel = function() {
		$rootScope.connecteObj.role = "";
		$rootScope.connecteObj.connecte = false;
	};

});

})();

