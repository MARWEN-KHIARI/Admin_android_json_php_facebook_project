package com.eCommerce.GestionProduit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends Activity {

	// AdminActivity.

	// JSON Node names
	public static String TAG_SUCCESS = "success";
	public static String TAG_PRODUCTS = "product";
	
	public static String TAG_PID = "pid";
	public static String TAG_NAME = "name";
	public static String TAG_PRICE = "price";
	public static String TAG_DESCRIPTION = "description";
	public static String TAG_URL = "url";
	public static String TAG_REF = "ref";
	public static String TAG_TAX = "tax";
	public static String TAG_SIZE = "size";
	public static String TAG_CATEGORIES = "categories";
	public static String TAG_AVAILABILITY = "availability";
	public static String TAG_CREATED_AT = "created_at";
	

	
	public static String TAG_MESSAGE = "message";
	public static String TAG_Address = "address";
	public static String TAG_PUT = "fichier";

	// url site
	//public static String urlSite = "http://10.0.2.2/eCommerce.Touch3D.tn";
	//public static String urlFolderPicture = "http://10.0.2.2/eCommerce.Touch3D.tn/products/";
	public static String urlSite = "http://eCommerce.Touch3D.tn";
	public static String urlFolderPicture = "http://eCommerce.Touch3D.tn/products/";

	// get product name
	public static String url_all_products = urlSite
			+ "/eAndroid/get_all_products.php";

	// single product url
	public static String url_product_details = urlSite
			+ "/eAndroid/get_product_details.php";

	// url to update product
	public static String url_update_product = urlSite
			+ "/eAndroid/update_product.php";

	// url to delete product
	public static String url_delete_product = urlSite
			+ "/eAndroid/delete_product.php";

	// url to upload picture product
	public static String url_product_PicUp = urlSite
			+ "/eAndroid/upload_Pic.php";

	// url to create new product
	public static String url_create_product = urlSite
			+ "/eAndroid/create_product.php";

	// single product url
	public static String urlLinkToShare = urlSite + "/index.php";
	public static String urlLOGOSite = urlSite + "/images/Logo.png";
	private String php_ID1;
	EditText input_Hach;
	EditText input_logo;
	Button load1;
	Button save1;
	Button btnReturn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_view);
		input_Hach = (EditText) findViewById(R.id.input_Hach);
		php_ID1 = getString(R.string.php_ID);
		if (LoadPreferences("HachCode") != null)
			input_Hach.setText(LoadPreferences("HachCode"));
		else
			input_Hach.setText(php_ID1);

		input_logo = (EditText) findViewById(R.id.input_logo);
		if (LoadPreferences("UrlLogo") != null)
			input_logo.setText(LoadPreferences("UrlLogo"));
		else
			input_logo.setText(urlLOGOSite);

		save1 = (Button) findViewById(R.id.save1);
		load1 = (Button) findViewById(R.id.load1);
		btnReturn = (Button) findViewById(R.id.btnReturn);
		save1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				SavePreferences("HachCode", "" + input_Hach.getText().toString());
				SavePreferences("UrlLogo", "" + input_logo.getText().toString());
				//urlLOGOSite = urlSite + "/" + input_logo.getText();
				Toast.makeText(AdminActivity.this,
						"you must restart the application", Toast.LENGTH_LONG)
						.show();
			}
		});
		load1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (LoadPreferences("HachCode") != null)
					input_Hach.setText(LoadPreferences("HachCode"));
				else
					input_Hach.setText(php_ID1);
				if (LoadPreferences("UrlLogo") != null)
					input_logo.setText(LoadPreferences("UrlLogo"));
				else
					input_logo.setText(urlLOGOSite);

			}
		});
		btnReturn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update product
				AdminActivity.this.finish();
			}
		});
	}
/*
	public void SavePreferences(String key, String value) {		
		SharedPreferences SavePreferences1;
		SavePreferences1 = getPreferences(MODE_PRIVATE);	
		Editor editor = SavePreferences1.edit();				
		editor.putString(key, value);
		editor.commit();
	}

	public String LoadPreferences(String key) {
		SharedPreferences LoadPreferences1;
		LoadPreferences1 = getPreferences(MODE_WORLD_READABLE);
		return LoadPreferences1.getString(key, null);
	}
		
		//MODE_APPEND
		//MODE_WORLD_READABLE
		//MODE_PRIVATE
	*/
	public void SavePreferences(String key, String value) {		
		SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
		SharedPreferences.Editor prefsEditor;  
		prefsEditor = myPrefs.edit();  
		//strVersionName->Any value to be stored  
		prefsEditor.putString(key, value);  
		prefsEditor.commit();
			
	}

	public String LoadPreferences(String key) {
		//Get Preferenece  
		SharedPreferences myPrefs;    
		myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);  			
		return myPrefs.getString(key, null);
	}

}
