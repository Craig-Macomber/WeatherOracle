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
        
        initializeAddFilterButton();
        populateFilterList();
    }
 	
 	// updates the view with the appropriate filter info every time it gets navigated to
 	public void onWindowFocusChanged(boolean hasFocus){
 		super.onWindowFocusChanged(hasFocus);
 		if(hasFocus) {
 	    	mainView.removeAllViews();
 	    	initializeAddFilterButton();
 	    	populateFilterList();
 		} else {
 			mainView.removeAllViews();
 		}
 	}

 
 	private void populateFilterList() {
 		FilterStore.setFilters(HomeMenuActivity.filterList);
 		int filterListSize = HomeMenuActivity.filterList.size();
 		for (int i = 0; i < filterListSize; i++) {
 			final TextView textview = new TextView(getApplicationContext());
 			final RelativeLayout rl = new RelativeLayout(this);

 			textview.setText(HomeMenuActivity.filterList.get(i).getName());
 			textview.setTextSize(2,30);
 			
 			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
 					LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
 			layoutParams.setMargins(8, 4, 8, 4); // top and bottom margins are 4 so that if two elements
 												 // appear in succession the total separation will be 8
 			
 			rl.setBackgroundResource(R.drawable.main_view_element);
 			rl.addView(textview);
    
 			final Filter currentFilter = HomeMenuActivity.filterList.get(i);
 			// when a given view element gets clicked on, launch FilterMenuActivity and pass it
 			// the given filter so that it can display it's information
 			rl.setOnClickListener(new View.OnClickListener(){
 				public void onClick(View v)
 				{
 					Intent myIntent = new Intent(v.getContext(), FilterMenuActivity.class);
 					myIntent.putExtra("filter", currentFilter);
 					startActivity(myIntent);
 				}
 			});
      	 
      	 
 			
 			
 			
 			
 			
 			final Button deleteButton = new Button(this);
 			deleteButton.setText("Delete");
  	
 			rl.addView(deleteButton);
 			LayoutParams params = (RelativeLayout.LayoutParams)deleteButton.getLayoutParams();
 			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
 			deleteButton.setLayoutParams(params); //causes layout update
	  	
 			// attach listener to the delete button
 			// when it gets clicked, delete the given relative layout from the view
 			// and remove the filter associated with it from the list of filters
 			deleteButton.setOnClickListener(new View.OnClickListener()
 			{
 				public void onClick(View v)
 				{
 					String filterName = (String) textview.getText();
   	   	  			for(int i = 0; i < HomeMenuActivity.filterList.size(); i++){  
   	   	  				Filter current = HomeMenuActivity.filterList.get(i);
   	   	  				if(current.getName().equals(filterName)){
   	   	  					HomeMenuActivity.filterList.remove(i);
   	   	  					i--;
   	   	  				}
   	   	  			}
	          
   	   	  			mainView.removeView(rl);
 				}
 			});
      
 			mainView.addView(rl, layoutParams);
 		}
 	}

 
 	
 	private void initializeAddFilterButton() {
 		Button filter = new Button(this);
 		filter.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) 
           {
        	    // create empty filter with empty string as name to pass into FilterMenuActivity
        	   	Filter filter = new Filter("");
            	Intent myIntent = new Intent(view.getContext(), FilterMenuActivity.class);
                myIntent.putExtra("filter", filter);
                startActivity(myIntent);     
           } 
 		});
  
 		mainView.addView(filter);
 		filter.setText("Add Filter");
 		filter.setWidth(40);
 	 }
}