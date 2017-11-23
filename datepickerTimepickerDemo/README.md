# Android DatePicker TimePicker Examples Codes
Selector Fecha Y Hora Android Studio (DatePicker TimePicker)
https://www.youtube.com/watch?v=y3exATaC0kA
Android Beginner Tutorial #25 - DatePicker Dialog [Choosing a Date from a Dialog Pop-Up]
https://www.youtube.com/watch?v=hwe1abDO2Ag


# datePickerDialog
```java
final Calendar c = Calendar.getInstance();
day = c.get(Calendar.DAY_OF_MONTH);
month = c.get(Calendar.MONTH);
year = c.get(Calendar.YEAR);

DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        edt_date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
    }
},year,month,day);
datePickerDialog.show();
```

# datePickerDialog2
```java
final  Calendar c = Calendar.getInstance();
day = c.get(Calendar.DAY_OF_MONTH);
month = c.get(Calendar.MONTH);
year = c.get(Calendar.YEAR);

DatePickerDialog dateDialog = new DatePickerDialog(
        MainActivity.this,
        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
        new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                edt_date.setText(date);
            }
        }, year, month, day
);
dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
dateDialog.show();
```

# timePickerDialog
```java
final  Calendar c = Calendar.getInstance();
hour = c.get(Calendar.HOUR_OF_DAY);
minutes = c.get(Calendar.MINUTE);

TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
        edt_time.setText(hourOfDay+":"+minutes);
    }
},hour,minutes,false);
timePickerDialog.show();
```
