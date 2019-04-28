$.get(
    "checkuser",
    function (result) {
        if (result == "phone_error") {
            alert("请先登录");
            location.href = "userlogin";
        }
        $.get(
            "getlicense",
            function (result) {
                if(result=="")
                {
                }
                else {
                    var obj=JSON.parse(result);
                    var card=document.getElementById("card");
                    card.style.display="block"
                    var tianjia=document.getElementById("tianjia");
                    tianjia.style.display="none"
                    var xingming=document.getElementById("xingming");
                    var xingbie=document.getElementById("xingbie");
                    var zhunjiachexing=document.getElementById("zhunjiachexing");
                    var chucilingzhengriqi=document.getElementById("chucilingzhengriqi");
                    var youxiaoqizhi=document.getElementById("youxiaoqizhi");
                    var danganhao=document.getElementById("danganhao");
                    var zhenghao=document.getElementById("zhenghao");
                    xingming.innerHTML=obj.姓名;
                    xingbie.innerHTML=obj.性别;
                    zhunjiachexing.innerHTML=obj.准驾车型;
                    chucilingzhengriqi.innerHTML=obj.初次领证日期;
                    youxiaoqizhi.innerHTML=obj.有效期至;
                    danganhao.innerHTML=obj.档案号;
                    zhenghao.innerHTML=obj.证号;
                }
            }
        );
    }
);

window.onload=function () {
    var tianjia=document.getElementById("tianjia");
    tianjia.addEventListener("click",function () {
        location.href="addlicense.html";
    })
}