<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
   

echo '   
   {
  "products": [
    { "id":1, "barcode":"9771473968012", "name":"BEZLEPKOVÝ MAZANEC", "description":"určeno pro bezlepkovou výživu", "status":"1", "change_date":"2012-05-05", "change_user":"1234567890"},
    { "id":2, "barcode":"9771473968012", "name":"BIO ZELENIA MIX", "description":"", "status":"0", "change_date":"2011-07-04", "change_user":"12345678901"},
    { "id":3, "barcode":"9771473968012", "name":"CALTA Speciální bezlepkový chléb", "description":"na etiketě uvedeno: Alergeny: lepek (obsah lepku nejvýše 100 mg/kg výrobku), lupina", "status":"1", "change_date":"2011-06-04", "change_user":"1234567890"},        
  ]
}
'; 
?>