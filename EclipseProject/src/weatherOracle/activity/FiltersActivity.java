package weatherOracle.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class FiltersActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        final LinearLayout mainView = (LinearLayout)findViewById(R.id.notification_activity_linear_layout);
        
// 		will be implementing the following:
//
//         		List<Filter> filterList = FilterStore.getFilters()
//              int numberOfFilters = filterList.size();
//              for (int i = 0; i < numberOfFilters; i++){
//              	// copy much of the code below
        // /      }
        
        Button filter = new Button(this);
		filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FilterMenuActivity.class);
                startActivity(myIntent);
                
                
            }	
        });
        mainView.addView(filter);
        filter.setText("Add Filter");
        filter.setWidth(40);
        

        for (int i = 0;i<20;i++) {
        	TextView textview =new TextView(getApplicationContext());
        	final RelativeLayout rl = new RelativeLayout(this); 
            textview.setText("Filter " + i);
            textview.setTextSize(2,30);
            rl.setBackgroundResource(R.drawable.main_view_element);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
           	     LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

           	layoutParams.setMargins(8, 4, 8, 4);
           	rl.addView(textview);
            rl.setOnClickListener(new View.OnClickListener()
            
            {
            	public void onClick(View v)
                {
                	Intent myIntent = new Intent(v.getContext(), FilterMenuActivity.class);
                    startActivity(myIntent);
                }
            });

           
    		
            mainView.addView(rl, layoutParams);
        }
    }
	
	
	
	


	
	
	
	
	
	public void goToFilterMenuActivity(View v) {
    	setContentView(R.layout.filter_menu_activity);
    }

}