<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
echo '   
{
  "products_parameters":[
    "product_parameters_declaration": [
      { "id":1, "name":"Obsah lepku", "description":"Stanovený obsah lepku", "type":"REAL", "required":1},
      { "id":2, "name":"Schváleno", "description":"Schváleno jako bezlepkové", "type":"BOOL", "required":1},
      { "id":3, "name":"Balení", "description":"Balení produktu", "type":"ENUM", "required":0, "options":["Malé", "Velké", "Střední" ]},                
    ],
    "product_parameters_data": [
      { "product_id":1, "parameter_id":1, "value":"10 mg"},
      { "product_id":1, "parameter_id":2, "value":"TRUE"}, 
      { "product_id":2, "parameter_id":1, "value":"120 mg"},
      { "product_id":2, "parameter_id":2, "value":"FALSE"},
      { "product_id":2, "parameter_id":3, "value":"Malé"}                     
    ]
   ]     
}
'; 
?>