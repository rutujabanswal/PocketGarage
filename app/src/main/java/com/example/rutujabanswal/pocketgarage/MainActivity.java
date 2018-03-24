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
import android.widget.Toast;

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

        ///
    }

    private void showFab() {
        isOpened=true;
        fabExpense.setVisibility(View.VISIBLE);
        fabRefueling.setVisibility(View.VISIBLE);
        fabService.setVisibility(View.VISIBLE);
        fabExpense.animate().translationY(-getResources().getDimension(R.dimen.fab_size_normal));
        fabRefueling.animate().translationY(-getResources().getDimension(R.dimen.fab_size_normal));
        fabService.animate().translationY(-getResources().getDimension(R.dimen.fab_size_normal));
    }

    private void closeFab(){
        isOpened=false;
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
