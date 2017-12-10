# How to use Tabs with navigation drawer in android - Codes
[YouTube](https://www.youtube.com/watch?v=SHkdU9sznW8)

# Process 
1. new drawer Activity 
1. add `tabLayout` in `app_bar...xml` after `toolbar`
1. add `tabsPager.java` extends `fragmentStatePagerAdapter`
1. add `Fragment` with unchecked last 2 options
1. add Switch between fragments 
1. add `viewpager` in `content_main.xml`
1. MainActivity.java set Adapter 


# Switch between Fragments 
```java 
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                BlankFragment blankFragment=new BlankFragment();
                return blankFragment;
            case 1:
                BlankFragment blankFragment2=new BlankFragment();
                return blankFragment2;
            case 2:
                BlankFragment blankFragment3=new BlankFragment();
                return blankFragment3;
        }

        return null;
    }
```

# MainActivity.java set Adapter 
```java 
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewPager);

        // pass fragment=getSupportFragmentManager() as constructor parameter
        tabsPager tabsPager = new tabsPager(getSupportFragmentManager());

        // set Adapter to link fragments
        viewPager.setAdapter(tabsPager);
        tabLayout.setupWithViewPager(viewPager); // it is important to check in action else not Work
```