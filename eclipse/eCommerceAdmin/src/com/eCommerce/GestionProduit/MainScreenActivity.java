package com.eCommerce.GestionProduit;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainScreenActivity extends Activity {
	final Context context = this;

	private Button btnViewProducts;
	private Button btnNewProduct;
	private Button btnShareProduct;
	private Button btnAdmin;
	private Button ExitButton;
	public static String hach_1 = "";
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		/*
		 *  android.permission.INTERNET
			android.permission.WRITE_EXTERNAL_STORAGE
			android.permission.WAKE_LOCK
		    android.permission.ACCESS_NETWORK_STATE
		Context context1=getBaseContext();
		ConnectivityManager cm =  (ConnectivityManager) context1.getSystemService(Context.CONNECTIVITY_SERVICE);
		Toast.makeText(MainScreenActivity.this, cm.getActiveNetworkInfo().isConnected()+"" ,Toast.LENGTH_LONG).show();
		if(cm.getActiveNetworkInfo()!=null){
		if (cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
		 //proceed with loading
			Toast.makeText(MainScreenActivity.this, "you are not connected",Toast.LENGTH_LONG).show(); 
		} else { 
		//showErrorDialog 
			Toast.makeText(MainScreenActivity.this, "internet is not available",Toast.LENGTH_LONG).show();
		}
		}
		*/
		
		// Buttons
		ExitButton = (Button) findViewById(R.id.buttonAlert);
		btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
		btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
		btnShareProduct = (Button) findViewById(R.id.btnShareProduct);
		btnAdmin = (Button) findViewById(R.id.btnAdmin);
		/*********************** enregistrer hash and load prefs **********************************/		
		
		
		if(LoadPreferences("HachCode")!=null){			
			hach_1 = getMD5(LoadPreferences("HachCode"));			
		}
		else {			
			hach_1 = getMD5(getString(R.string.php_ID));			
		}		
		if(LoadPreferences("UrlLogo")!=null)AdminActivity.urlLOGOSite=LoadPreferences("UrlLogo");
		/*********************** exit **********************************/
		// add button listener		
		ExitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				alertExit();
			}

			
		});
		//test network
		if(isOnline())
		//if(true)
        {
           
        
		/************************************************************************/
		// view products click event
		btnViewProducts.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching All products Activity
				Intent i = new Intent(getApplicationContext(),
						AllProductsActivity.class);
				startActivity(i);

			}
		});

		// view products click event
		btnNewProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching create new product activity
				Intent i = new Intent(getApplicationContext(),
						NewProductActivity.class);
				startActivity(i);

			}
		});
		/*********************** admin **********************************/
		btnAdmin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						AdminActivity.class);
				startActivity(i);
				
			}
		});

		// share products click event
		btnShareProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(),
						AllProductsShareActivity.class);
				startActivity(i);
				// Intent intent = new Intent(this,FacebookShareActivity.class);
				// startActivityForResult(intent, 1000);

			}
		});
        }else {
        	alertNoConnection();
        }
	}

	
	
	void alertExit() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("Exit Application");

		// set dialog message
		alertDialogBuilder
				.setMessage("Click yes to exit!")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								// if this button is clicked, close
								// current activity
								MainScreenActivity.this.finish();
							}
						})
				.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
	
	void alertNoConnection() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("Internet failure!");

		// set dialog message
		alertDialogBuilder
				.setMessage("Unable to connect. Please check your Internet connection and try again.")//Please reboot your phone if your connection problem persists.
				.setCancelable(false)
				.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								// if this button is clicked, close
								// current activity
								MainScreenActivity.this.finish();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
	

	public boolean isOnline() {
		boolean connected = false;
	    ConnectivityManager cm = 
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    if (cm != null) {
	        NetworkInfo[] netInfo = cm.getAllNetworkInfo();	        
	        for (NetworkInfo ni : netInfo) {
	            if ((ni.getTypeName().equalsIgnoreCase("WIFI")
	                    || ni.getTypeName().equalsIgnoreCase("MOBILE"))
	                    && ni.isConnected() && ni.isAvailable()) {
	                connected = true;
	            }
	        }
	    }
	    return connected;
	}
	
	// params.add(new BasicNameValuePair("hach", md5Activity.getMD5()));
	// hach = getMD5(getString(R.string.php_ID));
	public String getMD5(String input) {
		byte[] source;
		String result = "";
		try {
			source = input.getBytes("UTF-8");
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9', 'a', 'b', 'c', 'd', 'e', 'f' };
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(source);
				byte temp[] = md.digest();
				char str[] = new char[16 * 2];
				int k = 0;
				for (int i = 0; i < 16; i++) {
					byte byte0 = temp[i];
					str[k++] = hexDigits[byte0 >>> 4 & 0xf];
					str[k++] = hexDigits[byte0 & 0xf];
				}
				result = new String(str);
			} catch (Exception e) {
				System.out.println("Error!");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error!");
		}
		return result;
	}
	
	 
	private SharedPreferences LoadPreferences1;
	public String LoadPreferences(String key) {		
		LoadPreferences1 = getSharedPreferences("myPrefs",MODE_WORLD_READABLE);				 
		 return LoadPreferences1.getString(key, null);
		
		}
	
}



// get the hach***********************
/*
 * public static final String SignMD5(final String s) { try { // Create MD5 Hash
 * MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
 * digest.update(s.getBytes()); byte messageDigest[] = digest.digest(); //
 * Create Hex String StringBuffer hexString = new StringBuffer(); for (int i =
 * 0; i < messageDigest.length; i++) { String h = Integer.toHexString(0xFF &
 * messageDigest[i]); while (h.length() < 2) h = "0" + h; hexString.append(h); }
 * return hexString.toString(); //DigestUtils.md5(_hash);
 * 
 * } catch (NoSuchAlgorithmException e) { e.printStackTrace(); } return ""; }
 * public static String getMD5_2(String input){ String s="This is a test";
 * MessageDigest m=null; try { m = MessageDigest.getInstance("MD5"); } catch
 * (NoSuchAlgorithmException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } m.update(s.getBytes(),0,s.length()); return (new
 * BigInteger(1,m.digest()).toString(16)); }
 */
