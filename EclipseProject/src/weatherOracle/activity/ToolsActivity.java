package weatherOracle.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ToolsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    TextView textview = new TextView(this);
        textview.setText("Weather Oracle Version 0.70 :)");
        setContentView(textview);

	    // TODO Auto-generated method stub
	}

}
