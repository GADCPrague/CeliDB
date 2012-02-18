package gadcprague.fruits.celidb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CeliDBActivity extends Activity {
	
	private ListView mCategoriesList;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mCategoriesList = (ListView) findViewById(R.id.categoriesList);
        
        // Populate the contact list
        populateCategoriesList();
        
        mCategoriesList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Intent myIntent = new Intent(view.getContext(), ProductDetailActivity.class);
        		startActivityForResult(myIntent, 0);
        	}
        });
    }

    // Based on: http://www.vogella.de/articles/AndroidListView/article.html
    private void populateCategoriesList() {
    	String[] values = new String[] { "Pečivo", "Mléčné výrobky" };

    	// First paramenter - Context
    	// Second parameter - Layout for the row
    	// Third parameter - ID of the View to which the data is written
    	// Forth - the Array of data
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    		android.R.layout.simple_list_item_1, android.R.id.text1, values);

    	// Assign adapter to ListView
    	mCategoriesList.setAdapter(adapter);
    }
}