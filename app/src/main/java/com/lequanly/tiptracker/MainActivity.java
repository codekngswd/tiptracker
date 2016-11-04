package com.lequanly.tiptracker;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static double TAX = 1.13;

    EditText etBillAmnt;
    EditText etTipAmnt;
    EditText etNumPpl;
    EditText etNewRate;
    EditText etTipDefault;

    CheckBox cbAddHST;
    CheckBox cbChangeRate;

    CheckBox cbNoTip;
    CheckBox cbDollarValue;
    CheckBox cbSetDefault;

    RadioButton rbDollar;
    RadioButton rbPercent;

    CheckBox cbSplit;

    Button btnCalculate;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** The following EditTexts will gather the User's input **/

        etBillAmnt = (EditText) findViewById(R.id.etBillAmount);
        etTipAmnt = (EditText) findViewById(R.id.etTipAmount);
        etNumPpl = (EditText) findViewById(R.id.etNumPeople);
        etNewRate = (EditText) findViewById(R.id.etNewRate);
        etTipDefault = (EditText) findViewById(R.id.etTipDefault);

        /** The following CheckBoxes are Options for Bill, Tip, and Number of People **/

        // Bill Options
        cbAddHST = (CheckBox) findViewById(R.id.cbAddHst);
        cbChangeRate = (CheckBox) findViewById(R.id.cbChangeRate);

        // Tip Options
        cbNoTip = (CheckBox) findViewById(R.id.cbNoTip);
        cbDollarValue = (CheckBox) findViewById(R.id.cbDollarValue);
        cbSetDefault = (CheckBox) findViewById(R.id.cbTipDefault);

        rbDollar = (RadioButton) findViewById(R.id.rbDollar);
        rbPercent = (RadioButton) findViewById(R.id.rbPercent);

        // People Options
        cbSplit = (CheckBox) findViewById(R.id.cbSpilt);

        // Buttons that will Calculate/Clear the User's input
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnClear = (Button) findViewById(R.id.btnClear);

        // setOnClickListener for cbChangeRate
        cbChangeRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbChangeRate.isChecked()) {
                    etNewRate.setVisibility(View.VISIBLE);
                } else {
                    etNewRate.setVisibility(View.INVISIBLE);
                }
            }
        });

        // setOnClickListener for cbNoTip
        cbNoTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbNoTip.isChecked()) {
                    // Check if other Tip Options are checked
                    if (cbDollarValue.isChecked()) {
                        cbDollarValue.setChecked(false);
                    }

                    if (cbSetDefault.isChecked()) {

                        if (rbDollar.isChecked()) {
                            rbDollar.setChecked(false);
                            rbPercent.setEnabled(true);
                        } else if (rbPercent.isChecked()) {
                            rbPercent.setChecked(false);
                            rbDollar.setEnabled(true);
                        }

                        etTipDefault.setText("");

                        cbSetDefault.setChecked(false);

                        rbDollar.setVisibility(View.INVISIBLE);
                        rbPercent.setVisibility(View.INVISIBLE);

                        etTipDefault.setVisibility(View.INVISIBLE);
                    }

                    etTipAmnt.setText("");
                    etTipAmnt.setEnabled(false);

                    cbDollarValue.setEnabled(false);
                    cbSetDefault.setEnabled(false);
                } else {
                    etTipAmnt.setEnabled(true);

                    cbDollarValue.setEnabled(true);
                    cbSetDefault.setEnabled(true);
                }
            }
        });

        // setOnClickListener for cbDollarValue
        cbDollarValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDollarValue.isChecked()) {
                    etTipAmnt.setHint("$$$");
                } else {
                    etTipAmnt.setHint("%%%");
                }
            }
        });

        // setOnClickListener for cbSetDefault
        cbSetDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbSetDefault.isChecked()) {
                    rbDollar.setVisibility(View.VISIBLE);
                    rbPercent.setVisibility(View.VISIBLE);
                } else {
                    etTipDefault.setVisibility(View.INVISIBLE);
                    rbDollar.setVisibility(View.INVISIBLE);
                    rbPercent.setVisibility(View.INVISIBLE);

                    if (rbDollar.isChecked()) {
                        rbDollar.setChecked(false);
                        rbPercent.setEnabled(true);
                    } else if (rbPercent.isChecked()) {
                        rbPercent.setChecked(false);
                        rbDollar.setEnabled(true);
                    }

                    etTipDefault.setText("");
                }
            }
        });

        // setOnClickListener for rbDollar
        rbDollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbDollar.isChecked()) {
                    rbPercent.setEnabled(false);
                    etTipDefault.setVisibility(View.VISIBLE);
                }
            }
        });

        // setOnClickListener for rbPercent
        rbPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbPercent.isChecked()) {
                    rbDollar.setEnabled(false);
                    etTipDefault.setVisibility(View.VISIBLE);
                }
            }
        });

        // setOnClickListener for cbSplitBill
        cbSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSplit.isChecked()) {
                    etNumPpl.setEnabled(true);
                } else {
                    etNumPpl.setEnabled(false);
                    etNumPpl.setText("");
                }
            }
        });
    }

    /**
     * Declare and create a method that will create a new Bill object, display a Toast
     * indicating a successful new Bill creation.
     */

    public void calcReceipt(View view) {
        double billAmt = 0.00, subtotal = 0.00, taxAmt = 0.00, tipAmt = 0.00;
        int peopleAmt = 0;

        Bill newBill = new Bill();

        /** This section is responsible for gathering User input specific to the Bill Amount **/
        /** This section is responsible for gathering User input specific to the Tip Amount **/
        /** This section is responsible for gathering User input specific to the Number of People **/

        try {
            subtotal = Double.parseDouble(etBillAmnt.getText().toString());

            if (!cbAddHST.isChecked()) {
                TAX = 0.00;
            } else {
                TAX = 1.13;
            }

            taxAmt = subtotal * (TAX * 0.10);

            newBill.setSubtotal(subtotal);
            newBill.setTax(taxAmt);
        } catch (Exception ex) {

        }

        if (cbNoTip.isChecked()) {
            tipAmt = 0.00;
        } else {
            try {
                tipAmt = Double.parseDouble(etTipAmnt.getText().toString());

                if (cbDollarValue.isChecked()) {
                    newBill.setTip(tipAmt);

                } else {

                    tipAmt = subtotal * (tipAmt * 0.01);
                    newBill.setTip(tipAmt);
                }

            } catch (Exception ex) {

            }
        }

        if (cbSplit.isChecked()) {
            try {
                peopleAmt = Integer.parseInt(etNumPpl.getText().toString());

                billAmt = (subtotal + taxAmt + tipAmt) / peopleAmt;

                newBill.setPeople(peopleAmt);
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, "Split Amount", Toast.LENGTH_SHORT).show();
            }
        } else {

            newBill.setPeople(0);
            billAmt = (subtotal + taxAmt + tipAmt);
        }

        newBill.setTotal(billAmt);

        if (subtotal >= 0.01 && peopleAmt > 0) {
            // Second Activity
            Intent i = new Intent(MainActivity.this, SecondActivity.class);

            i.putExtra("bill", newBill);

            startActivity(i);
        }
    }

    public void resetUI(View view) {

        // Clear Bill Section
        etBillAmnt.setText("");

        etBillAmnt.requestFocus();

        if (cbAddHST.isChecked() || cbChangeRate.isChecked()) {
            cbAddHST.setChecked(false);
            cbChangeRate.setChecked(false);
        }

        // Clear Tip Section
        etTipAmnt.setText("");

        if (!cbNoTip.isChecked()) {
            etTipAmnt.setText("");
            etTipAmnt.setHint("%%%");
            cbDollarValue.setEnabled(true);
            cbSetDefault.setEnabled(true);
        } else {
            etTipAmnt.setText("");
            etTipAmnt.setHint("%%%");
            cbNoTip.setChecked(false);
            cbDollarValue.setEnabled(true);
            cbSetDefault.setEnabled(true);
        }

        if (cbDollarValue.isChecked()) {
            cbDollarValue.setChecked(false);
        }

        if (cbSetDefault.isChecked()) {
            cbSetDefault.setChecked(false);
        }

        if (rbDollar.isChecked()) {
            rbDollar.setChecked(false);
            rbPercent.setEnabled(true);
        } else if (rbPercent.isChecked()) {
            rbPercent.setChecked(false);
            rbDollar.setEnabled(true);
        }

        etTipDefault.setText("");

        etTipDefault.setVisibility(View.INVISIBLE);
        rbDollar.setVisibility(View.INVISIBLE);
        rbPercent.setVisibility(View.INVISIBLE);

        // Clear People Section
        if (cbSplit.isChecked()) {
            etNumPpl.setText("");
            cbSplit.setChecked(false);
        } else {
            etNumPpl.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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