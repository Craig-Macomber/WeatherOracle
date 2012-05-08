package weatherOracle.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class FiltersActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView mainView = new ScrollView(this);
        setContentView(mainView);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        mainView.addView(layout);
        
        // here there needs to be a call to FilterStore.getFilters()
        // 
        // iterate over every filter in the list/array
        // retrieve name and create view element with that name on every iteration
        
        for (int i = 0;i<20;i++) {
        	TextView textview =new TextView(getApplicationContext());
            textview.setText("Filter " + i);
            textview.setTextSize(2,30);
            layout.addView(textview);
            textview.setOnClickListener(new View.OnClickListener()
            {
            // attach an onclick listener to each textview that will cause FilterMenuActivity
            // to be launched when any filter is clicked ... the specific filter that was clicked
            // will be passed in the intent so that FilterMenuActivity will display the appropriate
            // data
            	public void onClick(View v)
                {
                	Intent myIntent = new Intent(v.getContext(), FilterMenuActivity.class);
                    startActivity(myIntent);
                }
            });

            
        }
        
        
    }
	
	
	
	
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	    TextView textview = new TextView(this);
//        textview.setText("Whole shit load of filters!!");
//        setContentView(R.layout.filters_activity);
//        createAddFilterButton();
//	    // TODO Auto-generated method stub
//	}
//	
//	
//	private void createAddFilterButton() {
//		Button filter = (Button) findViewById(R.id.ok);
//		filter.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), FilterMenuActivity.class);
//                startActivity(myIntent);
//                
//                
//            }	
//        });
//		
//    }
	
	
	
	
	
	public void goToFilterMenuActivity(View v) {
    	setContentView(R.layout.filter_menu_activity);
    }

}