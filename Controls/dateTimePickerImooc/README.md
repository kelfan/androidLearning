# android studio datepicker and timepicker dialog example codes 

# acquire elements 
```java 
//获取日历的一个对象
calendar = Calendar.getInstance();
// 获取年月日时分秒的信息
year = calendar.get(Calendar.YEAR);
month = calendar.get(Calendar.MONTH) + 1;
day = calendar.get(Calendar.DAY_OF_MONTH);
hour = calendar.get(Calendar.HOUR_OF_DAY);
minute = calendar.get(Calendar.MINUTE);
setTitle(year+"-"+month+"-"+day+"-"+hour+"-"+minute);

datePicker = findViewById(R.id.datePicker);
timePicker = findViewById(R.id.timePicker);
```

# datepicker 
```java 
//datepicker初始化
datePicker.init(year, calendar.get(Calendar.MINUTE), day, new DatePicker.OnDateChangedListener() {
    @Override
    public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
    }
});
```

# timepicker
```java 
//Timepicker初始化
timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
    @Override
    public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
        setTitle(hourOfDay+":"+minute);
    }
});
```

# DatePickerDialog
```java 
new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
    }
},year,calendar.get(Calendar.MONTH),day).show();
```

# TimePickerDialog
```java 
new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        setTitle(hourOfDay + ":" + minute);
    }
}, hour, minute, true).show(); //last parameter = is24hourView
```