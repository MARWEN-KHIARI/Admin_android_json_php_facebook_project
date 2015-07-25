package com.eCommerce.GestionProduit;

import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageSelectActivity extends Activity {

	private static int RESULT_LOAD_IMAGE = 1;
	private TextView textViewAdrLocal;
	private TextView textViewAdrNet;
	private ImageView imageView;
	private Button buttonSendPicture;
	private String PicturePath = "";
	private String AdrNet = "";
	private Bitmap bitmap;
	//private InputStream inputStream;
	JSONParser jsonParser = new JSONParser();
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagesrc);
		imageView = (ImageView) findViewById(R.id.imgView);
		imageView.setVisibility(ImageView.GONE);
		textViewAdrLocal = (TextView) findViewById(R.id.textViewAdrLocal);
		textViewAdrLocal.setVisibility(TextView.GONE);
		textViewAdrNet = (TextView) findViewById(R.id.textViewAdrNet);
		textViewAdrNet.setVisibility(TextView.GONE);
		Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
		buttonSendPicture = (Button) findViewById(R.id.buttonSendPicture);
		buttonSendPicture.setVisibility(Button.GONE); // visible 0 invisible 1
														// gone 2
		Button buttonReturn = (Button) findViewById(R.id.buttonReturn);
		buttonLoadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});

		buttonSendPicture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (bitmap != null) {
					FileUpload();
				}
			}
		});
		buttonReturn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if ((bitmap != null) && (AdrNet != "")) {
					Intent in = new Intent(getApplicationContext(),
							ImageSelectActivity.class);
					in.putExtra("URLPicture", AdrNet);
					setResult(2, in);
					// AdrNet
				} else {
					setResult(1);
				}
				ImageSelectActivity.this.finish();
			}
		});
	}

	public void bitmapCreate() {
		int maxDecodePixel = 20000;
		int maxinSampleSize = 32;
		// @SuppressWarnings("unused")
		try {
			BitmapFactory.Options option1 = new BitmapFactory.Options();
			option1.inSampleSize = maxinSampleSize;
			bitmap = BitmapFactory.decodeFile(PicturePath, option1);

			if ((bitmap.getWidth() * bitmap.getHeight()) < (maxDecodePixel / maxinSampleSize)) {
				option1.inSampleSize = 1;
				bitmap = BitmapFactory.decodeFile(PicturePath, option1);
			} else if ((bitmap.getWidth() * bitmap.getHeight()) < ((maxDecodePixel / maxinSampleSize) * 2)) {
				option1.inSampleSize = 2;
				bitmap = BitmapFactory.decodeFile(PicturePath, option1);

			} else if ((bitmap.getWidth() * bitmap.getHeight()) < ((maxDecodePixel / maxinSampleSize) * 4)) {
				option1.inSampleSize = 4;
				bitmap = BitmapFactory.decodeFile(PicturePath, option1);

			} else if ((bitmap.getWidth() * bitmap.getHeight()) < ((maxDecodePixel / maxinSampleSize) * 8)) {
				option1.inSampleSize = 8;
				bitmap = BitmapFactory.decodeFile(PicturePath, option1);

			} else if ((bitmap.getWidth() * bitmap.getHeight()) < ((maxDecodePixel / maxinSampleSize) * 16)) {
				option1.inSampleSize = 16;
				bitmap = BitmapFactory.decodeFile(PicturePath, option1);
			}
		} catch (OutOfMemoryError e1) {
			bitmap = null;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			PicturePath = cursor.getString(columnIndex);
			cursor.close();

			try {
				bitmapCreate();
			} catch (OutOfMemoryError e1) {
			}
			if (bitmap != null) {
				imageView.setVisibility(ImageView.VISIBLE);
				imageView.setImageBitmap(bitmap);
				textViewAdrLocal.setText("Address:" + PicturePath);
				textViewAdrLocal.setVisibility(TextView.VISIBLE);
				buttonSendPicture.setVisibility(Button.VISIBLE);
			}
		}

	}

	public void FileUpload() {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("hach", MainScreenActivity.hach_1));
		params.add(new BasicNameValuePair(AdminActivity.TAG_PUT, getPicString()));
		JSONObject json = jsonParser.makeHttpRequest(AdminActivity.url_product_PicUp, "POST",
				params);
		int success = 0;
		String message = "Error Upload";

		try {
			success = json.getInt(AdminActivity.TAG_SUCCESS);
			message = json.getString(AdminActivity.TAG_MESSAGE);
			AdrNet = json.getString(AdminActivity.TAG_Address);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (success == 1) {
			Toast.makeText(ImageSelectActivity.this, message, Toast.LENGTH_LONG)
					.show();
			textViewAdrNet.setText("URL:" + AdrNet);
			textViewAdrNet.setVisibility(TextView.VISIBLE);
		} else {
			// failed to upload
			Toast.makeText(ImageSelectActivity.this, message, Toast.LENGTH_LONG)
					.show();
			// ImageSelectActivity.this.finish();
		}

	}

	public String getPicString() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 98, stream);
		byte[] byte_arr = stream.toByteArray();
		String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
		return image_str.toString();
	}

	/*
	 * //principale
	 * 
	 * 
	 * Button btnShareImageProduct; TextView adresseURL; String URLPicture;
	 * 
	 * 
	 * 
	 * adresseURL = (TextView) findViewById(R.id.adresseURL);
	 * adresseURL.setVisibility(TextView.GONE); btnShareImageProduct = (Button)
	 * findViewById(R.id.btnShareImageProduct);
	 * btnShareImageProduct.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Intent i = new
	 * Intent(getApplicationContext(), ImageSelectActivity.class);
	 * startActivityForResult(i, 1); } });
	 * 
	 * 
	 * @Override protected void onActivityResult(final int requestCode, final
	 * int resultCode, final Intent in) { super.onActivityResult(requestCode,
	 * resultCode, in); if (resultCode == 2) {
	 * adresseURL.setVisibility(TextView.VISIBLE); URLPicture =
	 * in.getStringExtra("URLPicture"); adresseURL.setText("URL: " +
	 * URLPicture); } else { adresseURL.setVisibility(TextView.GONE);
	 * adresseURL.setText(""); } }
	 */
	/*
	 * //xml layout <LinearLayout android:layout_width="match_parent"
	 * android:layout_height="wrap_content" android:orientation="horizontal" >
	 * 
	 * <TextView android:id="@+id/adresseURL"
	 * android:layout_width="match_parent" android:layout_height="match_parent"
	 * android:layout_margin="15px" android:layout_weight="0.3"
	 * android:hint="URL:" />
	 * 
	 * <Button android:id="@+id/btnShareImageProduct"
	 * android:layout_width="match_parent" android:layout_height="match_parent"
	 * android:layout_weight="0.7" android:text="@string/c_Share_Image_Products"
	 * />
	 * 
	 * </LinearLayout>
	 */

}
