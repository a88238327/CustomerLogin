layer.open({
    type: 2,
    shadeClose: false,
    content: '加载中'

});
var size=720;
var img_jiaoqiang="";
var img_shangye="";
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
    var shangyexianbaodan;
    changetype();
    getusercar();
    getcarinfo(url_carnum);
    createbaoxianinfo();
    $("#updata_baoxian_info").click(function () {
        console.log(buybaoxian);
        var flag=false;
        for (var item in buybaoxian) {
            if (buybaoxian[item][0] != "不投保") {
                flag=true;
                if (item!="交强险")
                {
                    shangyexianbaodan=true;
                }
                break;
            }

        }
        if (!flag)
        {
            tip("请添加信息！");
        }
        else if(buybaoxian["交强险"][0]=="投保"&&img_jiaoqiang=="")
        {
            tip("请上传交强险保单照片");
        }
        else if (shangyexianbaodan==true&&img_shangye=="")
        {
            tip("请上传商业险保单照片");
        }
        else if (img_jiaoqiang!=""&&$("#jiaoqiangxian_date_start").val()=="")
        {
            tip("请填写保单有效期");
        }
        else if (img_jiaoqiang!=""&&$("#jiaoqiangxian_date_end").val()=="")
        {
            tip("请填写保单有效期");
        }
        else if (img_shangye!=""&&$("#shangyexian_date_start").val()=="")
        {
            tip("请填写保单有效期");
        }
        else if (img_shangye!=""&&$("#shangyexian_date_end").val()=="")
        {
            tip("请填写保单有效期");
        }
        else {
            layer.open({
                type: 2,
                shadeClose: false,
                content: '提交中'

            });
            $.post(
                "addbaoxianinfo",
                {
                    carnum:url_carnum,
                    buybaoxian:JSON.stringify(buybaoxian),
                    img_jiaoqiang:img_jiaoqiang,
                    img_shangye:img_shangye,
                    jiaoqiangxian_date_start:$("#jiaoqiangxian_date_start").val(),
                    jiaoqiangxian_date_end:$("#jiaoqiangxian_date_end").val(),
                    shangyexian_date_start:$("#shangyexian_date_start").val(),
                    shangyexian_date_end:$("#shangyexian_date_end").val(),
                },
                function (result) {
                    layer.closeAll();
                    if (result=="true")
                    {
                        alert("提交成功!");
                        location.href="carinfo.html?carnum="+url_carnum;
                    }
                    else if (result=="needlogin")
                    {
                        alert("登录超时，请重新登录");
                        location.href="userlogin";
                    }
                    else {
                        tip("网络错误，请刷新");
                    }
                }
            );
        }

    });

};
function changetype() {
    laydate.render({
        elem: '#jiaoqiangxian_date_start',
        theme: '#1e90ff'
    });
    laydate.render({
        elem: '#jiaoqiangxian_date_end',
        theme: '#1e90ff'
    });
    laydate.render({
        elem: '#shangyexian_date_start',
        theme: '#1e90ff'
    });
    laydate.render({
        elem: '#shangyexian_date_end',
        theme: '#1e90ff'
    });
    upimg("img_box_left_up","img_box_left_img");
    upimg("img_box_right_up","img_box_right_img");

}
function upimg(ele,img)
{
    var btn1=document.getElementById(ele);
    var btn2=document.getElementById(img);
    btn1.addEventListener("change", function () {
            var dataurl;
            if (btn1.value) {
                var file = btn1.files[0];
                var file_type = file.type;
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function (e) {
                    var image = new Image();
                    image.src = e.target.result;
                    image.onload = function () {  //创建一个image对象，给canvas绘制使用
                        var cvs = document.createElement('canvas');
                        var scale = 1;

                        if (this.width > size || this.height > size) {  //600只是示例，可以根据具体的要求去设定
                            if (this.width > this.height) {
                                scale = size / this.width;
                            } else {
                                scale = size / this.height;
                            }
                        }
                        cvs.width = this.width * scale;
                        cvs.height = this.height * scale;     //计算等比缩小后图片宽高
                        var newwidth=cvs.width;
                        var newheight=cvs.height;
                        var ctx = cvs.getContext('2d');
                        if(cvs.width>cvs.height)
                        {
                            var temp=cvs.height;
                            cvs.height= cvs.width;
                            cvs.width = temp;
                            ctx.rotate(-Math.PI/2);
                            ctx.translate(-cvs.height,0);
                        }
                        //ctx.drawImage(image,0,0)
                        ctx.drawImage(this, 0, 0, newwidth, newheight);
                        var newImageData = cvs.toDataURL(file_type, 0.8);   //重新生成图片，<span style="font-family: Arial, Helvetica, sans-serif;">fileType为用户选择的图片类型</span
                        //btn2.style.height="auto";
                        btn2.src = newImageData;
                        dataurl = newImageData.replace("data:" + file_type + ";base64,", '');
                        if (ele=="img_box_left_up")
                        {
                            img_jiaoqiang=dataurl;
                        }
                        else {
                            img_shangye=dataurl;
                        }
                    }
                }
            }
        }
    );
}
function tip(content) {
    layer.open({
        content: content
        ,skin: 'msg'
        ,time: 2 //2秒后自动关闭
    });
}

