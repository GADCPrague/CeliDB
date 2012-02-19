package gadcprague.fruits.celidb;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends Activity {

	private Button mScanButton;
	private Button mSearchButton;
	private EditText mEditText;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        mScanButton = (Button) findViewById(R.id.buttonScan);
        mSearchButton = (Button) findViewById(R.id.buttonSearch);
        mEditText = (EditText) findViewById(R.id.editText1);

        mScanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Main.mainActivity.startScanner(SearchActivity.this);
            }
        });
	}

	protected void onScanResult(String scanResult) {
		mEditText.setText(scanResult);
		mSearchButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
	}

}
