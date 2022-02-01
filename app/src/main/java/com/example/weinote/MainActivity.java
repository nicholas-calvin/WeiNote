package com.example.weinote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddNote, signOutBtn;
    RecyclerView rvItem;
    ItemAdapter noteAdapter;

    public static ArrayList<String> noteTitle = new ArrayList<>();
    public static ArrayList<String> noteDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNote = findViewById(R.id.btnAddNote);
        rvItem = findViewById(R.id.rvItem);
        signOutBtn = findViewById(R.id.signOutBtn);

        LinearLayoutManager llManager = new LinearLayoutManager(this);
        rvItem.setLayoutManager(llManager);

        noteAdapter = new ItemAdapter(this, noteTitle, noteDetails);
        rvItem.setAdapter(noteAdapter);

        // Banner Ads
        // initialize Huawei Ads
        HwAds.init(this);

        // Obtain BannerView
        BannerView botBannerView = findViewById(R.id.hw_banner_view);
        botBannerView.setAdId("testw6vs28auh3");
        botBannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_320_50);
        botBannerView.setBannerRefresh(60);
        AdParam adParam = new AdParam.Builder().build();
        botBannerView.loadAd(adParam);

        HuaweiIdAuthParams auth = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setAuthorizationCode().createParams();
        HuaweiIdAuthService services = HuaweiIdAuthManager.getService(MainActivity.this, auth);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task<Void> signOut = services.signOut();
                signOut.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        Log.i("", "Signout success");
                        Toast.makeText(MainActivity.this, "Sign out success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SignInPage.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    public void addNotes(View view) {
        Intent intent = new Intent(MainActivity.this, AddNotes.class);
        startActivity(intent);
    }
}