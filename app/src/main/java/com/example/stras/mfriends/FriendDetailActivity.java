package com.example.stras.mfriends;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendDetailActivity extends AppCompatActivity {

    private DAO dao;
    private long friendId;
    private Button btnLoadPicture;
    private Button btnSaveFriend;
    private Button btnEdit;
    private ImageView imageUser;
    private int loadCode = 56;
    private BEFriend selectedFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        //Load Picture from camera; manifest modified.
        dao = DAO.getInstance();

        btnLoadPicture = (Button) findViewById(R.id.btnLoadPicture);
        btnSaveFriend = (Button) findViewById(R.id.buttonSave);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        imageUser = (ImageView) findViewById(R.id.imageUser);


        ((EditText) findViewById(R.id.tvName)).setEnabled(false);
        ((EditText) findViewById(R.id.tvAddress)).setEnabled(false);
        ((EditText) findViewById(R.id.tvPhone)).setEnabled(false);
        ((EditText) findViewById(R.id.tvEmail)).setEnabled(false);
        ((EditText) findViewById(R.id.tvWebsite)).setEnabled(false);


        btnLoadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, loadCode);
            }
        });


        btnSaveFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFriend = dao.getById(friendId);

                ImageView iv = (ImageView) findViewById(R.id.imageUser);
                Bitmap bitmap = ((BitmapDrawable)(iv.getDrawable())).getBitmap();
                selectedFriend.setImage(bitmap);
                selectedFriend.setFirstName(((EditText) findViewById(R.id.tvName)).getText()+"");
                selectedFriend.setFirstName(((EditText) findViewById(R.id.tvAddress)).getText()+"");
                selectedFriend.setFirstName(((EditText) findViewById(R.id.tvPhone)).getText()+"");
                selectedFriend.setFirstName(((EditText) findViewById(R.id.tvEmail)).getText()+"");
                selectedFriend.setFirstName(((EditText) findViewById(R.id.tvWebsite)).getText()+"");

                dao.update(selectedFriend);

            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText) findViewById(R.id.tvName)).setEnabled(true);
                ((EditText) findViewById(R.id.tvAddress)).setEnabled(true);
                ((EditText) findViewById(R.id.tvPhone)).setEnabled(true);
                ((EditText) findViewById(R.id.tvEmail)).setEnabled(true);
                ((EditText) findViewById(R.id.tvWebsite)).setEnabled(true);

            }
        });

        Bundle extras = getIntent().getExtras();
        friendId = (long) extras.getSerializable("FriendId");
        loadFriendDetail(friendId);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == loadCode && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selectedFriend.setImage(imageBitmap);
            imageUser.setImageBitmap(imageBitmap);

        }
    }
    /**
     * Loading and showing of friend
     *
     * @param friendId
     */
    private void loadFriendDetail(long friendId) {
        selectedFriend = dao.getById(friendId);
        ((EditText) findViewById(R.id.tvName)).setText(selectedFriend.getName());
        ((EditText) findViewById(R.id.tvAddress)).setText(selectedFriend.getAddress());
        ((EditText) findViewById(R.id.tvWebsite)).setText(selectedFriend.getWebsite());
        ((EditText) findViewById(R.id.tvEmail)).setText(selectedFriend.getEmail());
        ((EditText) findViewById(R.id.tvPhone)).setText(selectedFriend.getPhone());
        ((ImageView) findViewById(R.id.imageUser)).setImageBitmap(selectedFriend.getImage());

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
