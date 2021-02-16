package com.example.softwareengineeringfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutUsActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }
    private void openNewActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void ClickMenu(View view){
        //open drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view)
    {
        //closeDrawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //redirect activity to home
        MainActivity.redirectActivity(this, MainActivity.class);
    }
    public void ClickList(View view){
        //logout
        MainActivity.redirectActivity(this, ListActivity.class);
    }
    public void ClickShop(View view){
        //logout
        MainActivity.redirectActivity(this, ShopActivity.class);
    }


    public void ClickDashboard(View view){
        //redirect to dashboard activity
        MainActivity.redirectActivity(this,DashboardActivity.class );
    }

    public void ClickAboutUs(View view){
        //go to our about us page
       recreate();
    }
    public void ClickLogout(View view){
        //logout
        MainActivity.logout(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //close the drawer
        MainActivity.closeDrawer(drawerLayout);
    }

}