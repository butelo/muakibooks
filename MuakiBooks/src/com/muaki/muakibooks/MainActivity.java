package com.muaki.muakibooks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {


	private static String LOGTAG = "MuakiMain";
	private InitTask initTask;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        initTask = new InitTask();
        initTask.execute(this);
        
    }

    private void metodoParaBaixarZip() {
        String path ="http://www.muaki.com/muakibooks/vintemegas.zip";
        String targetFileName = "/muakibooks/contodavaca/contodavaca.zip";
        boolean eof = false;
        Log.i(MainActivity.LOGTAG,"yuju tamos aqui ");
   
    	File directory = new File(Environment.getExternalStorageDirectory()
    			+ File.separator + "muakibooks" + File.separator + "contodavaca");
    			directory.mkdirs();
        
        try {
            FileOutputStream destinationFile = new FileOutputStream(
    				Environment.getExternalStorageDirectory()
    						 + targetFileName);
			URL u = new URL(path);
		    HttpURLConnection c = (HttpURLConnection) u.openConnection();
		    c.setRequestMethod("GET");
		    c.setDoOutput(true);
		    c.connect();
		       InputStream in = c.getInputStream();
		        byte[] buffer = new byte[1024];
		        int len1 = 0;
		        while ( (len1 = in.read(buffer)) > 0 ) {
		        	destinationFile.write(buffer,0, len1);
		                 }

		    
		        destinationFile.close();

		    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	  protected class InitTask extends AsyncTask<Context, Integer, String> {

		@Override
		protected String doInBackground(Context... arg0) {
	        metodoParaBaixarZip();

			return null;
		}
      	
      }
}
