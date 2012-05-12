package weatherOracle.activity;

import weatherOracle.filter.Filter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FilterOptionsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.filter_options_activity);
	    final Button saveButton = (Button) findViewById(R.id.save_filter_button_tools);
	    saveButton.setOnClickListener(new View.OnClickListener()
        {
         public void onClick(View v)
            {
        	 	EditText editText = (EditText)findViewById(R.id.text_box);
        	 	String filterName = editText.getText().toString();
        	 	
        	 	// Here must check format of string before adding to FilterList/Store:
        	 	// (1) String must be alphanumeric
        	 	// (2) String must not represent name that already exists
        	 	// (3) String must be non-empty
        	 	//
        	 	// There should be a method call here ... that will call a private method
        	 	// that will do this checking. If any one of the criteria are not met,
        	 	// then it will trigger an AlertDialogue that tells the user that they have
        	 	// put in incorrect data and it will also tell notify them of the 3 criteria
        	 	// listed above that must be met for a valid name
        	 	//
        	 	//
        	 	
        	 	Filter filter = new Filter(filterName);
        	 	HomeMenuActivity.testFilterList.add(filter);
        	 	finish();
            }
        });
	       
	}
}
