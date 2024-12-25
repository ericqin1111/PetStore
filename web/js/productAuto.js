$(function (){
    $('#keyword').on('keyup',function (){
      var keyword=$(this).val();
      if(keyword!==''&&keyword!==null&&keyword.length!==0){
          $.ajax({
              type     :'GET',
              url      :'http://localhost:8081/mypetstore_war_exploded/productAuto?keyword='+keyword,
              success  :function (data){
                  console.log(data);
                  var productListHTML='';
                  for (var i=0;i<data.length;i++){
                      productListHTML+=' <li class=\"productAutoItem\" data-productId="';
                      productListHTML+=data[i].productId;
                      productListHTML+='">';
                      productListHTML+= data[i].categoryId;
                      productListHTML+=': ';
                      productListHTML+= data[i].name;
                      productListHTML+='</li>';

                  }
                  // $.each(data, function(index, product) {
                  //     productListHTML += '<li class="productAutoItem" data-productId="' + product.productId + '">' +
                  //         product.categoryId + ': ' + product.name + '</li>';
                  // });
                  $('#productAutoList').show();
                  $('#productAutoList').html(productListHTML);
                  $('#productAutoComplete').show();
              },
              error    :function (errorMsg){
                  console.log(errorMsg);
              }
          });

      }
      else{
          $('#productAutoList').hide();
      }

    });

    $(document).on('click','.productAutoItem',function (){
       var productId=$(this).data('productid');
       //清空keyword的值
        $('#productAutoComplete').hide();
        $('#keyword').val('');
       window.location.href='http://localhost:8081/mypetstore_war_exploded/productForm?productId='+productId;
    });

    $('#productAutoComplete').on('mouseleave',function (){
        $(this).hide();
        $('#keyword').val('');
    })

});