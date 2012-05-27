package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.filter.Filter;
import weatherOracle.filter.TimeRule;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.AdapterView.OnItemSelectedListener;

public class TimeRuleViewerActivity extends Activity {
	
	final List<CheckBox> days = new ArrayList<CheckBox>();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.time_rule_viewer_activity);
	    initializeDays();
	    initializeDaysCheckbox();
	    initializeButtons();
	    initializeSaveButton();
	    displayTime();
	}

	/**
	 * For all the days check box, check it if it is currently part of the time
	 * Uncheck it the uncheck if that is not part of the time 
	 */
	private void displayTime() {
		
		for (CheckBox day : this.days) {
			day.setChecked(false);
		}
		
	    for (TimeRule r : FilterMenuActivity.times){
	    	String day = r.getDay();	    	
	    	if (day.equals("Monday")){
	    		(days.get(0)).setChecked(true);
	    	} else if(day.equals("Tuesday")){
	    		(days.get(1)).setChecked(true);
	    	} else if (day.equals("Wednesday")){
	    		(days.get(2)).setChecked(true);
	    	} else if (day.equals("Thursday")){
	    		(days.get(3)).setChecked(true);
	    	} else if (day.equals("Friday")){
	    		(days.get(4)).setChecked(true);
	    	} else if (day.equals("Saturday")){
	    		(days.get(5)).setChecked(true);
	    	} else if (day.equals("Sunday")){
	    		(days.get(6)).setChecked(true);
	    	}
	    }
	}

	/**
	 * Initialize the save button
	 */
	private void initializeSaveButton() {
		final Button saveButton = (Button) findViewById(R.id.save_filter_button_time);
	    initializeSaveButtonListener(saveButton);
		
	}

	private void initializeDaysCheckbox() {
		
		final Button monday = (CheckBox) findViewById(R.id.monday_checkbox);
	    final Button tuesday = (CheckBox) findViewById(R.id.tuesday_checkbox);
	    final Button wednesday = (CheckBox) findViewById(R.id.wednesday_checkbox);
	    final Button thursday = (CheckBox) findViewById(R.id.thursday_checkbox);
	    final Button friday = (CheckBox) findViewById(R.id.friday_checkbox);
	    final Button saturday = (CheckBox) findViewById(R.id.saturday_checkbox);
	    final Button sunday = (CheckBox) findViewById(R.id.sunday_checkbox);
	    
	    monday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)monday).isChecked()) {
					FilterMenuActivity.times.add(new TimeRule("Monday"));
				} else {		
					FilterMenuActivity.times.remove(new TimeRule("Monday"));
				}
			}
		});
	    
	    tuesday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)tuesday).isChecked()) {
					FilterMenuActivity.times.add(new TimeRule("Tuesday"));
				} else {		
					FilterMenuActivity.times.remove(new TimeRule("Tuesday"));
				}
			}
		});
	    
	    wednesday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)wednesday).isChecked()) {
					FilterMenuActivity.times.add(new TimeRule("Wednesday"));
				} else {
					FilterMenuActivity.times.remove(new TimeRule("Wednesday"));
				}
			}
		});
	    
	    thursday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)thursday).isChecked()) {
					FilterMenuActivity.times.add(new TimeRule("Thursday"));
				} else {		
					FilterMenuActivity.times.remove(new TimeRule("Thursday"));
				}
			}
		});
	    
	    friday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)friday).isChecked()) {
					FilterMenuActivity.times.add(new TimeRule("Friday"));
				} else {		
					FilterMenuActivity.times.remove(new TimeRule("Friday"));
				}
			}
		});
	    
	    saturday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)saturday).isChecked()) {

					FilterMenuActivity.times.add(new TimeRule("Saturday"));
				} else {
					FilterMenuActivity.times.remove(new TimeRule("Saturday"));
				}
			}
		});
	    
	    sunday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)sunday).isChecked()) {
					FilterMenuActivity.times.add(new TimeRule("Sunday"));
				} else {		
					FilterMenuActivity.times.remove(new TimeRule("Sunday"));
				}
			}
		});	
	}

	private void initializeButtons() {
		
		final Button weekdays = (Button) findViewById(R.id.weekdays);
		final Button weekends = (Button) findViewById(R.id.weekends);
	    final Button selectAll = (Button) findViewById(R.id.select_all);
	    final Button deselectAll = (Button) findViewById(R.id.deselect_all);
	    
	    weekdays.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(false);
					String day = (String)days.get(i).getText();
					FilterMenuActivity.times.remove(new TimeRule(day));
				}
				for (int i =0; i < 5;i++) {
					days.get(i).setChecked(true);		
					String day = (String)days.get(i).getText();
					FilterMenuActivity.times.add(new TimeRule(day));
				}
			}
		});
	    
	    weekends.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(false);
					String day = (String)days.get(i).getText();
					FilterMenuActivity.times.remove(new TimeRule(day));
				}
				for (int i =5; i < days.size();i++) {
					days.get(i).setChecked(true);		
					String day = (String)days.get(i).getText();
					FilterMenuActivity.times.add(new TimeRule(day));
				}
			}
		});
	    
	    selectAll.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(true);		
					String day = (String)days.get(i).getText();
					FilterMenuActivity.times.add(new TimeRule(day));
				}
			}
		});
	
	    deselectAll.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(false);
					String day = (String)days.get(i).getText();
					FilterMenuActivity.times.remove(new TimeRule(day));
				}
			}
		});
	}
	
	
	
	
	/**
	 * Initialize the days field by adding days from mon - sun in index 0 to 6
	 */
	private void initializeDays() {
		days.add((CheckBox) findViewById(R.id.monday_checkbox));
	    days.add((CheckBox) findViewById(R.id.tuesday_checkbox));
	    days.add((CheckBox) findViewById(R.id.wednesday_checkbox));
	    days.add((CheckBox) findViewById(R.id.thursday_checkbox));
	    days.add((CheckBox) findViewById(R.id.friday_checkbox));
	    days.add((CheckBox) findViewById(R.id.saturday_checkbox));
	    days.add((CheckBox) findViewById(R.id.sunday_checkbox));
	}
	
	/**
	 * 
	 * @param saveButton Button that will be used as a save button
	 */
	private void initializeSaveButtonListener(Button saveButton){
		 saveButton.setOnClickListener(new View.OnClickListener()
	        {
	         public void onClick(View v)
	            {
	        	 	String currentName = FilterMenuActivity.currentFilterName;
	        	 	boolean filterNameValid = true;
	        	 	boolean editingExistingFilter = false;
	        	 	// checks if filter name specified is already assigned to an existing
	        	 	// filter
	        	 	for (int i = 0; i < HomeMenuActivity.filterList.size(); i++){
	        	 		Filter current = HomeMenuActivity.filterList.get(i);
	        	 		if (FilterMenuActivity.initialFilterName.equals(current.getName())){
	        	 			editingExistingFilter = true;
	        	 		}
	        	 		if(current.getName().equals(FilterMenuActivity.currentFilterName) 
	        	 				&& !(editingExistingFilter)){
	        	 			filterNameValid = false;
	        	 		}
	        	 	}
	        	 	System.out.println(FilterMenuActivity.times.toString());
	        	 	// filter name is unique at this point, but not necessarily valid
	        	 	// because it could still be the empty string
	        	 	if(FilterMenuActivity.currentFilterName.trim().equals("")) {
	        	 		filterNameValid = false;
	        	 	}
	        	 	
	        	 	// filter name is valid
	        	 	if(filterNameValid){		
	        	 		FilterMenuActivity.filter.removeTimeRules();
	        	 		FilterMenuActivity.filter.addSetOfTimeRules(FilterMenuActivity.times);
	        	 		FilterMenuActivity.filter.removeConditionRules();
	        	 		FilterMenuActivity.filter.addSetOfConditionRules(FilterMenuActivity.conditions);
	        	 		FilterMenuActivity.filter.setName(FilterMenuActivity.currentFilterName);
	        	 		if(editingExistingFilter){
	        	 			int index = 0;
	        	 			for(int i = 0; i < HomeMenuActivity.filterList.size(); i++){  
	       	   	  				Filter current = HomeMenuActivity.filterList.get(i);
	       	   	  				if(current.getName().equals(FilterMenuActivity.initialFilterName)){
	       	   	  					HomeMenuActivity.filterList.remove(i);
	       	   	  					index = i;
	       	   	  					i--;
	       	   	  				}
	       	   	  			}
	        	 			HomeMenuActivity.filterList.add(index,FilterMenuActivity.filter);
	        	 		} else {
	        	 			HomeMenuActivity.filterList.add(FilterMenuActivity.filter);
	        	 		}
	        	 		finish();
	        	 	} else {
	        	 		FilterMenuActivity.tabHost.setCurrentTab(0);
	        	 		if(FilterMenuActivity.currentFilterName.trim().equals("")) {
	        	 			 AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
	                         builder.setMessage("Filter name must contain at least one alphanumeric letter.")
	                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog, int id) {
	                                 dialog.dismiss();
	                                }
	                            });
	                         AlertDialog alert = builder.create();
	                         alert.show();
		        	 	} else {
		        	 		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
	                         builder.setMessage("Filter names cannot be duplicate.")
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
