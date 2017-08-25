/**
*Initializing services module.
*'serverconnector' service definition for AJAX call
**/
var app = angular.module('NDCApp');
app.factory('serverconnector',function($http,$log){
	return {
        send: function(url,data,callBack) {
			$http.post(url,data,{headers : {
        'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
    	}}).then(function successCallback(response) {
				callBack(response.data);
			  }, function errorCallback(response) {
				$log.error("Error in serverconnector post");
				$log.log(response);
			  });
        }
   }
});
