package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import weatherOracle.filter.ConditionRule;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

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
		min = Integer.MIN_VALUE;
		max = Integer.MAX_VALUE;
		setUpAddButton();
		setUpConditionSpinner();
		setUpMinEditText();
		setUpMaxEditText();
    }


	private void setUpMinEditText() {
		final EditText et = (EditText) findViewById(R.id.min_condition_edittext);
		et.setHint("No minimum bound if not set");
		et.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
				String minNumber = et.getText().toString();
				if (minNumber.equals("")) {
					min = Integer.MIN_VALUE;
				} else if ((minNumber.startsWith("-") && minNumber.length() > 1) || (!minNumber.startsWith("-")) && minNumber.length() > 0) {
					try {
						min = Integer.parseInt(minNumber);
					} catch (Exception e) { //number too small to parse into int ob
						min = Integer.MIN_VALUE;
					}
				}
				
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});
		
	}


	private void setUpMaxEditText() {
		final EditText et = (EditText) findViewById(R.id.max_condition_edittext);
		et.setHint("No maximum bound if not set");
		et.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
				String minNumber = et.getText().toString();
				if (minNumber.equals("")) {
					max = Integer.MAX_VALUE;
				} else if ((minNumber.startsWith("-") && minNumber.length() > 1) || (!minNumber.startsWith("-")) && minNumber.length() > 0) {
					try {
						max = Integer.parseInt(minNumber);	
					} catch (Exception e) {
						max = Integer.MAX_VALUE;
					}
					
					
				}
				
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});
		
	}


	private void setUpAddButton() {
		Button b = (Button) findViewById(R.id.add_condition_button);
		b.setText("Add weather condition");
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if (min <= max) {
	            	FilterMenuActivity.conditions.add(new ConditionRule(condition,min,max));
	                Intent intent = new Intent();
	                setResult(RESULT_OK, intent);
	                finish();
            	} else {
            		 AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                     builder.setMessage("Please give a minimum number(From) that is lower than the maximum(To).")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                             dialog.dismiss();
                            }
                        });
                     AlertDialog alert = builder.create();
                     alert.show();
            	}
            }
        });
	}

	private void setUpConditionSpinner() {
		Spinner s = (Spinner) findViewById(R.id.condition_spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, possibleCondition);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(new ConditionOnItemSelectedListener());
	}

	private class ConditionOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			condition = (String)parent.getItemAtPosition(pos);
			
			TextView tv = (TextView) findViewById(R.id.to_textview);
			tv.setText("To (" + ConditionRule.getUnits(condition) + "):");
			
			tv = (TextView) findViewById(R.id.from_textview);
			tv.setText("From (" + ConditionRule.getUnits(condition) + "):");
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}

