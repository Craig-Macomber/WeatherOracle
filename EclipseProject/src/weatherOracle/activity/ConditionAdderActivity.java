package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import weatherOracle.filter.ConditionRule;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ConditionAdderActivity extends Activity {

    LinearLayout mainLayout;
    String condition;
    int min;
    int max;
    String[] possibleCondition;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.condition_adder_activity);
		mainLayout = (LinearLayout)findViewById(R.id.condition_adder_activity_linear_layout);
		possibleCondition = ConditionRule.conditions.clone();
		
		createAddButton();
		createConditionSpinner();
		
    }


	private void createAddButton() {
		Button b = (Button) findViewById(R.id.add_condition_button);
		b.setText("Add weather condition");
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	ConditionRuleViewerActivity.conditions.add(new ConditionRule(condition,min,max));
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
	}


	private void createConditionSpinner() {
//		Spinner s = new Spinner(this);
//		ArrayAdapter adapter = new ArrayAdapter(this,
//				android.R.layout.simple_spinner_item, array_spinner);
//		
	}

	    
 	
	  
}


