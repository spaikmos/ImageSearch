package com.example.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImageSearch extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	String query;
	String options = "";
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	static final int SETTINGS_REQUEST = 1234;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(),
						ImageDisplay.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}

		});

		gvResults.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// Log.d("DEBUG", "Loading more items: " + totalItemsCount);
				AsyncHttpClient client = new AsyncHttpClient();
				client.get(
						"https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
								+ "start=" + totalItemsCount + "&v=1.0&q="
								+ Uri.encode(query) + options,
						new JsonHttpResponseHandler() {
							@Override
							public void onSuccess(JSONObject response) {
								JSONArray imageJsonResults = null;
								try {
									imageJsonResults = response.getJSONObject(
											"responseData").getJSONArray(
											"results");
									imageAdapter.addAll(ImageResult
											.fromJSONArray(imageJsonResults));
									Log.d("DEBUG", imageResults.toString());
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						});
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			showSettingsPage();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);

	}

	public void onImageSearch(View v) {
		query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_LONG)
				.show();
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(
				"https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
						+ "start=" + 0 + "&v=1.0&q=" + Uri.encode(query)
						+ options, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						JSONArray imageJsonResults = null;
						try {
							imageJsonResults = response.getJSONObject(
									"responseData").getJSONArray("results");
							imageResults.clear();
							imageAdapter.addAll(ImageResult
									.fromJSONArray(imageJsonResults));
							// Log.d("DEBUG", imageResults.toString());
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
	}

	public void showSettingsPage() {
		Intent i = new Intent(getApplicationContext(), ImageSettings.class);
		startActivityForResult(i, SETTINGS_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == SETTINGS_REQUEST) {
			// Generate the new options string
			String itemText;
			options = ""; 
			
			itemText = data.getExtras().getString("fileType");
			if(!itemText.equals("any")) {
				options += "&as_filetype=" + itemText;
			}
			
			itemText = data.getExtras().getString("color");
			if(!itemText.equals("any")) {
				options += "&imgcolor=" + itemText;
			}
			
			itemText = data.getExtras().getString("size");
			if(!itemText.equals("any")) {
				options += "&imgsz=" + itemText;
			}
			
			itemText = data.getExtras().getString("type");
			if(!itemText.equals("any")) {
				options += "&imgtype=" + itemText;
			}
			
			itemText = data.getExtras().getString("safeSearch");
			if(!itemText.equals("off")) {
				options += "&safe=" + itemText;
			}
			
			itemText = data.getExtras().getString("site");
			if(!itemText.equals("")) {
				options += "&as_sitesearch=" + itemText;
			}
		}
	}
}
