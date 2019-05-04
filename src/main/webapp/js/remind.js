layer.open({
    type: 2,
    shadeClose: false,
    content: '加载中'

});
var url_carnum = "";
var url_carnum = GetQueryString("carnum");
var start = function () {
    if (url_carnum == "") {
        alert("请先选择车辆");
        location.href = "cars";
    }
};
var carnumjson;

function getusercar() {
    $.get(
        "getusercar",
        function (result) {
            console.log(result);
            if (result == "needlogin") {
                alert("请先登录");
                location.href = "userlogin";
            } else if (result == "nocar") {
                alert("您还未添加车辆");
                location.href = history.back(-1);
            } else {
                var obj = JSON.parse(result);
                for (var i = 0; i < obj.length; i++) {
                    console.log(obj[i].号牌号码);
                    if (url_carnum == obj[i].号牌号码) {
                        carnumjson = obj;
                        console.log(obj[i].号牌号码);
                        return;
                    }
                }
                alert("您还未添加该车辆");
                location.href = "car.html";

            }

        }
    );
}

function createchoosecarnum() {
    console.log(carnumjson);
    var content = "";
    for (var i = 0; i < carnumjson.length; i++) {

        var a = '<div class="choose_carnum" onclick="getcarinfo(\'CARNUM\');">CARNUM</div>';
        content = content + a.replace(/CARNUM/g, carnumjson[i].号牌号码);
        console.log(content);
    }
    console.log(content);
    layer.open({
        content: '<p class="choose_carnum_p">请选择车辆</p> ' + content,
        skin: 'footer'
    });
}

function getcarinfo(num) {
    layer.open({
        type: 2,
        shadeClose: false,
        content: '加载中'

    });
    $.post(
        "getcarbaoxianinfo",
        {
            carnum: num
        },
        function (result) {
            layer.closeAll();
            var obj = JSON.parse(result);
            console.log(result);
            url_carnum = num;
            $("#info_carnum").html(num);
            $("#car_type").html(obj.品牌型号);
            layer.closeAll();

        }
    );

}

function createli(obj) {
    for (var i = 0; i < obj.length; i++) {
        (function () {
            if (obj[i].stauts!="true")
            {
                var content=obj[i].remmind_content;
                $("#baoxian_info_content_ul").append(' <li><p>车辆年审提醒</p><div class="kaiguan"><span id="'+obj[i].remmind_content+'" class="switch-off" themeColor="#6d9eeb"></span></div></li>')
                switchEvent("#"+obj[i].remmind_content,function () {
                    //开
                    updatastats(content,"true");
                }, function () {
                    //关
                    updatastats(content,"false");
                });
            }
            else {
                $("#baoxian_info_content_ul").append(' <li><p>车辆年审提醒</p><div class="kaiguan"><span id="'+obj[i].remmind_content+'" class="switch-on" themeColor="#6d9eeb"></span></div></li>')
                switchEvent("#"+obj[i].remmind_content,function () {
                    //开
                    updatastats(content,"true");
                }, function () {
                    //关
                    updatastats(content,"false");
                });
            }

        })();

    }
}
function updatastats(content,stauts) {
    $.post(
        "updatatixing",
        {
            carnum:url_carnum,
            remind_content:content,
            stauts:stauts
        },
        function (result) {
            if (result=="true")
            {
                layer.open({
                    content: "修改成功！"
                    ,skin: 'msg'
                    ,time: 2 //2秒后自动关闭
                });
            }
            else
            {
                alert("登录超时，请重新登录");
                location.href="userlogin";
            }
        }
    );
}

function gettixing() {
    $.post(
        "gettixing",
        {
            carnum: url_carnum,
        },
        function (result) {
            var obj = JSON.parse(result);
        }
    );
}

window.onload = function () {
    getusercar();
    getcarinfo(url_carnum);

    $("#buy_baoxian").click(function () {
        location.href = "buybaoxian.html?carnum=" + url_carnum;
    });
    $("#updata_baoxian_info").click(function () {
        location.href = "addbaoxian.html?carnum=" + url_carnum;
    });
};