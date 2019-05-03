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
var bjmp = ["不计免赔", "否"];
var baoxianinfo = {
    "交强险": ["不投保", "投保"],
    "车辆损失险": {"不投保": [], "投保": bjmp},
    "第三者险": {"不投保": [], "30万": bjmp, "50万": bjmp, "100万": bjmp},
    "司机责任险": {
        "不投保": [],
        "1万": bjmp,
        "2万": bjmp,
        "3万": bjmp,
        "4万": bjmp,
        "5万": bjmp,
        "6万": bjmp,
        "7万": bjmp,
        "8万": bjmp,
        "9万": bjmp,
        "10万": bjmp,
        "11万": bjmp,
        "12万": bjmp,
        "13万": bjmp,
        "14万": bjmp,
        "15万": bjmp,
        "16万": bjmp,
        "17万": bjmp,
        "18万": bjmp,
        "19万": bjmp,
        "20万": bjmp
    },
    "乘客责任险": {
        "不投保": [],
        "1万": bjmp,
        "2万": bjmp,
        "3万": bjmp,
        "4万": bjmp,
        "5万": bjmp,
        "6万": bjmp,
        "7万": bjmp,
        "8万": bjmp,
        "9万": bjmp,
        "10万": bjmp,
        "11万": bjmp,
        "12万": bjmp,
        "13万": bjmp,
        "14万": bjmp,
        "15万": bjmp,
        "16万": bjmp,
        "17万": bjmp,
        "18万": bjmp,
        "19万": bjmp,
        "20万": bjmp
    },
    "车辆盗抢": {"不投保": [], "投保": bjmp},
    "玻璃单独破碎险": ["不投保", "国产玻璃", "进口玻璃"],
    "涉水险": {"不投保": [], "投保": bjmp},
    "自燃险": {"不投保": [], "投保": bjmp},
    "划痕险": {"不投保": [], "投保": bjmp},
    "第三方特约险": ["不投保", "投保"]
};
for (var item in baoxianinfo) {
    console.log(item);
}
var buybaoxian = {
    "交强险": ["不投保",""],
    "车辆损失险": ["不投保",""],
    "第三者险": ["不投保",""],
    "司机责任险": ["不投保",""],
    "乘客责任险": ["不投保",""],
    "车辆盗抢": ["不投保",""],
    "玻璃单独破碎险": ["不投保",""],
    "涉水险": ["不投保",""],
    "自燃险": ["不投保",""],
    "划痕险": ["不投保",""],
    "第三方特约险": ["不投保",""]
};

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
                location.href = "cars";
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
            //createbaoxianinfo();
            layer.closeAll();
        }
    );
}

function createtip() {
    $("#baoxian_info_content_ul").html("");
    $("#baoxian_info_content_ul").append('<li><div class="tip_no_info">请点击下方更新信息</div></li>');
}

function createbaoxianinfo() {
    $("#baoxian_info_content_ul").html("");
    for (var item in baoxianinfo) {
        (function () {
            $("#baoxian_info_content_ul").append('<li id="' + item + '"><p>' + item + '</p><p class="bujimianpei" >不计免赔</p><span >不投保</span></li>');
            var btn = document.getElementById(item);
            var i = item;
            btn.addEventListener("click", function () {
                var pickerView = new PickerView({
                    bindElem: btn, // 绑定的元素
                    data: baoxianinfo[i], // 说明：该参数必须符合json格式 且最里层是个数组，如上面的data变量所展示的[3,4]。
                    title: i, // 顶部标题文本 默认为“标题”
                    leftText: '取消', // 头部左侧按钮文本 默认为‘取消’
                    rightText: '确定', // 头部右侧按钮文本 默认为“确定”
                    rightFn: function (selectArr) {  // 点击头部右侧按钮的回调函数，参数为一个数组，数组对应滚轮中每项对应的值
                        console.log(selectArr, 'selectarr');
                        if (selectArr.length == 1) {
                            $("#" + i + " span").html(selectArr[0]);
                            if (selectArr[0] == "不投保") {
                                $("#" + i + " span").css("color", "red");
                            } else {
                                $("#" + i + " span").css("color", "green");

                            }
                            buybaoxian[i] = [selectArr[0], ""];
                        } else {
                            $("#" + i + " span").html(selectArr[0]);
                            if (selectArr[1] == "不计免赔") {
                                $("#" + i + " .bujimianpei").css("display", "block");
                            } else {
                                $("#" + i + " .bujimianpei").css("display", "none");

                            }
                            if (selectArr[0] == "不投保") {
                                $("#" + i + " span").css("color", "red");
                            } else {
                                $("#" + i + " span").css("color", "green");

                            }

                            buybaoxian[i] = [selectArr[0], selectArr[1]];
                        }
                        //document.querySelector(".showText").innerText = selectArr.join("-");
                    }
                });
            })
        })()
    }

}

window.onload = function () {
    getusercar();
    getcarinfo(url_carnum);
    createbaoxianinfo();
    $("#updata_baoxian_info").click(function () {
        console.log(buybaoxian);
        var flag=false;

        for (var item in buybaoxian) {
            if (buybaoxian[item][0] != "不投保") {
                flag=true;
                break;
            }

        }
        if (!flag)
        {
            alert("未投保，无需提交！");
        }
        else {
            layer.open({
                type: 2,
                shadeClose: false,
                content: '提交中'

            });
            $.post(
                "baoxianyuyue",
                {
                    carnum:url_carnum,
                    buybaoxian:JSON.stringify(buybaoxian)
                },
                function (result) {
                    layer.closeAll();
                    if (result=="true")
                    {
                        alert("提交成功，稍后有工作人员联系您");
                        location.href="carinfo.html?carnum="+url_carnum;
                    }
                    else {
                        alert("网络错误，请刷新");
                    }
                }
            );
        }

    });

};
