<?php
$context = stream_context_create(array(
  'http' => array('ignore_errors' => true)
));
$response = file_get_contents('http://google.com/', false, $context);
$pos = strpos($http_response_header[0], '200');
if ($pos === false) {
  echo 'offline';
  $input = file_get_contents("last.txt");
  if($input==="online"){
    mb_language("Ja") ;
    mb_internal_encoding("EUC-JP") ;
    $mailto="abc@lanevok.com";
    $subject="offline";
    $content="text";
    $mailfrom="From:" .mb_encode_mimeheader("def@lanevok.com") ."<def@lanevok.com>";
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
    $mailto="abc@lanevok.com";
    $subject="online";
    $content="text";
    $mailfrom="From:" .mb_encode_mimeheader("def@lanevok.com") ."<def@lanevok.com>";
    mb_send_mail($mailto,$subject,$content,$mailfrom);
    file_put_contents("last.txt","online");
  }
}