
var servicename = decodeURI(GetQueryString("servicename"));
var $url = window.location.href;
var headerp = document.getElementById("servicename");
headerp.innerHTML = servicename + "服务网点";
var mycity;
var map = null;
var selettype = "list";
var oldcity = "";
var mk = null;
function Location() {
    //判断是否支持 获取本地位置
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        alert("浏览器不支持定位.");
    }
}

function showPosition(position) {
    var lat = position.coords.latitude;
    var lng = position.coords.longitude;
    position1(lat,lng);
//调用地图命名空间中的转换接口   type的可选值为 1:GPS经纬度，2:搜狗经纬度，3:百度经纬度，4:mapbar经纬度，5:google经纬度，6:搜狗墨卡托
//     qq.maps.convertor.translate(new qq.maps.LatLng(lat, lng), 1, function (res) {
//         //取出经纬度并且赋值
//         alert("111");
//         latlng1 = res[0];
//
//     });
}

// 地址和经纬度之间进行转换服务
var getcity = new qq.maps.Geocoder({
    complete: function (result) {
        console.log(result);
        var city = document.getElementById("city");
        city.value = result.detail.addressComponents.city;
        mycity = result.detail.addressComponents.city;
        map.setCenter(result.detail.location);
        init();
        getlist();
    }
});
function position1(lat,lng){
    getcity.getAddress(new qq.maps.LatLng(lat,lng));
    map = new qq.maps.Map(document.getElementById("container"), {
        center: new qq.maps.LatLng(lat,lng),
        zoom: 13,
        disableDefaultUI: true
    });
    qq.maps.event.addListener(map, 'click', function () {
        mk.setAnimation(null);
        document.getElementById("content").style.display = "none";
    });
    //var geocoder = null;
};


function init() {
    if (selettype == "map") {
        $.get(
            "getmarkers",
            {
                city: mycity,
                servicename: servicename
            },
            function (result) {
                oldcity = mycity;
                if (result == "") {
                    alert("该地区暂未提供线下服务");
                }
                var latlngs = JSON.parse(result);
                for (var i = 0; i < latlngs.length; i++) {
                    (function () {
                        var phone = latlngs[i].phone;
                        var geocoder = new qq.maps.Geocoder({
                            complete: function (result) {
                                var anchor = new qq.maps.Point(15, 35);
                                var size = new qq.maps.Size(30, 36);
                                var origin = new qq.maps.Point(0, 0);
                                var icon = new qq.maps.MarkerImage('img/marcker1.png', size, origin, anchor);
                                size = new qq.maps.Size(52, 30);
                                var originShadow = new qq.maps.Point(32, 0);
                                var marker = new qq.maps.Marker({
                                    icon: icon,
                                    position: result.detail.location,
                                    animation: qq.maps.MarkerAnimation.DROP,
                                    map: map
                                });
                                qq.maps.event.addListener(marker, 'click', function () {
                                    if (mk != marker & mk != null) {
                                        mk.setAnimation(null);
                                    }
                                    $.post(
                                        "getmarkers",
                                        {
                                            phone: phone,
                                            servicename: servicename
                                        },
                                        function (result) {
                                            var content = document.getElementById("content");
                                            content.style.display = "block";
                                            var obj = JSON.parse(result);
                                            var address = document.getElementById("address");
                                            var logo = document.getElementById("logo");
                                            var title = document.getElementById("title");
                                            var call = document.getElementById("call");
                                            address.innerHTML = obj.address;
                                            logo.src = obj.touxiang;
                                            title.innerHTML = obj.name;
                                            call.href = "store.html?sellerID=" + obj.sellerID;
                                            var compelet = document.getElementById("compelet");
                                            compelet.innerHTML = "月完成" + obj.compelet;
                                        }
                                    );

                                    marker.setAnimation(qq.maps.MarkerAnimation.BOUNCE);
                                    map.panTo(result.detail.location);
                                    mk = marker;
                                });
                            }
                        });
                        geocoder.getAddress(new qq.maps.LatLng(latlngs[i].lat, latlngs[i].lng));
                    })(i)
                }

            }
        );
    }

}

window.onload = function () {
    Location();
    select();


}
var getLocation = new qq.maps.Geocoder({
    complete: function (result) {
        console.log(result);
        var city = document.getElementById("city");
        city.value = result.detail.addressComponents.city;
        mycity = result.detail.addressComponents.city;
        map.setCenter(result.detail.location);
        init();
        getlist();
    }
});

function select() {
    document.getElementById("select_left").addEventListener("click", function () {
        if (selettype != "list") {
            document.getElementById("body_list").style.display = "block";
            document.getElementById("select_left").style.backgroundColor = "dodgerblue";
            document.getElementById("select_right").style.backgroundColor = "white";
            document.getElementById("select_right").style.color = "black";
            document.getElementById("select_left").style.color = "white";
            document.getElementById("container").style.display="none";
            document.getElementById("content").style.display="none";
            selettype = "list";
            getlist();
        }


    });
    document.getElementById("select_right").addEventListener("click", function () {
        if (selettype != "map") {
            document.getElementById("body_list").style.display = "none";
            document.getElementById("select_right").style.backgroundColor = "dodgerblue";
            document.getElementById("select_left").style.backgroundColor = "white";
            document.getElementById("select_right").style.color = "white";
            document.getElementById("select_left").style.color = "black";
            document.getElementById("container").style.display="block";
            selettype = "map";
            if (mycity != oldcity) {
                init();
            }

        }
    });
}

function getlist() {
    if (selettype == "list") {
        document.getElementById("list_ul").innerHTML = "";
        $.post(
            "getlist",
            {
                city: mycity,
                servicename: servicename
            },
            function (result) {
                if (result == "") {
                    alert("该地区暂未提供线下服务");
                } else {
                    var obj = JSON.parse(result);
                    createbodylist(obj);
                }
            }
        );
    }
}

function createbodylist(obj) {
    var list_ul = document.getElementById("list_ul");
    for (var i = 0; i < obj.length; i++) {
        var li = document.createElement("li");
        var a = document.createElement("a");
        var img = document.createElement("img");
        var name = document.createElement("p");
        name.setAttribute("class", "name");
        var address = document.createElement("p");
        address.setAttribute("class", "address");
        var complete = document.createElement("p");
        complete.setAttribute("class", "complete");
        var jindian = document.createElement("div");
        jindian.setAttribute("class", "jindian");
        var p = document.createElement("p");
        p.innerHTML = "进店";
        list_ul.appendChild(li);
        li.appendChild(a);
        a.appendChild(img);
        a.appendChild(name);
        a.appendChild(address);
        a.appendChild(complete);
        a.appendChild(jindian);
        jindian.appendChild(p);
        img.src = obj[i].touxiang;
        name.innerHTML = obj[i].name;
        address.innerHTML = obj[i].address;
        complete.innerHTML = "月完成" + obj[i].complete;
        a.href = "store.html?sellerID=" + obj[i].sellerID;
    }
}
