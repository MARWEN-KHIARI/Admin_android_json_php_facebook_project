package com.eCommerce.GestionProduit;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eCommerce.GestionProduit.NewProductActivity.CreateNewProduct;

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

public class EditProductActivity extends Activity {

	EditText inputName;
	EditText inputPrice;
	EditText inputDesc;
	EditText inputCreatedAt;
	EditText inputRef;
	EditText inputCategories;
	EditText inputSize;
	EditText inputTax;
	CheckBox inputAvailability;
	
	Button btnSave;
	Button btnDelete;
	Button btnReturn;
	Button btnShareImageProduct;
	TextView adresseURL;
	String URLPicture = "";

	String pid;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_product);

		// save button
		btnSave = (Button) findViewById(R.id.btnSave);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnReturn = (Button) findViewById(R.id.btnReturn);
		// getting product details from intent
		Intent i = getIntent();

		// getting product id (pid) from intent
		pid = i.getStringExtra(AdminActivity.TAG_PID);

		// Getting complete product details in background thread
		new GetProductDetails().execute();

		// save button click event
		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update product
				new SaveProductDetails().execute();
			}
		});

		// exit
		btnReturn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update product
				EditProductActivity.this.finish();
			}
		});

		// Delete button click event
		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// deleting product in background thread
				new DeleteProduct().execute();
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
	 * Background Async Task to Get complete product details
	 * */
	class GetProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditProductActivity.this);
			pDialog.setMessage("Loading product details. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);			
			pDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
	                public void onClick(final DialogInterface dialog, final int id) {
	                  cancel(true);
	                  try {
						EditProductActivity.this.finalize();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                }});
			pDialog.show();
		}

		/**
		 * Getting product details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Check for success tag
					int success;
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("hach", MainScreenActivity.hach_1 ));
						params.add(new BasicNameValuePair("pid", pid));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jsonParser.makeHttpRequest(
								AdminActivity.url_product_details, "GET", params);

						// check your log for json response
						//Log.d("Single Product Details", json.toString());

						// json success tag
						success = json.getInt(AdminActivity.TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray productObj = json
									.getJSONArray(AdminActivity.TAG_PRODUCTS); // JSON Array

							// get first product object from JSON Array
							JSONObject product = productObj.getJSONObject(0);

							// product with this pid found
							// Edit Text
							inputName = (EditText) findViewById(R.id.inputName);
							inputPrice = (EditText) findViewById(R.id.inputPrice);
							inputDesc = (EditText) findViewById(R.id.inputDesc);
							inputCreatedAt = (EditText) findViewById(R.id.inputCreatedAt);
							inputRef = (EditText) findViewById(R.id.inputRef);
							inputSize = (EditText) findViewById(R.id.inputSize);
							inputTax = (EditText) findViewById(R.id.inputTax);
							inputCategories = (EditText) findViewById(R.id.inputCategories);
							inputAvailability = (CheckBox) findViewById(R.id.inputAvailability);
							
							

							// display product data in EditText
							inputName.setText(product.getString(AdminActivity.TAG_NAME));
							inputPrice.setText(product.getString(AdminActivity.TAG_PRICE));
							inputDesc.setText(product.getString(AdminActivity.TAG_DESCRIPTION));
							
							inputCreatedAt.setText(product.getString(AdminActivity.TAG_CREATED_AT));
							inputRef.setText(product.getString(AdminActivity.TAG_REF));
							inputSize.setText(product.getString(AdminActivity.TAG_SIZE));
							inputTax.setText(product.getString(AdminActivity.TAG_TAX));
							inputCategories.setText(product.getString(AdminActivity.TAG_CATEGORIES));
							Log.d("TAG_AVAILABILITY_get:",product.getString(AdminActivity.TAG_AVAILABILITY));
							if(product.getString(AdminActivity.TAG_AVAILABILITY).equals("0")){
								inputAvailability.setChecked(false);
							}else if(product.getString(AdminActivity.TAG_AVAILABILITY).equals("1")){ 
								inputAvailability.setChecked(true); 
							}
							
							
							
							URLPicture = product.getString(AdminActivity.TAG_URL);
							if (URLPicture != "") {
								adresseURL.setVisibility(TextView.VISIBLE);
								adresseURL.setText("URL: " + URLPicture);
							}

						} else {
							// product with pid not found
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}

	/**
	 * Background Async Task to Save product Details
	 * */
	class SaveProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditProductActivity.this);
			pDialog.setMessage("Saving product ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialog, final int id) {
                  cancel(true);
                  try {
					EditProductActivity.this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }});
			pDialog.show();
		}

		/**
		 * Saving product
		 * */
		protected String doInBackground(String... args) {

			// getting updated data from EditTexts
			String name = inputName.getText().toString();
			String price = inputPrice.getText().toString();
			String description = inputDesc.getText().toString();
			
			String CreatedAt = inputCreatedAt.getText().toString();
			String Ref = inputRef.getText().toString();
			String Size = inputSize.getText().toString();
			String Tax = inputTax.getText().toString();
			String Categories = inputCategories.getText().toString();
			Boolean Availability = inputAvailability.isChecked();
			Log.d("TAG_AVAILABILITY_Update:",""+Availability);
			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("hach", MainScreenActivity.hach_1 ));
			params.add(new BasicNameValuePair(AdminActivity.TAG_PID, pid));
			params.add(new BasicNameValuePair(AdminActivity.TAG_NAME, name));
			params.add(new BasicNameValuePair(AdminActivity.TAG_PRICE, price));
			params.add(new BasicNameValuePair(AdminActivity.TAG_DESCRIPTION, description));
			params.add(new BasicNameValuePair(AdminActivity.TAG_URL, URLPicture));
			params.add(new BasicNameValuePair(AdminActivity.TAG_REF, Ref));
			params.add(new BasicNameValuePair(AdminActivity.TAG_CREATED_AT, CreatedAt));
			params.add(new BasicNameValuePair(AdminActivity.TAG_CATEGORIES, Categories));
			params.add(new BasicNameValuePair(AdminActivity.TAG_SIZE, Size));
			params.add(new BasicNameValuePair(AdminActivity.TAG_TAX, Tax));
			if(Availability){
				params.add(new BasicNameValuePair(AdminActivity.TAG_AVAILABILITY, "1"));
			}
			else{ 
				params.add(new BasicNameValuePair(AdminActivity.TAG_AVAILABILITY, "0"));
			}

			// sending modified data through http request
			// Notice that update product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(AdminActivity.url_update_product,
					"POST", params);

			// check json success tag
			try {
				int success = json.getInt(AdminActivity.TAG_SUCCESS);

				if (success == 1) {
					// successfully updated
					Intent i = getIntent();
					// send result code 100 to notify about product update
					setResult(100, i);
					finish();
				} else {
					// failed to update product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product uupdated
			pDialog.dismiss();
		}
	}

	/*****************************************************************
	 * Background Async Task to Delete Product
	 * */
	class DeleteProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditProductActivity.this);
			pDialog.setMessage("Deleting Product...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialog, final int id) {
                  cancel(true);
                  try {
					EditProductActivity.this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }});
			pDialog.show();
		}

		/**
		 * Deleting product
		 * */
		protected String doInBackground(String... args) {

			// Check for success tag
			int success;
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("hach", MainScreenActivity.hach_1 ));
				params.add(new BasicNameValuePair("pid", pid));

				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						AdminActivity.url_delete_product, "POST", params);

				// check your log for json response
				//Log.d("Delete Product", json.toString());

				// json success tag
				success = json.getInt(AdminActivity.TAG_SUCCESS);
				if (success == 1) {
					// product successfully deleted
					// notify previous activity by sending code 100
					Intent i = getIntent();
					// send result code 100 to notify about product deletion
					setResult(100, i);
					finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();

		}

	}
	
}