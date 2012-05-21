package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.filter.Filter;
import weatherOracle.filter.TimeRule;

import android.app.Activity;
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
	    initializeSaveButton();
	    displayTime();
	
	
	}

	private void displayTime() {
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
		    weekdayCheck();
		    weekendCheck();
	    }
		
	}

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
	    final Button weekends = (Button) findViewById(R.id.weekends);
	    final Button weekdays = (Button) findViewById(R.id.weekdays);
	    
	    if (FilterMenuActivity.initialFilterName.equals("")){
	    	((CheckBox)monday).setChecked(true);
	    	FilterMenuActivity.times.add(new TimeRule("Monday"));
	    	
	    	((CheckBox)tuesday).setChecked(true);
	    	FilterMenuActivity.times.add(new TimeRule("Tuesday"));
	    	
	    	((CheckBox)wednesday).setChecked(true);
	    	FilterMenuActivity.times.add(new TimeRule("Wednesday"));
	    	
	    	((CheckBox)thursday).setChecked(true);
	    	FilterMenuActivity.times.add(new TimeRule("Thursday"));
	    	
	    	((CheckBox)friday).setChecked(true);
	    	FilterMenuActivity.times.add(new TimeRule("Friday"));
	    	
	    	((CheckBox)saturday).setChecked(true);
	    	FilterMenuActivity.times.add(new TimeRule("Saturday"));
	    	
	    	((CheckBox)sunday).setChecked(true);
	    	FilterMenuActivity.times.add(new TimeRule("Sunday"));
	    	
	    	((CheckBox)weekdays).setChecked(true);
	    	((CheckBox)weekends).setChecked(true);
	    }
	    
	    monday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)monday).isChecked()) {
					weekdayCheck();
					FilterMenuActivity.times.add(new TimeRule("Monday"));
				} else {		
					((CheckBox) weekdays).setChecked(false);
					FilterMenuActivity.times.remove(new TimeRule("Monday"));
				}
			}
		});
	    
	    tuesday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)tuesday).isChecked()) {
					weekdayCheck();
					FilterMenuActivity.times.add(new TimeRule("Tuesday"));
				} else {		
					((CheckBox) weekdays).setChecked(false);
					FilterMenuActivity.times.remove(new TimeRule("Tuesday"));
				}
			}
		});
	    
	    wednesday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)wednesday).isChecked()) {
					weekdayCheck();
					FilterMenuActivity.times.add(new TimeRule("Wednesday"));
				} else {		
					((CheckBox) weekdays).setChecked(false);
					FilterMenuActivity.times.remove(new TimeRule("Wednesday"));
				}
			}
		});
	    
	    thursday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)thursday).isChecked()) {
					weekdayCheck();
					FilterMenuActivity.times.add(new TimeRule("Thursday"));
				} else {		
					((CheckBox) weekdays).setChecked(false);
					FilterMenuActivity.times.remove(new TimeRule("Thursday"));
				}
			}
		});
	    
	    friday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)friday).isChecked()) {
					weekdayCheck();
					FilterMenuActivity.times.add(new TimeRule("Friday"));
				} else {		
					((CheckBox) weekdays).setChecked(false);
					FilterMenuActivity.times.remove(new TimeRule("Friday"));
				}
			}
		});
	    
	    saturday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)saturday).isChecked()) {
					weekendCheck();
					FilterMenuActivity.times.add(new TimeRule("Saturday"));
				} else {		
					((CheckBox) weekends).setChecked(false);
					FilterMenuActivity.times.remove(new TimeRule("Saturday"));
				}
			}
		});
	    
	    sunday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)sunday).isChecked()) {
					weekendCheck();
					FilterMenuActivity.times.add(new TimeRule("Sunday"));
				} else {		
					((CheckBox) weekends).setChecked(false);
					FilterMenuActivity.times.remove(new TimeRule("Sunday"));
				}
			}
		});
	    weekends.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)weekends).isChecked()) {
					for (int i =5; i < days.size();i++) {
						days.get(i).setChecked(true);
						String day = (String)days.get(i).getText();
						FilterMenuActivity.times.add(new TimeRule(day));
					}
				} else {
					for (int i =5; i < days.size();i++) {
						days.get(i).setChecked(false);
						String day = (String)days.get(i).getText();
						FilterMenuActivity.times.remove(new TimeRule(day));
					}	
				}
				
			}
		});
	    
	    weekdays.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)weekdays).isChecked()) {
					for (int i =0; i < 5;i++) {
						days.get(i).setChecked(true);
						String day = (String)days.get(i).getText();
						FilterMenuActivity.times.add(new TimeRule(day));
					}
				} else {
					for (int i =0; i < 5;i++) {
						days.get(i).setChecked(false);
						String day = (String)days.get(i).getText();
						FilterMenuActivity.times.remove(new TimeRule(day));
					}	
				}
			}
		});
	    
	    final Button selectAll = (Button) findViewById(R.id.select_all);
	    final Button deselectAll = (Button) findViewById(R.id.deselect_all);
	    
	        
	    selectAll.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(true);
			//		String day = (String)days.get(i).getText();
			//		FilterMenuActivity.times.add(new TimeRule(day));
				}
				((CheckBox)weekends).setChecked(true);
				((CheckBox)weekdays).setChecked(true);
			}
		});
	
	    deselectAll.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(false);
			//		String day = (String)days.get(i).getText();
			//		FilterMenuActivity.times.remove(new TimeRule(day));
				}
				((CheckBox)weekends).setChecked(false);
				((CheckBox)weekdays).setChecked(false);
			}
		});
	    
	    
		
	}

	private void weekdayCheckBox(CheckBox c) {
		c.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void initializeDays() {
		days.add((CheckBox) findViewById(R.id.monday_checkbox));
	    days.add((CheckBox) findViewById(R.id.tuesday_checkbox));
	    days.add((CheckBox) findViewById(R.id.wednesday_checkbox));
	    days.add((CheckBox) findViewById(R.id.thursday_checkbox));
	    days.add((CheckBox) findViewById(R.id.friday_checkbox));
	    days.add((CheckBox) findViewById(R.id.saturday_checkbox));
	    days.add((CheckBox) findViewById(R.id.sunday_checkbox));
	}

	private void weekdayCheck() {
		boolean check = true;
		for (int i = 0; i < 5;i++) {
			if (!days.get(i).isChecked()) {
				check = false;
			}
		}
		if (check) {
		    ((CheckBox)findViewById(R.id.weekdays)).setChecked(true);
		}
	}
	
	private void weekendCheck() {
		boolean check = true;
		for (int i = 5; i < days.size();i++) {
			if (!days.get(i).isChecked()) {
				check = false;
			}
		}
		if (check) {
			((CheckBox)findViewById(R.id.weekends)).setChecked(true);
		}
	}
	
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
	        	 			for(int i = 0; i < HomeMenuActivity.filterList.size(); i++){  
	       	   	  				Filter current = HomeMenuActivity.filterList.get(i);
	       	   	  				if(current.getName().equals(FilterMenuActivity.initialFilterName)){
	       	   	  					HomeMenuActivity.filterList.remove(i);
	       	   	  					i--;
	       	   	  				}
	       	   	  			}
	        	 		}
	        	 		HomeMenuActivity.filterList.add(FilterMenuActivity.filter);	
	        	 		finish();
	        	 	} else {
	        	 		FilterMenuActivity.tabHost.setCurrentTab(0);
	        	 	}
	            }
	        });
	} 
}
