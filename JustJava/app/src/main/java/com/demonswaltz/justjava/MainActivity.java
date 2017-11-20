package com.demonswaltz.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int quantity = 0;
    private int pricePerCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the "-" button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            quantityDisplay(quantity);
        } else {
            showToast(getString(R.string.none_coffees));
        }
    }

    /**
     * This method is called when the "+" button is clicked
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity++;
            quantityDisplay(quantity);
        } else {
            showToast(getString(R.string.too_many));
        }
    }

    /**
     * This method is called when the ORDER button is clicked
     */
    public void onOrder(View view) {

        EditText editName = findViewById(R.id.name_edit_view);
        String userName= editName.getText().toString();
        boolean hasWhippedCream = checkWhippedCream();
        boolean hasChocolate = checkChocolate();
        int price = calculatePrice(quantity, hasWhippedCream, hasChocolate);
        String displayMessage = createOrderSummary(userName, hasWhippedCream, hasChocolate, price);
        composeEmail(displayMessage, userName);

    }

    /**
     * This method creates the order summary text
     */
    private String createOrderSummary(String name, boolean whippedCream, boolean chocolate, int price){
        return getString(R.string.name) + ": " + name +
                "\n" + getString(R.string.add_whipped_cream) + " " + whippedCream +
                "\n" + getString(R.string.add_chocolate) + " " + chocolate +
                "\n" + getString(R.string.total) + NumberFormat.getCurrencyInstance().format(price) +
                "\n" + getString(R.string.thanks);
    }
    /**
     * This method calculates the price
     */
    private int calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChocolate) {
        int singlePrice = pricePerCup;
        if (hasWhippedCream == true) {
            singlePrice += 1;
        }
        if (hasChocolate == true) {
            singlePrice += 2;
        }
        return (quantity * singlePrice);
    }
    /**
     * This method is for test purposes displays order summary
     */
    //private void orderSummaryDisplay(String displayMessage) {
      //  TextView quantityTextView = findViewById(R.id.order_summary_text_view);
      //  quantityTextView.setText(displayMessage);
    //}

    /**
     * This method displays the given quantity value on the screen.
     */
    private void quantityDisplay(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is used to show toasts
     */
    private void showToast(String toastMessage) {
        Context context = getApplicationContext();
        CharSequence text = toastMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    /**
     * This method checks if whipped cream was checked
     */
    private boolean checkWhippedCream() {
        CheckBox hasWhippedCream = findViewById(R.id.whipped_cream_checkbox);
        return (hasWhippedCream.isChecked());
    }
    /**
     * This method checks if chocolate was checked
     */
    private boolean checkChocolate() {
        CheckBox haschocolate = findViewById(R.id.chocolate_checkbox);
        return (haschocolate.isChecked());
    }

    /**
     * This method creates an email message for ordering
     */
    public void composeEmail(String orderSummary, String username) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + " " + username);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}