package gadcprague.fruits.celidb;

import java.util.ArrayList;
import java.util.List;

import gadcprague.fruits.celidb.data.Category;
import gadcprague.fruits.celidb.data.Data;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryListActivity extends Activity {

	private ListView mCategoryList;
	private TextView mLabel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        mCategoryList = (ListView) findViewById(R.id.categoriesList);
        mLabel = (TextView) findViewById(R.id.label);

        Bundle extras = getIntent().getExtras();
        final int categoryId = extras != null ? (Integer) extras.get("categoryId") : 0;
        final Boolean inRootCategory = categoryId == 0;

        if (!inRootCategory)
        	mLabel.setText("Podkategorie " + categoryId);

        // Populate the contact list
        populateCategoriesList(categoryId);

        mCategoryList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		if (inRootCategory) {
	        		Intent myIntent = new Intent(view.getContext(), CategoryListActivity.class);
	        		myIntent.putExtra("categoryId", position);

	        		// Create the view using Group's LocalActivityManager
	        		View groupView = CategoriesActivityGroup.group.getLocalActivityManager()
	        				.startActivity("detail", myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
	        				.getDecorView();

	        		// Again, replace the view
	        		CategoriesActivityGroup.group.replaceView(groupView);
        		} else {
	        		Intent myIntent = new Intent(view.getContext(), ProductDetailActivity.class);
	        		myIntent.putExtra("productId", 999);

	        		// Create the view using Group's LocalActivityManager
	        		View groupView = CategoriesActivityGroup.group.getLocalActivityManager()
	        				.startActivity("detail", myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
	        				.getDecorView();

	        		// Again, replace the view
	        		CategoriesActivityGroup.group.replaceView(groupView);
        		}
        	}
        });
    }

    // Based on: http://www.vogella.de/articles/AndroidListView/article.html
    private void populateCategoriesList(int categoryId) {
    	Data data = new Data();

    	List<Category> categories = data.getCategoriesWithParent(categoryId);

//    	String[] values = new String[] { "Pečivo", "Mléčné výrobky" };
    	ArrayList<String> values = new ArrayList<String>();

    	for (Category c : categories) {
    		values.add(c.getName());
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