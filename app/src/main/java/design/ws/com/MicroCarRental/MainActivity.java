package design.ws.com.MicroCarRental;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import Adapters.PagerAdapterForBanner;
import Adapters.RecycleAdapter_Cusine;
import Adapters.RecycleAdapter_Dish;
import BeanClasses.BeanClassForCusine;
import BeanClasses.BeanClassForDish;

public class MainActivity extends AppCompatActivity {

    private Button txt1, txt2, txt3, txt4;
    private ImageView img;
    private CardView cardfb,cardtwitter,cardskype,cardinstagram;
    private PagerAdapterForBanner pagerAdapterForBanner;

    private ViewPager viewPager;
    private FirebaseAnalytics mFirebaseAnalytics;
    private LinearLayout book;

    private ArrayList<BeanClassForDish> beanClassForDashboards;

    private RecyclerView recyclerView_dish;
    private RecycleAdapter_Dish mAdapter_dish;
    Integer image[] = {R.drawable.mc1, R.drawable.mc2, R.drawable.mc3, R.drawable.mc4, R.drawable.mc5, R.drawable.mc6, R.drawable.mc7};

    String dish_name[] = {"Grand Cabin", "Honda Aspire", "Honda City", "Honda Civic", "Honda Civic", "Land Crusier", "Mercedes Benz. s class"};
    String price[] = {"Rs 12000", "Rs 4500", "Rs 4000", "Rs 6000", "Rs 10000", "Rs 25000", "Rs 100000"};


    private ArrayList<BeanClassForCusine> beanClassForCusines;

    private RecyclerView recyclerView_cusine;
    private RecycleAdapter_Cusine mAdapter_cusine;


    private DrawerLayout mDrawer;

    private Toolbar toolbar;
    private NavigationView nvDrawer;
    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        txt1 = findViewById(R.id.txtisb);
        txt2 = findViewById(R.id.txtkch);
        txt3 = findViewById(R.id.txtlhr);
        txt4 = findViewById(R.id.txmul);
        img = findViewById(R.id.imgchg);
        cardfb=findViewById(R.id.cardfb);
        cardtwitter=findViewById(R.id.cardtwitter);
        cardinstagram=findViewById(R.id.cardinstagram);
        cardskype=findViewById(R.id.cardskype);
        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout);
        // ...From section above...
        // Find our drawer view
        nvDrawer = findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        drawerToggle = setupDrawerToggle();
        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThirdPartyEmailActivity.class));
            }
        });
//        Dish Recyclerview Code

        recyclerView_dish = findViewById(R.id.recyclerview_dish);
        beanClassForDashboards = new ArrayList<>();


        for (int i = 0; i < image.length; i++) {
            BeanClassForDish beanClassForRecyclerView_contacts = new BeanClassForDish(image[i], dish_name[i], price[i]);


            beanClassForDashboards.add(beanClassForRecyclerView_contacts);
        }


        mAdapter_dish = new RecycleAdapter_Dish(MainActivity.this, beanClassForDashboards);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_dish.setLayoutManager(mLayoutManager);
        //    recyclerView_dish.setItemAnimator(new DefaultItemAnimator());
        recyclerView_dish.setAdapter(mAdapter_dish);


        //        Cusine Recyclerview Code




        /*for (int i = 0; i < image1.length; i++) {
            BeanClassForCusine beanClassForCusine = new BeanClassForCusine(image1[i],price1[i],cusine_name[i],city[i]);


            beanClassForCusines.add(beanClassForCusine);
        }


        mAdapter_cusine = new RecycleAdapter_Cusine(MainActivity.this,beanClassForCusines);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_cusine.setLayoutManager(mLayoutManager1);
        recyclerView_cusine.setItemAnimator(new DefaultItemAnimator());
        recyclerView_cusine.setAdapter(mAdapter_cusine);
*/


        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.isb);
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.karachi);
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.lahore);
            }
        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.mul);
            }
        });
        cardfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://root"));

                    startActivity(intent);
                } catch (Exception e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));

                    startActivity(intent);
                    e.printStackTrace();
                }
            }
        });
        cardtwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("com.twitter.android"));

                    startActivity(intent);
                } catch (Exception e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/google/"));

                    startActivity(intent);
                    e.printStackTrace();
                }
            }
        });
cardskype.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            PackageManager packageManager = getPackageManager();
            startActivity(packageManager.getLaunchIntentForPackage("com.skype.android"));
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://skype.com/"));

            startActivity(intent);
            e.printStackTrace();
        }
    }
});
cardinstagram.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            Uri uri = Uri.parse("http://instagram.com/_u/xxx");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");
            startActivity(likeIng);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/google/"));

            startActivity(intent);
            e.printStackTrace();
        }
    }
});
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment: {
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                break;
            }

            case R.id.nav_third_fragment: {
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));

                break;
            }
            case R.id.whstsappchat: {
                PackageManager pm = getPackageManager();
                try {
                    String text = " ";// Replace with your message.

                    String toNumber = "923335004275"; // Replace with mobile phone number without +Sign or leading zeros.


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }

                break;
            }
            case R.id.cfw: {
                startActivity(new Intent(MainActivity.this, CarForWeddingActivity.class));

                break;
            }
            case R.id.vft: {
                try {
                   // mDrawer.closeDrawers();
                    startActivity(new Intent(MainActivity.this, VahicalForTourActivity.class));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }

                break;
            }
            case R.id.elc: {
                try {


                    startActivity(new Intent(MainActivity.this, ExoticCarActivity.class));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }

                break;
            }
            default:
                // fragmentClass = FirstFragment.class;
        }
        try {
            //  fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        // fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        //setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_whatsapp: {
                PackageManager pm = getPackageManager();
                try {
                    String text = " ";// Replace with your message.

                    String toNumber = "923335004275"; // Replace with mobile phone number without +Sign or leading zeros.


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }
              return true;

            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public void onBackPressed()
    {
        finish();
        moveTaskToBack(true);

    }
}
