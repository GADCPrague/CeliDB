package gadcprague.fruits.celidb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    }
    
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