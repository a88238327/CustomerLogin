function createCarousel(array) {
    var ul=document.getElementById("Carousel");
    for(var i=0;i<array.length;i++)
    {
        if(i==0){
            createlia(array[array.length-1],array.length);
            createlia(array[i],i+1);
        }else if(i==array.length-1)
        {
            createlia(array[i],i+1);
            createlia(array[0],'1');
        }
        else {
            createlia(array[i],i+1);
        }
    }
    // $(function() {
    //     $("img.lazy").lazyload({
    //         effect : "fadeIn",
    //         container: $("#Carousel"),
    //         threshold : 0,
    //         skip_invisible : false
    //     });
    // });
    for(var i=0;i<array.length-1;i++){
        var ul=document.getElementById("dian");
        var li=document.createElement("li");
        ul.appendChild(li);
    }
    function createlia(i,index) {
        if (i==1)
        {
            var li=document.createElement("li");
            var a=document.createElement("a");
            var img=document.createElement("img");
            a.setAttribute("href",i.href);
            //img.setAttribute("src",i.src);
            img.setAttribute("src",i.src);
            img.setAttribute("data-original",i.src);
            img.setAttribute("class","img"+index);
            a.appendChild(img);
            li.appendChild(a);
            ul.appendChild(li);
        }
        else {
            var li=document.createElement("li");
            var a=document.createElement("a");
            var img=document.createElement("img");
            a.setAttribute("href",i.href);
            //img.setAttribute("src",i.src);
            img.setAttribute("src",i.src);
            img.setAttribute("data-original",i.src);
            img.setAttribute("class","img"+index);
            a.appendChild(img);
            li.appendChild(a);
            ul.appendChild(li);
        }

    }

}
function createfuli(array) {
    var ul=document.getElementById("fuli");
    for(var i=0;i<array.length;i++)
    {
        var li=document.createElement("li");
        var a=document.createElement("a");
        var img=document.createElement("img");
        var p=document.createElement("p");
        ul.appendChild(li);
        li.appendChild(a);
        a.appendChild(img);
        a.appendChild(p);
        a.setAttribute("href",array[i].href);
        img.setAttribute("src",array[i].src);
        p.innerHTML=array[i].title;
    }
}
function createzengzhi(array) {
    var ul=document.getElementById("zengzhi");
    for(var i=0;i<array.length;i++)
    {
        var li=document.createElement("li");
        var a=document.createElement("a");
        var img=document.createElement("img");
        var p=document.createElement("p");
        ul.appendChild(li);
        li.appendChild(a);
        a.appendChild(img);
        a.appendChild(p);
        a.setAttribute("href","service?servicename="+array[i].href);
        img.setAttribute("src",array[i].src);
        p.innerHTML=array[i].title;
    }
}
window.onload = function () {
    $.post(
        "shouye",
        {
            type: "getimg"
        },
        function (result) {
            var obj = JSON.parse(result);
            createCarousel(obj.carousel);
            banner(obj.carousel.length + 2);
            createfuli(obj.fuli);
            createzengzhi(obj.zengzhi);
        }
    );
    $("#shiguchuli").click(function () {
        location.href="shenqingchoose.html?servicename=事故处理"
    });
    $("#mianfeinianshen").click(function () {
        location.href="shenqingchoose.html?servicename=免费年审"
    });
    $("#tixing").click(function () {
        location.href="shenqingchoose.html?servicename=提醒"
    });
};

function getphone() {
    var customerphone;
    $.get(
        "getphone",
        function (result) {
            if (result == "needlogin") {
                location.href = "userlogin";
            } else {
                customerphone = result;
            }
            console.log("https://cloud.hnjtbf.com/service/customerlogin?phone=" + customerphone);
            location.href = "https://cloud.hnjtbf.com/service/customerlogin?phone=" + customerphone;

        }
    );
}
