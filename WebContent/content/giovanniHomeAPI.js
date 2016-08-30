var casaGiovanni = new google.maps.LatLng(39.6119669,16.5689641);

var impostazioniMappa = {
				zoom: 15,
				center: casaGiovanni,
				mapTypeControl: true,
				mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
				navigationControl: true,
				navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};

var map = new google.maps.Map(document.getElementById("casaMIA"), impostazioniMappa);
