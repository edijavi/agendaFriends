package com.example.stras.mfriends;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendDetailActivity extends AppCompatActivity {

    private DAO dao;
    private long friendId;
    private Button btnLoadPicture;
    private ImageView imageUser;
    private int loadCode = 56;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        //Load Picture from camera; manifest modified.
        btnLoadPicture = (Button) findViewById(R.id.btnLoadPicture);
        imageUser = (ImageView) findViewById(R.id.imageUser);

        btnLoadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, loadCode);
            }
        });

        Bundle extras = getIntent().getExtras();
        long friendId = (long) extras.getSerializable("FriendId");
        loadFriendDetail(friendId);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == loadCode && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageUser.setImageBitmap(imageBitmap);
        }
    }
    /**
     * Loading and showing of friend
     *
     * @param friendId
     */
    private void loadFriendDetail(long friendId) {
        dao = DAO.getInstance();
        BEFriend selectedFriend = dao.getById(friendId);
        ((TextView) findViewById(R.id.tvName)).setText(selectedFriend.getName());
        ((TextView) findViewById(R.id.tvAddress)).setText(selectedFriend.getAddress());
        ((TextView) findViewById(R.id.tvWebsite)).setText(selectedFriend.getWebsite());
        ((TextView) findViewById(R.id.tvEmail)).setText(selectedFriend.getEmail());
        ((TextView) findViewById(R.id.tvPhone)).setText(selectedFriend.getPhone());
    }

    /**
     * Redirecting back to the main activity
     *
     * @param view
     */
    public void onClickBack(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * Deleting current friend
     *
     * @param view
     */
    public void onClickDelete(View view) {
        dao = DAO.getInstance();
        dao.deleteById(friendId);
        finish();
    }
}
