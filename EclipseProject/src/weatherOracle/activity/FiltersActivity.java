package weatherOracle.activity;


import java.util.LinkedList;
import java.util.List;

import weatherOracle.filter.Filter;
import weatherOracle.filter.FilterStore;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class FiltersActivity extends Activity {
 
 LinearLayout mainView;
 List<Filter> filterList;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        mainView = (LinearLayout)findViewById(R.id.notification_activity_linear_layout);
        int x = 9;
        CreateAddFilterButton();
        populateFilterList();
        
        DisplayElements();
    }
 
// 	public void onStart(Bundle savedInstanceState){
// 		super.onStart();
// 		if(true){
// 			throw new IllegalArgumentException();
// 		}
// 		setContentView(R.layout.notification_activity);
// 		populateFilterList();
//   }
 	
 	@Override
 	public void onWindowFocusChanged(boolean hasFocus){
 	    if(hasFocus) {
 	    	mainView.removeAllViews();
 	    	CreateAddFilterButton();
 	    	populateFilterList();
 		} else {
 			mainView.removeAllViews();
 		}
 	}

 
 private void populateFilterList() {
	 //mainView.removeAllViews();
	 int filterListSize = HomeMenuActivity.testFilterList.size();
     for (int i = 0; i < filterListSize; i++) {
    	 final TextView textview =new TextView(getApplicationContext());
      	 final RelativeLayout rl = new RelativeLayout(this);

      	textview.setText(HomeMenuActivity.testFilterList.get(i).getName());
        textview.setTextSize(2,30);
        rl.setBackgroundResource(R.drawable.main_view_element);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
             LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        
        layoutParams.setMargins(8, 4, 8, 4);
        rl.addView(textview);
    //    final String filterNumber = "Filter " + i;
        Filter filterNumber = HomeMenuActivity.testFilterList.get(i);
        final String filterName = filterNumber.getName();
        rl.setOnClickListener(new View.OnClickListener()
        {
         public void onClick(View v)
            {
             Intent myIntent = new Intent(v.getContext(), FilterMenuActivity.class);
                myIntent.putExtra("filterName", filterName);
             startActivity(myIntent);
            }
        });
      	 
      	 
      	 final Button deleteButton = new Button(this);
      	 deleteButton.setText("Delete");
  	
         rl.addView(deleteButton);
	  	 LayoutParams params = (RelativeLayout.LayoutParams)deleteButton.getLayoutParams();
	  	 params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	
	     deleteButton.setLayoutParams(params); //causes layout update
	  	
	  	 deleteButton.setOnClickListener(new View.OnClickListener()
         {
         public void onClick(View v)
           {
        	 	String filterName = (String) textview.getText();
   	   	  		int filterListSize = HomeMenuActivity.testFilterList.size();
	          for(int i = 0; i < HomeMenuActivity.testFilterList.size(); i++){
	        	 // if(i == 0){
	        		//  HomeMenuActivity.testFilterList.remove(i);
	        	  
	        	  
	        	   	  Filter current = HomeMenuActivity.testFilterList.get(i);
	        	   	  if(current.getName() == filterName){
	        	   		  HomeMenuActivity.testFilterList.remove(i);
       	 		//alert dialogue
	        	  }
       	 	
	        	  // Filter currentFilter = filterList.get(i);
	         	// if(currentFilter.getName() == filterName){
	         	//	 filterList.remove(i);
	         	 //}
	          }
	        	  // Filter currentFilter = filterList.get(i);
	         	// if(currentFilter.getName() == filterName){
	         	//	 filterList.remove(i);
	         	 //}
	          
    	   	  mainView.removeView(rl);
//	          String filterName = (String) deleteButton.getText();
//	          Pair<List<Filter>, Integer> filterListPair = FilterStore.getFilters();
//	          List<Filter> filterList = filterListPair.first;
//	          int filterListSize = filterList.size();
//	          for(int i = 0; i < filterListSize; i++){
//	         	 Filter currentFilter = filterList.get(i);
//	         	 if(currentFilter.getName() == filterName){
//	         		 filterList.remove(i);
//	         	 }
//	          }
//	          FilterStore.setFilters(filterList);
          
          }
      });
      
         mainView.addView(rl, layoutParams);
     }
 }

 private void CreateAddFilterButton() {
  Button filter = new Button(this);
  filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent myIntent = new Intent(view.getContext(), FilterMenuActivity.class);
                
                myIntent.putExtra("newFilter", true);
                startActivity(myIntent);     
            } 
        });
  
        mainView.addView(filter);
        filter.setText("Add Filter");
        filter.setWidth(40);
 }

 private void DisplayElements() {
  // TODO Auto-generated method stub
  
 }

 

}