package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity;
    String priceMessage;

    public void increment(View view) {
        displayQuantity(++quantity);
    }

    public void decrement(View view) {
        Context context = getApplicationContext();
        CharSequence text = "can not add less than 0 cups";
        int duration = Toast.LENGTH_SHORT;

        if (quantity < 1) {
            Toast.makeText(context, text, duration).show();
            return;
        }
        displayQuantity(--quantity);
    }

    public void submitOrder(View view) {
        int prices = quantity * 5;

        EditText name_view = (EditText) findViewById(R.id.name_view);
        String valuename = name_view.getText().toString();

        CheckBox Whippedcream = (CheckBox) findViewById(R.id.Whipped_cream);
        boolean hasWhippedcream = Whippedcream.isChecked();

        CheckBox Chocolate = (CheckBox) findViewById(R.id.Chocolate);
        boolean hasChocolate = Chocolate.isChecked();

        if (hasWhippedcream == true) {
            prices = quantity * (5 + 1);
        } else {
            prices = prices;
        }

        if (hasChocolate == true) {
            prices = quantity * (5 + 2);
        } else {
            prices = prices;
        }
        priceMessage = "name : " + valuename + "\nadd Whipped cream : " + hasWhippedcream +
                "\nadd Chocolate : " + hasChocolate + "\nQuantity " + quantity + "\nTotal: $" + prices + "\nThank You!";
        displayMessage(priceMessage);
        }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


    public void send_to_vendor(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "madel@isfpegypt.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}


