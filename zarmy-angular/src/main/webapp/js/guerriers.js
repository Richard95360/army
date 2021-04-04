(function() {
	var app = angular.module('guerriers', []);

	app.directive("guerriers", function() {
		return {
			restrict:'E',
			templateUrl:"guerriers.html",
			controller:function($http) {
				var self = this;
				self.guerriers = [];
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/guerrier'
					}).then(function(response) {
						self.guerriers = response.data; 
					}, function(response) {

					});
				};
				
				self.getPhoto = function(id,nomPhoto) {
					return 'api/guerrier/'+id+'/photo'+'/'+nomPhoto;
				};
				
				self.list();
				
			}, 
			controllerAs:'guerriersCtrl'
		};
	});	

})();
