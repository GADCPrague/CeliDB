                          <?php 
//include 'config.php' 


$db_host = '127.0.0.1';
$db_user = 'celidb';
$db_password = '6VnLDuLeZ6seCx4n';
$database = 'celidb';

$db = MySQL_Connect($db_host, $db_user, $db_password);

MySQL_Connect($db_host, $db_user, $db_password) or die('Nepodařilo se připojit k databázi.'); // pripojeni k db
MySQL_Select_DB($database) or die('Nepodařilo se otevřít databázi'); // otevreni db


MySQL_Query("SET CHARACTER SET utf8");
// ponovu by to melo byt takhle == mysql_set_characterset('utf8');
?>
