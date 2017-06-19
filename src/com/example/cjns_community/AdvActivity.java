package com.example.cjns_community;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.FileHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdvActivity extends Activity implements OnClickListener{
    
	private static final int REQUEST_PICK_FILE = 1;
	 
    private TextView filePath;
    private Button Browse;
    private File selectedFile;
    private Button postAdBtn;
    private String filelocation;
    
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_adv);
 
        filePath = (TextView)findViewById(R.id.file_path);
        Browse = (Button)findViewById(R.id.browse);
        View button = findViewById(R.id.browse);
        button.setOnClickListener(this); 
        
        postAdBtn = (Button)findViewById(R.id.postAd);
        postAdBtn.setOnClickListener(this);
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adv, menu);
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
	
 
    public void onClick(View v) {
    	
        switch(v.getId()) {
        
        case R.id.browse:
            Intent intent = new Intent(this, FilePicker.class);            
            startActivityForResult(intent, REQUEST_PICK_FILE);
            break;
        case R.id.postAd:
        	//EditText sig = (EditText) findViewById(R.id.sig_msg);
    		//String sigDetail = sig.getText().toString();
        	//String sigDetail = "Jala";
        	String sigDetail = getIntent().getStringExtra("signature");
        	 System.out.println("Got this from Mainactivity  Signature: " + sigDetail);
        	 
        	 String ipaddr = getIntent().getStringExtra("ipaddr");
        	 System.out.println("Got this from Mainactivity ipaddr: " + ipaddr);
        	 
        	 //Get the product and price details
         	EditText editTextProduct = (EditText)findViewById(R.id.product);
 	        String textProduct = editTextProduct.getText().toString();
 	        
 	       EditText editTextPrice = (EditText)findViewById(R.id.price);
	        String textPrice = editTextPrice.getText().toString();
	        
	        EditText editTextMobile = (EditText)findViewById(R.id.mobile);
	        String textMobile = editTextMobile.getText().toString();
 	        
        	 
        	 new sendHttp().execute("adv", filelocation,sigDetail,ipaddr,textProduct,textPrice,textMobile);
        	//sendImageToServer();
        	break;
        }
    }
 
    public void sendImageToServer() {
    	final File uploadFile = new File(filelocation);
        try {
        	URL url = new URL("http://192.168.105.57:6262/ADVERTISE");
            final MultipartUtility http = new MultipartUtility(url);
            http.addFormField("someField", "someValue");
            http.addFormField("someButton", "Submit");
            http.addFilePart("someFile", uploadFile);
            final byte[] bytes = http.finish();
            try {
            	//This is a simple one
            	final OutputStream os = new FileOutputStream("some.txt");
                os.write(bytes);
                os.close();
            }
            catch(Exception e) {
            	e.printStackTrace();
            }
            
         } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
        if(resultCode == RESULT_OK) {
        	
            switch(requestCode) {
            
            case REQUEST_PICK_FILE:
            	
                if(data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {
                	
                    selectedFile = new File
                    		(data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
                    filePath.setText(selectedFile.getPath());
                    filelocation = (String) filePath.getText();
                }
                break;
            }
        }
    }
}
