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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView mainView = new ScrollView(this);
        setContentView(mainView);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        mainView.addView(layout);
        

        for (int i = 0;i<20;i++) {
        	TextView textview =new TextView(getApplicationContext());
            textview.setText("Filter " + i);
            textview.setTextSize(2,30);
            layout.addView(textview);
            textview.setOnClickListener(new View.OnClickListener()
            {

            	public void onClick(View v)
                {
                	Intent myIntent = new Intent(v.getContext(), FilterMenuActivity.class);
                    startActivity(myIntent);
                }
            });

            
        }
        
		Button filter = new Button(this);
		filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FilterMenuActivity.class);
                startActivity(myIntent);
                
                
            }	
        });
		layout.addView(filter);
        filter.setText("Add filter");
    }
	
	
	
	


	
	
	
	
	
	public void goToFilterMenuActivity(View v) {
    	setContentView(R.layout.filter_menu_activity);
    }

}