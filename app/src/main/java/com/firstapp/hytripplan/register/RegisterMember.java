package com.firstapp.hytripplan.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.hytripplan.R;
import com.firstapp.hytripplan.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterMember extends AppCompatActivity {
    private Button btnCancel, btnSave;
    private FirebaseAuth mAuth;
    private EditText et_id, et_pw, et_name, et_num;
    private String email, password, TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_member);

        btnCancel = findViewById(R.id.cancel_btn);
        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        et_name = findViewById(R.id.et_name);
        et_num = findViewById(R.id.et_num);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelInten = new Intent(RegisterMember.this, LoginActivity.class);
                startActivity(cancelInten);
                finish();
            }
        });

        TAG = "create";
        mAuth = FirebaseAuth.getInstance();
        btnSave = findViewById(R.id.save_btn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_id.getText().toString().trim();
                password = et_pw.getText().toString().trim();
                createEmail(email, password);
            }
        });

    }

    private void createEmail(String email, String password) {
        Log.e("trace", "trace : " + (mAuth == null) + ", " + email + ", " + password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterMember.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            AlertDialog dlg = new AlertDialog.Builder(this)
                    .setMessage("회원가입에 실패하였습니다.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dlg.show();
        }
    }
}
