package gadcprague.fruits.celidb;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class Main extends TabActivity {

	public static Main mainActivity;
	private SearchActivity searchActivity;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    mainActivity = this;

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    // and Initialize a TabSpec for each tab and add it to the TabHost
	    intent = new Intent().setClass(this, SearchActivityGroup.class);
	    spec = tabHost.newTabSpec("artists").setIndicator("Vyhledat",
	                      res.getDrawable(R.drawable.ic_action_search))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, CategoriesActivityGroup.class);
	    spec = tabHost.newTabSpec("categories").setIndicator("Kategorie",
	                      res.getDrawable(R.drawable.ic_action_category))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, UploadActivityGroup.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("Ukl‡dat",
	                      res.getDrawable(R.drawable.ic_action_edit))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}

	public void startScanner(SearchActivity searchActivity) {
		Log.d("CeliDB", "Starting barcode scanner");

		this.searchActivity = searchActivity;

		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
		startActivityForResult(intent, 0);
	}

	/* Return from scanning barcode */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("CeliDB", "Return from barcode scanner");

		if (resultCode == RESULT_OK) {
			String scanResult = intent.getStringExtra("SCAN_RESULT");

			Log.d("CeliDB", "Barcode scanner returned " + scanResult);

			if (searchActivity != null) {
				searchActivity.onScanResult(scanResult);
			} else {
				Log.d("CeliDB", "Missing search activity ???!?!?!?");
			}

		} else if (resultCode == RESULT_CANCELED) {
			Log.d("CeliDB", "result CANCELED");
		}
   }

}
