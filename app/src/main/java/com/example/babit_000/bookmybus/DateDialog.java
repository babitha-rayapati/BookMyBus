package com.example.babit_000.bookmybus;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by babit_000 on 12/26/2016.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText editText;
    public DateDialog(View view)
    {
        editText=(EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date=day+"-"+(month+1)+"-"+year;
        editText.setText(date);

    }
}
