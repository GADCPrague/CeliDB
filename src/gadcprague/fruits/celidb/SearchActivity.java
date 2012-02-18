package gadcprague.fruits.celidb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends Activity {

	private Button mScanButton;
	private EditText mEditText;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        mScanButton = (Button) findViewById(R.id.buttonScan);
        mEditText = (EditText) findViewById(R.id.editText1);

        mEditText.setText("intro text");

        mScanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//            	Intent intent = new Intent("com.google.zxing.client.android.SCAN");
////            	intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            	Log.d("TEST", "starting scanner");
//            	startActivityForResult(intent, 0);

            	Intent intent1 = new Intent("com.google.zxing.client.android.SCAN");
//                intent1.putExtra("SCAN_MODE", "ONE_D_MODE");
                startActivityForResult(intent1, 0);
            }
        });
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);

		Log.d("TEST", "testststs");
		mEditText.setText("resultCode: " + requestCode);
	}

}
