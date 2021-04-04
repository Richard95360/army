(function() {
	var app = angular.module('arme', []);

	app.directive("arme", function() {
		return {
			restrict:'E',
			templateUrl:"arme.html",
			controller:function($http) {
				var self = this;
				self.armes = [];
				self.arme = null;
				self.guerriers = [];
				self.files = [];
								
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/arme'
					}).then(function(response) {
						self.armes = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					$http({
						method : 'GET',
						url : 'api/guerrier'
					}).then(function(response) {
						self.guerriers = response.data; 
					}, function(response) {

					});
					self.arme = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/guerrier'
					}).then(function(response) {
						self.guerriers = response.data; 
					}, function(response) {

					});
					$http({
						method : 'GET',
						url : 'api/arme/'+id
					}).then(function(response) {
						self.arme = response.data; 
					}, function(response) {

					});
				};
				
				self.save = function() {
					if(self.arme.id) {
//						$http({
//							method : 'PUT',
//							url : 'api/arme/'+self.arme.id,
//							data : self.arme
//						}).then(function(response) {
//							self.list();
//							self.cancel();
//						}, function(response) {
//
//						});
						$http({
							method : 'POST',
							url : 'api/arme/'+self.arme.id,
							headers: {
					            'Content-Type': undefined // --> makes sure the boundary is set in the Content-Type header, see result file
					        },
					        data: {
					            arme: self.arme,
					            file: self.files[0]
					        },
					        transformRequest: function(data) {
					            var fd = new FormData();
					            fd.append('arme', new Blob([angular.toJson(data.arme)], {
					                type: "application/json"
					            }));
					            fd.append("file", data.file);
					            return fd;
					        }
						}).then(function(response) {
							self.list();
							self.cancel();
						}, function(response) {

						});
					} else {
//						$http({
//							method : 'POST',
//							url : 'api/arme/',
//							data : self.arme
//						}).then(function(response) {
//							self.list();
//							self.cancel();
//						}, function(response) {
//
//						});
						$http({
							method : 'POST',
							url : 'api/arme/',
							headers: {
					            'Content-Type': undefined // --> makes sure the boundary is set in the Content-Type header, see result file
					        },
					        data: {
					            arme: self.arme,
					            file: self.files[0]
					        },
					        transformRequest: function(data) {
					            var fd = new FormData();
					            fd.append('arme', new Blob([angular.toJson(data.arme)], {
					                type: "application/json"
					            }));
					            fd.append("file", data.file);
					            return fd;
					        }
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
						url : 'api/arme/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.arme = null;
					self.files = [];
				};
				
				self.getPhoto = function(id,nomPhoto) {
					return 'api/arme/'+id+'/photo'+'/'+nomPhoto;
				};
				
				self.readImg = function() {
					$.getScript('js/fileImgSrc.js', function() {
						readImgInclude("imgarme",self.files);
					});
				};

				self.list();
				
			}, 
			controllerAs:'armeCtrl'
		};
	});
})();
