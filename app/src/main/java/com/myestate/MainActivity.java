package com.myestate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myestate.adapter.GridViewAdapter;
import com.myestate.baseclass.BaseAppCompactActivity;
import com.myestate.container.BaseContainer;
import com.myestate.container.HomeContainer;
import com.myestate.container.LandContainer;
import com.myestate.container.NewsBlogContainer;
import com.myestate.container.PropertyContainer;
import com.myestate.fragments.LandFragment;
import com.myestate.fragments.NewsBlogFragment;
import com.myestate.fragments.PropertyFragment;
import com.myestate.fragments.WebFragment;
import com.squareup.picasso.Picasso;
//
//import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import static android.R.attr.name;

public class MainActivity extends BaseAppCompactActivity
        implements NavigationView.OnNavigationItemSelectedListener {




    private static String currentFragment;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private String email,name;
    private Uri photoUrl;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        currentFragment = HomeContainer.class.getSimpleName();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new HomeContainer(), HomeContainer.class.getSimpleName());
        ft.commit();

//        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        mGridViewAdapter = new GridViewAdapter(this,mGridValue,gridColor);
//        mRecyclerView.setAdapter(mGridViewAdapter);



        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                }
            }
        };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set user data
        ImageView userProfileImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.user_profile_image);
        TextView userName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
        TextView userEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email);

//            userProfileImage.setImageURI(photoUrl);
        Picasso.with(this).load(photoUrl).into(userProfileImage);
            userName.setText(name);
            userEmail.setText(email);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            boolean isPop = false;
            if (currentFragment.equals(HomeContainer.class.getSimpleName())){
                isPop = ((BaseContainer)getSupportFragmentManager().findFragmentByTag(HomeContainer.class.getSimpleName())).popFragment();
            }else if (currentFragment.equals(PropertyContainer.class.getSimpleName())){
                isPop = ((BaseContainer)getSupportFragmentManager().findFragmentByTag(PropertyContainer.class.getSimpleName())).popFragment();
            }else if (currentFragment.equals(LandContainer.class.getSimpleName())){
                isPop = ((BaseContainer)getSupportFragmentManager().findFragmentByTag(LandContainer.class.getSimpleName())).popFragment();
            }else if (currentFragment.equals(NewsBlogContainer.class.getSimpleName())){
                isPop = ((BaseContainer)getSupportFragmentManager().findFragmentByTag(NewsBlogContainer.class.getSimpleName())).popFragment();
            }else if (currentFragment.equals(WebFragment.class.getSimpleName())){
                isPop = false;
            }


            if (!isPop){
                super.onBackPressed();
            }
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
//        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        String title = null;

        switch (id){
            case R.id.nav_home:
                title = "My Attorney";
                currentFragment = HomeContainer.class.getSimpleName();
                fragment = new HomeContainer();
                break;
            case R.id.nav_property:
                title = "Property";
                currentFragment = PropertyContainer.class.getSimpleName();
                fragment = new PropertyFragment();
                break;
            case R.id.nav_land:
                title = "Land";
                currentFragment = LandContainer.class.getSimpleName();
                fragment = new LandFragment();
                break;
            case R.id.nav_news:
                title = "News & Blog";
                currentFragment = NewsBlogContainer.class.getSimpleName();
                fragment = new NewsBlogFragment();
                break;

            case R.id.nav_revenue:
                title = "Revenue Records";
                Bundle bundle = new Bundle();
                bundle.putString("keyurl", "https://anyror.gujarat.gov.in/");
                currentFragment = WebFragment.class.getSimpleName();
                fragment = new WebFragment();
                fragment.setArguments(bundle);
                break;

            case R.id.nav_govt_circular:
                title = "Government Circilar";
                Bundle bundleGov = new Bundle();
                bundleGov.putString("keyurl", "http://www.circulars.gov.ie/");
                currentFragment = WebFragment.class.getSimpleName();
                fragment = new WebFragment();
                fragment.setArguments(bundleGov);
                break;

            case R.id.nav_TPDP:
                title = "TP/DP/Village Maps";
                Bundle bundleTPDP = new Bundle();
                bundleTPDP.putString("keyurl", "https://revenuedepartment.gujarat.gov.in/village-map");
                currentFragment = WebFragment.class.getSimpleName();
                fragment = new WebFragment();
                fragment.setArguments(bundleTPDP);
                break;
            case R.id.nav_jantri:
                title = "Jantri";
                Bundle bundleJantri = new Bundle();
                bundleJantri.putString("keyurl", "http://garvi.gujarat.gov.in/WebForm1.aspx");
                currentFragment = WebFragment.class.getSimpleName();
                fragment = new WebFragment();
                fragment.setArguments(bundleJantri);
                break;
            case R.id.nav_logout:
                mAuth.signOut();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, fragment.getClass().getSimpleName());
            ft.commit();
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
