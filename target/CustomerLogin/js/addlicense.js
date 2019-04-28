var size = 720;
var img_front="";
var img_back="";
var xingming;
var xingbie;
var zhuzhi;
var guoji;
var zhunjiachexing;
var zhenghao;
var danganhao;
var chucilingzhengriqi;
var youxiaoqizhi;
$.get(
    "checkuser",
    function (result) {
        if (result == "phone_error") {
            alert("请先登录");
            location.href = "userlogin";
        }
    }
);
window.onload = function () {
    var upzhuye = document.getElementById("upzhuye");
    var upfuye = document.getElementById("upfuye");
    var zhuye = document.getElementById("zhuye");
    var fuye = document.getElementById("fuye");

    upzhuye.addEventListener("change", function () {
            fuye.src = "img/jiashizhengfuye.png";
            var dataurl;
            if (upzhuye.value) {
                var file = upzhuye.files[0];
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
                        var ctx = cvs.getContext('2d');
                        if(cvs.width<cvs.height)
                        {
                            var temp=cvs.height;
                            cvs.height= cvs.width;
                            cvs.width = temp;
                            ctx.rotate(-Math.PI/2);
                            ctx.translate(-cvs.height,0);
                        }
                        ctx.drawImage(this, 0, 0, cvs.width, cvs.height);
                        var newImageData = cvs.toDataURL(file_type, 0.8);   //重新生成图片，<span style="font-family: Arial, Helvetica, sans-serif;">fileType为用户选择的图片类型</span
                        zhuye.src = newImageData;
                        dataurl = newImageData.replace("data:" + file_type + ";base64,", '');
                        img_front = dataurl;
                        $.post(
                            "getlicenseinfo",
                            {
                                license_side: "front",
                                imgurl: dataurl
                            },
                            function (result) {
                                if (result=="error")
                                {
                                    alert("识别失败");
                                }
                                else {
                                    danganhao = "";
                                    var obj = JSON.parse(result);
                                    xingming = obj.姓名.words;
                                    xingbie = obj.性别.words;
                                    zhuzhi = obj.住址.words;
                                    guoji = obj.国籍.words;
                                    zhunjiachexing = obj.准驾车型.words;
                                    zhenghao = obj.证号.words;
                                    chucilingzhengriqi = insertStr(insertStr(obj.初次领证日期.words, 4, "/"), 7, "/");
                                    youxiaoqizhi = insertStr(insertStr(obj.至.words, 4, "/"), 7, "/");
                                    var xingming1 = document.getElementById("xingming");
                                    var xingbie1 = document.getElementById("xingbie");
                                    var zhuzhi1 = document.getElementById("zhuzhi");
                                    var guoji1 = document.getElementById("guoji");
                                    var zhunjiachexing1 = document.getElementById("zhunjiachexing");
                                    var zhenghao1 = document.getElementById("zhenghao");
                                    var chucilingzhengriqi1 = document.getElementById("chucilingzhengriqi");
                                    var youxiaoqizhi1 = document.getElementById("youxiaoqizhi");
                                    xingming1.innerHTML = xingming;
                                    xingbie1.innerHTML = xingbie;
                                    zhuzhi1.innerHTML = zhuzhi;
                                    guoji1.innerHTML = guoji;
                                    zhunjiachexing1.innerHTML = zhunjiachexing;
                                    zhenghao1.value = zhenghao;
                                    chucilingzhengriqi1.innerHTML = chucilingzhengriqi;
                                    youxiaoqizhi1.innerHTML = youxiaoqizhi;
                                }

                            }
                        );
                    }
                    // 以DataURL的形式读取文件:


                }
            }
        }
    );

    upfuye.addEventListener("change", function () {
        var dataurl;
        if (upfuye.value) {
            var file = upfuye.files[0];
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
                    var ctx = cvs.getContext('2d');
                    if(cvs.width<cvs.height)
                    {
                        var temp=cvs.height;
                        cvs.height= cvs.width;
                        cvs.width = temp;
                        ctx.rotate(-Math.PI/2);
                        ctx.translate(-cvs.height,0);
                    }
                    ctx.drawImage(this, 0, 0, cvs.width, cvs.height);
                    var newImageData = cvs.toDataURL(file_type, 0.8);   //重新生成图片，<span style="font-family: Arial, Helvetica, sans-serif;">fileType为用户选择的图片类型</span
                    fuye.src = newImageData;
                    dataurl = newImageData.replace("data:" + file_type + ";base64,", '');
                    img_back = dataurl;
                    $.post(
                        "getlicenseinfo",
                        {
                            license_side: "back",
                            imgurl: dataurl
                        },
                        function (result) {
                            if(result=="error")
                            {
                                alert("识别错误");
                            }
                            else {
                                var obj = JSON.parse(result);
                                danganhao = obj.证号.words;
                                var danganhao1 = document.getElementById("danganhao");
                                danganhao1.value = danganhao;
                            }


                        }
                    );
                }
                // 以DataURL的形式读取文件:

            }
        }
    });
    var tijiao = document.getElementById("tijiao");
    tijiao.addEventListener("click", function () {
        if (xingming == null) {
            alert("点击上图识别驾驶证主页");
        } else if (img_back=="") {
            alert("点击上图识别驾驶证副页");
        } else {
            $.post(
                "addlicense",
                {
                    img_front: img_front,
                    img_back: img_back,
                    danganhao:document.getElementById("danganhao").value,
                    xingming: xingming,
                    xingbie: xingbie,
                    zhuzhi: zhuzhi,
                    guoji: guoji,
                    zhunjiachexing: zhunjiachexing,
                    zhenghao: document.getElementById("zhenghao").value,
                    chucilingzhengriqi: document.getElementById("chucilingzhengriqi").innerHTML,
                    youxiaoqizhi: document.getElementById("youxiaoqizhi").innerHTML
                },
                function (result) {
                    if (result == "true") {
                        alert("添加成功,已为您开启年检提醒");
                        location.href = "license.html";
                    } else {
                        alert("添加失败该驾驶证已存在");
                    }
                }
            );
        }
    })

}

function insertStr(soure, start, newStr) {

    return soure.slice(0, start) + newStr + soure.slice(start);
}
