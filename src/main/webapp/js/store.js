var sellerID = GetQueryString("sellerID");
var lat;
var lng;
var storename;
var storeaddress;
var flag_textbox=false;
var flag_imgbox=false;

$.post(
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
        createbox_info_evaluate(obj);


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
    });


};


//加载评价板块
function createbox_info_evaluate(obj){
    //加载文字评价
    console.log(obj);
    if (obj.text!="0")
    {
        var text=JSON.parse(obj.text);
        $("body").append('<div class="box_info_evaluate"></div>');
        $(".box_info_evaluate").append(' <div class="box_title"><p>客户评价('+text.total+')</p><span onclick="createtextbox();" class="chakanquanbu">查看全部&nbsp;&nbsp;&gt;</span></div>');
        $(".box_info_evaluate").append('<div class="box_evalutate"></div>');
        $(".box_evalutate").append('<img class="user_touxiang" src="'+text.touxiang+'">');
        $(".box_evalutate").append('<p class="user_nick">'+hidden(text.name)+'</p>');
        $(".box_evalutate").append('<p class="user_evalutate">'+text.content+'</div>');
        var img=JSON.parse(obj.img);
        if (img.total!="0")
        {

            var imgs=JSON.parse(img.imgs);
            $(".box_info_evaluate").append('<div class="box_img"> <p class="box_img_title">客户相册('+img.total+')</p> <span onclick="createimgbox();" class="chakanquanbu">查看全部&nbsp;&nbsp;&gt;</span> <div onclick="createimgbox();" class="box_img_imgs"><ul></ul></div></div>');
            for (var i=0;i<imgs.length;i++)
            {
                $(".box_img_imgs ul").append(' <li id="li'+i+'"><img src="'+imgs[i].img+'"></li>');
                $("#li"+i).css("height",$(".box_img_imgs ul li").css("width"));
            }



        }
    }


    //加载图片评价
}
function createtextbox()
{
    if (!flag_textbox)
    {

    }else {
        $(".text_box").css("display","block");
    }
}
function createimgbox(){

}
function hidden(str) {
    var xing = '**';
    return str.substring(0,1)+xing+str.substring(str.length-1);
}

