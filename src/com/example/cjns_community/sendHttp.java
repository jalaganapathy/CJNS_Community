package com.example.cjns_community;
import com.example.cjns_community.MultipartUtility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.view.View;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

public class sendHttp extends AsyncTask<String, Void, Void>{
	protected Void doInBackground(String... action) {
		System.out.println("in doInBackGround");
		System.out.println("action - 0:"+action[0]);
		System.out.println("action - 1:"+action[1]);
		System.out.println("action -2 :"+action[2]);
		System.out.println("action -3 :"+action[3]);
		
		try {
				if(action[0] == "eas") {
				String easmsg = "http://"+action[3]+":5678/?extended&action=user_eas&resourceId="+ action[1]+"&from="+action[3];
				URL url = new URL(easmsg);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				System.out.println("in sendMessage");
				// read the response
				System.out.println("Response Code EAS: " + conn.getResponseCode());
				InputStream in = new BufferedInputStream(conn.getInputStream());
				//String response = IOUtils.toString(in, "UTF-8");
				String response = in.toString();
				System.out.println(response);
				}
				else if(action[0] == "adv") {
					//Post the file
					URL url = new URL("http://192.168.105.57:6262/ADVERTISE/image1.php");
                    final File uploadFile = new File(action[1]);
		            try {
		            	
		            	System.out.println("action -4 product :"+action[4]);
		            	System.out.println("action -5 price :"+action[5]);
		            	System.out.println("action -6 mobile :"+action[6]);
		            	
		            	System.out.println("File Path:" + uploadFile);
		            	final MultipartUtility http = new MultipartUtility(url);
		                http.addFormField("product", action[4]);
		                http.addFormField("price", action[5]);
		                http.addFormField("owner", action[2]);
		                http.addFormField("mobile", action[6]);
		                http.addFilePart("userfile", uploadFile);
		                final byte[] bytes = http.finish();
		               
		              //send the alert
						String MDTmsg = "http://"+action[3]+":5678/?action=new_adv&resourceId="+action[3];
						URL alert = new URL(MDTmsg);
						HttpURLConnection conn = (HttpURLConnection) alert.openConnection();
						conn.setRequestMethod("GET");
						System.out.println("Response Code ADV: " + conn.getResponseCode());
						
		                try {
		                	//final OutputStream os = new FileOutputStream("someoutput.txt");
		                  //  os.write(bytes);
		                }
		                catch(Exception e) {
		                	e.printStackTrace();
		                }
		            } 
		            catch (Exception e) {
		                e.printStackTrace();
		            }
				}
		}
		catch(Exception e) {
			System.out.println("Sendhttp Exception:" + e);
		}
       return null;
        
    }

 
    protected void onProgressUpdate(Long result) {
       // showDialog("Downloaded " + result + " bytes");
    }



	protected Void onPostExecute(Long result) {
		// TODO Auto-generated method stub
		return null;
	}


}
