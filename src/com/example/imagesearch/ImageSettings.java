package com.example.imagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ImageSettings extends Activity {
	static int fileTypePos = 0;
	static int colorPos = 0;
	static int sizePos = 0;
	static int typePos = 0;
	static int safeSearchPos = 0;
	static String site = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_settings);
		
		SetSpinnerAdapter(R.id.spFileType, R.array.file_type_array, fileTypePos);
		SetSpinnerAdapter(R.id.spColor, R.array.color_array, colorPos);
		SetSpinnerAdapter(R.id.spSize, R.array.size_array, sizePos);
		SetSpinnerAdapter(R.id.spType, R.array.type_array, typePos);
		SetSpinnerAdapter(R.id.spSafeSearch, R.array.safe_search_array, safeSearchPos);
		
		// Populate the text box with previous setting if applicable
		if(!site.equals("")) {
			EditText etSite = (EditText)findViewById(R.id.etSite);
			etSite.setText(site);
		}
		
	}
	
	void SetSpinnerAdapter(int spinId, int stringArrayId, int pos) {
		Spinner spinner = (Spinner) findViewById(spinId);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        stringArrayId, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setSelection(pos);
	}
	
	public void onSaveSetings(View v) {
		Toast.makeText(this,  "Saved Settings", Toast.LENGTH_SHORT).show();
		Spinner spinner;
		
		EditText etSite = (EditText)findViewById(R.id.etSite);
		
		// Create intent to send back to main activity
		Intent data = new Intent();
		
		spinner = (Spinner) findViewById(R.id.spFileType);
		data.putExtra("fileType",  spinner.getSelectedItem().toString());
		fileTypePos = spinner.getSelectedItemPosition();
		
		spinner = (Spinner) findViewById(R.id.spColor);
		data.putExtra("color",  spinner.getSelectedItem().toString());
		colorPos = spinner.getSelectedItemPosition();

		spinner = (Spinner) findViewById(R.id.spSize);
		data.putExtra("size",  spinner.getSelectedItem().toString());
		sizePos = spinner.getSelectedItemPosition();

		spinner = (Spinner) findViewById(R.id.spType);
		data.putExtra("type",  spinner.getSelectedItem().toString());
		typePos = spinner.getSelectedItemPosition();

		spinner = (Spinner) findViewById(R.id.spSafeSearch);
		data.putExtra("safeSearch",  spinner.getSelectedItem().toString());
		safeSearchPos = spinner.getSelectedItemPosition();
		
		site = etSite.getText().toString();
		data.putExtra("site", site);
		
		setResult(RESULT_OK, data);
		finish();
	}
}
