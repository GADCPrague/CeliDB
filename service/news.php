<?php
   header('Content-Type: text/plain; charset=UTF-8');
   include "includes/website_conn.php";
   
  $query = 'Select id as news_id, perex as news_perex, main_text as news_main_text, picture as news_picure, anchor as news_anchor, change_date as news_change_date, user as news_user from `PRODUCTS` P LIMIT 1';
  /* echo $query;*/
  $Sql_result = MySQL_Query($query);

  echo  '';
  //echo  '{';
echo  '[';  
  //echo  '  "products": [';  
  while ($record = MySQL_Fetch_Array($Sql_result)):
    echo  '{ "id":'.$record['news_id'].', "perex":"'.$record['news_perex'].'", "mainText":"'.$record['news_picture'].'", "anchor":"'.$record['news_anchor'].'", "changeDate":"'.$record['news_change_date'].'", "changeDate":"'.$record['product_change_date'].'", "changeUser":"'.$record['news_user'].'"},'."\n";
    $i++;
  endwhile;
  echo  '{ "id":"'.$record['product_id'].'0", "barCode":"'.$record['product_barcode'].'0", "name":"'.$record['product_name'].'0", "description":"'.$record['product_description'].'0", "status":"'.$record['product_status'].'0", "changeDate":"'.$record['product_change_date'].'0", "changeUser":"'.$record['product_user'].'0"}'."\n";echo  '{ "id":'.$record['news_id'].', "perex":"'.$record['news_perex'].'", "mainText":"'.$record['news_picture'].'", "anchor":"'.$record['news_anchor'].'", "changeDate":"'.$record['news_change_date'].'", "changeDate":"'.$record['product_change_date'].'", "changeUser":"'.$record['news_user'].'"},'."\n";echo  '{ "id":'.$record['news_id'].', "perex":"'.$record['news_perex'].'", "mainText":"'.$record['news_picture'].'", "anchor":"'.$record['news_anchor'].'", "changeDate":"'.$record['news_change_date'].'", "changeDate":"'.$record['product_change_date'].'", "changeUser":"'.$record['news_user'].'"}'."\n";

echo '';     
echo '  ]';
//echo '}'; 
echo '';
  MySQL_FreeResult($Sql_result);
?>