$(document).ready(function() {
	console.log('ready to serve...')
	getListHistoryWeather();
	initBtn();
});

function getListHistoryWeather() {
	$('#weatherLogHistory').html('');
	var url = "api/weather/getListWeatherLog";
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : url,
		dataType : 'json',
		success : function(response) {
			drawLogData(response);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function drawLogData(weatherLogs) {
	var content = "";
	for (var i = 0; i < weatherLogs.length; i++) {
		//
		const weatherLog = weatherLogs[i];
		content += ' <hr/> <div class="row" style="margin-top: 20px; width: 800px;">\n' +
			'\t\t\t<div class="col-md-2">\n' +
			'\t\t\t ' + getDayIcon(weatherLog.weather) + '\n' +
			'\t\t</div>\n' +
			'\t\t<div class="col-md-3">\n' +
			'\t\t\t' + weatherLog.city + ' - ' + weatherLog.country +' <br/> \n' +
			'\t\t\t' + getDateInFormat(weatherLog.updatedOn) + '\n' +
			'\t\t</div>\n' +
			'\t\t<div class="col-md-3">\n' +
			'\t\t\t ' + convertKelvinToCelsius(weatherLog.temperature) + ' Clouds: <br/> \n' +
			'\t\t\t ' + weatherLog.windSpeed + ' m/s, ' + weatherLog.humidity +'% \n' +
			'\t\t</div>\n' +
			'\t\t<div class="col-md-2">\n' +
			'\t\t\t <button type="button" class="btn btn-danger mb-2" onclick="deleteWeatherLog(' + weatherLog.id +')">Delete</button> \n' +
			'\t\t</div>\n' +
			'\t\t</div> ';
	}
	$('#weatherLogHistory').html(content);
}

function getDayIcon(weathers) {
	var iconsList = '';
	for (var i = 0; i < weathers.length; i++) {
		var icon = weathers[i].icon;
		iconsList += '<img style="width: 80px; margin-right:10px;" src="http://openweathermap.org/img/wn/'+icon+'@2x.png"/>';
	}
	return iconsList;
}

function convertKelvinToCelsius (temperature) {
	var temC = (temperature - 273.15).toFixed(0);
	return '<span class="badge badge-secondary">'+temC+' degrees </span>';
}

function deleteWeatherLog(weatherLogId) {
	var url = "api/weather/weather-log/"+weatherLogId;
	$.ajax({
		type : "DELETE",
		contentType : "application/json",
		url : url,
		dataType : 'json',
		success : function() {
			getListHistoryWeather();
		},
		error : function(e) {
			console.log("ERROR: ", e);
			if (e.status == 200) {
				getListHistoryWeather();
			}
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function getDateInFormat(time) {
	var date = new Date(time);
	return date.customFormat( "#DD#/#MM#/#YYYY# #hh#:#mm#:#ss#" );
}

Date.prototype.customFormat = function(formatString){
	var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
	YY = ((YYYY=this.getFullYear())+"").slice(-2);
	MM = (M=this.getMonth()+1)<10?('0'+M):M;
	MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
	DD = (D=this.getDate())<10?('0'+D):D;
	DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][this.getDay()]).substring(0,3);
	th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
	formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
	h=(hhh=this.getHours());
	if (h==0) h=24;
	if (h>12) h-=12;
	hh = h<10?('0'+h):h;
	hhhh = hhh<10?('0'+hhh):hhh;
	AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
	mm=(m=this.getMinutes())<10?('0'+m):m;
	ss=(s=this.getSeconds())<10?('0'+s):s;
	return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
};

function initBtn() {
	$("#searchWeatherBtn").click(function() {
		$("#warning-message").hide();
		var cityInput = $('#cityInput').val();
		var url = "api/weather/searchByParam?inputParam=" + cityInput;
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : url,
			dataType : 'json',
			success : function(response) {
				console.log(response);

				$('#weatherLogHistory').html('');
				getListHistoryWeather();

			},
			error : function(e) {
				if (e.status == 404) {
					$("#warning-message").show();
				}
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
}

