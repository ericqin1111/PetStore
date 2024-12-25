var elUsername=document.getElementById('username'),
    elMsg=document.getElementById('feedback');

var xhr='';
function checkUsername(username){
    if(username===null||username===''||username.length===0){
        elMsg.textContent='用户名不能为空';
        return;
    }
    if(username.length<=2){
        elMsg.textContent='用户名长度必须大于2';
        return;
    }
    elMsg.textContent='';
    sendRequst(username);
}

//向服务器发送请求
function sendRequst(username){
    xhr=new XMLHttpRequest();//新建一个请求
    xhr.onreadystatechange=process;//当服务器给了响应时使用回调函数
    xhr.open('GET','usernameIsExist?username='+username);
    xhr.send(null);
}
//回调函数
function process(){
    if(xhr.readyState===4){//服务器响应结束
        if(xhr.status===200){
            var responseInfo=xhr.responseText;
            console.log(responseInfo);
            if(responseInfo==='Exist'){
                elMsg.textContent='用户名已存在';
            }
            else {
                elMsg.textContent='';
            }
        }
    }
}
elUsername.addEventListener('blur',function (){
    var username=elUsername.value.trim();
    checkUsername(username);
})