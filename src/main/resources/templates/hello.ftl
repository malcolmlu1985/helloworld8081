<!DOCTYPE html>
<html >
    <#include "/header.ftl" >
    <body>
       
       <center>
       <img src = "/images/logo.jpg">
       <h1 id = "title">${title}</h1>
       </center>
       
       <script type = "text/javascript" src = "/webjars/jquery/2.1.4/jquery.min.js"></script>
       
       <script>
           $(function(){
               $('#title').click(function(){
                  //  alert('点击了title');
                  //8081的脚本去访问8080的controller拿数据
                  $.ajax({
                   url: "http://localhost:8080/api/get",
                type: "POST",
                data: {
                    name: "测试"
                },
                success: function(data, status, xhr) {
                   console.log(data);
                   alert(data.name);
                }
              });
                  
               });
           })
           </script>
       
       <p>
           welcome ${name}  to freemarker!
       </p>      
      
      
       <p>性别：
           <#if id==0>
              女
           <#elseif id==1>
              男
           <#else>
              保密   
           </#if>
        </p>
      
      <#--遍历friends-->
      
       <h4>我的好友：</h4>
       <#list friends as item>
           姓名：${item.name} , 生日：${item.date?string("yyyy-MM-dd HH:mm:ss zzzz")}  
           <br>
       </#list>
      
      
    </body>
       <#include "/footer.ftl" >
</html>