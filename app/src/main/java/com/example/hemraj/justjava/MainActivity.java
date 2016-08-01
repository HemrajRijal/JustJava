package com.example.hemraj.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //Find the users Name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        //Figure out if the user wants WhippedCream or not
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //Figure out if the user wants Choclate or not
        CheckBox choclateCheckBox = (CheckBox) findViewById(R.id.choclate_checkbox);
        boolean hasChoclate = choclateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChoclate);
        String PriceMessage = orderSummary(name, price, hasWhippedCream, hasChoclate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
       // intent.putExtra(Intent.EXTRA_EMAIL, "hemraj.rijal2.c6@gmal.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, PriceMessage);

        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }

//        displayMessage(PriceMessage);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100){
            //Show Toastmessage as an error
            Toast.makeText(MainActivity.this, "You can not have more than 100 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1 ;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1){
            //Show Toastmessage as an error
            Toast.makeText(MainActivity.this, "You can not have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int noOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + noOfCoffees);

    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView order_summary_text_view = (TextView) findViewById(R.id.order_summary_text_view);
//        order_summary_text_view.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     *This method display the given text on screen
     */
//    private void displayMessage(String message) {
//        TextView orderSummary = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummary.setText(message);
//    }

    /**
     * Calculate the price of the order
     *
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChoclate){
        //Price of 1 cup of coffee
        int basePrice=5;
        //if whippedcream is checked
        if (hasWhippedCream){
            basePrice = basePrice +1;
        }

        //if Choclate was checked
        if (hasChoclate){
            basePrice = basePrice +2;
        }

        //Calculate total Price
        return (basePrice * quantity);
    }

    private String orderSummary(String name, int price, boolean addWhippedCream, boolean addChoclate){
        String priceMessage = "Name:" + name +
                "\nAdd WhippedCream?" + addWhippedCream +
                "\nAdd WhippedCream?" + addChoclate +
                "\nQuantity: " + quantity +
                "\nTotal Price is: $ " +  price +
                "\n Thank You for Your Service !";
        //displayMessage(priceMessage);
        return (priceMessage);
    }

}