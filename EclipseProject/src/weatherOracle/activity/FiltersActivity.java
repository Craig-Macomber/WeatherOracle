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
        
        populateFilterList();
        CreateAddFilterButton();
        DisplayElements();
        

        for (int i = 0;i<5;i++) {
         TextView textview =new TextView(getApplicationContext());
         final RelativeLayout rl = new RelativeLayout(this);


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
             String filterName = (String) deleteButton.getText();
             Pair<List<Filter>, Integer> filterListPair = FilterStore.getFilters();
             List<Filter> filterList = filterListPair.first;
             int filterListSize = filterList.size();
             for(int i = 0; i < filterListSize; i++){
            	 Filter currentFilter = filterList.get(i);
            	 if(currentFilter.getName() == filterName){
            		 filterList.remove(i);
            	 }
             }
             FilterStore.setFilters(filterList);
             
             }
         });
         
         
         
            textview.setText("Filter " + i);
            textview.setTextSize(2,30);
            rl.setBackgroundResource(R.drawable.main_view_element);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                 LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(8, 4, 8, 4);
            rl.addView(textview);
            final String filterNumber = "Filter " + i;
            rl.setOnClickListener(new View.OnClickListener()
            {
             public void onClick(View v)
                {
                 Intent myIntent = new Intent(v.getContext(), FilterMenuActivity.class);
                    myIntent.putExtra("filterName", filterNumber);
                 startActivity(myIntent);
                }
            });
            mainView.addView(rl, layoutParams);
        }
    }
 
 private void populateFilterList() {
  //filterList = FilterStore.getFilters();
  //REASSIGN the pieces of shit filterList to a fake one while waiting for model team to finish this shit
  // TODO
//  filterList.add(new Filter("I Will End"));
//  filterList.add(new Filter("Your life"));
//  filterList.add(new Filter("-long search for a soulmate <3"));
  //END OF FAKE CODE
 }

 private void CreateAddFilterButton() {
  Button filter = new Button(this);
  filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FilterMenuActivity.class);
                
                myIntent.putExtra("cereal", "cereal4");
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