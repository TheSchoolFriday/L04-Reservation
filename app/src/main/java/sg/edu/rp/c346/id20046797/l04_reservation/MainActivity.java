package sg.edu.rp.c346.id20046797.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etMobileNumber, etPax;
    Button btnReservation, btnClear;
    DatePicker dp;
    TimePicker tp;
    CheckBox cbSmoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etMobileNumber = findViewById(R.id.editTextMobile);
        etPax = findViewById(R.id.editTextPax);
        btnReservation = findViewById(R.id.buttonReservation);
        btnClear = findViewById(R.id.buttonClear);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        cbSmoke = findViewById(R.id.cbSmoking);

        dp.updateDate(2020, 5, 1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay < 8) {
                    tp.setCurrentHour(hourOfDay+1);
                }

                if (hourOfDay > 20) {
                    tp.setCurrentHour(hourOfDay-1);
                }
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalToast = "Required Fields are Empty.";
                if (!(etName.getText().toString().isEmpty() || etPax.getText().toString().isEmpty() || etMobileNumber.getText().toString().isEmpty())) {
                    String nameOfCustomer = etName.getText().toString();
                    String paxOfCustomer = etPax.getText().toString();
                    String mobileNumber = etMobileNumber.getText().toString();

                    String dateOfReserve = String.valueOf(dp.getDayOfMonth() + "/" +dp.getMonth() + "/" + dp.getYear());
                    String timeOfReserve24 = String.format("%.2s%.2s", tp.getCurrentHour().toString(), tp.getCurrentMinute().toString());

                    // Something I just want to test. (not part of requirements)
                    String timeOfReserve12 = "";
                    if(tp.getCurrentHour() > 12) {
                        timeOfReserve12 = String.valueOf((tp.getCurrentHour() - 12) + ":" + tp.getCurrentMinute() + "PM");
                    } else {
                        timeOfReserve12 = String.valueOf(tp.getCurrentHour() + ":" +  tp.getCurrentMinute() + "AM");
                    }

                    finalToast = String.format("Customer: %s\nMobile Number: %s\nPax: %s\nDate: %s\nTime: %s | %s", nameOfCustomer,  mobileNumber, paxOfCustomer, dateOfReserve, timeOfReserve24, timeOfReserve12);

                    if (cbSmoke.isChecked()) {
                        finalToast += String.format("\n%s", "Smoke Area");
                    } else {
                        finalToast += String.format("\n%s", "No Smoke Area");
                    }
                }
                Toast.makeText(MainActivity.this, finalToast, Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.updateDate(2020, 5, 1);
                tp.setCurrentHour(7);
                tp.setCurrentMinute(30);

                etName.setText("");
                etPax.setText("");
                etMobileNumber.setText("");
            }
        });
    }
}