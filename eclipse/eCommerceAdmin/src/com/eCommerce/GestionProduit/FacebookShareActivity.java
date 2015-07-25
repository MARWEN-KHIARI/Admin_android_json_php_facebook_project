package com.eCommerce.GestionProduit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;

public class FacebookShareActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	Facebook fb;
	ImageView picture_fb, button_fb;
	private SharedPreferences sp;
	TextView welcome;

	Button post;

	EditText txtName;
	EditText txtPrice;
	EditText txtDesc;
	EditText txtUrl;
	EditText linkSite;
	Button btnReturn;

	String pid;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.facebk);

		Intent i = getIntent();
		// getting product id (pid) from intent
		pid = i.getStringExtra(AdminActivity.TAG_PID);
		// Getting complete product details in background thread
		new GetProductDetails().execute();

		post = (Button) findViewById(R.id.btnPost);
		post.setVisibility(Button.GONE);
		String FB_ID = getString(R.string.FB_ID);
		// intializer le plugin facebook avec l'id de l'application
		fb = new Facebook(FB_ID);

		welcome = (TextView) findViewById(R.id.welcome);
		// prendre les parametre de la session sauvegarder
		sp = getPreferences(MODE_PRIVATE);
		String access_token = sp.getString("access_token", null);
		long expires = sp.getLong("access_expires", 0);
		if (access_token != null) {
			fb.setAccessToken(access_token);
		}
		if (expires != 0) {
			fb.setAccessExpires(expires);
		}
		button_fb = (ImageView) findViewById(R.id.button_fb);
		picture_fb = (ImageView) findViewById(R.id.picture_fb);
		button_fb.setOnClickListener(this);
		updateButtonImage();

		// exit
		btnReturn = (Button) findViewById(R.id.btnReturn);
		btnReturn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update product
				FacebookShareActivity.this.finish();
			}
		});

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
			pDialog = new ProgressDialog(FacebookShareActivity.this);
			pDialog.setMessage("Loading product details. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
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
						params.add(new BasicNameValuePair("hach",
								MainScreenActivity.hach_1));
						params.add(new BasicNameValuePair("pid", pid));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jsonParser.makeHttpRequest(
								AdminActivity.url_product_details, "GET",
								params);

						// check your log for json response
						// Log.d("Single Product Details", json.toString());

						// json success tag
						success = json.getInt(AdminActivity.TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray productObj = json
									.getJSONArray(AdminActivity.TAG_PRODUCTS); // JSON
																				// Array

							// get first product object from JSON Array
							JSONObject product = productObj.getJSONObject(0);

							// product with this pid found
							// Edit Text
							txtName = (EditText) findViewById(R.id.inputName);
							txtPrice = (EditText) findViewById(R.id.inputPrice);
							txtDesc = (EditText) findViewById(R.id.inputDesc);
							txtUrl = (EditText) findViewById(R.id.txtUrl);
							linkSite = (EditText) findViewById(R.id.linkSite);

							// display product data in EditText
							txtName.setText(product
									.getString(AdminActivity.TAG_NAME));
							txtPrice.setText(product
									.getString(AdminActivity.TAG_PRICE));
							txtDesc.setText(product
									.getString(AdminActivity.TAG_DESCRIPTION));
							txtUrl.setText(product
									.getString(AdminActivity.TAG_URL));
							linkSite.setText(AdminActivity.urlFolderPicture+AdminActivity.urlLinkToShare);

						} else {
							// product with pid not found
							txtName = (EditText) findViewById(R.id.inputName);
							txtName.setText("product selected is not found");
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

	@SuppressWarnings("deprecation")
	private void updateButtonImage() {
		// TODO Auto-generated method stub
		if (fb.isSessionValid()) {
			post.setVisibility(Button.VISIBLE);
			button_fb.setImageResource(R.drawable.logout_button);

			JSONObject obj = null;
			URL img_url = null;

			try {
				String jsonUser = fb.request("me");
				obj = Util.parseJson(jsonUser);
				// prendre l'id de l'utilisateur pour avoir son photo
				String id = obj.optString("id");
				String name = obj.optString("name");
				welcome.setText("Welcome, " + name);
				img_url = new URL("http://graph.facebook.com/" + id
						+ "/picture?type=small");
				// metre la photo de l'utilisateur dans l'imageview avec le flux
				Bitmap bmp = BitmapFactory.decodeStream(img_url
						.openConnection().getInputStream());
				if (bmp != null) {
					picture_fb.setImageBitmap(bmp);
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FacebookError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// si l'utilisateur n'est pas connecter changer la photo de
			// connexion
			post.setVisibility(Button.GONE);
			button_fb.setImageResource(R.drawable.login_button);
			picture_fb.setImageResource(R.drawable.icon);

		}
	}

	@SuppressWarnings("deprecation")
	public void buttonClicks(View v) {
		switch (v.getId()) {
		case R.id.btnPost:
			// metre les parametres de partage dans un bundle ENUM pour le
			// partager sur facebook
			Bundle params = new Bundle();

			params.putString("name", "" + txtName.getText());
			params.putString("caption", "" + txtPrice.getText());
			params.putString("description", "" + txtDesc.getText());
			params.putString("link", "" + linkSite.getText());
			String urlPic = "" + txtUrl.getText();
			if (urlPic != "" && urlPic!=null)
				params.putString("picture", urlPic);
			else
				params.putString("picture", AdminActivity.urlLOGOSite);

			// partager sur le mur avec la commande feed
			fb.dialog(FacebookShareActivity.this, "feed", params,
					new DialogListener() {

						@Override
						public void onFacebookError(FacebookError e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onError(DialogError e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onComplete(Bundle values) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onCancel() {
							// TODO Auto-generated method stub

						}
					});
			break;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		if (fb.isSessionValid()) {
			// button log out
			try {
				// deconnexion de facebook
				fb.logout(getApplicationContext());
				updateButtonImage();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// login to fb
			// se connecter a facebook et demander l'email de l'utilisateur et
			// creer une session
			fb.authorize(FacebookShareActivity.this, new String[] { "email" },
					new DialogListener() {

						@Override
						public void onFacebookError(FacebookError e) {
							Toast.makeText(FacebookShareActivity.this,
									"OnFbError", Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onError(DialogError e) {
							Toast.makeText(FacebookShareActivity.this,
									"OnError", Toast.LENGTH_SHORT).show();
						}

						// creation d'une session
						@Override
						public void onComplete(Bundle values) {
							Editor editor = sp.edit();
							editor.putString("access_token",
									fb.getAccessToken());
							editor.putLong("access_expires",
									fb.getAccessExpires());
							editor.commit();
							updateButtonImage();
						}

						@Override
						public void onCancel() {
							// creer une boite de texte qui s'affiche
							// automatiquement avec un delai avec TOAST
							Toast.makeText(FacebookShareActivity.this,
									"OnCancel", Toast.LENGTH_SHORT).show();

						}
					});
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// autorisation de la reconnexion pour rester connecter
		fb.authorizeCallback(requestCode, resultCode, data);
	}
}