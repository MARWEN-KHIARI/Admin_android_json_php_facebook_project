package com.eCommerce.GestionProduit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class AllProductsActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;
	Button btnReturn;
	EditText inputSearch;
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;
	ArrayList<HashMap<String, String>> productsList_sort;
	// url to get all products list
	


	// products JSONArray
	JSONArray products = null;

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);
	
	
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		btnReturn = (Button) findViewById(R.id.btnReturn);
		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();
		productsList_sort = new ArrayList<HashMap<String, String>>();
		// Loading products in Background Thread
		new LoadAllProducts().execute();

		// Get listview
		ListView lv = getListView();
		
				
		
		// on seleting single product
		// launching Edit Product Screen
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String pid = ((TextView) view.findViewById(R.id.pid)).getText()
						.toString();

				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						EditProductActivity.class);
				// sending pid to next activity
				in.putExtra(AdminActivity.TAG_PID, pid);

				// starting new activity and expecting some response back
				startActivityForResult(in, 100);
			}
		});

		// recherche produit
		inputSearch.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// Abstract Method of TextWatcher Interface.
				productsList_sort.clear();
				String ch = inputSearch.getText().toString();
				int textlength = 0;
				textlength = ch.length();

				for (HashMap<String, String> hm : productsList) {
					if (textlength <= hm.get(AdminActivity.TAG_NAME).toString().length()) {
						if (ch.equalsIgnoreCase((String) (hm.get(AdminActivity.TAG_NAME)
								.toString()).subSequence(0, textlength))) {
							productsList_sort.add(hm);
						}
					}
				}

				ListAdapter adapter = new SimpleAdapter(
						AllProductsActivity.this, productsList_sort,
						R.layout.list_item, new String[] { AdminActivity.TAG_PID, AdminActivity.TAG_NAME },
						new int[] { R.id.pid, R.id.name });
				setListAdapter(adapter);

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Abstract Method of TextWatcher Interface.
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			
			}
		});
		// exit
		btnReturn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update product
				AllProductsActivity.this.finish();
			}
		});


//		Animation animation = AnimationUtils.loadAnimation(this, R.layout.anim.slide_top_to_bottom);
//		lv.startAnimation(animation);
		//ImageView spaceshipImage = (ImageView) findViewById(R.id.spaceshipImage);
		//Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
		//lv.startAnimation(hyperspaceJumpAnimation);
	}

	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AllProductsActivity.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();


			params.add(new BasicNameValuePair("hach", MainScreenActivity.hach_1 ));
			

			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(AdminActivity.url_all_products, "GET",
					params);

			// Check your log cat for JSON reponse
			

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(AdminActivity.TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(AdminActivity.TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(AdminActivity.TAG_PID);
						String name = c.getString(AdminActivity.TAG_NAME);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(AdminActivity.TAG_PID, id);
						map.put(AdminActivity.TAG_NAME, name);

						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {
					// no products found
					// Launch Add New product Activity
					Intent i = new Intent(getApplicationContext(),
							NewProductActivity.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
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
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							AllProductsActivity.this, productsList,
							R.layout.list_item, new String[] { AdminActivity.TAG_PID,
									AdminActivity.TAG_NAME },
							new int[] { R.id.pid, R.id.name });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}

	
}
