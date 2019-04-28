$.get(
    "shareservlet",
    function (result) {
        if(result=="phone_error")
        {
            alert("请先登录");location.href="userlogin";
        }
        else{
            console.log(result);
            var obj=JSON.parse(result);
            for(var i=0;i<obj.length;i++)
            {
                var qrbox=document.getElementById("qrbox");
                var li=document.createElement("li");
                var a=document.createElement("a");
                var img=document.createElement("img");
                qrbox.appendChild(li);
                li.appendChild(a);
                a.appendChild(img);
                img.src=obj[i].src;
                a.href=obj[i].src;
            }

        }
    }
);