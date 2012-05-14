package weatherOracle.activity;

import weatherOracle.filter.Filter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FilterOptionsActivity extends Activity {
	boolean invalidName = false;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.filter_options_activity);
	    
	    final EditText editText = (EditText)findViewById(R.id.text_box);
	    final Button saveButton = (Button) findViewById(R.id.save_filter_button_tools);
	    
	    initializeSaveButtonListener(saveButton);
	    initializeEditTextListener(editText);
	    
	    Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	invalidName = extras.getBoolean("invalidName");
        }
        
	}
	
	
	
	private void initializeSaveButtonListener(Button saveButton){
		 saveButton.setOnClickListener(new View.OnClickListener()
	        {
	         public void onClick(View v)
	            {
	        	 	boolean filterNameValid = true;
	        	 	
	        	 	// checks if filter name specified is already assigned to an existing
	        	 	// filter
	        	 	for (int i = 0; i < HomeMenuActivity.testFilterList.size(); i++){
	        	 		Filter current = HomeMenuActivity.testFilterList.get(i);
	        	 		if(current.getName().equals(FilterMenuActivity.filter.getName())){
	        	 			filterNameValid = false;
	        	 			//alert dialogue
	        	 		}
	        	 	}
	        	 	
	        	 	// filter name is unique at this point, but not necessarily valid
	        	 	// because it could still be the empty string
	        	 	if(FilterMenuActivity.filter.getName().trim().equals("")) {
	        	 		filterNameValid = false;
	        	 	}
	        	 	
	        	 	// filter name is valid
	        	 	if(filterNameValid){
	        	 	//	Filter filter = new Filter(FilterMenuActivity.filterName);
	        	 		HomeMenuActivity.testFilterList.add(FilterMenuActivity.filter);
	        	 	//	FilterMenuActivity.filterName = "";
	        	 		finish();
	        	 	} else {
	        	 		FilterMenuActivity.tabHost.setCurrentTab(0);
	        	 	}
	            }
	        });
	}
	
	private void initializeEditTextListener(final EditText editText){
		editText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	boolean validName = true;
	        	String tempFilterName = editText.getText().toString(); 
	        	for (int i = 0; i < HomeMenuActivity.testFilterList.size(); i++){
        	 		Filter current = HomeMenuActivity.testFilterList.get(i);
        	 		// with just the first test
        	 		if(current.getName().equals(tempFilterName) && !(tempFilterName.equals(FilterMenuActivity.initialFilterName))){
        	 			validName = false;
        	 		}
        	 	}
	        	// name is valid
	            FilterMenuActivity.filter.setName(editText.getText().toString()); 
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	
	
}
