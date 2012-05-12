// tim is the best

package weatherOracle.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ConditionRuleViewerActivity extends Activity {

	PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.condition_rule_viewer_activity);
     
     
//     popUp = new PopupWindow(this);
//     layout = new LinearLayout(this);
//     mainLayout = new LinearLayout(this);
//     tv = new TextView(this);
//     but = new Button(this);
//     but.setText("Click Me");
//     but.setOnClickListener(new View.OnClickListener() {
//
//      public void onClick(View v) {
//    	  if (click) {
//    		  popUp.showAtLocation(layout, Gravity.TOP, 10, 10);
//    		  popUp.update(50, 50, 400, 500);
//    		  click = false;
//    	  } else {
//    		  popUp.dismiss();
//    		  click = true;
//    	  }
//      	}
//     });
//     params = new LayoutParams(LayoutParams.WRAP_CONTENT,
//       LayoutParams.WRAP_CONTENT);
//     layout.setOrientation(LinearLayout.VERTICAL);
//     tv.setText("I am such a fucking beast!!");
//     Button but2 = new Button(this);
//     but2.setText("CLICK NOW!");
//     layout.addView(tv, params);
//     layout.addView(but2);
//     popUp.setContentView(layout);
//     // popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
//     mainLayout.addView(but, params);
//     setContentView(mainLayout);
    }
    
    private void populateConditionRules(){
    	
    	//FilterMenuActivity.filter
    }
    
    
    
    
   }
