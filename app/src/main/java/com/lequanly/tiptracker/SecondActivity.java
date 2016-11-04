package com.lequanly.tiptracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SecondActivity extends AppCompatActivity {

    private TextView tvSubtotal;
    private TextView tvTipResult;
    private TextView tvHSTResult;
    private TextView tvTotalResult;

    private Bill billObject;

    // Declare and initialize df (DecimalFormat) in order to format all displayed numbers to two decimal places.
    final DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvSubtotal = (TextView) findViewById(R.id.tvSubtotal);
        tvTipResult = (TextView) findViewById(R.id.tvTipResult);
        tvHSTResult = (TextView) findViewById(R.id.tvHSTResult);
        tvTotalResult = (TextView) findViewById(R.id.tvTotalResult);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        billObject = (Bill) getIntent().getSerializableExtra("bill");

        tvSubtotal.setText(df.format(billObject.getSubtotal()));
        tvTipResult.setText(df.format(billObject.getTip()));
        tvHSTResult.setText(df.format(billObject.getTax()));
        tvTotalResult.setText(df.format(billObject.getTotal()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}