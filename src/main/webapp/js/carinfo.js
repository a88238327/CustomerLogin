var url_carnum = "";
var url_carnum = GetQueryString("carnum");
alert(url_carnum);
var start = function () {
    if (url_carnum == "") {
        alert("请先选择车辆");
        location.href = "cars";
    }
};
//var carnumjson = getusercar();

function getusercar() {
    $.get(
        "getusercar",
        function (result) {
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
                    if (url_carnum==obj[i].carnum)
                    {
                        return obj;
                    }
                }
                alert("您还未添加该车辆");
                location.href="car.html";

            }

        }
    );
}

function createchoosecarnum() {
    var content = "";
    for (var i = 0; i < carnumjson.length; i++) {

        var a = '<div class="choose_carnum" onclick="getcarinfo(\'CARNUM\');">CARNUM</div>';
        content = content + a.replace(/CARNUM/g, carnumjson[i].carnum);
    }
    console.log(content);
    layer.open({
        content: '<p class="choose_carnum_p">请选择车辆</p> ' + content,
        skin: 'footer'
    });
}

function getcarinfo(num) {
    $.post(
        "getcarbaoxianinfo",
        {
            carnum: num
        },
        function (result) {
            console.log(result);
            url_carnum=num;
            if (result == "noinsurance") {
                createtip();
            } else {
                var obj = JSON.parse(result);

                $("#info_carnum").html(num);
                $("#car_type").html(obj[0].品牌型号);
                createbaoxianinfo(obj);
            }

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
        if (baoxianjson[i].Excluding_deductible == "true") {
            $("#baoxian_info_content_ul").append('<li><p>' + baoxianjson[i].insurance + '</p><p class="bujimianpei" style="display: block">不计免赔</p><span >' + baoxianjson[i].type + '</span></li>');

        } else {
            $("#baoxian_info_content_ul").append('<li><p>' + baoxianjson[i].insurance + '</p><p class="bujimianpei" style="display: none">不计免赔</p><span >' + baoxianjson[i].type + '</span></li>');

        }
    }
}

window.onload = function () {
    getcarinfo(url_carnum);
    $("#updata_baoxian_info").click(function () {
        location.href="addbaoxian.html?carnum="+url_carnum;
    })
};