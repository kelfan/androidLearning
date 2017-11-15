# myfirstapp = intent
- This simplest example includes about how to transfer message between two activities;
![app](https://developer.android.com/training/basics/firstapp/images/screenshot-activity2.png)


# send message
```java
/** Called when the user taps the Send button */
public void sendMessage(View view){
    Intent intent = new Intent(this, DisplayMessageActivity.class);
    EditText editText = (EditText) findViewById(R.id.editText);
    String message = editText.getText().toString();
    intent.putExtra(EXTRA_MESSAGE, message);
    startActivity(intent);
}
```

# receive message
```java
// Get the Intent that started this activity and extract the string
Intent intent = getIntent();
String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

// Capture the layout's TextView and set the string as its text
TextView textView = findViewById(R.id.textView);
textView.setText(message);
```
