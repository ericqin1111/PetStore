var elPassword=document.getElementById('current_password'),
    elPassword1=document.getElementById('password1'),
    elPassword2=document.getElementById('password2'),
    elMsg1=document.getElementById('feedback1');
    elMsg2              =document.getElementById('feedback2');
    elMsg3              =document.getElementById('feedback3');
console.log("abc");
var xhr='';
function checkPassword(current_password){
    console.log("aaa");
    if(current_password===null||current_password===''||current_password.length===0){
        elMsg1.textContent='请输入原密码!';
        return;
    }
    else
        elMsg1.textContent='';
}
function checkPassword1(current_password,password1){

    if (password1===current_password){
        elMsg2.textContent='该密码与原密码相同！';
        return;
    }
    else
        elMsg3.textContent='';

}
function checkPassword2(password1,password2){

    if (password1!==password2){
        console.log("no")
        elMsg3.textContent='两次密码输入不一致！';
        return;
    }
    else
        elMsg1.textContent='';

}
elPassword.addEventListener('blur',function (){
    var current_password=elPassword.value.trim();
    checkPassword(current_password);
})
elPassword1.addEventListener('blur',function (){
    var current_password=elPassword.value.trim();
    var password1=elPassword1.value.trim();
    checkPassword1(current_password,password1);
})
elPassword2.addEventListener('blur',function (){
    var password1=elPassword1.value.trim();
    var password2=elPassword2.value.trim();
    checkPassword2(password1,password2);
})


