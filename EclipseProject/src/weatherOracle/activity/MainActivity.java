package weatherOracle.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	int tabsiwtchindex = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.filter_menu_activity);
	    startup();
	    
	}
	
	private void startup() {
		Intent myIntent = new Intent(this, HomeMenuActivity.class);
        startActivity(myIntent);
	}
	
}