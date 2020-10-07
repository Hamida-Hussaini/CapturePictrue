package com.codewithfarah.donationapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;





public class Add_Listing extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE=123;
    Button btnCaptureImage;
    ImageView imageDisplay;
    Button btnPick;

    TabLayout tabLayout;
    TabItem tabItem1, tabItem2;
    ViewPager viewPager;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__listing);

        btnCaptureImage=(Button) findViewById(R.id.btn_captureImage);
        imageDisplay=(ImageView) findViewById(R.id.imageCapture);
        btnPick = findViewById(R.id.btnPickImage);



        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });

        imageDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });

        btnPick.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose from gallery"),GALLERY_REQUEST_CODE);
            }
        });

        tabLayout=(TabLayout)findViewById(R.id.dashboardTablayout);
        viewPager=(ViewPager)findViewById(R.id.vPager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
       /* tabLayout = (TabLayout) findViewById(R.id.tabLayout1);
        tabItem1 = (TabItem) findViewById(R.id.foodItem);
        tabItem2 = (TabItem) findViewById(R.id.nonFoodItem);
        viewPager = (ViewPager) findViewById(R.id.vPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1)
                    pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));*/


    }
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new FoodFragment(),"Food");
        viewPagerAdapter.addFragment(new NonFoodFragment(),"Non-food");
        viewPager.setAdapter(viewPagerAdapter);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==GALLERY_REQUEST_CODE && resultCode==RESULT_OK && data != null){
            Uri imageData =data.getData();

            imageDisplay.setImageURI(imageData);
        }
        else{ super.onActivityResult(requestCode, resultCode, data);

            Bitmap bitmap =(Bitmap)data.getExtras().get("data");
            imageDisplay.setImageBitmap(bitmap);

        }
    }


   }