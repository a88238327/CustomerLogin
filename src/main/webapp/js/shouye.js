function createCarousel(array) {
    var ul=document.getElementById("Carousel");
    for(var i=0;i<array.length;i++)
    {
        if(i==0){
            createlia(array[array.length-1]);
            createlia(array[i]);
        }else if(i==array.length-1)
        {
            createlia(array[i]);
            createlia(array[0]);
        }
        else {
            createlia(array[i]);
        }
    }
    for(var i=0;i<array.length-1;i++){
        var ul=document.getElementById("dian");
        var li=document.createElement("li");
        ul.appendChild(li);
    }
    function createlia(i) {
        var li=document.createElement("li");
        var a=document.createElement("a");
        var img=document.createElement("img");
        a.setAttribute("href",i.href);
        img.setAttribute("src",i.src);
        a.appendChild(img);
        li.appendChild(a);
        ul.appendChild(li);
    }

}
function createfuli(array) {
    var ul=document.getElementById("fuli");
    for(var i=0;i<array.length;i++)
    {
        var li=document.createElement("li");
        var a=document.createElement("a");
        var img=document.createElement("img");
        var p=document.createElement("p");
        ul.appendChild(li);
        li.appendChild(a);
        a.appendChild(img);
        a.appendChild(p);
        a.setAttribute("href",array[i].href);
        img.setAttribute("src",array[i].src);
        p.innerHTML=array[i].title;
    }
}
function createzengzhi(array) {
    var ul=document.getElementById("zengzhi");
    for(var i=0;i<array.length;i++)
    {
        var li=document.createElement("li");
        var a=document.createElement("a");
        var img=document.createElement("img");
        var p=document.createElement("p");
        ul.appendChild(li);
        li.appendChild(a);
        a.appendChild(img);
        a.appendChild(p);
        a.setAttribute("href","service?servicename="+array[i].href);
        img.setAttribute("src",array[i].src);
        p.innerHTML=array[i].title;
    }
}
