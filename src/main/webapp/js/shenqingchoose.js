var servicename=GetQueryString("servicename");
$("#carnum").html(servicename);
if(servicename=="事故处理")
{
    $(".baoxian_info_head p").html("请点击需要处理事故的车辆（点击号牌）")
}
if(servicename=="免费年审")
{
    $(".baoxian_info_head p").html("请点击需要办理年审的车辆（点击号牌）")
}
if(servicename=="提醒")
{
    $(".baoxian_info_head p").html("请点击需要开启提醒的车辆（点击号牌）")
}
window.onload=function () {
    $("#tianjia").click(function () {
        location.href = "addcar.html";
    });
    $.get(
        "getcar",
        function (result) {
            if (result == "phone_error") {
                alert("请先登录");
                location.href = "userlogin";
            } else {
                if (result != "") {
                    var content = document.getElementById("content");
                    var obj = JSON.parse(result);
                    for (var i = 0; i < obj.length; i++) {
                        (function () {
                            var li = document.createElement("li");
                            var a = document.createElement("a");
                            var p = document.createElement("p");
                            content.appendChild(li);
                            li.appendChild(a);
                            a.appendChild(p);
                            p.innerHTML = insert_flg(obj[i].number, "·", 2);
                            if (obj[i].color == "蓝") {
                                li.style.backgroundColor = "dodgerblue";
                            }
                            if (obj[i].color == "黄") {
                                li.style.backgroundColor = "yellow";
                                p.style.color = "black"
                                p.style.border = "2px black solid";
                            }
                            if (obj[i].color == "绿") {
                                li.style.backgroundColor = "green";
                                p.style.color = "black"
                                p.style.border = "2px black solid";
                            }
                            var carnum = obj[i].number;
                            li.addEventListener("click", function () {
                                if (servicename=="提醒")
                                {
                                    location.href = "remind.html?carnum=" + carnum;

                                }else {
                                    location.href = "shenqing.html?carnum=" + carnum+"&servicename="+servicename;

                                }
                            })
                        })()

                    }
                }
            }
        }
    );

};
function insert_flg(str, flg, sn) {
    var newstr = "";
    var tmp = str.substring(0, sn);
    newstr += tmp + flg + str.substring(sn, str.length);

    return newstr;
}