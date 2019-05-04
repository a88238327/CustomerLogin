$.get(
    "getcar",
    function (result) {
        if(result=="phone_error")
        {
            alert("请先登录");
            location.href="userlogin";
        }
        else {
            if(result!="")
            {
                var content=document.getElementById("content");

                var obj=JSON.parse(result);
                for( var i=0;i<obj.length;i++)
                {
                    (function () {
                        var li=document.createElement("li");
                        var a=document.createElement("a");
                        var p=document.createElement("p");
                        content.appendChild(li);
                        li.appendChild(a);
                        a.appendChild(p);
                        p.innerHTML=insert_flg(obj[i].number,"·",2);
                        if(obj[i].color=="蓝")
                        {
                            li.style.backgroundColor="dodgerblue";
                        }
                        if(obj[i].color=="黄")
                        {
                            li.style.backgroundColor="yellow";
                            p.style.color="black"
                            p.style.border="2px black solid";
                        }
                        if(obj[i].color=="绿")
                        {
                            li.style.backgroundColor="green";
                            p.style.color="black"
                            p.style.border="2px black solid";
                        }
                        var carnum=obj[i].number;
                        li.addEventListener("click",function () {
                            location.href="carinfo.html?carnum="+carnum;
                        })
                    })()

                }
            }
        }
    }
);
window.onload=function () {
    var tianjia=document.getElementById("tianjia");
    tianjia.addEventListener("click",function () {
        location.href="addcar.html";
    })
}
function insert_flg(str,flg,sn){
    var newstr="";

        var tmp=str.substring(0, sn);
        newstr+=tmp+flg+str.substring(sn, str.length);

    return newstr;
}