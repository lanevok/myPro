<?php
/**
 * PHP→mySQLでPDOを用いたトランザクション(ACID意識)
 * http://php.net/manual/ja/pdo.transactions.php
 */
try {
  $dbh = new PDO('mysql:dbname=schema;host=localhost', 'user', 'password',
      array(PDO::ATTR_PERSISTENT => true));
  echo "接続しました\n";
} catch (Exception $e) {
  die("接続できません: " . $e->getMessage());
}

try {
  $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

  $dbh->beginTransaction();
  $dbh->exec("UPDATE schema.table SET visible=b'1' WHERE id=745");
  $dbh->exec("UPDATE schema.table SET visible=b'1' WHERE id=a");
  $dbh->commit();

} catch (Exception $e) {
  $dbh->rollBack();
  echo "失敗しました。" . $e->getMessage();
}
