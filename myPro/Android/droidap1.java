package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
    SeekBar seekBar;
    TextView tv1;
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	seekBar = (SeekBar)findViewById(R.id.seekBar1);
	tv1 = (TextView)findViewById(R.id.textView2);
	
	tv1.setText("Current Value:"+seekBar.getProgress());
	
	seekBar.setOnSeekBarChangeListener(
	     new OnSeekBarChangeListener() {
		 public void onProgressChanged(SeekBar seekBar,
			                           int progress, boolean fromUser) {
		      tv1.setText("Current Value:"+progress);
		 }			       
		 public void onStartTrackingTouch(SeekBar seekBar) {}
		 public void onStopTrackingTouch(SeekBar seekBar) {}
	     }
	);
    }
}
