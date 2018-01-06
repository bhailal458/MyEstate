package com.myestate;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.myestate.databinding.ActivitySignUpBinding;
import com.myestate.dialog.LoadingDialog;

import java.util.concurrent.TimeUnit;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.phoneNumber;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding mBinding;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String varification_code;
    private LoadingDialog mLoadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mLoadingDialog = new LoadingDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }
            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                varification_code = s;
                mLoadingDialog.dismiss();
                mBinding.llSendOTP.setVisibility(View.GONE);
                mBinding.llVarifyOTP.setVisibility(View.VISIBLE);
            }
        };
    }

    @OnClick(R.id.back_button)
    public void onBackClick(){
        onBackPressed();
    }

    @OnClick(R.id.btnSignUp)
    public void clickSignUp() {
        String number = mBinding.edtPhone.getText().toString();
        String email = mBinding.actEmail.getText().toString();
        String password = mBinding.actPassword.getText().toString();
        if (!number.equals("")) {
            mLoadingDialog.show();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91"+number,
                    60,
                    TimeUnit.SECONDS,
                    this,
                    mCallback
            );
        }
    }

    @OnClick(R.id.varify_button)
    public void varifyOTP(){
        String input_OTP = mBinding.edtOTP.getText().toString();
        if (!varification_code.equals(""))
            mLoadingDialog.show();
            varifyPhoneNumber(varification_code,input_OTP);
    }

    public void signInWithPhone(PhoneAuthCredential credential){
        mLoadingDialog.dismiss();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "SignIN Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void varifyPhoneNumber(String varification_code, String input_otp) {
        PhoneAuthCredential crendetial = PhoneAuthProvider.getCredential(varification_code, input_otp);
        signInWithPhone(crendetial);
    }
}
