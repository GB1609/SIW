var location31b = new google.maps.LatLng(39.363162,16.2256618);
var impostazioniMappa = {
				zoom: 15,
				center: location31b,
				mapTypeControl: true,
				mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
				navigationControl: true,
				navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
var map = new google.maps.Map(document.getElementById("apiMAP"), impostazioniMappa);

var siwLogo = new google.maps.MarkerImage(	"../assets/markerSIW.png",
											new google.maps.Size(63,100),
											new google.maps.Point(0,0)
											);
var mioMarker = new google.maps.Marker({
					position: location31b,
					map: map,
					icon: siwLogo,
					title:"SIWticketSeller"
			});
