package weatherOracle.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FiltersActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    TextView textview = new TextView(this);
        textview.setText("Whole shit load of filters!!");
        setContentView(R.layout.filters_activity);
        createAddFilterButton();
	    // TODO Auto-generated method stub
	}
	
	
	private void createAddFilterButton() {
		Button filter = (Button) findViewById(R.id.ok);
		filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FilterMenuActivity.class);
                startActivity(myIntent);
                
                
            }	
        });
		
    }
	
	
	
	
	
	public void goToFilterMenuActivity(View v) {
    	setContentView(R.layout.filter_menu_activity);
    }

}