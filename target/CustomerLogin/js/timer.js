//标签倒计时
var countdown=60;
function settime() {
    if (countdown == 0) {
        document.getElementById("huoqu").removeAttribute('disabled');
        document.getElementById("huoqu").value="获取验证码";
        countdown = 60;
        return false;
    } else {
        document.getElementById("huoqu").setAttribute('disabled','disabled');
        document.getElementById("huoqu").value="重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function () {
        settime();
    }, 1000);
}
