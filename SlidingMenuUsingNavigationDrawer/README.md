# Android Studio - Creating Sidebar Navigation Drawer - Over Action Bar - Codes
[Part 1 ](https://www.youtube.com/watch?v=AS92bq3XxkA)
[Part 2 ](https://www.youtube.com/watch?v=dpE8kzZznAU)
[Part 3 ](https://www.youtube.com/watch?v=A-AI5AbLVqo)
[Part 4](https://www.youtube.com/watch?v=u9gDooP8IhU)

[YouTube](https://www.youtube.com/watch?v=-SUvA1fXaKw)

# Process 
1. add dependency `implementation 'com.android.support:design:27.0.2'`
1. Change Layout To `drawerLayout`
1. Link `navigationView` To `menu` recourse xml 
    1. `app:menu="@menu/navigation_menu"` -> add menu items
    1. `android:layout_gravity="start"` -> hide in left side
1. MainActivity add menu icon
1. add header Layout `app:headerLayout="@layout/navigation_header"` -> area before items
1. add toolbar -> hide area cover more area on top 
    1. add navigation_action.xml 
    1. change style.xml into NoActionBar
    1. MainActivity add `setSupportActionBar`
    1. Change theme to `android:theme="@style/ThemeOverlay.AppCompat.Dark"`
1. 

# MainActivity add menu icon
```java 
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
```

# navigation_header.xml
```java 
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="140dp"
    android:background="@color/colorPrimary"
    android:padding="10dp">

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:padding="20dp"
        android:text="Header Navigation"
        android:textColor="#ffffff"
        android:textSize="20dp" />
</RelativeLayout>
```

