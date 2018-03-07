package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity <= 100) {
            quantity++;
            displayQuantity(quantity);
        }
        else {
            Context context = getApplicationContext();
            Toast quantityToast = Toast.makeText(context,"Too much coffees!", Toast.LENGTH_SHORT);
            quantityToast.show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
        }
        else {
            Context context = getApplicationContext();
            Toast quantityToast = Toast.makeText(context,"Needs at least 1 coffee!", Toast.LENGTH_SHORT);
            quantityToast.show();
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText nameEditText = (EditText)findViewById(R.id.name_edit_text);
        String customerName = nameEditText.getText().toString();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(customerName, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @param hasWhippedCream shows whether or not the user wants whipped cream topping
     * @param hasChocolate shows whether or not the user chocolate topping
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        // Price of 1 cup of coffee
        int basePrice = 5;

        // Adds $1 if the user wants whipped cream
        if (hasWhippedCream)
            basePrice += 1;

        // Adds $2 if the user wants chocolate
        if (hasChocolate)
            basePrice += 2;

        // Calculates the total price by multiplying the basePrice with the quantity
        return quantity * basePrice;
    }
   /**
    * Creates summary of the order
    *
    * @param price of the order
    * @return text summary
    */
    private String createOrderSummary(String customerName, int price, boolean hasWhippedCream, boolean hasChocolate){
        String message = "Name: " + customerName;
        message += "\nAdd whipped cream?" +hasWhippedCream;
        message += "\nAdd chocolate?" + hasChocolate;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price;
        message += "\n Thank you!";
        return message;
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
}