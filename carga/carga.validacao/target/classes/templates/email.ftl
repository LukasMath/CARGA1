  <!DOCTYPE html>
<html>
<head>

 </head>
  <body>
  
  <#list log as key, value>
    <h3>${key}</h3>
    <ul>   
      <#list value as x>
          <li>${x}</li> 
    </#list>
  </ul> 
  </#list>
  
  </body>
</html>