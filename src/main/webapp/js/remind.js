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

            gettixing()


        }
    );

}

function createli(obj) {
    for (var i = 0; i < obj.length; i++) {
        (function () {
            var content=obj[i].remmind_content;
            if (obj[i].stauts!="true")
            {

                $("#baoxian_info_content_ul").append(' <li><p>'+obj[i].remmind_content+'</p><div class="kaiguan"><span id="'+obj[i].remmind_content+'" class="switch-off" themeColor="#6d9eeb"></span></div></li>');
                switchEvent("#"+obj[i].remmind_content,function () {
                    //开
                    updatastats(content,"false");
                }, function () {
                    //关
                    updatastats(content,"true");

                });
            }
            else {
                $("#baoxian_info_content_ul").append(' <li><p>'+obj[i].remmind_content+'</p><div class="kaiguan"><span id="'+obj[i].remmind_content+'" class="switch-on" themeColor="#6d9eeb"></span></div></li>');
                switchEvent("#"+obj[i].remmind_content,function () {
                    //开
                    updatastats(content,"false");
                }, function () {
                    //关
                    updatastats(content,"true");


                });
            }

        })();

    }
    go();
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
            createli(obj);
            console.log(obj);
            layer.closeAll();
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
        location.href = "shouye.html"
    });
};


function go() {
    var honeySwitch = {};
    honeySwitch.themeColor = "rgb(100, 189, 99)";
    honeySwitch.init = function() {
        var s = "<span class='slider'></span>";
        $("[class^=switch]").append(s);
        $("[class^=switch]").click(function() {
            if ($(this).hasClass("switch-disabled")) {
                return;
            }
            if ($(this).hasClass("switch-on")) {
                $(this).removeClass("switch-on").addClass("switch-off");
                $(".switch-off").css({
                    'border-color' : '#dfdfdf',
                    'box-shadow' : 'rgb(223, 223, 223) 0px 0px 0px 0px inset',
                    'background-color' : 'rgb(255, 255, 255)'
                });
            } else {
                $(this).removeClass("switch-off").addClass("switch-on");
                if (honeySwitch.themeColor) {
                    var c = honeySwitch.themeColor;
                    $(this).css({
                        'border-color' : c,
                        'box-shadow' : c + ' 0px 0px 0px 16px inset',
                        'background-color' : c
                    });
                }
                if ($(this).attr('themeColor')) {
                    var c2 = $(this).attr('themeColor');
                    $(this).css({
                        'border-color' : c2,
                        'box-shadow' : c2 + ' 0px 0px 0px 16px inset',
                        'background-color' : c2
                    });
                }
            }
        });
        window.switchEvent = function(ele, on, off) {
            $(ele).click(function() {
                if ($(this).hasClass("switch-disabled")) {
                    return;
                }
                if ($(this).hasClass('switch-on')) {
                    if ( typeof on == 'function') {
                        on();
                    }
                } else {
                    if ( typeof off == 'function') {
                        off();
                    }
                }
            });
        }
        if (this.themeColor) {
            var c = this.themeColor;
            $(".switch-on").css({
                'border-color' : c,
                'box-shadow' : c + ' 0px 0px 0px 16px inset',
                'background-color' : c
            });
            $(".switch-off").css({
                'border-color' : '#dfdfdf',
                'box-shadow' : 'rgb(223, 223, 223) 0px 0px 0px 0px inset',
                'background-color' : 'rgb(255, 255, 255)'
            });
        }
        if ($('[themeColor]').length > 0) {
            $('[themeColor]').each(function() {
                var c = $(this).attr('themeColor') || honeySwitch.themeColor;
                if ($(this).hasClass("switch-on")) {
                    $(this).css({
                        'border-color' : c,
                        'box-shadow' : c + ' 0px 0px 0px 16px inset',
                        'background-color' : c
                    });
                } else {
                    $(".switch-off").css({
                        'border-color' : '#dfdfdf',
                        'box-shadow' : 'rgb(223, 223, 223) 0px 0px 0px 0px inset',
                        'background-color' : 'rgb(255, 255, 255)'
                    });
                }
            });
        }
    };
    honeySwitch.showOn = function(ele) {
        $(ele).removeClass("switch-off").addClass("switch-on");
        if(honeySwitch.themeColor){
            var c = honeySwitch.themeColor;
            $(ele).css({
                'border-color' : c,
                'box-shadow' : c + ' 0px 0px 0px 16px inset',
                'background-color' : c
            });
        }
        if ($(ele).attr('themeColor')) {
            var c2 = $(ele).attr('themeColor');
            $(ele).css({
                'border-color' : c2,
                'box-shadow' : c2 + ' 0px 0px 0px 16px inset',
                'background-color' : c2
            });
        }
    }
    honeySwitch.showOff = function(ele) {
        $(ele).removeClass("switch-on").addClass("switch-off");
        $(".switch-off").css({
            'border-color' : '#dfdfdf',
            'box-shadow' : 'rgb(223, 223, 223) 0px 0px 0px 0px inset',
            'background-color' : 'rgb(255, 255, 255)'
        });
    }
    $(function() {
        honeySwitch.init();
    });
}