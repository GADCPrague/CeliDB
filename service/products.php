<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
   
 /*
echo '   
{
  "products": [
    { "id":1, "barcode":"9771473968012", "name":"BEZLEPKOVÝ MAZANEC", "description":"urèeno pro bezlepkovou výživu", "status":"1", "change_date":"2012-05-05", "change_user":"1234567890"},
    { "id":2, "barcode":"9771473968012", "name":"BIO ZELENIA MIX", "description":"", "status":"0", "change_date":"2011-07-04", "change_user":"12345678901"},
    { "id":3, "barcode":"9771473968012", "name":"CALTA Speciální bezlepkový chléb", "description":"na etiketì uvedeno: Alergeny: lepek (obsah lepku nejvýše 100 mg/kg výrobku), lupina", "status":"1", "change_date":"2011-06-04", "change_user":"1234567890"}        
  ]
} 
';                                                   
*/

  $query = 'Select id as product_id, barcode as product_barcode, name as product_name, description as product_description, status as product_status, change_date as product_change_date, user as product_user from `PRODUCTS` P LIMIT 10';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);

  echo  '
{
  "products": [';  
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "id":'.$record['product_id'].', "barcode":"'.$record['product_barcode'].'", "name":"'.$record['product_name'].'", "description":"'.$record['product_description'].'", "status":"'.$record['product_status'].'", "change_date":"'.$record['product_change_date'].'", "change_user":"'.$record['product_user'].'"},'."\n";
    $i++;
  endwhile;
  echo  '{ "id":"'.$record['product_id'].'", "barcode":"'.$record['product_barcode'].'", "name":"'.$record['product_name'].'", "description":"'.$record['product_description'].'", "status":"'.$record['product_status'].'", "change_date":"'.$record['product_change_date'].'", "change_user":"'.$record['product_user'].'"}'."\n";

echo '     
  ]
} 
';
  MySQL_FreeResult($Sql_result);
?>