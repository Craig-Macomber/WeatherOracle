package weatherOracle.activity;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import weatherOracle.filter.Filter;
import weatherOracle.filter.TimeRule;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FilterNameActivity extends Activity {
	
	
	boolean invalidName = false;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.filter_name_activity);
	    
	    final EditText filterNameEditText = (EditText)findViewById(R.id.filter_name);
	    final EditText locationNameEditText = (EditText)findViewById(R.id.location_name);
	    final Button saveButton = (Button) findViewById(R.id.save_filter_button_tools);
	    final Button currentLocationButton = (Button) findViewById(R.id.current_location_button);
	    final EditText latitude = (EditText)findViewById(R.id.latitude);
	    final EditText longitude = (EditText)findViewById(R.id.longitude);
	    
	    
	    filterNameEditText.setText(FilterMenuActivity.filter.getName());
	    locationNameEditText.setText(FilterMenuActivity.filter.getLocationName());
	    latitude.setText("" + FilterMenuActivity.latitude);
	    longitude.setText("" + FilterMenuActivity.longitude);
	    
	    FilterMenuActivity.currentFilterName = FilterMenuActivity.filter.getName();
	    FilterMenuActivity.currentLocationName = FilterMenuActivity.filter.getLocationName();
	    
	    initializeSaveButtonListener(saveButton);
	    initializeFilterNameEditTextListener(filterNameEditText);
	    initializeLocationNameEditTextListener(locationNameEditText);
	    initializeCurrentLocationButtonListener(currentLocationButton);
	    initializeLatitudeEditTextListener(latitude);
	    initializeLongitudeEditTextListener(longitude);
	    
	    Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	invalidName = extras.getBoolean("invalidName");
        }
        
	}
	
	private void initializeCurrentLocationButtonListener(final Button currentLocationButton){
		currentLocationButton.setOnClickListener(new View.OnClickListener()
        {
         public void onClick(View v)
            {
        	    double[] location = getLocation();
     		    double latitude = location[0];
     		    double longitude = location[1];
     		    EditText latView = (EditText) findViewById(R.id.latitude);
     		    EditText longView = (EditText) findViewById(R.id.longitude);
     		    latView.setText(""+latitude);
     		    longView.setText(""+longitude);
            }
        });
	}
	
	private void initializeLongitudeEditTextListener(final EditText editText){
		editText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	String temp = Double.toString(FilterMenuActivity.longitude);
	        	try {
	        		FilterMenuActivity.longitude = Double.parseDouble(editText.getText().toString());	
	        	} catch (Exception e) {
	        		editText.setText(temp);
	        	}	        	
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	private void initializeLatitudeEditTextListener(final EditText editText){
		editText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	String temp = Double.toString(FilterMenuActivity.latitude);
	        	try {
	        		FilterMenuActivity.latitude = Double.parseDouble(editText.getText().toString());	
	        	} catch (Exception e) {
	        		editText.setText(temp);
	        	}
	        	
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	private void initializeFilterNameEditTextListener(final EditText editText){
		editText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	FilterMenuActivity.currentFilterName = editText.getText().toString();
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	private void initializeLocationNameEditTextListener(final EditText editText){
		editText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	FilterMenuActivity.currentLocationName = editText.getText().toString();
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
	        	 	// these four variables are gere for debuggin purposes
	        	 	String currentFilterName = FilterMenuActivity.currentFilterName;
	        	 	String initialFilterName = FilterMenuActivity.initialFilterName;
	        	 	String currentLocationName = FilterMenuActivity.currentLocationName;
	        	 	String initialLocationName = FilterMenuActivity.initialLocationName;
	        	 	
	        	 	boolean filterNameValid = true;
	        	 	boolean editingExistingFilter = false; // set to true if the given filter
	        	 										   // being edited is not a 'new' filter
	        	 	
	        	 	
	        	 	// checks to see if filter and location names represent a valid combo ...
	        	 	// meaning the pair is unique
	        	 	for (int i = 0; i < HomeMenuActivity.filterList.size(); i++){
	        	 		// this is set to true only if the 'current' filter at index i
	        	 		// in filterList is the filter that is being edited. This is different
	        	 		// from editingExistingFilter which will never revert back to false
	        	 		// once set to true
	        	 		boolean editingCurrentFilter = false; 
	        	 		
	        	 		Filter current = HomeMenuActivity.filterList.get(i);
	        	 		String iterationFilterName = current.getName();
	        	 		String iterationLocationName = current.getLocationName();
	        	 		
	        	 		if (initialFilterName.equals(current.getName())
	        	 				&& initialLocationName.equals(current.getLocationName())){
	        	 			editingExistingFilter = true;
	        	 			editingCurrentFilter = true;
	        	 		}
	        	 		if(current.getName().equals(currentFilterName) 
	        	 				&& current.getLocationName().equals(currentLocationName)	
	        	 				&& !(editingCurrentFilter)){
	        	 			filterNameValid = false;
	        	 		}
	        	 		
	        	 	}
	        	 	
	        	 	
	        	 	// filter name and location are a unique pair at this point, but not necessarily valid
	        	 	// because it could still be the empty string
	        	 	if(FilterMenuActivity.currentFilterName.trim().equals("") ||
	        	 			FilterMenuActivity.currentLocationName.trim().equals("")) {
	        	 		filterNameValid = false;
	        	 	}
	        	 	
	        	 	// filter name is valid
	        	 	if(filterNameValid){		
	        	 		FilterMenuActivity.filter.removeTimeRules();
	        	 		FilterMenuActivity.filter.addSetOfTimeRules(FilterMenuActivity.times);
	        	 		FilterMenuActivity.filter.setName(FilterMenuActivity.currentFilterName);
	        	 		FilterMenuActivity.filter.setLocationName(FilterMenuActivity.currentLocationName);
	        	 		FilterMenuActivity.filter.removeConditionRules();
	        	 		FilterMenuActivity.filter.addSetOfConditionRules(FilterMenuActivity.conditions);
	        	 		FilterMenuActivity.filter.getLocation().lat = FilterMenuActivity.latitude;
	        	 		FilterMenuActivity.filter.getLocation().lon = FilterMenuActivity.longitude;
	        	 		if(editingExistingFilter){
	        	 			int index = 0;
	        	 			for(int i = 0; i < HomeMenuActivity.filterList.size(); i++){  
	       	   	  				Filter current = HomeMenuActivity.filterList.get(i);
	       	   	  				if(current.getName().equals(FilterMenuActivity.initialFilterName)
	       	   	  						&& current.getLocationName().equals(FilterMenuActivity.initialLocationName)){
	       	   	  					HomeMenuActivity.filterList.remove(i);
	       	   	  					index = i;
	       	   	  					i--;
	       	   	  				}
	       	   	  			}
	        	 			HomeMenuActivity.filterList.add(index, FilterMenuActivity.filter);
	        	 		} else {
	        	 			HomeMenuActivity.filterList.add(FilterMenuActivity.filter);
	        	 		}
	        	 		finish();
	        	 	} else {
	        	 		FilterMenuActivity.tabHost.setCurrentTab(0);
	        	 		if(FilterMenuActivity.currentFilterName.trim().equals("")
	        	 				|| FilterMenuActivity.currentLocationName.trim().equals("")) {
	        	 			 AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
	                         builder.setMessage("Filter and location names must contain at least one character.")
	                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog, int id) {
	                                 dialog.dismiss();
	                                }
	                            });
	                         AlertDialog alert = builder.create();
	                         alert.show();
		        	 	} else {
		        	 		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
	                         builder.setMessage("Filter name already in use for given location.")
	                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog, int id) {
	                                 dialog.dismiss();
	                                }
	                            });
	                         AlertDialog alert = builder.create();
	                         alert.show();
		        	 	}
	        	 	}
	            }
	        });
	}
	
	// gets current location and returns a double array holding the lat and long
	private double[] getLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        List<String> providers = lm.getProviders(true);

        /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
        Location l = null;
        
        for (int i=providers.size()-1; i>=0; i--) {
                l = lm.getLastKnownLocation(providers.get(i));
                if (l != null) break;
        }
        
        double[] gps = new double[2];
        if (l != null) {
                gps[0] = l.getLatitude();
                gps[1] = l.getLongitude();
        }
        return gps;
	}
}
