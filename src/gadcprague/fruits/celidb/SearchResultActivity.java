package gadcprague.fruits.celidb;

import gadcprague.fruits.celidb.data.Data;
import gadcprague.fruits.celidb.data.Product;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResultActivity extends Activity {

	protected ListView mCategoryList;
	protected TextView mLabel;

	protected Data data;

	protected List<Product> products;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        mCategoryList = (ListView) findViewById(R.id.categoriesList);
        mLabel = (TextView) findViewById(R.id.label);

        Bundle extras = getIntent().getExtras();
        final String queryString = extras != null ? (String) extras.get("queryString") : null;

        if (queryString != null) {
        	mLabel.setText("Hled‡n’ " + queryString);
        }

        // Populate the contact list
        populateCategoriesList(queryString);

        mCategoryList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    			// Clicked on a product
        		Intent myIntent = new Intent(view.getContext(), ProductDetailActivity.class);
        		myIntent.putExtra("productId", products.get(position).getId());

        		// Create the view using Group's LocalActivityManager
        		View groupView = SearchActivityGroup.group.getLocalActivityManager()
        				.startActivity("detail-from-search", myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        				.getDecorView();

        		// Again, replace the view
        		SearchActivityGroup.group.replaceView(groupView);
        	}
        });
    }

    // Based on: http://www.vogella.de/articles/AndroidListView/article.html
    private void populateCategoriesList(String queryString) {
    	if (data == null)
    		data = new Data();

    	Log.d("CeliDB", "Hu1");

    	products = data.searchProduct(queryString);

    	Log.d("CeliDB", "Hu2");


    	ArrayList<String> values = new ArrayList<String>();

    	for (Product p : products) {
    		values.add(p.getName());
    	}

    	// First parameter - Context
    	// Second parameter - Layout for the row
    	// Third parameter - ID of the View to which the data is written
    	// Forth - the Array of data
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    		android.R.layout.simple_list_item_1, android.R.id.text1, values);

    	// Assign adapter to ListView
    	mCategoryList.setAdapter(adapter);
    }
}