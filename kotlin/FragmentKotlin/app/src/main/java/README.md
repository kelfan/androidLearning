# structure 
fragment.xml -> fragment.class 

# process 
1. build fragement xml file 
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/colorAccent"
    >

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home Fragment"
        android:textSize="30sp"
        android:layout_centerInParent="true"
        />

</RelativeLayout>
```

1. create fragment class 
```kotlin
class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }
}
```

1. presentation xml file add fragment element 
```xml
    <fragment
        android:id="@+id/fragment_container"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintBottom_toTopOf="@+id/bottom_navigation"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

1. set fragment in activity 
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: android.support.v4.app.Fragment? = null
        when (item.itemId) {
            R.id.nav_home -> selectedFragment = HomeFragment()
            R.id.nav_favorites -> selectedFragment = FavoriteFragment()
            R.id.nav_search -> selectedFragment = SearchFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()

        true
    }
}
```