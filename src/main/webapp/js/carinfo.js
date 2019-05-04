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
                location.href = "cars";
            } else {
                var obj = JSON.parse(result);
                for (var i=0;i<obj.length;i++)
                {
                    console.log(obj[i].号牌号码);
                    if (url_carnum==obj[i].号牌号码)
                    {
                        carnumjson= obj;
                        console.log(obj[i].号牌号码);
                        return;
                    }
                }
                alert("您还未添加该车辆");
                location.href="car.html";

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
            url_carnum=num;
            $("#info_carnum").html(num);
            $("#car_type").html(obj.品牌型号);
            if (obj.insurance == "noinsurance") {
                createtip();
            } else {
                var baoxianjson=JSON.parse(obj.insurance);
                createbaoxianinfo(baoxianjson);
            }
            layer.closeAll();

        }
    );

}

function createtip() {
    $("#baoxian_info_content_ul").html("");
    $("#baoxian_info_content_ul").append('<li><div class="tip_no_info">请点击下方更新信息</div></li>');
}

function createbaoxianinfo(baoxianjson) {
    $("#baoxian_info_content_ul").html("");
    for (var i = 0; i < baoxianjson.length; i++) {
        if (baoxianjson[i].Excluding_deductible == "不计免赔") {
            $("#baoxian_info_content_ul").append('<li><p>' + baoxianjson[i].insurance + '</p><p class="bujimianpei" style="display: block">不计免赔</p><span >' + baoxianjson[i].type + '</span></li>');

        } else {
            $("#baoxian_info_content_ul").append('<li><p>' + baoxianjson[i].insurance + '</p><p class="bujimianpei" style="display: none">不计免赔</p><span >' + baoxianjson[i].type + '</span></li>');

        }
    }
}

window.onload = function () {
    getusercar();
    getcarinfo(url_carnum);

    $("#buy_baoxian").click(function () {
        location.href="buybaoxian.html?carnum="+url_carnum;
    });
    $("#updata_baoxian_info").click(function () {
        location.href="addbaoxian.html?carnum="+url_carnum;
    });
};