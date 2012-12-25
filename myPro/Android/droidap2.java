package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    TextView value;
    Button button1;
    Button button2;
    int cnt = 0;
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	button1 = (Button)findViewById(R.id.button1);
	button2 = (Button)findViewById(R.id.button2);
	value = (TextView)findViewById(R.id.textView1);
	value.setText(String.valueOf(cnt));
	button1.setOnClickListener(this);
	button2.setOnClickListener(this);
    }
    
    public void onClick(View v) {
	if(v.equals(button1)){
	    cnt++;
	    value.setText(String.valueOf(cnt));
	}
	else{
	    cnt = 0;
	    value.setText(String.valueOf(cnt));
	}
    }
}
