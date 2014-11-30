<?php
$context = stream_context_create(array(
				       'http' => array('ignore_errors' => true)
				       ));
$time_start = microtime(TRUE);
$response = file_get_contents('http://webmax.mind.meiji.ac.jp/', false, $context);
$time_end = microtime(TRUE);
$time = $time_end - $time_start;
$pos = strpos($http_response_header[0], '200');
if ($pos === false) {
  echo 'offline/busy';
  if($time<5){
    sleep(10);
    $response = file_get_contents('http://webmax.mind.meiji.ac.jp/', false, $context);
    $pos = strpos($http_response_header[0], '200');
    if($pos !== false){
      exit();
    }
  }
  $input = file_get_contents("last.txt");
  if($input==="online"){
    mb_language("Ja") ;
    mb_internal_encoding("EUC-JP") ;
    $mailto="webmax@lanevok.com";
    if($time>50){
      $subject="webmax alert (busy)";
      $content="webmax server busy. (".$time.")";
    }
    else{
      $subject="webmax alert (offline)";
      $content="webmax server down. (".$time.")".$response.",".$http_response_header[0];
    }
    $mailfrom="From:" .mb_encode_mimeheader("deamon@lanevok.com") ."<deamon@lanevok.com>";
    mb_send_mail($mailto,$subject,$content,$mailfrom);

    file_put_contents("last.txt","offline");
  }
}
else{
  echo 'online';
  $input = file_get_contents("last.txt");
  if($input=="offline"){
    mb_language("Ja") ;
    mb_internal_encoding("EUC-JP") ;
    $mailto="webmax@lanevok.com";
    $subject="webmax alert (online)";
    $content="webmax server startup.";
    $mailfrom="From:" .mb_encode_mimeheader("deamon@lanevok.com") ."<deamon@lanevok.com>";
    mb_send_mail($mailto,$subject,$content,$mailfrom);
    file_put_contents("last.txt","online");
  }
}
