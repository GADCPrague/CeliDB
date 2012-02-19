<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
/* 
echo '   
{
  "product_categories":[{
    "product_category_declaration": [
      { "id":1, "parent_id":0, "name":"Peèivo"},
      { "id":2, "parent_id":1, "name":"Chléb"},
      { "id":3, "parent_id":2, "name":"Bílý chléb"},
      { "id":4, "parent_id":2, "name":"Pšenièný chléb"},
      { "id":5, "parent_id":2, "name":"Žitný"}                                              
    ],
    "product_category_data": [
      { "id":1, "product_id":1, "category_id":1},
      { "id":2, "product_id":1, "category_id":2},
      { "id":3, "product_id":3, "category_id":4}                        
    ]        

  }]     
}
';
*/
  $query = 'Select id as category_id, parent as parent_category, name as category_name, description as category_description from `CATEGORIES` C order by category_name';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);


//  echo  '{';
//  echo  '  "product_categories":[{';  
  echo  '{"product_category_declaration": [';  
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "id":"'.$record['category_id'].'", "parentId":'.$record['parent_category'].', "name":"'.$record['category_name'].'"},'."\n";
    $i++;
  endwhile;
    echo  '{ "id":"'.$record['category_id'].'0", "parentId":"'.$record['parent_category'].'0", "name":"'.$record['category_name'].'0"}'."\n";
  MySQL_FreeResult($Sql_result); 
  
  $query = 'Select id as id, product_id as product_id, category_id as category_id from `PRODUCT_CATEGORY` P';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);
  
  echo '     
    ],
    "product_category_data": [ 
  ';
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "id":"'.$record['id'].'", "productId":"'.$record['product_id'].'", "categoryId":"'.$record['category_id'].'"},'."\n";
    $i++;
  endwhile;
  echo  '{ "id":"'.$record['id'].'0", "productId":"'.$record['product_id'].'0", "categoryId":"'.$record['category_id'].'0"}'."\n";
     
echo  '    ]';
echo  '}';
//  echo  '{    ]';        
//  echo  '{  }]';     
//  echo  '{}';

  MySQL_FreeResult($Sql_result); 
?>