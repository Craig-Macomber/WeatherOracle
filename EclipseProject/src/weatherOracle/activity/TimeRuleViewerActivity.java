package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.filter.Filter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class TimeRuleViewerActivity extends Activity {

	final List<CheckBox> days = new ArrayList<CheckBox>();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.time_rule_viewer_activity);
	    
	    days.add((CheckBox) findViewById(R.id.monday_checkbox));
	    days.add((CheckBox) findViewById(R.id.tuesday_checkbox));
	    days.add((CheckBox) findViewById(R.id.wednesday_checkbox));
	    days.add((CheckBox) findViewById(R.id.thursday_checkbox));
	    days.add((CheckBox) findViewById(R.id.friday_checkbox));
	    days.add((CheckBox) findViewById(R.id.saturday_checkbox));
	    days.add((CheckBox) findViewById(R.id.sunday_checkbox));
	    
	    final Button saveButton = (Button) findViewById(R.id.save_filter_button_time);
	    initializeSaveButtonListener(saveButton);
	    
	    final Button selectAll = (Button) findViewById(R.id.select_all);
	    final Button deselectAll = (Button) findViewById(R.id.deselect_all);
	    final Button weekends = (Button) findViewById(R.id.weekends);
	    final Button weekdays = (Button) findViewById(R.id.weekdays);
	    final Button monday = (CheckBox) findViewById(R.id.monday_checkbox);
	    final Button tuesday = (CheckBox) findViewById(R.id.tuesday_checkbox);
	    final Button wednesday = (CheckBox) findViewById(R.id.wednesday_checkbox);
	    final Button thursday = (CheckBox) findViewById(R.id.thursday_checkbox);
	    final Button friday = (CheckBox) findViewById(R.id.friday_checkbox);
	    final Button saturday = (CheckBox) findViewById(R.id.saturday_checkbox);
	    final Button sunday = (CheckBox) findViewById(R.id.sunday_checkbox);
	    
	    selectAll.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(true);
				}
				((CheckBox)weekends).setChecked(true);
				((CheckBox)weekdays).setChecked(true);
			}
		});
	
	    deselectAll.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				for (int i =0; i < days.size();i++) {
					days.get(i).setChecked(false);
				}
				((CheckBox)weekends).setChecked(false);
				((CheckBox)weekdays).setChecked(false);
			}
		});	
	
	    weekends.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)weekends).isChecked()) {
					for (int i =5; i < days.size();i++) {
						days.get(i).setChecked(true);
					}
				} else {
					for (int i =5; i < days.size();i++) {
						days.get(i).setChecked(false);
					}	
				}
				
			}
		});
	    
	    weekdays.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)weekdays).isChecked()) {
					for (int i =0; i < 5;i++) {
						days.get(i).setChecked(true);
					}
				} else {
					for (int i =0; i < 5;i++) {
						days.get(i).setChecked(false);
					}	
				}
			}
		});
	    
	    monday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)monday).isChecked()) {
					weekdayCheck();
				} else {		
					((CheckBox) weekdays).setChecked(false);
				}
			}
		});
	    
	    tuesday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)tuesday).isChecked()) {
					weekdayCheck();
				} else {		
					((CheckBox) weekdays).setChecked(false);
				}
			}
		});
	    
	    wednesday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)wednesday).isChecked()) {
					weekdayCheck();
				} else {		
					((CheckBox) weekdays).setChecked(false);
				}
			}
		});
	    
	    thursday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)thursday).isChecked()) {
					weekdayCheck();
				} else {		
					((CheckBox) weekdays).setChecked(false);
				}
			}
		});
	    
	    friday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)friday).isChecked()) {
					weekdayCheck();
				} else {		
					((CheckBox) weekdays).setChecked(false);
				}
			}
		});
	    
	    saturday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)saturday).isChecked()) {
					weekendCheck();
				} else {		
					((CheckBox) weekends).setChecked(false);
				}
			}
		});
	    
	    sunday.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox)saturday).isChecked()) {
					weekendCheck();
				} else {		
					((CheckBox) weekends).setChecked(false);
				}
			}
		});
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
	        	 	Filter filter = new Filter(FilterMenuActivity.filterName);
	        	 	HomeMenuActivity.testFilterList.add(filter);
	        	 	finish();
	            }
	        });
	}
}
