// tim is the best

package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Context;
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

public class ConditionRuleViewerActivity extends Activity {

	LayoutParams params;
    LinearLayout mainLayout;
    
    static Set<ConditionRule> conditions;
    
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
    	conditions = new TreeSet<ConditionRule>();
    	conditions.add(new ConditionRule("C1", 0, 10));
    }
    
    private void displayConditionRules() {
    	List<ConditionRule> conditionList = new ArrayList<ConditionRule>(conditions);
    	for (int i = 0; i < conditionList.size(); i++) {    		
    		final RelativeLayout rl = new RelativeLayout(this);
    		//final Button deleteButton = new Button(this);
    		final TextView textview = new TextView(this);
    		int min = conditionList.get(i).getMinMax().first;
    		int max = conditionList.get(i).getMinMax().second;
    		textview.setText(conditionList.get(i).getCondition() + ": " + min + " - " + max);
    		textview.setTextSize(2,30);
         	 
    	  	final Button deleteButton = new Button(this);
          	deleteButton.setText("Delete");
      	
            rl.addView(deleteButton);
    	  	LayoutParams params = (RelativeLayout.LayoutParams)deleteButton.getLayoutParams();
    	  	((android.widget.RelativeLayout.LayoutParams) params).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    		
   	  	 	deleteButton.setOnClickListener(new View.OnClickListener() {
   	  	 		public void onClick(View v) {
   	  	 			//DO SOMETHING
   	  	 			mainLayout.removeView(rl); 
   	  	 		}
   	  	 	});
    	  	
    		rl.addView(textview);
    		rl.setBackgroundResource(R.drawable.main_view_element);
    		mainLayout.addView(rl);
    	}
    }
    
    

	private void CreateAddConditionButton() {
    	Button b = (Button)findViewById(R.id.add_condition_filter_button);
    	 b.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
             	Intent myIntent = new Intent(view.getContext(), ConditionAdderActivity.class);
                startActivity(myIntent);     
             } 
         });
    	
    }
    
    
 	@Override
 	public void onWindowFocusChanged(boolean hasFocus){
 	    if(hasFocus) {
 	    	mainLayout.removeAllViews();
 	    	CreateAddConditionButton();
 	    	populateConditionRules();
 	    	displayConditionRules();
 		} else {
 			mainLayout.removeAllViews();
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
	        	 		if(current.getName().equals(FilterMenuActivity.filterName)){
	        	 			filterNameValid = false;
	        	 			//alert dialogue
	        	 		}
	        	 	}
	        	 	
	        	 	// filter name is unique at this point, but not necessarily valid
	        	 	// because it could still be the empty string
	        	 	if(FilterMenuActivity.filterName.trim().equals("")) {
	        	 		filterNameValid = false;
	        	 	}
	        	 	
	        	 	
	        	 	if(!filterNameValid){
	        	 	//	Intent myIntent = new Intent(v.getContext(), FilterOptionsActivity.class);
	                //    myIntent.putExtra("invalidName", true);
	        	 	//	startActivity(myIntent);
	        	 		FilterMenuActivity.tabHost.setCurrentTab(0);
	        	 		
	        	 	// filter name is valid, add filter and return to
	        	 	// FiltersActivity.java
	        	 	} else { 
	        	 		Filter filter = new Filter(FilterMenuActivity.filterName);
	        	 		HomeMenuActivity.testFilterList.add(filter);	
	        	 		finish();
	        	 	}
	            }
	        });
	}  
}


