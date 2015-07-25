package com.eCommerce.GestionProduit;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewProductActivity extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;
	Button btnReturn;
	Button btnNext;

	Button btnShareImageProduct;
	TextView adresseURL;
	String URLPicture = "";

	JSONParser jsonParser = new JSONParser();
	EditText inputName;
	EditText inputPrice;
	EditText inputDesc;
	EditText inputCategories;
	EditText inputSize;
	EditText inputTax;
	CheckBox inputAvailability;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_product);
		btnReturn = (Button) findViewById(R.id.btnReturn);
		// Edit Text
		inputName = (EditText) findViewById(R.id.inputName);
		inputPrice = (EditText) findViewById(R.id.inputPrice);
		inputDesc = (EditText) findViewById(R.id.inputDesc);
		inputCategories = (EditText) findViewById(R.id.inputCategories);
		inputSize = (EditText) findViewById(R.id.inputSize);
		inputTax = (EditText) findViewById(R.id.inputTax);
		inputAvailability = (CheckBox) findViewById(R.id.inputAvailability);		
		btnNext = (Button) findViewById(R.id.btnNext);

		// Create button
		Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

		// button click event
		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});

		// exit
		btnReturn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update product
				NewProductActivity.this.finish();
			}
		});
		// suivant
		btnNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update product
				// envoyer vers la page view
				Intent i = new Intent(getApplicationContext(),
						AllProductsActivity.class);
				startActivity(i);
				finish();
			}
		});

		adresseURL = (TextView) findViewById(R.id.adresseURL);
		adresseURL.setVisibility(TextView.GONE);
		btnShareImageProduct = (Button) findViewById(R.id.btnShareImageProduct);
		btnShareImageProduct.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						ImageSelectActivity.class);
				startActivityForResult(i, 1);
			}
		});
	}

	@Override
	protected void onActivityResult(final int requestCode,
			final int resultCode, final Intent in) {
		super.onActivityResult(requestCode, resultCode, in);
		if (resultCode == 2) {
			adresseURL.setVisibility(TextView.VISIBLE);
			URLPicture = in.getStringExtra("URLPicture");
			adresseURL.setText("URL: " + URLPicture);
		} else {
			adresseURL.setVisibility(TextView.GONE);
			adresseURL.setText("");
		}
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewProductActivity.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.setTitle("Loading...");
			pDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
	                public void onClick(final DialogInterface dialog, final int id) {
	                  cancel(true);
	                  try {
						CreateNewProduct.this.finalize();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                }});
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		private List<NameValuePair> params = new ArrayList<NameValuePair>();
		protected String doInBackground(String... args) {
			String name = inputName.getText().toString();
			String price = inputPrice.getText().toString();
			String description = inputDesc.getText().toString();
			String Categories = inputCategories.getText().toString();
			String Size = inputSize.getText().toString();
			String Tax = inputTax.getText().toString();
			Boolean Availability = inputAvailability.isChecked();
			
			/*
			 * URLPicture = product.getString(TAG_URL); if(URLPicture!=""){
			 * adresseURL.setVisibility(TextView.VISIBLE);
			 * adresseURL.setText("URL: " + URLPicture); }
			 */

			// Building Parameters
			

			params.add(new BasicNameValuePair("hach", MainScreenActivity.hach_1 ));
			params.add(new BasicNameValuePair(AdminActivity.TAG_NAME, name));
			params.add(new BasicNameValuePair(AdminActivity.TAG_PRICE, price));
			params.add(new BasicNameValuePair(AdminActivity.TAG_DESCRIPTION, description));
			params.add(new BasicNameValuePair(AdminActivity.TAG_CATEGORIES, Categories));
			params.add(new BasicNameValuePair(AdminActivity.TAG_SIZE, Size));
			params.add(new BasicNameValuePair(AdminActivity.TAG_TAX, Tax));
			if(Availability)params.add(new BasicNameValuePair(AdminActivity.TAG_AVAILABILITY, "1"));
			else params.add(new BasicNameValuePair(AdminActivity.TAG_AVAILABILITY, "0"));
			params.add(new BasicNameValuePair(AdminActivity.TAG_URL, URLPicture));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(AdminActivity.url_create_product,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(AdminActivity.TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Toast.makeText(NewProductActivity.this, "successfully created product", Toast.LENGTH_LONG)
					.show();
					/*
					 * //envoyer vers la page view Intent i = new
					 * Intent(getApplicationContext(),
					 * AllProductsActivity.class); startActivity(i);
					 * 
					 * finish();
					 */
					// closing this screen

				} else {
					// failed to create product
					Toast.makeText(NewProductActivity.this, "failed to create product", Toast.LENGTH_LONG)
					.show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		   protected void onProgressUpdate(Integer... progress) {
			   //pDialog.setProgress(progress[0]);
			   pDialog.setTitle("Loading..."+progress[0]);
		     }

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

}