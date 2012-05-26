package weatherOracle.activity;


import java.util.LinkedList;
import java.util.List;

import weatherOracle.filter.Filter;
import weatherOracle.filter.FilterStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class FiltersActivity extends Activity {
 boolean confirmationDialogueUp = false;
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
 		if(!confirmationDialogueUp){
	 		if(hasFocus) {
	 	    	mainView.removeAllViews();
	 	    	initializeAddFilterButton();
	 	    	populateFilterList();
	 		} else {
	 			mainView.removeAllViews();
	 		}
 		} else {
 			if(!hasFocus){
 				confirmationDialogueUp = false;
 			}
 		}
 	}

 
 	private void populateFilterList() {
 		FilterStore.setFilters(HomeMenuActivity.filterList);
 		int filterListSize = HomeMenuActivity.filterList.size();
 		for (int i = 0; i < filterListSize; i++) {
 			final TextView textview = new TextView(getApplicationContext());
 			textview.setTextColor(R.color.black);
 			final LinearLayout ll = new LinearLayout(this);
 			ll.setOrientation(LinearLayout.VERTICAL);
 			final RelativeLayout rl = new RelativeLayout(this);
 			
 			
 			final View divider = new View(this);
 			divider.setBackgroundColor(R.color.grey);
 			LayoutParams dividerParams = new LayoutParams(LayoutParams.FILL_PARENT, 2);
 			//dividerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
 			ll.addView(divider, dividerParams);
 			
 			ll.addView(rl);
 			
 			final View divider2 = new View(this);
 			divider2.setBackgroundColor(R.color.grey);
 			LayoutParams dividerParams2 = new LayoutParams(LayoutParams.FILL_PARENT, 2);
 			//dividerParams2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
 			ll.addView(divider2, dividerParams2);
 			
 			textview.setText(HomeMenuActivity.filterList.get(i).getName());
 			textview.setTextSize(2,30);
 			textview.setGravity(Gravity.CENTER_VERTICAL);
 			
 			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
 					LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
 			layoutParams.setMargins(8, 4, 8, 4); // top and bottom margins are 4 so that if two elements
 												 // appear in succession the total separation will be 8
 			
 		//	rl.setBackgroundResource(R.drawable.main_view_element);
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
 			
 			
 			deleteButton.setGravity(Gravity.CENTER_VERTICAL);
 			rl.addView(deleteButton);
 			LayoutParams params = (RelativeLayout.LayoutParams)deleteButton.getLayoutParams();
 			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
 			deleteButton.setLayoutParams(params); //causes layout update
	  	
 			// attach listener to the delete button
 			// when it gets clicked, delete the given relative layout from the view
 			// and remove the filter associated with it from the list of filters
 			deleteButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					confirmationDialogueUp = true;
					AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());					
					builder.setMessage("Are you sure you want to delete this filter?")
						.setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								String filterName = (String) textview.getText();
			   	   	  			for(int i = 0; i < HomeMenuActivity.filterList.size(); i++){  
		   	   	  				Filter current = HomeMenuActivity.filterList.get(i);
		   	   	  				if(current.getName().equals(filterName)){
		   	   	  					HomeMenuActivity.filterList.remove(i);
		   	   	  					FilterStore.setFilters(HomeMenuActivity.filterList);
		   	   	  					i--;
		   	   	  				}
			   	   	  		}
			   	   	  	
			   	   	  			mainView.removeView(rl);
								
								
							}
						})
						.setNegativeButton("No", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// Do Nothing!
								
							}
						});
					
					AlertDialog alert = builder.create();
					alert.show();
				}
				
			});
 		//	mainView.addView(divider);
 			mainView.addView(ll, params);
 		//	mainView.addView(divider);
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