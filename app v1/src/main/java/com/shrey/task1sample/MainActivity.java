package com.shrey.task1sample;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.content.res.ResourcesCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView toggleProf;
    SwipeRefreshLayout mSwipeRefresh;
    TextView mBodyMsg, mBodyNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggleProf = findViewById(R.id.togglePic);
        mSwipeRefresh = findViewById(R.id.swiperefresh);
        mBodyMsg = findViewById(R.id.body_msg);
        mBodyNotif = findViewById(R.id.body_notif);

        // Logic for the side navigation drawer

        // The Account Profiles
        Resources res = this.getResources();
        ProfileDrawerItem profile1 = new ProfileDrawerItem().withName("Dana Scott").withEmail("@Scottie85").withIcon(ResourcesCompat.getDrawable(res, R.drawable.main_profile_1, null));
        ProfileDrawerItem profile2 = new ProfileDrawerItem().withName("James T. Kirk").withEmail("@Cap.Kirk30").withIcon(ResourcesCompat.getDrawable(res, R.drawable.main_profile_2, null));

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.header)
                .withAccountHeader(R.layout.custom_header)
                .addProfiles(
                        // Adding the account profiles to the Account Header
                        profile1,
                        profile2
                )
                .withOnAccountHeaderListener((View view, IProfile profile, boolean currentProfile) -> {
                    if (profile == profile1) {
                        toggleProf.setImageResource(R.drawable.main_profile_1);
                    } else if (profile == profile2) {
                        toggleProf.setImageResource(R.drawable.main_profile_2);
                    }
                    return true;
                })
                .build();

        final Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(

                        // Adding different options to th side navigation drawer
                        new PrimaryDrawerItem().withIdentifier(2).withName(R.string.item_profile).withIcon(FontAwesome.Icon.faw_user).withSelectable(false),
                        new PrimaryDrawerItem().withIdentifier(3).withName(R.string.item_lists).withIcon(FontAwesome.Icon.faw_list_alt).withSelectable(false),
                        new PrimaryDrawerItem().withIdentifier(4).withName(R.string.item_moments).withIcon(FontAwesome.Icon.faw_bolt).withSelectable(false),
                        new PrimaryDrawerItem().withIdentifier(5).withName(R.string.item_highlights).withIcon(FontAwesome.Icon.faw_clone).withSelectable(false),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withIdentifier(6).withName(R.string.item_sap).withSelectable(false),
                        new PrimaryDrawerItem().withIdentifier(7).withName(R.string.item_help).withSelectable(false)

                ).withOnDrawerItemClickListener((view, position, drawerItem) -> true)
                .addStickyDrawerItems(

                        // Adding options to the footer of the side navigation drawer
                        new SecondaryDrawerItem().withName(R.string.item_night).withIcon(FontAwesome.Icon.faw_moon).withIconColorRes(R.color.colorAccent).withTextColorRes(R.color.colorAccent),
                        new SecondaryDrawerItem().withName(R.string.item_qr).withIcon(FontAwesome.Icon.faw_qrcode).withIconColorRes(R.color.colorAccent).withTextColorRes(R.color.colorAccent)

                )
                .withSelectedItem(-1)
                .build();

        toggleProf.setOnClickListener((view) -> {

            if (result.isDrawerOpen()) {
                result.closeDrawer();
            } else {
                result.openDrawer();
            }

        });

        // Logic for the navigation tabs

        TabLayout tabLayout = findViewById(R.id.tabs);

        TabLayout.Tab home = tabLayout.newTab().setIcon(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_home).colorRes(R.color.colorAccent));
        TabLayout.Tab search = tabLayout.newTab().setIcon(new IconicsDrawable(this).icon(Ionicons.Icon.ion_ios_search).colorRes(R.color.draw_description));
        TabLayout.Tab notif = tabLayout.newTab().setIcon(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_bell).colorRes(R.color.draw_description));
        TabLayout.Tab msg = tabLayout.newTab().setIcon(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_envelope).colorRes(R.color.draw_description));

        tabLayout.addTab(home);
        tabLayout.addTab(search);
        tabLayout.addTab(notif);
        tabLayout.addTab(msg);

        // Handling the click events on the tabs
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0){

                    mSwipeRefresh.setVisibility(View.VISIBLE);
                    mBodyMsg.setVisibility(View.GONE);
                    mBodyNotif.setVisibility(View.GONE);

                    home.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_home).colorRes(R.color.colorAccent));
                    search.setIcon(new IconicsDrawable(getApplicationContext()).icon(Ionicons.Icon.ion_ios_search).colorRes(R.color.draw_description));
                    notif.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_bell).colorRes(R.color.draw_description));
                    msg.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_envelope).colorRes(R.color.draw_description));

                } else if(tabLayout.getSelectedTabPosition() == 1){

                    //Toast.makeText(getApplicationContext(), "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(i);

                    new Handler().postDelayed(() -> {
                        home.select();
                        mSwipeRefresh.setVisibility(View.VISIBLE);
                        mBodyMsg.setVisibility(View.GONE);
                        mBodyNotif.setVisibility(View.GONE);
                    }, 50);


                    home.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_home).colorRes(R.color.draw_description));
                    search.setIcon(new IconicsDrawable(getApplicationContext()).icon(Ionicons.Icon.ion_ios_search).colorRes(R.color.colorAccent));
                    notif.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_bell).colorRes(R.color.draw_description));
                    msg.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_envelope).colorRes(R.color.draw_description));

                } else if(tabLayout.getSelectedTabPosition() == 2){

                    mSwipeRefresh.setVisibility(View.GONE);
                    mBodyNotif.setVisibility(View.VISIBLE);
                    mBodyMsg.setVisibility(View.GONE);

                    home.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_home).colorRes(R.color.draw_description));
                    search.setIcon(new IconicsDrawable(getApplicationContext()).icon(Ionicons.Icon.ion_ios_search).colorRes(R.color.draw_description));
                    notif.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_bell).colorRes(R.color.colorAccent));
                    msg.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_envelope).colorRes(R.color.draw_description));

                } else if(tabLayout.getSelectedTabPosition() == 3){

                    mSwipeRefresh.setVisibility(View.GONE);
                    mBodyNotif.setVisibility(View.GONE);
                    mBodyMsg.setVisibility(View.VISIBLE);

                    home.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_home).colorRes(R.color.draw_description));
                    search.setIcon(new IconicsDrawable(getApplicationContext()).icon(Ionicons.Icon.ion_ios_search).colorRes(R.color.draw_description));
                    notif.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_bell).colorRes(R.color.draw_description));
                    msg.setIcon(new IconicsDrawable(getApplicationContext()).icon(FontAwesome.Icon.faw_envelope).colorRes(R.color.colorAccent));

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Logic for swipe down to refresh

        mSwipeRefresh.setColorSchemeResources(R.color.colorAccent);

        mSwipeRefresh.setOnRefreshListener(() -> new Handler().postDelayed(() -> mSwipeRefresh.setRefreshing(false), 5000));

        // Logic for list view

        final ListView listview = findViewById(R.id.list);
        final ArrayList<Tweet> list = new ArrayList<Tweet>();
        list.add(new Tweet("Doug Judy", "@Judy98", "30m", "Hey there !!", R.drawable.profile_3, "#61045F", 0, 5, 1));
        list.add(new Tweet("Ariel Pollich", "@Ariel49", "40m", "Hey there !!", R.drawable.profile_4, "#333B2E", 4, 500, 10));
        list.add(new Tweet("Sasha Ho", "@Makenna38", "50m", "Hey there !!", R.drawable.profile_2, "#44322E", 1, 8, 25));
        list.add(new Tweet("Zia Burkett", "@Burketto", "1h", "Hey there !!", R.drawable.profile_1, "#3689F3", 5, 1, 5));

        final TweetAdapter adapter = new TweetAdapter(this, R.layout.tweet_row, list);
        listview.setAdapter(adapter);

        // The tweet now button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
//    }

}
