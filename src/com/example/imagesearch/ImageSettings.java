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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_settings);
		
		SetSpinnerAdapter(R.id.spFileType, R.array.file_type_array);
		SetSpinnerAdapter(R.id.spColor, R.array.color_array);
		SetSpinnerAdapter(R.id.spSize, R.array.size_array);
		SetSpinnerAdapter(R.id.spType, R.array.type_array);
		SetSpinnerAdapter(R.id.spSafeSearch, R.array.safe_search_array);
		
	}
	
	void SetSpinnerAdapter(int spinId, int stringArrayId) {
		Spinner spinner = (Spinner) findViewById(spinId);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        stringArrayId, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}
	
	public void onSaveSetings(View v) {
		Toast.makeText(this,  "Saved Settings", Toast.LENGTH_SHORT).show();
		Spinner spinner;
		
		EditText etSite = (EditText)findViewById(R.id.etSite);
		
		// Create intent to send back to main activity
		Intent data = new Intent();
		
		spinner = (Spinner) findViewById(R.id.spFileType);
		data.putExtra("fileType",  spinner.getSelectedItem().toString());
		
		spinner = (Spinner) findViewById(R.id.spColor);
		data.putExtra("color",  spinner.getSelectedItem().toString());

		spinner = (Spinner) findViewById(R.id.spSize);
		data.putExtra("size",  spinner.getSelectedItem().toString());

		spinner = (Spinner) findViewById(R.id.spType);
		data.putExtra("type",  spinner.getSelectedItem().toString());

		spinner = (Spinner) findViewById(R.id.spSafeSearch);
		data.putExtra("safeSearch",  spinner.getSelectedItem().toString());
		
		data.putExtra("site", etSite.getText().toString());
		setResult(RESULT_OK, data);
		finish();
	}
}
