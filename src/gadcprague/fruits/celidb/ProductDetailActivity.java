package gadcprague.fruits.celidb;

import gadcprague.fruits.celidb.data.Data;
import gadcprague.fruits.celidb.data.Product;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetailActivity extends Activity {

	protected int productId;

	protected Data data = Data.getInstance();

	protected TextView mNameLabel;
	protected TextView mBarcodeLabel;
	protected TextView mDescription;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        mNameLabel = (TextView) findViewById(R.id.textView1);
        mBarcodeLabel = (TextView) findViewById(R.id.textView4);
        mDescription = (TextView) findViewById(R.id.textView6);

        Bundle extras = getIntent().getExtras();
        productId = extras != null ? (Integer) extras.get("productId") : 0;

    	Product product = data.getProduct(productId);
    	if (product != null) {
    		mNameLabel.setText(product.getName());
    		mBarcodeLabel.setText(product.getBarCode());
    		mDescription.setText(product.getDescription());
    	}
    }
}
