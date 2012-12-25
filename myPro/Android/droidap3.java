package com.example.counter;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity{

    private TextView[] text_view;
    private int[] cnt;
    private GestureDetector mGD;
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	mGD = new GestureDetector(this, mOGL);
	text_view = new TextView[4];
	cnt = new int[4];
	text_view[0] = (TextView)findViewById(R.id.right);
	text_view[1] = (TextView)findViewById(R.id.left);
	text_view[2] = (TextView)findViewById(R.id.down);
	text_view[3] = (TextView)findViewById(R.id.up);
	for(int i=0;i<4;i++){
	    text_view[i].setText("0");
	}
    }
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
	return mGD.onTouchEvent(event);
    }
    
    private final SimpleOnGestureListener mOGL = new SimpleOnGestureListener() {
	    
	    private void set(int idx){
		text_view[idx].setText(String.valueOf(++cnt[idx]));
	    }
	    
	    @Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if(Math.abs(velocityX)>Math.abs(velocityY)){
		    if(velocityX>0) set(0); else set(1);
		}
		else{
		    if(velocityY>0) set(2); else set(3);
		}
		return true;
	    }
	    
	};
}

/* xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <TextView
        android:id="@+id/up"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="33dp"
        android:textAppearance="?android:attr/textAppearanceLarge" />

    <TextView
        android:id="@+id/down"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="33dp"
        android:textAppearance="?android:attr/textAppearanceLarge" />

    <TextView
        android:id="@+id/left"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_centerVertical="true"
        android:layout_marginLeft="33dp"
        android:textAppearance="?android:attr/textAppearanceLarge" />

    <TextView
        android:id="@+id/right"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignBaseline="@+id/left"
        android:layout_alignBottom="@+id/left"
        android:layout_alignParentRight="true"
        android:layout_marginRight="33dp"
        android:textAppearance="?android:attr/textAppearanceLarge" />

</RelativeLayout>

*/
