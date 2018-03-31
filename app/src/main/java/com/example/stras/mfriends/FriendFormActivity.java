package com.example.stras.mfriends;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class FriendFormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_form);
    }

    /**
     * Validation of email address
     *
     * @param email
     * @return
     */
    private boolean isEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    /**
     * Displaying DatePicker when user clicks on icon of the calendar
     *
     * @param view
     */
    public void onClickDatePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    /**
     * Override for callback when user sets the date in DatePicker     *
     *
     * @param view
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    /**
     * Setting date to TextView
     *
     * @param calendar
     */
    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.tvBirthday)).setText(dateFormat.format(calendar.getTime()));
    }

    /**
     * Redirecting back to the main activity
     *
     * @param view
     */
    public void onClickBack(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onClickAddFriend(View view) {

    }
}


