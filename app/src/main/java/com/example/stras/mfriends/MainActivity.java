package com.example.stras.mfriends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listViewFriends;
    int SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        DAO.setContext(this);

        loadFriends();

        listViewFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BEFriend friend = (BEFriend) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FriendDetailActivity.class);
                intent.putExtra("FriendId", friend.getId());
                startActivityForResult(intent, SECOND_ACTIVITY);
            }
        });
    }

    /**
     * Redirecting to new activity for creating new friend
     *
     * @param view
     */
    public void onClickAddFriend(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FriendFormActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY);
    }

    /**
     * Loading friends from database and generating ListView
     */
    private void loadFriends() {
        DAO dao = DAO.getInstance();

        /*
        dao.deleteAll();

        dao.insert(new BEFriend("1", "Jack", "R.", "River Side 48", "www.google.com", "test@gmail.com", "555-777-999"));
        dao.insert(new BEFriend("2", "Peter", "L.", "River Side 75", "www.bbc.com", "test75@gmail.com", "6547892"));
        dao.insert(new BEFriend("3", "Jane", "U.", "Desert Side 1112", "www.github.com", "test1112@gmail.com", "+12-555-777-999"));
        */

        BEFriendAdapter adapter = new BEFriendAdapter(this, dao.getAll());
        listViewFriends = (ListView) findViewById(R.id.lvFriends);
        listViewFriends.setAdapter(adapter);
    }

    public void onResume(){
        super.onResume();
        loadFriends();
    }
}
