package com.example.cjns_community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity 
                implements OnClickListener  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//get the Button reference
		//Button is a subclass of View
		//buttonClick is defined in main.xml "@+id/buttonClick"
	        View v = findViewById(R.id.easbuttonId);
		//set event listener
	        v.setOnClickListener(this);
	        //For adverstisement
	        View ad = findViewById(R.id.advButtonId);
	        ad.setOnClickListener(this);
	        
	        /*View sig = findViewById(R.id.sigButtonId);
	        sig.setOnClickListener(this);*/
	}
    
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.easbuttonId){
			//define a new Intent for the second Activity. get the signature
     		 EditText editText = (EditText)findViewById(R.id.sig_msg);
	         String text = editText.getText().toString();
	         
	         //get the IP address
	         EditText editText1 = (EditText)findViewById(R.id.ip_msg);
	         String text1 = editText1.getText().toString();
	         System.out.println("IP address in main activity :" +text1);
			Intent intent = new Intent(this,EASActivity.class);
			intent.putExtra("signature", text);	
			intent.putExtra("ipaddr", text1);
			//start the second Activity
			this.startActivity(intent);
		}
		else if(v.getId() == R.id.advButtonId){

			EditText editText = (EditText)findViewById(R.id.sig_msg);
	        String text = editText.getText().toString();
	        
	      //get the IP address
	         EditText editText1 = (EditText)findViewById(R.id.ip_msg);
	         String text1 = editText1.getText().toString();
	         System.out.println("IP address in main activity :" +text1);
			//define a new Intent for the second Activity
			Intent intent = new Intent(this,AdvActivity.class);

			intent.putExtra("signature", text);	
			intent.putExtra("ipaddr", text1);
			//start the second Activity
			this.startActivity(intent);
		}
		/*else if(v.getId() == R.id.sigButtonId){
			//define a new Intent for the second Activity
			Intent intent = new Intent(this,SigActivity.class);
	 
			//start the second Activity
			this.startActivity(intent);
		}*/
	}
}
