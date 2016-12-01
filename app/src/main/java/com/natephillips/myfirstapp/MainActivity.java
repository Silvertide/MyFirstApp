/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.natephillips.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.natephillips.myfirstapp.R;

import java.text.NumberFormat;

import static android.R.attr.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "MessageExtra";
    private int quantity = 0;
    private double whippedCreamPrice = 1.00;
    private double chocolatePrice = 2.00;
    private double coffeePrice = 5.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked();

        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();

        double price = calculatePrice(quantity,coffeePrice,hasWhippedCream,hasChocolate);

        String nameField = ((EditText) findViewById(R.id.name_field)).getText().toString();

        String[] emailList = {"natephillips801@gmail.com"};

        composeEmail(emailList,"JustJava order for " + nameField,createOrderSummary(nameField, quantity, price,hasWhippedCream,hasChocolate));
    }

    public void incrementQuantity(View view){
        if(quantity >= 100){
            Toast.makeText(this, "You cannot have more than 100 coffees.", Toast.LENGTH_SHORT).show();
        }
        else{
            displayQuantity(++quantity);
        }


    }

    public void decrementQuantity(View view){
        if(quantity <= 0){
            Toast.makeText(this, "You cannot have a negative quantity.", Toast.LENGTH_SHORT).show();
        }
        else{
            displayQuantity(--quantity);
        }
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

    private String createOrderSummary(String name, int quantity, double price, boolean hasWhippedCream, boolean hasChocolate){
        String result = "Name: " + name + "\n";
        if(hasWhippedCream) result += "Whipped Cream: $" + whippedCreamPrice + "\n";
        if(hasChocolate) result += "Chocolate: $" + chocolatePrice + "\n";
        result += "Quantity: " + quantity + "\n";
        result += "Total: $" + price + "\nThank you!";
        return result;
    }

    private double calculatePrice(int quantity, double pricePerCup, boolean hasWhippedCream, boolean hasChocolate){
        double basePrice = pricePerCup;
        if(hasWhippedCream) basePrice += whippedCreamPrice;
        if(hasChocolate) basePrice += chocolatePrice;
        return quantity * basePrice;
    }

    public void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}