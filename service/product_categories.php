<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
echo '   
{
  "product_categories":[
    "product_category_declaration": [
      { "id":1, "parent_id":0, "name":"Pečivo"},
      { "id":2, "parent_id":1, "name":"Chléb"},
      { "id":3, "parent_id":2, "name":"Bílý chléb"},
      { "id":4, "parent_id":2, "name":"Pšeničný chléb"},
      { "id":5, "parent_id":2, "name":"Žitný"}                                              
    ],
    "product_category_data": [
      { "id":1, "product_id":1, "category_id":1},
      { "id":2, "product_id":1, "category_id":2},
      { "id":3, "product_id":3, "category_id":4}                        
    ]        

  ]     
}
'; 
?>