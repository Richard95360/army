(function() {
	var app = angular.module('connexion', []);

	app.directive("connexion", function() {
		return {
			restrict : 'E',
			templateUrl : "connexion.html",
			controller : function($http,$rootScope) {
				var self = this;
				self.user = null;
				self.username = "";
				self.password = "";

				self.cancel = function() {
					self.user = null;
					self.username = "";
					self.password = "";
					$rootScope.connecteObj.role = "";
					$rootScope.connecteObj.connecte = false;
				};

				self.connecter = function() {					
					$http({
						method : 'GET',
						url : 'api/user/username/' + self.username
					}).then(function(response) {
						self.user = response.data;
						if (self.password == self.user.password) {
							$rootScope.connecteObj.role = "AdminGue"; // à modifier plus tard !
							$rootScope.connecteObj.connecte = true;
						}
					}, function(response) {

					});
					
				};

			},
			controllerAs : 'connexionCtrl'
		};
	});
})();
