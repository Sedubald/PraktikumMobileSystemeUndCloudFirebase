package praktikum.msuc.hsas.de.bitspleasepraktikumfirebase;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Bundle extra = getIntent().getExtras();
        GoogleSignInAccount account = extra.getParcelable("user");

        TextView tvName = findViewById(R.id.tv_welcome_name);
        tvName.setText(" " + account.getDisplayName());

        TextView tvAccountId = findViewById(R.id.tv_account_id);
        tvAccountId.setText(account.getId());

        if(account.getPhotoUrl() != null){
            ImageView ivProfileImage = findViewById(R.id.iv_image);
            Uri uri = account.getPhotoUrl();

            try {
                new DownloadImageTask(ivProfileImage).execute(uri.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Button clickButton = findViewById(R.id.btn_logout);
        clickButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_logout){
            finishActivity(0);
        }
    }
}
