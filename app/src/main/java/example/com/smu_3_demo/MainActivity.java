package example.com.smu_3_demo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView nameList = (ListView) findViewById(R.id.nameList);
        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        nameList.setAdapter(mListAdapter);
        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long I) {
                String name = (String) adapterView.getAdapter().getItem(i);
                SharedPreferences pref = Main2Activity.getPref(MainActivity.this);
                String code = pref.getString(name, "UNKNOWN");

                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("name", name);
                intent.putExtra("code", code);
                startActivity(intent);

            }
        });

    }

    public void onClick(View view) {
        SharedPreferences preferences = getSharedPreferences("Mypref", 0);
        preferences.edit().remove("shared_pref_key").commit();

        Toast.makeText(MainActivity.this, "Sending to 병무청 is completed", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResumeeeeeeeeeee");
        refresh();
    }

    private void refresh() {
        Log.d("MainActivity", "refreshhhhhhhhh");
        SharedPreferences pref = Main2Activity.getPref(this);

        mListAdapter.clear();
        Map<String, ?> values = pref.getAll();
        for (String key : values.keySet()) {
            Log.d("aaa", "" + key);
            mListAdapter.add(key);
        }
        mListAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu:
                intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "\t\t\t\t\t\t\t입대를 축하드립니다.\n나라를 지키는 그대가 자랑스럽습니다.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.help:
                Intent i = new Intent(this, Main4Activity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}