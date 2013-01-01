<?php
print '<h1>はいたちゅ 強制実行</h1>';
require_once('twitteroauth/twitteroauth.php');
require_once('config.php');
$conn = new TwitterOAuth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
$api = "http://www.drk7.jp/weather/xml/14.xml";
$xml = simplexml_load_file($api)->pref->area->info->rainfallchance;

$am1 = $xml->period[1];
$pm1 = $xml->period[2];
$pm2 = $xml->period[3];
$rnd1 = rand()%3;
$rnd2 = rand()%5;

$day = array('本日','今日','きょう');
$gob = array('です','よ','でぇーす','だお','だよ');

if($am1<=30&&$pm1<=30&&$pm2<=30){
$params = array(
    'status' => '@lanevok '.$day[$rnd1].'は雨が降らない'.$gob[rand()%3]
);
}
else{
$params = array(
    'status' => '@lanevok '.$day[$rnd1].'の降水確率は '.$am1.'/'.$pm1.'/'.$pm2.' '.$gob[$rnd2]
);
}
$now = getdate();
$hour = $now['hours'];
$min = $now['minutes'];
if($hour==6&&($min>=4&&$min<7)){
print '呟きが実行されました -> '.$params[status];
$result = $conn->post('statuses/update', $params);
}
else{
print '呟きは実行されません -> '.$params[status];
}

print '<br>';
// AOJ API
$api21 = "http://judge.u-aizu.ac.jp/onlinejudge/webservice/status_log?user_id=lanevok";
$api2 = simplexml_load_file($api21)->status[0];
$act = $api2->submission_date;
$sta = $api2->status;
$pro = $api2->problem_id;
$nw = time()*1000;
$pro = ltrim($pro);
$pro = rtrim($pro);
$sta = ltrim($sta);
$sta = rtrim($sta);
$tim = floor(($nw - (double)$act)/1000/60);
$params = array(
    'status' => 'お！ @lanevok が '.$pro.' で '.$sta.'！'
);
if($tim<5){
print '呟きが実行されました -> '.$params[status];
$result = $conn->post('statuses/update', $params);
}
else{
print '呟きは実行されません -> '.$params[status];
}
print '<br>';