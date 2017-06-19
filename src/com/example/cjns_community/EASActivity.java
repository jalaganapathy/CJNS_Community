package com.example.cjns_community;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;



@SuppressWarnings("deprecation")
public class EASActivity extends Activity implements OnEditorActionListener {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.eas); 
        
        EditText editText = (EditText) findViewById(R.id.eas_msg);
        editText.setOnEditorActionListener(this);

    }
	
	@Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            sendMessage();
            handled = true;
        }
        return handled;
    }
	public void sendMessage() {
		EditText editText = (EditText) findViewById(R.id.eas_msg);
		String messageEAS = editText.getText().toString();
		System.out.println("sendMessage:"+messageEAS);
		//EditText sig = (EditText) findViewById(R.id.sig_msg);
	//	String sigDetail = sig.getText().toString();
		//System.out.println("NAME:"+sigDetail);
       String sigDetail = getIntent().getStringExtra("signature");
       System.out.println("Got this from Mainactivity: " + sigDetail);
       
       String ipaddr = getIntent().getStringExtra("ipaddr");
  	 System.out.println("Got this from Mainactivity ipaddr: " + ipaddr);
		//String sigDetail = "Jala";
		 new sendHttp().execute("eas", messageEAS, sigDetail,ipaddr);

	}

}

	
	


