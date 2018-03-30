package com.example.rutujabanswal.pocketgarage;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.rutujabanswal.pocketgarage.Model.Expenses;
import com.example.rutujabanswal.pocketgarage.Model.FindNearbyGarage;
import com.example.rutujabanswal.pocketgarage.Model.Fuel;
import com.example.rutujabanswal.pocketgarage.Model.Profile;
import com.example.rutujabanswal.pocketgarage.Model.ServiceAgain;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

 //    private FloatingActionMenu fam;
 //  private FloatingActionButton fabEdit, fabDelete, fabAdd;
 //private FloatingActionButton fabService, fabExpense,fabRefueling;
    FloatingActionButton fabService, fabExpense,fabRefueling,fam;
    boolean isOpened= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //First of all, we will check if Google Play Services available or not in onCreate() function of MainActivity.java.
        CheckGooglePlayServices();
        ///
         fam = (FloatingActionButton)findViewById(R.id.fab);
         fabService = (FloatingActionButton)findViewById(R.id.fab1);
         fabRefueling = (FloatingActionButton)findViewById(R.id.fab2);
         fabExpense = (FloatingActionButton)findViewById(R.id.fab3);

         fam.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!isOpened)
                 {
                     showFab();
                 }
                 else
                 {
                     closeFab();
                 }
             }
         });

         fabRefueling.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getBaseContext(),Fuel.class));
             }
         });

         fabService.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getBaseContext(),ServiceAgain.class));
             }
         });

         fabExpense.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getBaseContext(),Expenses.class));
             }
         });



        ///
    }

    private void showFab() {
        isOpened=true;
        fabExpense.setVisibility(View.VISIBLE);
        fabRefueling.setVisibility(View.VISIBLE);
        fabService.setVisibility(View.VISIBLE);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.fab_scale_up);
        fabService.startAnimation(animation);
        fabRefueling.startAnimation(animation);
        fabExpense.startAnimation(animation);
//        fabExpense.animate().translationY(-getResources().getDimension(R.dimen.fab_size_normal));
//        fabRefueling.animate().translationY(-getResources().getDimension(R.dimen.fab_size_normal));
//        fabService.animate().translationY(-getResources().getDimension(R.dimen.fab_size_normal));
    }

    private void closeFab(){
        isOpened=false;
        fabExpense.setVisibility(View.GONE);
        fabRefueling.setVisibility(View.GONE);
        fabService.setVisibility(View.GONE);
        fabService.animate().translationY(0);
        fabRefueling.animate().translationY(0);
        fabExpense.animate().translationY(0);
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_fuel) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(),"Fuel Fragment Selected",Toast.LENGTH_SHORT).show();
            Fuel fuel = new Fuel();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayoutMain,fuel).commit();
        }

        else if (id == R.id.nav_find_nearby_garage) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(),"Garage Fragment Selected",Toast.LENGTH_SHORT).show();
            FindNearbyGarage findNearbyGarage = new FindNearbyGarage();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayoutMain,findNearbyGarage).commit();
        }
        else if (id == R.id.nav_find_nearby_garage) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(),"Garage Fragment Selected",Toast.LENGTH_SHORT).show();
            FindNearbyGarage findNearbyGarage = new FindNearbyGarage();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayoutMain,findNearbyGarage).commit();
        }

        else if (id == R.id.nav_servicesagain) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "Service Fragment Selected", Toast.LENGTH_SHORT).show();
            ServiceAgain again = new ServiceAgain();
            startActivity(new Intent(getBaseContext(),ServiceAgain.class));
            //   FragmentManager fragmentManager = getFragmentManager();
           // fragmentManager.beginTransaction().replace(R.id.frameLayoutMain,again).commit();
        }

        else if (id == R.id.nav_profile) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "Profile Activity Selected", Toast.LENGTH_SHORT).show();
            Profile profile = new Profile();
            startActivity(new Intent(getBaseContext(),Profile.class));
            //   FragmentManager fragmentManager = getFragmentManager();
            // fragmentManager.beginTransaction().replace(R.id.frameLayoutMain,again).commit();
        }

        else if (id == R.id.nav_expenses) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "Expenses Activity Selected", Toast.LENGTH_SHORT).show();
            Expenses expenses = new Expenses();
            startActivity(new Intent(getBaseContext(),Expenses.class));
            //   FragmentManager fragmentManager = getFragmentManager();
            // fragmentManager.beginTransaction().replace(R.id.frameLayoutMain,again).commit();
        }

        else if (id == R.id.nav_share) {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBodyText = "Hey checkout this my app on github : https://play.google.com/store/apps/details?id=br.com.ctncardoso.ctncar&hl=en_IN";
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(intent, "Choose sharing method"));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
