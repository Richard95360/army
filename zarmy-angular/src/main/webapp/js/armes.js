(function() {
	var app = angular.module('armes', []);

	app.directive("armes", function() {
		return {
			restrict:'E',
			templateUrl:"armes.html",
			controller:function($http) {
				var self = this;
				self.armes = [];
								
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/arme/selectionne'
					}).then(function(response) {
						self.armes = response.data; 
					}, function(response) {

					});
				};
				
				self.getPhoto = function(id,nomPhoto) {
					return 'api/arme/'+id+'/photo'+'/'+nomPhoto;
				};

				self.list();
				
			}, 
			controllerAs:'armesCtrl'
		};
	});
})();
