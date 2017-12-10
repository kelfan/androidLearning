# Android TabLayout Tutorial using ViewPager and Fragments
- [YouTube](https://www.youtube.com/watch?v=ZnhSbXuJaqQ)

# Process 
1. new Tab Activity 
1. Create `Tab.java` and related `tab.xml` 
1. Change `getItem` Of `SectionsPagerAdapter` in MainActivity.java 

# getItem 
```java 
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Tab1();
                case 1:
                    return new Tab2();
                case 2:
                    return new Tab3();
            }
            return null;
        }
```