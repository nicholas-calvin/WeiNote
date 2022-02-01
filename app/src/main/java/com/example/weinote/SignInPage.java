package com.example.weinote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;

public class SignInPage extends AppCompatActivity{


    private static final int REQUEST_SIGN_IN_LOGIN = 0;
    Button huawei, guest;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        huawei = findViewById(R.id.huaweiSignIn);
        guest = findViewById(R.id.guestSignIn);
        ivLogo = findViewById(R.id.ivLogo);

        ivLogo.setImageResource(R.drawable.weinotelogo);
        
        huawei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Test");
                huaweiSignIn();

            }
        });
        
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestSignIn();
            }
        });

    }

    private void guestSignIn() {
        Intent intent = new Intent(SignInPage.this, MainActivity.class);
        startActivity(intent);
    }

    private void huaweiSignIn() {
        HuaweiIdAuthParams auth = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setIdToken().createParams();
        HuaweiIdAuthService service = HuaweiIdAuthManager.getService(SignInPage.this, auth);

        startActivityForResult(service.getSignInIntent(), REQUEST_SIGN_IN_LOGIN);
        Log.i("", "Sign in success");
        Intent intent = new Intent(SignInPage.this, MainActivity.class);
        startActivity(intent);


    }

}