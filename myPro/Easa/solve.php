<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta name="author" content="(TAT)chaN">
	<meta name="copyright" content="(TAT)chaN">
	<title>英文解析支援アプリ Easa</title>
	<script type="text/javascript">








	</script>
</head>
<body background="bg.gif" style="font-family:Verdana,Arial,sans-serif;">
<div style="padding:20px">
	<a href="./"><img src="title.png" style="border:3px double #BBBBBB"></a>
	<br><br><br><br>
	<form action="solve.php" method="post" enctype="multipart/form-data">
	    <textarea name="input" rows="25" cols="100"></textarea><br><br>
	    <p>表示 >> 最小値 : <input type="text" name="disp" size="4" value="1" maxlength="4"></p>
		<p>太字 >> 最小値 : <input type="text" name="min" size="4" value="0" maxlength="4"> 
		&nbsp;最大値 : <input type="text" name="max" size="4" value="0" maxlength="4"></p>
		<p>整列 >> <input type="radio" name="srt" value="1" checked>辞書式アルファベット順　
				<input type="radio" name="srt" value="2">本文出現順序　
				<input type="radio" name="srt" value="3">出現回数 降順</p>
		<p>削除 >> <input type="checkbox" name="spwdin" value="1" checked><a href="spwd.txt">一般頻出語</a> の除去　　
		<input type="checkbox" name="tag" value="1">HTMLタグ の除去 (Style,Script ソースを含む)</p><!-- SMART Stop List + alphaを使用-->
		<input type="file" name="upfile" size="10">
		<input type="submit" value="解析" style="padding: 5px 20px; margin: 5px 10px;">
	</form>
	<br>
	<h3><font color="#EE5522">Result</font></h3>
	<p>
	<ul>
		<li id="al"></li>
		<li id="kd"></li>
		<li id="tm"></li>
	</ul>
	</p>
	<br>

<?php
// ===========================================================================================
$time_start = microtime(true);
if(@fopen($_FILES["upfile"]["tmp_name"],'r')!=NULL){
  if((int)strlen($_POST['input'])!=0){
    echo "[フォーム入力とファイル入力の同時処理は行えません]";
    goto end;
  }
  if($_FILES["upfile"]["type"]!="text/plain"){
    echo "[入力ファイルは処理できないファイル形式です]";
    goto end;
  }
  $fp = @fopen($_FILES["upfile"]["tmp_name"],'r');
  $str=@fread($fp,(int)filesize($_FILES["upfile"]["tmp_name"]));
  if($str==FALSE){
    echo "[読み込み時に何らかのエラーが発生しました]";
    goto end;
  }
}
else{
  if((int)strlen($_POST['input'])==0){
    echo "[処理する文章が入力されていません]";
    goto end;
  }
  $str = $_POST['input'];
  if($str==FALSE){
    echo "[何らかのエラーが発生しました]";
    goto end;
  }
}
if(strlen($str)>500000){
  echo "[解析文章が５０万字を越えており、処理が行えません]";
  goto end;
}
$tag = (int)$_POST['tag'];
if($tag==1){
  $str = preg_replace('!<style.*?>.*?</style.*?>!is','',$str);
  $str = preg_replace('!<script.*?>.*?</script.*?>!is','',$str);
  $str = strip_tags($str);
}
$ary = split('[ ,.:&(/%)^#_?[:digit:][:cntrl:][:blank:][:space:][:punct:]$;\[\']',$str);
$cnt = count($ary);	
for($i=0;$i<$cnt;$i++){
  $ary[$i] = strtolower($ary[$i]);
  $lng = strlen($ary[$i]);
  if($lng<2||$lng>25||!preg_match('/^[a-zA-Z]+$/',$ary[$i])){
    unset($ary[$i]);
  }
}
$ary = array_values($ary);
$cnt = count($ary);
if($cnt==0){
  echo "[結果として表示されるものはありません]";
}
else if($cnt>60000){
  echo "[英単語の総数が６万語を越えており、処理が行えません]";
}
else{
  echo '<script type="text/javascript">';
  echo "document.getElementById('al').innerHTML = '英単語の総数 = {$cnt} 語'\n";
  echo '</script>';
  $srt = (int)$_POST['srt'];
  $spwdin = (int)$_POST['spwdin'];
  $spwd;
  if($spwdin==1){
    $contents=@file_get_contents("spwd.txt");
    $file=split('[[:cntrl:][:blank:][:space:][:punct:]]',$contents);
    $cnt2 = count($file);
    for($i=0;$i<$cnt2;$i++){
      if(strlen($file[$i])<1){
	unset($file[$i]);
      }
    }
    $file = array_values($file);
    $cnt2 = count($file);
    for($i=0;$i<$cnt2;$i++){
      $spwd[$file[$i]] = true;
    }
  }
  if($srt<3){
    if($srt==1){
      sort($ary);
    }
    for($i=0;$i<$cnt;$i++){
      $db[$ary[$i]]++;
    }
    $db_cnt = count($db);
    if($db_cnt>60000){
      echo "[英単語の種類数が６万語を越えており、処理が行えません]";
    }
    else{
      echo '<script type="text/javascript">';
      echo "document.getElementById('kd').innerHTML = '英単語の種類数 = {$db_cnt} 語'\n";
      echo '</script>';
      if(!is_numeric($_POST['disp'])||
	 !is_numeric($_POST['min'])||
	 !is_numeric($_POST['max'])){
	echo "[オプション設定で整数が入力されていません]";
	goto end;
      }
      $disp = (int)$_POST['disp'];
      $min = (int)$_POST['min'];
      $max = (int)$_POST['max'];
      foreach($db as $key => $value){
	if($value>=$disp&&!$spwd[$key]){
	  if($value>=$min&&$value<=$max){
	    echo "<b>".$key." (".$value.")</b><br>\n";
	  }
	  else{
	    echo $key." (".$value.")<br>\n";
	  }
	}
      }
    }
  }
  else{
    $ap_max;
    for($i=0;$i<$cnt;$i++){
      $ap_max = max($ap_max,++$db[$ary[$i]]);
    }
    $db_cnt = count($db);
    if($db_cnt>60000){
      echo "[英単語の種類数が６万語を越えており、処理が行えません]";
    }
    else{
      echo '<script type="text/javascript">';
      echo "document.getElementById('kd').innerHTML = '英単語の種類数 = {$db_cnt} 語'\n";
      echo '</script>';
      if(!is_numeric($_POST['disp'])||
	 !is_numeric($_POST['min'])||
	 !is_numeric($_POST['max'])){
	echo "[オプション設定で整数が入力されていません]";
	goto end;
      }
      $disp = (int)$_POST['disp'];
      $min = (int)$_POST['min'];
      $max = (int)$_POST['max'];
      for($i=$ap_max;$i>=$disp;$i--){
	foreach($db as $key => $value){
	  if($i==$value&&!$spwd[$key]){
	    if($value>=$min&&$value<=$max){
	      echo "<b>".$key." (".$value.")</b><br>\n";
	    }
	    else{
	      echo $key." (".$value.")<br>\n";
	    }
	  }
	}
      }
    }
  }
  $time_end = microtime(true);
  $time = round($time_end - $time_start,3);
  echo '<script type="text/javascript">';
  echo "document.getElementById('tm').innerHTML = '実行時間 = {$time} 秒'\n";
  echo '</script>';
end:
}
// ===========================================================================================
?>

<br><br>
<p>Copyright &copy; <a href="http://lanevok.com">(TAT)chaN</a> All Rights Reserved.</p>
</div>
</body>
</html>