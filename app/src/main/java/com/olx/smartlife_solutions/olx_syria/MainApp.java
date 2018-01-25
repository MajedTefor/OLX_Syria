package com.olx.smartlife_solutions.olx_syria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.olx.smartlife_solutions.olx_syria.LocalDatabaseAndConnections.CheckInternet;

public class MainApp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    FrameLayout fragmentFL;

    ImageButton homeBtn, favBtn, addNewAdBtn, chatsBtn, accountBtn;
    //

    public static android.app.FragmentTransaction fragmentTransaction;
    public static CheckInternet checkInternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
        checkInternet = new CheckInternet(this);
    }
    public void goToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
    void init() {
        fragmentFL = findViewById(R.id.fragmentFL);
        homeBtn = findViewById(R.id.homeBtn);
        favBtn = findViewById(R.id.favBtn);
        addNewAdBtn = findViewById(R.id.addNewAdBtn);
        chatsBtn = findViewById(R.id.chatsBtn);
        accountBtn = findViewById(R.id.accountBtn);


        homeBtn.setOnClickListener(this);
        favBtn.setOnClickListener(this);
        addNewAdBtn.setOnClickListener(this);
        chatsBtn.setOnClickListener(this);
        accountBtn.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_app, menu);
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

        if (id == R.id.drawerAboutApp) {
            startActivity(new Intent(this, AdsMap.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        Fragment frag = null;
        switch (view.getId())
        {
            case R.id.homeBtn:
                frag = new HomeFragment();
                break;
            case R.id.addNewAdBtn:
                Intent goToCreateNewAd = new Intent(MainApp.this,CreateNewAdActivity.class);
                startActivity(goToCreateNewAd);
                break;
            case R.id.favBtn:
                frag = new FavoriteFragment();
                break;
        }

        if(frag != null)
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentFL, frag);
            transaction.commit();
        }
    }
}
