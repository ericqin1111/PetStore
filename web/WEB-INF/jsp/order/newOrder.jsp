<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/top.jsp"%>
<style>
    .personinf {
        color: blue;
    }
</style>
<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CartActionBean">
    Return to Shopping Cart</stripes:link></div>

<div id="Catalog">
    <form action="submit" method="post">
    <table>
        <tr>
            <td>
                <h2>Checkout Summary</h2>

                <table>

                    <tr>
                        <td><b>Item ID</b></td>
                        <td><b>Product ID</b></td>
                        <td><b>In Stock?</b></td>
                        <td><b>Quantity</b></td>
                        <td><b>List Price</b></td>
                        <td><b>Total Cost</b></td>
                    </tr>

                    <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                        <tr>
<%--                            <td><stripes:link--%>
<%--                                    beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                                    event="viewItem">--%>
<%--                                <stripes:param name="itemId" value="${cartItem.item.itemId}" />--%>
<%--                                ${cartItem.item.itemId}--%>
<%--                            </stripes:link></td>--%>
                            <td>${cartItem.item.product.productId}</td>
                            <td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                    ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                    ${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
                            <td>${cartItem.inStock}</td>
                            <td>${cartItem.quantity}</td>
                            <td><fmt:formatNumber value="${cartItem.item.listPrice}"
                                                  pattern="$#,##0.00" /></td>
                            <td><fmt:formatNumber value="${cartItem.total}"
                                                  pattern="$#,##0.00" /></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td  colspan="8">
                            <h3 id="personal" >个人信息</h3>
                        </td>
                    </tr>
                    <tbody id="content" class="content">
                    <tr>
                        <td ></td> <td class='personinf' id='email'> </td>
                        </tr>
                    <tr>
                        <td ></td> <td class='personinf' id='firstName'></td>
                        <td class='personinf' id='lastName'>  </td>
                        </tr>
                    <tr>
                        <td ></td>
                        <td class='personinf' id='address1'> </td>
                        <td  class='personinf' id='address2'></td>
                        <td  class='personinf' id='city'></td>
                        <td  class='personinf' id='country'>  </td>
                        </tr>
                    <tr>
                        <td></td> <td class='personinf' id='phone'></td>
                        </tr>
                    </tbody >
                    <tr>
                        <td colspan="7">Sub Total: <fmt:formatNumber
                                value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                            <input type="submit" value="submission"></td>
                    </tr>


                </table>

            <td>&nbsp;</td>

        </tr>
    </table> </form>

    <script>

        var userinfs;

        document.getElementById('content').addEventListener('click', (event)=>{
            if (event.target.classList.contains('personinf')){

                const userInput = prompt('please enter ' + event.target.id, '');

                if(userInput !== null){
                    const xhr1 = new XMLHttpRequest();
                    xhr1.open('GET', "userInfChange?userId=" + event.target.id + "&input=" + userInput, true);

                    xhr1.onreadystatechange = function (){
                        if (xhr1.readyState === 4){
                            if (xhr1.status === 200){
                                console.log('success1');
                                const xhr2 = new XMLHttpRequest();
                                xhr2.open('GET', "personalInformation?shown=false" , true);
                                xhr2.onreadystatechange = function (){
                                    if (xhr2.readyState === 4){
                                        if (xhr2.status === 200){
                                            document.getElementById('content').innerHTML = xhr2.responseText;
                                            console.log("success2");
                                        }}}

                                xhr2.send();
                            }
                        }
                    }
                    xhr1.send();
                }




            }

        })

        console.log(userinfs);
        var userId;




       const person = document.getElementById('personal');
       person.style.color = 'red';

       var userInput;
       var shown = false;
       person.addEventListener('click', function(){

           const xhr = new XMLHttpRequest();

           xhr.open('GET', "personalInformation?shown=" + shown, true);

           xhr.onreadystatechange = function (){
               if (xhr.readyState === 4){
                   if (xhr.status === 200){
                       document.getElementById('content').innerHTML = xhr.responseText;
                       shown = !shown;
                   }
               }
           }
           xhr.send();
       })


    </script>

</div>
<%@ include file="../common/bottom.jsp"%>