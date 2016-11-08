/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.natephillips.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.natephillips.myfirstapp.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "MessageExtra";
    private int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(quantity,5);

        displayMessage(createOrderSummary("Nate Phillips", quantity, price));
    }

    public void incrementQuantity(View view){
        displayQuantity(++quantity);

    }

    public void decrementQuantity(View view){
        if(quantity != 0) displayQuantity(--quantity);

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private String createOrderSummary(String name, int quantity, int price){
       return "Name: " + name + "\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank you!";
    }

    private int calculatePrice(int quantity, int pricePerCup){
        return quantity * pricePerCup;
    }

}