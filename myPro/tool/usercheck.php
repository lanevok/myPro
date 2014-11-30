<?php
$ROOM = "A203";

// 時刻系取得
$timestamp = time();
$hour = (int)gmdate("G", $timestamp);
$youbi = (int)gmdate("w", $timestamp);

// 11時(2)のときログ消去
if($hour==2){
  file_put_contents("log.txt", "");
  print "ログを消しました\n";
  exit();
}

// 12時(3)～18時(9)の午後TA時間内判定
if(!(3<=$hour&&$hour<=9)){
  print "TA時間外です．\n";
  exit();
}
// 木曜日(4)の曜日判定
else if($youbi!=4){
  print "TA曜日外です．\n";
  exit();
}

// ログチェックの読み込み
$response = file_get_contents('http://lanevok.com/', false);

// ログの読み込み
$str = file("log.txt");
for($i=0;$i<count($str);$i++){
  $str[$i] = str_replace(array("\r\n", "\r", "\n"), '', $str[$i]);
}

// メール通知判定
if(strpos($response,$ROOM)===FALSE&&strpos($response,"Red")===FALSE){
  print "5分以内に設定教室は二重ログインされていません．\n";
}
else{
  preg_match_all("/(<tr>(.*?)<td><font color=\"Red\">)(.*?)(<\/font>)(.*?)(<\/tr>)/is", $response, $answerArray);
  for($i=0;$i<count($answerArray[0]);$i++){
    if(strpos($answerArray[0][$i], $ROOM)===FALSE){
      print "処理対象のログは，通知すべきログではありませんでした．<br>\n";
    }
    else{
      if(in_array($answerArray[3][$i], $str)===FALSE){
	print "メールします．\n";
	print_r($answerArray[5][$i]."\n");

	// メール設定
	mb_language("Ja") ;
	mb_internal_encoding("EUC-JP") ;
	$mailto="sample@lanevok.com";
	$subject="double login alert";
	$content=$answerArray[5][$i];
	$mailfrom="From:" .mb_encode_mimeheader("sample@lanevok.com") ."<sample@lanevok.com>";
	mb_send_mail($mailto,$subject,$content,$mailfrom);

	// ログの書込み
	file_put_contents("log.txt", $answerArray[3][$i]."\n", FILE_APPEND);
	exit();
      }
      else{
	print "過去にメール通知したログです．<br>\n";
      }
    }
  }
}