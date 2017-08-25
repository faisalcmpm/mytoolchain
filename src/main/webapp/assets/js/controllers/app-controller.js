angular.module('appServices', []).factory('data', function($http, $q, $interval){
        var factory = {};
        return factory;
});

var app = angular.module('NDCApp', ['appServices','angularSpinner']);

app.controller('NDCAppController', ['$scope','serverconnector','RESOURCES','$location','$timeout','usSpinnerService',
                                    function($scope,serverconnector,RESOURCES,$location,$timeout,usSpinnerService){
	$scope.init = function(){
		$scope.RESOURCES = RESOURCES;
		//Basic offer search
		getOffers();
	};

	 $scope.startSpinner = function() {
		    $scope.data = 'Fetching data..';
		    $timeout(function(){
		    	 usSpinnerService.spin('spinner-1');
		    });
		    }

			$scope.stopSpinner = function() {
				$timeout(function(){
				usSpinnerService.stop('spinner-1');
				$scope.data = '';
				 });
			}

	function getOffers(){
		var imageUrl = getURLParameter("q");
		if(imageUrl){
			/*serverconnector.send("http://ip-api.com/json",null,function(response){
				var originLat = response.lat;
				var originLon = response.lon;
			});*/
			//Hard coded for demo purpose
			var originLat = "50.1109";
			var originLon = "8.6821";
			$scope.startSpinner();
			serverconnector.send("getOffer.htm?imageUrl="+imageUrl.slice(1, -1)+"&originLat="+originLat+"&originLon="+originLon,null,function(response){
			$scope.stopSpinner();
        if(response.recommendedOfferVO){
          $scope.flightDetails = response.recommendedOfferVO.airOffer;
          $scope.moreFlightsDetails = response.airOffers;

          $scope.carDetails = response.recommendedOfferVO.carOffer;
          $scope.moreCarDetails = response.carOffers;

          $scope.hotelDetails = response.recommendedOfferVO.hotelOffer;
          $scope.moreHotelDetails = response.hotelOffers;
          $scope.showErrorMsg = false;

          serverconnector.send("https://api.southpolegroup.services/api.php/category/flight_emission?origin="+$scope.flightDetails.flights[0].bdAirportCode+"&destination="+$scope.flightDetails.flights[0].offAirportCode+
          "&way=oneway&class=economy_class&passengers=1&auth_code=penguin",null,function(response){
    				$scope.co2 = response["Flight Emission"].tons;
    			});
        }else{
          $scope.errorText = response.errorMessage;
          $scope.showErrorMsg = true;
        }
        $scope.destinationLocation = response.destinationLocation;

			});
		}

	}

	$scope.getCarrierImage = function(code){
		return RESOURCES.CarrierIcons[code]? "assets/img/carriers/"+RESOURCES.CarrierIcons[code]: code;
	};

	$scope.getSymbol = function(code){
		return RESOURCES.CurrencySymbols[code]? RESOURCES.CurrencySymbols[code].symbol_native: code;
	};

  $scope.updateBest = function(offer,type){
    if(type=="FLIGHT"){
      $scope.flightDetails = offer;
    }else if(type=="CAR"){
      $scope.carDetails = offer;
    }else{
      $scope.hotelDetails = offer;
    }
  };

  $scope.getNumber = function(num) {
    var arr = [];
    for(var i=1; i<=num; i++) {
       arr.push(i.toString());
    }
    return arr;
  };

  $scope.getTotal = function(){
   var total=0;
   if($scope.flightDetails){
     total += $scope.flightDetails.price;
   }
   if($scope.carDetails){
     total+= $scope.carDetails.totalCost;
   }
   if($scope.hotelDetails){
     total += $scope.hotelDetails.price;
   }
   return Math.round(total * 100) / 100;
 };

	function getURLParameter(name) {
		return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
	}

  $scope.formatDuration = function(time){
    if(time){
        return time.replace("-","h ")+"m";
    }else{
      return "";
    }
  }



	$scope.init();

}]);
