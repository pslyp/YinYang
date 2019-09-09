package com.pslyp.yinyang.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Response;
import com.pslyp.yinyang.services.api.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button submitButton;
    private CircularImageView pictureCircle;
    private TextInputLayout userTextInput, emailTextInput, passTextInput;

    private int PICK_IMAGE_REQUEST = 1010;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_circle_image_view :
                choosePicture();
                break;
            case R.id.submit_button :
                submit();
                break;
        }
    }

    private void initInstance() {
        pictureCircle = findViewById(R.id.profile_circle_image_view);
        pictureCircle.setOnClickListener(this);
        userTextInput = findViewById(R.id.username_text_input);
        emailTextInput = findViewById(R.id.email_text_input);
        passTextInput = findViewById(R.id.password_text_input);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            try {
                final Uri uri = data.getData();
                final InputStream inputStream = getContentResolver().openInputStream(uri);
                imageBitmap = BitmapFactory.decodeStream(inputStream);
                pictureCircle.setImageBitmap(imageBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_SHORT).show();
        }
    }

    private String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageByte = baos.toByteArray();
        String encodeImage = Base64.encodeToString(imageByte, Base64.DEFAULT);
        return encodeImage;
    }

    private void submit() {
        String username = userTextInput.getEditText().getText().toString().trim();
        String email = emailTextInput.getEditText().getText().toString().trim();
        String password = passTextInput.getEditText().getText().toString().trim();
        String picture = bitmapToString(imageBitmap);

        Call<Response> call = RetrofitClient.getInstance().api().signUp(username, email, password, picture);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response res = response.body();

                if(res.getStatus() == 200) {
                    if(res.getMessage().equals("success")) {
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("Sign up", t.getMessage());
            }
        });
    }
}
