package com.example.navigationdrawerwithfragments;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import static com.example.navigationdrawerwithfragments.R.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Toolbar toolBar = findViewById(id.toolBar);
        setSupportActionBar(toolBar);

        NavigationView navigationView = findViewById(id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new MessageFragment()).commit();

            navigationView.setCheckedItem(id.navigation_view);
        }

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case id.navigation_message:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new MessageFragment()).commit();
                break;
            case id.navigation_chat:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ChatFragment()).commit();
                break;
            case id.navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ProfileFragment()).commit();
                break;
            case id.navigation_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case id.navigation_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
