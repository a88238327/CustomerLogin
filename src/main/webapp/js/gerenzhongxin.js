window.onload=function () {
    var box_left=document.getElementById("box_left");
    var box_right=document.getElementById("box_right");
    var My_Car=document.getElementById("My_Car");
    var updata_left=document.getElementById("updata_left");
    var updata_right=document.getElementById("updata_right");
    var My_License=document.getElementById("My_License");
    createtouch(My_License);
    createtouch(box_left);
    createtouch(box_right);
    createtouch(My_Car);
    createtouch(updata_left);
    createtouch(updata_right);
    window.addEventListener('contextmenu', function(e){
        e.preventDefault();
    });
    $.get(
        "gerenzhongxin",
        function (result) {
            if(result=="phone_error")
            {
                alert("请先登录");
                location.href="userlogin";
            }
            else{
                var sharenumber=document.getElementById("sharenumber");
                sharenumber.innerHTML=result;
            }
        }
    );
}
function createtouch(element) {
    element.addEventListener("touchstart",function () {
        element.style.backgroundColor="darkblue";
    })
    element.addEventListener("touchend",function () {
        element.style.removeProperty("background-color");
    })
}