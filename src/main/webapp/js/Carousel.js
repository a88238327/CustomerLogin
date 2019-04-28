window.onload=function () {
    banner(7);
}
var banner=function (maximgcount) {
    //初始化触摸起始x坐标点
    var startx=0;
    //初始化触摸距离起始点距离(x坐标点)
    var distancex=0;
    //初始化触摸状态
    var ismove=false;
    //获取需要操作的大容器
    var banner=document.querySelector('#header');
    //获取轮播图宽度
    var width=banner.offsetWidth;
    //获取图片盒子
    var imageBox=banner.querySelector('ul:first-child');
    //点容器
    var pintBox=banner.querySelector('ul:last-child');
    // //所有的点
    var points=pintBox.querySelectorAll('li');
    //添加过度
    var addTransition=function () {
        imageBox.style.transition='all 0.2s';
        imageBox.style.webkitTransition='all 0.2s'//兼容操作
    }
    //清除过度
    var removeTransition=function () {
        imageBox.style.transition='none';
        imageBox.style.webkitTransition='none'//兼容操作
    }
    //设置位移
    var setTranslateX=function (translatex) {
        imageBox.style.transform='translateX('+translatex+'px)';//移轮播图的宽度
        imageBox.style.webkitTransform='translateX('+translatex+'px)';//移轮播图的宽度
    }
    var index=1;
    var timer=setInterval(function () {
        index ++;
        //过度
        addTransition();
        setTranslateX(-index*width);
    },3000);
    imageBox.addEventListener('transitionend',function () {
        if(index>=maximgcount-1)
        {
            //瞬间定位到第一张
            index=1;
            removeTransition();
            setTranslateX(-index*width);
        }
        else if(index<=0){
            //瞬间定位到倒数第二张
            index=maximgcount-2;
            removeTransition();
            setTranslateX(-index*width);
        }
        setPoint();
    });
    var setPoint=function () {

        for(var i=0;i<points.length;i++)
        {
            points[i].classList.remove('now');
        }
        points[index-1].classList.add('now');
    }
    //touch开始
    imageBox.addEventListener('touchstart',function (e) {
        //清除计时器
        clearInterval(timer);
        //记录开始的位置
        startx=e.touches[0].clientX;
    })
    //touchmove
    imageBox.addEventListener('touchmove',function (e) {
        //记录当前的位置
        var movex=e.touches[0].clientX;
        distancex=movex-startx;
        var translatex=-index*width+distancex;
        ismove=true;
        removeTransition();
        setTranslateX(translatex);
    })
    //touch结束
    imageBox.addEventListener('touchend',function (e) {
        //滑动事件结束之后来判断当前滑动的距离
        //有一个范围  如果大于3分之一切换图片  反之吸附回去定位回去
        if(ismove){
            //distancex的绝对值
            if(Math.abs(distancex)<width/3){
                //当滑动距离不够的时候，吸附回去，过度位移
                //过度
                addTransition();
                //位移
                setTranslateX(-index*width);
            }
            else{
                //当滑动距离够的时候  跳转  上一张 下一张 （判断 过度 位移）
                if(distancex>0){
                    index=index-1;
                    addTransition();
                    setTranslateX(-index*width);
                }
                else{
                    index=index+1;
                    addTransition();
                    setTranslateX(-index*width);
                }
            }
        }
        //清除计时器
        clearInterval(timer);
        //添加定时器
        timer=setInterval(function () {
            index ++;
            //过度
            addTransition();
            setTranslateX(-index*width);
        },3000);
        //重置参数
        startx=0;
        distancex=0;
        ismove=false;

    })
}
