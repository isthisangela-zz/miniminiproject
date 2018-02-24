package com.projects.miniminiproject.mdbsocials;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Aayush on 10/11/2016.
 * The main list of Socials screen
 */
public class ListActivity extends AppCompatActivity {

    private Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerAdapter = (RecyclerView)findViewById(R.id.recycleman);
        recyclerAdapter.setLayoutManager(new LinearLayoutManager(this));
        utils = new Utils();
        SocialsAdapter adapter = new SocialsAdapter(getApplicationContext(), utils.getSocialist());
        recyclerAdapter.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewSocialActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Signs the user out and returns to the login screen
     * @param item the signout menu item
     */
    public void signout(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        LoginActivity.getmAuth().signOut();
        startActivity(intent);
    }
}
