// tim is the best

package weatherOracle.activity;

import java.util.List;

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
import weatherOracle.filter.*;

public class ConditionRuleViewerActivity extends Activity {

	PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click = true;

    List<ConditionRule> conditions;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.condition_rule_viewer_activity);
     
     
     populateConditionRules();
     

    }
    
    private void populateConditionRules(){
 
    }
    
    
    
    
   }
