//package weatherOracle.activity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.zip.Inflater;
//
//import weatherOracle.filter.ConditionRule;
//
//import android.app.Activity;
//import android.app.TabActivity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
//import android.widget.Spinner;
//import android.widget.TabHost;
//import android.widget.TextView;
//
//public class TestFilterActivity extends Activity {
//	
//	
//	
//	ScrollView wrapper;  
//	LinearLayout mainLayout;
//	
//	
//	String[] daysName = {"M","T","W","TH","F","S","Su"};
//	String[] conditionList = ConditionRule.conditions;
//	 
//	
//	
//	List<Boolean> days;
//	
//	
//	
//	
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        wrapper = new ScrollView(this);
//        mainLayout = new LinearLayout(this);
//        wrapper.addView(mainLayout);
//        mainLayout.setOrientation(LinearLayout.VERTICAL);
//        setContentView(wrapper);
//        
////        setNameLayout();
////        timeHeaderLayout();
////        timeContentLayout();
////        conditionHeaderLayout();
////        conditionContentLayout();
////        PopupWindow pw = new PopupWindow();
//        
//        
//        
//        
//     //   PopupWindow pw = new PopupWindow();
//       
//        
//        
//        LayoutInflater inflater = (LayoutInflater)
//        	       this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        	   
//        	    // The code below assumes that the root container has an id called 'main'
//        	    //pw.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);         
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//    }
//
//	public void setNameLayout() {		
//		LinearLayout l = new LinearLayout(this);
//		TextView filterName = new TextView(this);
//		filterName.setText("Filter Name: ");
//		filterName.setTextSize(1,15);
//		EditText et = new EditText(this);
//		et.setHint("Filter Name");
//		et.setEms(10);
//		l.addView(filterName,0);
//		l.addView(et,1);
//		filterName.setTextColor(Color.parseColor("#FFFFFF"));
//		mainLayout.addView(l);
//	}
//	
//	public void timeContentLayout() {
//		LinearLayout l = new LinearLayout(this);
//		for(int i = 0; i <daysName.length;i++) {
//			LinearLayout day = new LinearLayout(this);
//			day.setOrientation(LinearLayout.VERTICAL);
//			TextView dayName = new TextView(this);
//			dayName.setText("    " + daysName[i]);
//			CheckBox c = new CheckBox(this);
//			
//			day.addView(dayName);
//			day.addView(c);
//			l.addView(day);
//		}
//		mainLayout.addView(l);
//	}
//	
//	public void timeHeaderLayout() {		
//		LinearLayout l = new LinearLayout(this);
//		TextView header = new TextView(this);
//		header.setText("Time: ");
//		header.setTextSize(1,10);
//		l.addView(header,0);
//		header.setTextColor(Color.parseColor("#FFFFFF"));
//		l.setBackgroundColor(Color.parseColor("#CCCCCC"));
//		mainLayout.addView(l);
//	}
//	
//	public void conditionContentLayout() {
//		LinearLayout l = new LinearLayout(this);
//		Button b = new Button(this);
//		b.setText("Add Condition");
//		b.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//PopupWindow pw = new PopupWindow();
//	            //pw.showAtLocation(v, Gravity.LEFT,0,0);
//	            //pw.update(8,-70,150,270);
//			}
//		});
//		
//		
//		
//		
//		            
//
////            	public void onClick(View v)
////                {
////                	Intent myIntent = new Intent(v.getContext(), FilterMenuActivity.class);
////                    startActivity(myIntent);
////                }
////            });
//		
//		l.addView(b);
//		
//		
//		mainLayout.addView(l);
//	}
//	
//	public void conditionHeaderLayout() {
//		LinearLayout l = new LinearLayout(this);
//		TextView filterName = new TextView(this);
//		filterName.setText("Condition: ");
//		filterName.setTextSize(1,10);
//		filterName.setTextColor(Color.parseColor("#FFFFFF"));
//		l.setBackgroundColor(Color.parseColor("#CCCCCC"));
//		l.addView(filterName,0);
//		mainLayout.addView(l);
//	}
//	
//}