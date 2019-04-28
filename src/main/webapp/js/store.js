var sellerID = GetQueryString("sellerID");
var lat;
var lng;
var storename;
var storeaddress;
$.get(
    "getsellerinfo",
    {
        sellerID: sellerID
    },
    function (result) {
        if (result == "") {
            alert("该商家不存在");
            location.href = "shouye.html";
        }
        var obj = JSON.parse(result);
        lat = obj.lat;
        lng = obj.lng;
        var Carousel = JSON.parse(obj.touxiang);
        createCarousel(Carousel);
        banner(Carousel.length + 2);
        var name = document.getElementById("name");
        var address_href = document.getElementById("address_href");
        var phone = document.getElementById("phone");
        var address = document.getElementById("address");
        name.innerHTML = obj.name;
        storename=obj.name;
        phone.href = "tel:" + obj.phone;
        address.innerHTML = obj.address;
        storeaddress = obj.address;

    }
);

function createCarousel(array) {
    var ul = document.getElementById("Carousel");
    for (var i = 0; i < array.length; i++) {
        if (i == 0) {
            createlia(array[array.length - 1]);
            createlia(array[i]);
            createlia(array[i]);
        } else if (i == array.length - 1) {
            createlia(array[i]);
            createlia(array[0]);
        } else {
            createlia(array[i]);
        }
    }
    for (var i = 0; i < array.length - 1; i++) {
        var ul = document.getElementById("dian");
        var li = document.createElement("li");
        ul.appendChild(li);
    }

    function createlia(i) {
        var li = document.createElement("li");
        var a = document.createElement("a");
        var img = document.createElement("img");
        img.setAttribute("src", i.src);
        li.appendChild(img);
        ul.appendChild(li);
    }

}

window.onload = function () {
    // layer.msg('的确很重要', {icon: 1});
    var address_href = document.getElementById("address_href");
    address_href.addEventListener("click", function () {
        layer.open({
            content: "<div class='btn'><ul><li id='baidu'><p>百度地图</p></li><li id='tengxun'><p>腾讯地图</p></li><li id='gaode'><p>高德地图</p></li></ul></div>"
            , skin: 'footer'
        });
        var baidu = document.getElementById("baidu");
        var tengxun = document.getElementById("tengxun");
        var gaode = document.getElementById("gaode");
        baidu.addEventListener("click", function () {
            location.href="http://api.map.baidu.com/geocoder?location="+lat+","+lng+"&coord_type=gcj02&output=html&src=webapp.baidu.openAPIdemo";
        });
        tengxun.addEventListener("click", function () {
            location.href = "https://apis.map.qq.com/uri/v1/marker?marker=coord:"+lat+","+lng+";title:"+storename+";addr:"+storeaddress+"&referer=myapp";
        });
        gaode.addEventListener("click", function () {
            location.href="https://uri.amap.com/marker?position="+lng+","+lat+"&name="+storename+"&src=mypage&coordinate=gaode&callnative=0";
        });
    })

}
