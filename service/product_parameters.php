<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
/*
echo '   
{
  "products_parameters":[{
    "product_parameters_declaration": [
      { "id":1, "name":"Obsah lepku", "description":"Stanoven� obsah lepku", "type":"REAL", "required":1},
      { "id":2, "name":"Schv�leno", "description":"Schv�leno jako bezlepkov�", "type":"BOOL", "required":1},
      { "id":3, "name":"Balen�", "description":"Balen� produktu", "type":"ENUM", "required":0, "options":["Mal�", "Velk�", "St�edn�" ]}                
    ],
    "product_parameters_data": [
      { "product_id":1, "parameter_id":1, "value":"10 mg"},
      { "product_id":1, "parameter_id":2, "value":"TRUE"}, 
      { "product_id":2, "parameter_id":1, "value":"120 mg"},
      { "product_id":2, "parameter_id":2, "value":"FALSE"},
      { "product_id":2, "parameter_id":3, "value":"Mal�"}                     
    ]
   } ]     
}
';
*/

  $query = 'Select id as category_id, parent as parent_category, name as category_name, description as category_description from `CATEGORIES` C';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);

  echo  '
{
  "products_parameters":[{
    "product_parameters_declaration": [';  
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "id":1, "name":"Obsah lepku", "description":"Stanoven� obsah lepku", "type":"REAL", "required":1},'."\n";
    $i++;
  endwhile;
    echo  '{ "id":"", "name":"", "description":"", "type":"", "required":""}'."\n";
  MySQL_FreeResult($Sql_result); 
  
  $query = 'Select id as id, product_id as product_id, product_parameter_id as product_parameter_id, value as product_value from `PRODUCT_PROPERTIES` PP';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);
  
  echo '     
    ],
    "product_parameters_data": [
  ';
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "productId":"'.$record['product_id'].'", "parameterId":"'.$record['product_prameter_id'].'", "value":"'.$record['product_value'].'"},'."\n";
    $i++;
  endwhile;
  echo  '{ "productId":"", "parameterId":"", "value":""}'."\n";
  echo '     
    ]
   } ]     
}
  ';
  MySQL_FreeResult($Sql_result); 
?>