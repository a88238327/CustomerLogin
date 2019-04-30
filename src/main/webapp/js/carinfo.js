var carnumjson=getusercar();
var url_carnum=GetQueryString("carnum");

function getusercar() {
    $.get(
        "getusercar",
        function (result) {
            if (result=="needlogin")
            {
                location.href="userlogin";
            }
            else {
                var obj=JSON.parse(result);
                return obj;
            }

        }
    );
}

function createchoosecarnum() {
    var content="";
    for (var i=0;i<carnumjson.length;i++)
    {

        var a='<div class="choose_carnum" onclick="getbaocarinfo(\'CARNUM\');">CARNUM</div>';
        content=content+a.replace(/CARNUM/g,carnumjson[i].carnum);
    }
    console.log(content);
    layer.open({
        content: '<p class="choose_carnum_p">请选择车辆</p> '+content,
        skin: 'footer'
    });
}
function getbaocarinfo(num) {
    $.post(
        "",
        {
            carnum:num
        },
        function (result) {
            var obj=JSON.parse(result);
            $("#info_carnum").html(num);
            $("#car_type").html(obj.品牌型号);
            if (obj.baoxianxinxi=="")
            {
                createtip();
            }
            else {

                createbaoxianinfo(obj.baoxianxinxi);
            }
        }
    );

}

function createtip()
{
    $("#baoxian_info_content_ul").append('<li><div class="tip_no_info">请点击下方更新信息</div></li>');
}
function createbaoxianinfo(baoxianjson)
{
    $("#baoxian_info_content_ul").html("");
    for (var i=0;i<baoxianjson.length;i++)
    {
        if (baoxianjson[i].Excluding_deductible=="true")
        {
            $("#baoxian_info_content_ul").append('<li><p>'+baoxianjson[i].insurance+'</p><p class="bujimianpei" style="display: block">不计免赔</p><span >'+baoxianjson[i].type+'</span></li>');

        }else {
            $("#baoxian_info_content_ul").append('<li><p>'+baoxianjson[i].insurance+'</p><p class="bujimianpei" style="none: block">不计免赔</p><span >'+baoxianjson[i].type+'</span></li>');

        }
    }
}


window.onload=function () {
    getbaocarinfo(url_carnum);
};