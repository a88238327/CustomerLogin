layer.open({
    type: 2,
    shadeClose: false,
    content: '加载中'

});


window.onload=function(){
    $.get(
        "getdingdan",
        function (result) {
            if(result=="phone_error")
            {
                location.href="userlogin";
            }
            else if(result=="")
            {

            }
            else {
                var obj=JSON.parse(result);
                createbox(obj);
                layer.closeAll();
            }
        }
    );
    function createbox(obj) {
        var box_list=document.getElementById("box_list");
        for(var i=0;i<obj.length;i++)
        {
            (function () {
                var box=document.createElement("div");
                var img=document.createElement("img");
                var name=document.createElement("p");
                var status=document.createElement("span");
                var content=document.createElement("p");
                var pingjia=document.createElement("div");
                var p=document.createElement("p");
                var li=document.createElement("li");
                var hr=document.createElement("hr");
                box_list.appendChild(li);
                li.appendChild(box);
                box.appendChild(img);
                box.appendChild(name);
                box.appendChild(status);
                box.appendChild(hr);
                box.appendChild(content);
                box.appendChild(pingjia);
                pingjia.appendChild(p);
                box.setAttribute("class","box");
                name.setAttribute("class","name");
                status.setAttribute("class","status");
                content.setAttribute("class","content");
                pingjia.setAttribute("class","pingjia");
                img.src=obj[i].touxiang;
                name.innerHTML=obj[i].name;
                status.innerHTML=obj[i].status;
                content.innerHTML=obj[i].content;
                var orderID=obj[i].orderID;
                p.innerHTML="评价";
                if(obj[i].evaluation=="true")
                {
                    pingjia.style.display="none";
                }
                else {
                    pingjia.addEventListener("click",function () {
                        location.href="http://cloud2.hnjtbf.com/Sellers/evaluation.html?orderID="+orderID;
                    })
                }

            })(i)
        }
    }
}

