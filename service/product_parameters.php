<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
/*
echo '   
{
  "products_parameters":[{
    "product_parameters_declaration": [
      { "id":1, "name":"Obsah lepku", "description":"Stanovený obsah lepku", "type":"REAL", "required":1},
      { "id":2, "name":"Schváleno", "description":"Schváleno jako bezlepkové", "type":"BOOL", "required":1},
      { "id":3, "name":"Balení", "description":"Balení produktu", "type":"ENUM", "required":0, "options":["Malé", "Velké", "Støední" ]}                
    ],
    "product_parameters_data": [
      { "product_id":1, "parameter_id":1, "value":"10 mg"},
      { "product_id":1, "parameter_id":2, "value":"TRUE"}, 
      { "product_id":2, "parameter_id":1, "value":"120 mg"},
      { "product_id":2, "parameter_id":2, "value":"FALSE"},
      { "product_id":2, "parameter_id":3, "value":"Malé"}                     
    ]
   } ]     
}
';
*/

  $query = 'Select id as product_properties_param_id from `PRODUCT_PROPERTIES_PARAM` PPP LIMIT 1';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);

  echo  '
{
    "product_parameters_declaration": [';  
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "id":1, "name":"Obsah lepku", "description":"Stanovený obsah lepku", "type":"REAL", "required":1},'."\n";
    echo  '{ "id":2, "name":"Povolená hranice lepku", "description":"Stanovený obsah lepku", "type":"REAL", "required":1},'."\n";
    echo  '{ "id":3, "name":"Certifikace na lepek", "description":"Produkt je certifikován na obsah lepku", "type":"REAL", "required":1},'."\n";
    echo  '{ "id":4, "name":"Výrobce", "description":"Výrobce produktu", "type":"REAL", "required":1},'."\n";
    $i++;
  endwhile;
    echo  '{ "id":"0", "name":"0", "description":"0", "type":"0", "required":"0"}'."\n";
  MySQL_FreeResult($Sql_result); 
  
  $query = 'Select id as id, product_id as product_id, product_parameter_id as product_parameter_id, value as product_value from `PRODUCT_PROPERTIES` PP';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);
  
  echo '     
    ],
    "product_parameters_data": [
  ';
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "productId":"'.$record['product_id'].'", "parameterId":"'.$record['product_parameter_id'].'", "value":"'.$record['product_value'].'"},'."\n";
    $i++;
  endwhile;
  echo  '{ "productId":"0", "parameterId":"0", "value":"0"}'."\n";
  echo '     
    ]    
}
  ';
  MySQL_FreeResult($Sql_result); 
?>