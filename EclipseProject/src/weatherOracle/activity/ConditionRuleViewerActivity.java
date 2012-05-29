
// tim is the best

package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import weatherOracle.filter.*;
import weatherOracle.notification.NotificationStore;

public class ConditionRuleViewerActivity extends Activity {

	LayoutParams params;
    LinearLayout mainLayout;
    
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.condition_rule_viewer_activity);
		mainLayout = (LinearLayout)findViewById(R.id.condition_activity_linear_layout);

		final Button saveButton = (Button) findViewById(R.id.save_filter_button_conditions);   
		initializeSaveButtonListener(saveButton);

		CreateAddConditionButton();
		populateConditionRules();
		displayConditionRules();

    }
    
    private void populateConditionRules(){

    	//conditions = FilterMenuActivity.filter.getConditionRules();
    	//conditions = new TreeSet<ConditionRule>();
    	//conditions.add(new ConditionRule("C1", 0, 10));
    }
    
    private void displayConditionRules() {
    	final List<ConditionRule> conditionList = new ArrayList<ConditionRule>(FilterMenuActivity.conditions);
    	for (int i = 0; i < conditionList.size(); i++) {    		
    		final RelativeLayout rl = new RelativeLayout(this);
    		//final Button deleteButton = new Button(this);
    		final TextView textview = new TextView(this);
    		int min = conditionList.get(i).getMinMax().first;
    		int max = conditionList.get(i).getMinMax().second;
    		String range;
    		range = min + ConditionRule.getUnits(conditionList.get(i).getCondition()) + " - " + max + ConditionRule.getUnits(conditionList.get(i).getCondition());
    		
    		textview.setText(conditionList.get(i).getCondition() + ":\n " + range);
    		textview.setTextSize(2,15);
         	 
    		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
 					LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
 			layoutParams.setMargins(8, 4, 8, 4); // top and bottom margins are 4 so that if two elements
 												 // appear in succession the total separation will be 8
    		
    	  	final Button deleteButton = new Button(this);
          	deleteButton.setText("Delete");
      	
            rl.addView(deleteButton);
    	  	LayoutParams params = (RelativeLayout.LayoutParams)deleteButton.getLayoutParams();
    	  	((android.widget.RelativeLayout.LayoutParams) params).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    		
    	  	final int index = i;
   	  	 	deleteButton.setOnClickListener(new View.OnClickListener() {
   	  	 		public void onClick(View v) {
   	  	 			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
   	  	 			builder.setMessage("Are you sure you want to delete this Condition?")
						.setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
			   	  	 			FilterMenuActivity.conditions.remove(conditionList.get(index));
			   	  	 			mainLayout.removeAllViews();
			   	  	 			displayConditionRules();
							}
						})
						.setNegativeButton("No", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// Do Nothing!
								
							}
						});
   	  	 			
   					AlertDialog alert = builder.create();
   					alert.show();
   	  	 		}
   	  	 	});
    	  	
    		rl.addView(textview);
    		rl.setBackgroundResource(R.drawable.main_view_element);
    		mainLayout.addView(rl, layoutParams);
    	}
    }
    
    

	private void CreateAddConditionButton() {
    	Button b = (Button)findViewById(R.id.add_condition_button);
    	 b.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
             	Intent myIntent = new Intent(view.getContext(), ConditionAdderActivity.class);
                startActivity(myIntent);     
             } 
         });
    	
    }

// 	public void onResume() {
// 		super.onResume();
// 		mainLayout.removeAllViews();
//    	populateConditionRules();
//    	displayConditionRules();
// 	}

 	public void onWindowFocusChanged(boolean hasFocus){
 		super.onWindowFocusChanged(hasFocus);
 		if(hasFocus) {
 	 		mainLayout.removeAllViews();
 	    	populateConditionRules();
 	    	displayConditionRules();
 		} 
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
	        	 		
	        	 		if (iterationFilterName.equals(initialFilterName)
	        	 				&& iterationLocationName.equals(initialLocationName)){
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
	        	 	
	        	 	boolean longitudeValid = true;
	        	 	boolean latitudeValid = true;
	        	 	
	        	 	try {
		        		FilterMenuActivity.latitude = Double.parseDouble(FilterNameActivity.latitude.getText().toString());	
		        	} catch (Exception e) {
		        		latitudeValid = false;
		        	}
		        	
		        	try {
		        		FilterMenuActivity.longitude = Double.parseDouble(FilterNameActivity.longitude.getText().toString());	
		        	} catch (Exception e) {
		        		longitudeValid = false;
		        	}
	        	 	
	        	 	// filter name is valid
	        	 	if(filterNameValid && latitudeValid && longitudeValid){		
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
	        	 		} else if (!longitudeValid || !latitudeValid){
	        	 			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
	                         builder.setMessage("Latitude and longitude must be positive or negative decimal number.")
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
}


