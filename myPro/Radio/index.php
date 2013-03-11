<?php

$start = microtime(true);

echo "<meta http-equiv=\"refresh\" content=\"15;URL=./\">";

$opts = array('http' => array(
		'method' => 'GET',
		'header' => 'User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)'
));

$url = "http://173.192.205.178";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Current Song)(.*)(table)(.*)(br)(.*)/is";
preg_match($string, $html,$result);
$play = $result[3];
$song = substr($play,71,strlen($play)-71-37);
$url = "173.192.205.178";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://199.241.187.194:8070";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Current Song)(.*)(table)(.*)(br)(.*)/is";
preg_match($string, $html,$result);
$play = $result[3];
$song = substr($play,71,strlen($play)-71-37);
$url = "199.241.187.194";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://208.43.33.114:11520";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Current Song)(.*)(table)(.*)(br)(.*)/is";
preg_match($string, $html,$result);
$play = $result[3];
$song = substr($play,71,strlen($play)-71-37);
$url = "208.43.33.114";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://91.121.117.196:8000";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Current Song)(.*)(table)(.*)(br)(.*)(Current Song)/is";
preg_match($string, $html,$result);
$play = $result[0];
preg_match($string, $play,$result);
$play = $result[3];
$song = substr($play,51,strlen($play)-51-27);
$url = "91.121.117.196";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://www.radionomy.com/en/radio/j-music--3/index";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Share)(.*)(Now playing)/is";
preg_match($string, $html,$result);
$play = $result[0];
$string = "/(strong)(.*)(itunes_id)/is";
preg_match($string, $play,$result);
$play = $result[0];
$song = substr($play,0,strlen($play)-63);
$song = str_replace("strong", "", $song);
$song = str_replace(htmlspecialchars("<"), "", $song);
$song = str_replace(htmlspecialchars(">"), "", $song);
$song = str_replace(htmlspecialchars("/"), "", $song);
$url = "j-music--3";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://www.radionomy.com/en/radio/a0-1-world/index";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Share)(.*)(Now playing)/is";
preg_match($string, $html,$result);
$play = $result[0];
$string = "/(strong)(.*)(itunes_id)/is";
preg_match($string, $play,$result);
$play = $result[0];
$song = substr($play,0,strlen($play)-63);
$song = str_replace("strong", "", $song);
$song = str_replace(htmlspecialchars("<"), "", $song);
$song = str_replace(htmlspecialchars(">"), "", $song);
$song = str_replace(htmlspecialchars("/"), "", $song);
$url = "a0-1-world";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://www.radionomy.com/en/radio/nippon-no-radio-s2/index";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Share)(.*)(Now playing)/is";
preg_match($string, $html,$result);
$play = $result[0];
$string = "/(strong)(.*)(itunes_id)/is";
preg_match($string, $play,$result);
$play = $result[0];
$song = substr($play,0,strlen($play)-63);
$song = str_replace("strong", "", $song);
$song = str_replace(htmlspecialchars("<"), "", $song);
$song = str_replace(htmlspecialchars(">"), "", $song);
$song = str_replace(htmlspecialchars("/"), "", $song);
$url = "nippon-no-radio-s2";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://www.radionomy.com/en/radio/japan-station/index";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Share)(.*)(Now playing)/is";
preg_match($string, $html,$result);
$play = $result[0];
$string = "/(strong)(.*)(itunes_id)/is";
preg_match($string, $play,$result);
$play = $result[0];
$song = substr($play,0,strlen($play)-63);
$song = str_replace("strong", "", $song);
$song = str_replace(htmlspecialchars("<"), "", $song);
$song = str_replace(htmlspecialchars(">"), "", $song);
$song = str_replace(htmlspecialchars("/"), "", $song);
$url = "japan-station";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$url = "http://www.radionomy.com/en/radio/welovejapan/index";
$html = htmlspecialchars(file_get_contents($url, false, stream_context_create($opts)));
$string = "/(.*)(Share)(.*)(Now playing)/is";
preg_match($string, $html,$result);
$play = $result[0];
$string = "/(strong)(.*)(itunes_id)/is";
preg_match($string, $play,$result);
$play = $result[0];
$song = substr($play,0,strlen($play)-63);
$song = str_replace("strong", "", $song);
$song = str_replace(htmlspecialchars("<"), "", $song);
$song = str_replace(htmlspecialchars(">"), "", $song);
$song = str_replace(htmlspecialchars("/"), "", $song);
$url = "welovejapan";
echo "<ul><li><b>".$song."</b> @ ".$url."</li></ul>";

$end = microtime(true);
// echo "<br><br><h4>".($end-$start)."</h4>";
