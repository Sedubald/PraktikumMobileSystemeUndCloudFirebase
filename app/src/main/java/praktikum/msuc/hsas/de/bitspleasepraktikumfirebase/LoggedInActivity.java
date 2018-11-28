package praktikum.msuc.hsas.de.bitspleasepraktikumfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


public class LoggedInActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        // Get account data
        Bundle extra = getIntent().getExtras();
        GoogleSignInAccount account = extra.getParcelable("user");

        // Get fullname from account
        TextView tvName = findViewById(R.id.tv_welcome_name);
        tvName.setText(" " + account.getDisplayName());

        // Get accountid
        TextView tvAccountId = findViewById(R.id.tv_account_id);
        tvAccountId.setText(account.getId());

        // Get emailaddress
        TextView tvEmail = findViewById(R.id.tv_email);
        tvEmail.setText(account.getEmail());

        // Get profilepic
        if(account.getPhotoUrl() != null){
            ImageView iv_profilePic = findViewById(R.id.iv_image);
            Log.d("Imagepath:", "path: " + account.getPhotoUrl());
            //Download and set profile pic to ImageView
            Object[] object=new Object[2];
            object[0] = iv_profilePic;
            object[1] = account.getPhotoUrl();
            // download pic asynchron
            DownloadProfilePicTask dppt = new DownloadProfilePicTask();
            dppt.execute(object);
        }

        Button clickButton = findViewById(R.id.btn_logout);
        clickButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_logout){
            finish();
        }
    }
}

