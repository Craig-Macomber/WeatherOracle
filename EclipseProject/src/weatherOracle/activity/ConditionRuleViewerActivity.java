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
		CreateAddConditionButton();
		populateConditionRules();
		displayConditionRules();

    }
    
    private void populateConditionRules(){

    	//conditions = FilterMenuActivity.filter.getConditionRules();
    	conditions = new TreeSet<ConditionRule>();
    	conditions.add(new ConditionRule("a", 0, 10));
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
	  
}


