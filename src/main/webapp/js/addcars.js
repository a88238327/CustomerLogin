var selecttext = "";
var $url = window.location.href;
var pingpaixinghao = "";
var fazhengriqi = "";
var shiyongxingzhi = "";
var fadongjihaoma = "";
var haopaihaoma = "";
var suoyouren = "";
var zhuzhi = "";
var zhuceriqi = "";
var cheliangshibiedaihao = "";
var cheliangleixing = "";
var jiancejilu = "";
var hedingzaizhiliang = "";
var zhengbeizhiliang = "";
var waiguochicun = "";
var hedingzairenshu = "";
var zongzhiliang = "";
var zhunqianyinzongzhiliang = "";
var danganhao = "";
var size = 720;
var img_front;
var img_back;
$.get(
    "checkuser",
    function (result) {
        if (result == "phone_error") {
            alert("请先登录");
            location.href = "userlogin";
        }
    }
);
/**
 * 旋转图片
 * @param image         HTMLImageElement
 * @returns newImage    HTMLImageElement
 */
function rotateImage(image) {
    console.log('rotateImage');

    var width = image.width;
    var height = image.height;

    var canvas = document.createElement("canvas")
    var ctx = canvas.getContext('2d');

    var newImage = new Image();

    //旋转图片操作
    EXIF.getData(image,function () {
            var orientation = EXIF.getTag(this,'Orientation');
            // orientation = 6;//测试数据
            console.log('orientation:'+orientation);
            switch (orientation){
                //正常状态
                case 1:
                    console.log('旋转0°');
                    // canvas.height = height;
                    // canvas.width = width;
                    newImage = image;
                    break;
                //旋转90度
                case 6:
                    console.log('旋转90°');
                    canvas.height = width;
                    canvas.width = height;
                    ctx.rotate(Math.PI/2);
                    ctx.translate(0,-height);

                    ctx.drawImage(image,0,0)
                    imageDate = canvas.toDataURL('Image/jpeg',1)
                    newImage.src = imageDate;
                    break;
                //旋转180°
                case 3:
                    console.log('旋转180°');
                    canvas.height = height;
                    canvas.width = width;
                    ctx.rotate(Math.PI);
                    ctx.translate(-width,-height);

                    ctx.drawImage(image,0,0)
                    imageDate = canvas.toDataURL('Image/jpeg',1)
                    newImage.src = imageDate;
                    break;
                //旋转270°
                case 8:
                    console.log('旋转270°');
                    canvas.height = width;
                    canvas.width = height;
                    ctx.rotate(-Math.PI/2);
                    ctx.translate(-height,0);

                    ctx.drawImage(image,0,0)
                    imageDate = canvas.toDataURL('Image/jpeg',1)
                    newImage.src = imageDate;
                    break;
                //undefined时不旋转
                case undefined:
                    console.log('undefined  不旋转');
                    newImage = image;
                    break;
            }
        }
    );
    return newImage;
}
window.onload = function () {
    var upzhuye = document.getElementById("upzhuye");
    var upfuye = document.getElementById("upfuye");
    var zhuye = document.getElementById("zhuye");
    var fuye = document.getElementById("fuye");
    var xiacinianjian=document.getElementById("xiacinianjian");
    xiacinianjian.addEventListener("change",function () {
        if(xiacinianjian.value){
            jiancejilu=xiacinianjian.value;
        }
    });
    upzhuye.addEventListener("change", function () {
            fuye.src = "img/xingshizhengfuye.png";
            var dataurl;
            load.style.display = "block";
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
                        var newwidth=cvs.width;
                        var newheight=cvs.height;
                        var ctx = cvs.getContext('2d');
                        if(cvs.width<cvs.height)
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
                        zhuye.src = newImageData;
                        dataurl = newImageData.replace("data:" + file_type + ";base64,", '');
                        img_front=dataurl;
                        $.post(
                            "getcarinfo",
                            {
                                vehicle_license_side: "front",
                                imgurl: dataurl
                            },
                            function (result) {
                                load.style.display = "none";
                                if(result=="error")
                                {
                                    alert("识别错误");

                                }
                                else {
                                    jiancejilu = "";
                                    hedingzaizhiliang = "";
                                    zhengbeizhiliang = "";
                                    waiguochicun = "";
                                    hedingzairenshu = "";
                                    zongzhiliang = "";
                                    zhunqianyinzongzhiliang = "";
                                    danganhao = "";
                                    var obj = JSON.parse(result);
                                    load.style.display = "none";
                                    pingpaixinghao = obj.品牌型号.words;
                                    fazhengriqi = obj.发证日期.words;
                                    shiyongxingzhi = obj.使用性质.words;
                                    fadongjihaoma = obj.发动机号码.words;
                                    haopaihaoma = obj.号牌号码.words;
                                    suoyouren = obj.所有人.words;
                                    zhuzhi = obj.住址.words;
                                    zhuceriqi = obj.注册日期.words;
                                    cheliangshibiedaihao = obj.车辆识别代号.words;
                                    cheliangleixing = obj.车辆类型.words;
                                    var chepaihao = document.getElementById("chepaihao");
                                    var pingpai = document.getElementById("pingpai");
                                    var xingzhi = document.getElementById("xingzhi");
                                    var fazheng = document.getElementById("fazheng");
                                    var zhuce = document.getElementById("zhuce");
                                    var leixing = document.getElementById("leixing");
                                    var chiyouren = document.getElementById("chiyouren");
                                    chepaihao.innerHTML = haopaihaoma;
                                    pingpai.innerHTML = pingpaixinghao;
                                    xingzhi.innerHTML = shiyongxingzhi;
                                    fazheng.innerHTML = fazhengriqi;
                                    zhuce.innerHTML = zhuceriqi;
                                    leixing.innerHTML = cheliangleixing;
                                    chiyouren.innerHTML = suoyouren;
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
            load.style.display = "block";
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
                    img_back=dataurl;
                    $.post(
                        "getcarinfo",
                        {
                            vehicle_license_side: "back",
                            imgurl: dataurl
                        },
                        function (result) {
                        if(result=="error")
                            {
                                alert("识别错误");
                                return;
                            }
                            var obj = JSON.parse(result);
                            load.style.display = "none";
                            if (obj.号牌号码.words != haopaihaoma) {
                                alert("请上传同一台车的副本");
                            }
                            else {
                                var str = obj.检验记录.words;
                                hedingzaizhiliang = obj.核定载质量.words;
                                zhengbeizhiliang = obj.整备质量.words;
                                waiguochicun = obj.外廓尺寸.words;
                                hedingzairenshu = obj.核定载人数.words;
                                zongzhiliang = obj.总质量.words;
                                zhunqianyinzongzhiliang = obj.准牵引总质量.words;
                                danganhao = obj.档案编号.words;
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
        var obj = document.getElementById("select"); //定位id
        var index = obj.selectedIndex; // 选中索引
        var xiacinianjian=document.getElementById("xiacinianjian");
        selecttext = obj.options[index].text; // 选中文本
        if (haopaihaoma == "") {
            alert("点击上图识别行驶证主页");
        } else if (danganhao=="") {
            alert("点击上图识别行驶证副页");
        } else if (selecttext == "") {
            alert("请选择车牌颜色");
        }else if (!xiacinianjian.value) {
            alert("请选择下次年检日期");
        }
        else {
            $.post(
                "addcar",
                {
                    img_front:img_front,
                    img_back:img_back,
                    selecttext: selecttext,
                    pingpaixinghao: pingpaixinghao,
                    fazhengriqi: fazhengriqi,
                    shiyongxingzhi: shiyongxingzhi,
                    fadongjihaoma: fadongjihaoma,
                    haopaihaoma: haopaihaoma,
                    suoyouren: suoyouren,
                    zhuzhi: zhuzhi,
                    zhuceriqi: zhuceriqi,
                    cheliangshibiedaihao: cheliangshibiedaihao,
                    cheliangleixing: cheliangleixing,
                    jiancejilu: jiancejilu,
                    hedingzaizhiliang: hedingzaizhiliang,
                    zhengbeizhiliang: zhengbeizhiliang,
                    waiguochicun: waiguochicun,
                    hedingzairenshu: hedingzairenshu,
                    zongzhiliang: zongzhiliang,
                    zhunqianyinzongzhiliang: zhunqianyinzongzhiliang,
                    danganhao: danganhao
                },
                function (result) {
                    if (result == "true") {
                        alert("添加成功");
                        location.href = history.back(-1);
                    } else {
                        alert("添加失败该车辆已存在");
                    }
                }
            );
        }
    })

};
