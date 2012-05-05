package weatherOracle.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FiltersActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    TextView textview = new TextView(this);
        textview.setText("Whole shit load of filters!!");
        setContentView(R.layout.filters_activity);

	    // TODO Auto-generated method stub
	}
	
	public void goToFilterMenuActivity(View v) {
    	setContentView(R.layout.filter_menu_activity);
    }

}