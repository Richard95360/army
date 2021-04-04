(function() {
	var app = angular.module('guerrier', []);

	app.directive("guerrier", function() {
		return {
			restrict:'E',
			templateUrl:"guerrier.html",
			controller:function($http) {
				var self = this;
				self.guerriers = [];
				self.guerrier = null;
				self.meres = [];
				self.files = [];
//				self.photoModif = false;
//				self.guerrierEditId = 0; // id catégorie éditée
//				self.timeModif = new Date().toTimeString().substr(0, 8); // hh:mm:ss
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'api/guerrier'
					}).then(function(response) {
						self.guerriers = response.data; 
					}, function(response) {

					});
				};
				
				self.add = function() {
					$http({
						method : 'GET',
						url : 'api/guerrier'
					}).then(function(response) {
						self.meres = response.data; 
					}, function(response) {

					});
					self.guerrier = {};
				};
				
				self.edit = function(id) {
					$http({
						method : 'GET',
						url : 'api/guerrier'
					}).then(function(response) {
						self.meres = response.data; 
					}, function(response) {

					});
					$http({
						method : 'GET',
						url : 'api/guerrier/'+id
					}).then(function(response) {
						self.guerrier = response.data;
					}, function(response) {

					});
				};
				
				self.save = function() {
//					console.log("Catégorie : "+JSON.stringify(self.guerrier));
					if (self.guerrier.id) {
//						$http({
//							method : 'PUT',
//							url : 'api/guerrier/'+self.guerrier.id,
//							data : self.guerrier
//						}).then(function(response) {							
//							self.list();
//							self.cancel();
//						}, function(response) {
//
//						});
						$http({
							method : 'POST',
							url : 'api/guerrier/'+self.guerrier.id,
							headers: {
					            'Content-Type': undefined // --> makes sure the boundary is set in the Content-Type header, see result file
					        },
					        data: {
					            guerrier: self.guerrier,
					            file: self.files[0]
					        },
					        transformRequest: function(data) {
					            var fd = new FormData();
					            fd.append('guerrier', new Blob([angular.toJson(data.guerrier)], {
					                type: "application/json"
					            }));
					            fd.append("file", data.file);
					            return fd;
					        }
						}).then(function(response) {
//							var idImg = "img"+self.guerrier.id;
//							if (self.files && self.files[0]) {
//								$.getScript('js/fileImgSrc.js', function() {
//									readImgInclude(idImg, self.files);
//								});
//							}
//							if (self.files && self.files[0]) {
////								self.photoModif = true; // on a changé la photo...
////								self.guerrierEditId = self.guerrier.id; // ... de cette catégorie
//								self.timeModif = new Date().toTimeString().substr(0, 8); // hh:mm:ss
//							}
//							console.log("edit");
//							console.log(self.photoModif);
//							console.log(self.guerrierEditId);
							self.list();							
							self.cancel();
						}, function(response) {

						});
					} else {
						$http({
							method : 'POST',
							url : 'api/guerrier/',
							headers: {
					            'Content-Type': undefined // --> makes sure the boundary is set in the Content-Type header, see result file
					        },
					        data: {
					            guerrier: self.guerrier,
					            file: self.files[0]
					        },
					        transformRequest: function(data) {
					            var fd = new FormData();
					            fd.append('guerrier', new Blob([angular.toJson(data.guerrier)], {
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
						url : 'api/guerrier/'+id
					}).then(function(response) {
						self.list();
					}, function(response) {

					});
					
				};
				
				self.cancel = function() {
					self.guerrier = null;
					self.files = [];
				};

				self.getPhoto = function(id,nomPhoto) {
//					$http({
//						method : 'GET',
//						url : 'api/guerrier/'+id+'/photo'
//					}).then(function(response) {
//						self.file = response.data;
//					}, function(response) {
//					});
//					var time = '';
//					console.log("getPhoto");
//					console.log(id);
//					console.log(self.photoModif);
//					console.log(self.guerrierEditId);
//					if (self.photoModif) {
//						time = new Date().toTimeString().substr(0, 5); // rafraîchir la photo dans la minute
//						self.photoModif = false;
//						self.guerrierEditId = 0;
//					}
//					else
//						time = "T";
//					console.log(time);
//					return 'api/guerrier/'+id+'/photo';
//					return 'api/guerrier/'+id+'/photo'+'/'+time;
//					return 'api/guerrier/'+id+'/photo'+'/'+self.timeModif;
					return 'api/guerrier/'+id+'/photo'+'/'+nomPhoto;
				};

				self.readImg = function() {
//		            if (self.files && self.files[0]) {
//		                var reader = new FileReader();
//		                reader.onload = function (e) {
//		                	$('#imgguerrier').attr('src', e.target.result);
//		                }
//		                reader.readAsDataURL(self.files[0]);
//		            }
					$.getScript('js/fileImgSrc.js', function() {
//						console.log( "fileImgSrc.js : Load was performed." );
						readImgInclude("imgguerrier",self.files);
					});
				};
				
				self.list();
				
			}, 
			controllerAs:'guerrierCtrl'
		};
	});
	
//	app.directive("filesInput", function() {
//		return {
//		    require: "ngModel",
//		    link: function postLink(scope,elem,attrs,ngModel) {
//		      elem.on("change", function(e) {
//		        var files = elem[0].files;
//		        ngModel.$setViewValue(files);
//		      })
//		    }
//		};
//	});

})();
