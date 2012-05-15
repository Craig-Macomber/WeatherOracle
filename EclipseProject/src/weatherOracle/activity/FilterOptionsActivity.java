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
	
	private void initializeEditTextListener(final EditText editText){
		editText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	boolean validName = true;
	        	String tempFilterName = editText.getText().toString(); 
	        	for (int i = 0; i < HomeMenuActivity.testFilterList.size(); i++){
        	 		Filter current = HomeMenuActivity.testFilterList.get(i);

        	 		// name currently entered in edit text element matches the name of a filter
        	 	    // in the filter list ... and the name in the edit text is NOT equal to the
        	 	    // initial name of the filter. This means that the user entered in a name that is
       	 	 		// already taken. If the name they entered is the same as the initial name that all
       	 			// it means is that they made a number of edits but changed it back to the original
           			// which is valid
        	 		if(current.getName().equals(tempFilterName) && !(tempFilterName.equals(FilterMenuActivity.initialFilterName))){
        	 			validName = false;
        	 		}
        	 	}
	        	if(tempFilterName.trim().equals("")){
	        		validName = false;
	        	}
	        	if(validName){
	        		// name is valid ... at this point currentFilterName is guaranteed to be a unique non-empty
	        		// filter name
	        		FilterMenuActivity.currentFilterName = tempFilterName;
	        	}
	        	
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	private void initializeSaveButtonListener(Button saveButton){
		 saveButton.setOnClickListener(new View.OnClickListener()
	        {
	         public void onClick(View v)
	            {
	        	 	
	        	 	// as long as currentFilterName is non empty we know it holds a valid filter name
	        	 	// this fact is guaranteed to us by the checks in the edit text listener ... if 
	        	 	// currentFilterName is the empty string then either (1) the user never specified a
	        	 	// name, or (2) the user tried to specify a name containing only spaces, or (3) the
	        	 	// user entered in a name of a filter that already existed. Either way, the name would
	        	 	// be invalid and the edit text listener would ensure that currentFilterName was not
	        	 	// updated with this invalid name
	        	 	if(!FilterMenuActivity.currentFilterName.equals("")){ 
	        	 		FilterMenuActivity.filter.setName(FilterMenuActivity.currentFilterName);
	        	 		HomeMenuActivity.testFilterList.add(FilterMenuActivity.filter);
	        	 	//	FilterMenuActivity.filterName = "";
	        	 		finish();
	        	 	} else {
	        	 		FilterMenuActivity.tabHost.setCurrentTab(0);
	        	 	}
	            }
	        });
	} 
}
