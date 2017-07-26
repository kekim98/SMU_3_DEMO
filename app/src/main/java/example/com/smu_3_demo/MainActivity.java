package example.com.smu_3_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        refresh();
    }

    private void refresh() {
        SharedPreferences pref = Main2Activity.getPref(this);

        mListAdapter.clear();
        Map<String, ?> values = pref.getAll();
        for (String key : values.keySet()) {
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
                Toast.makeText(MainActivity.this, "환영합니다.\n상명대학교 병무행정팀입니다.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.help:
                Intent i = new Intent(this, Main4Activity.class);
                startActivity(i);
                return true;
            case R.id.gotomarine:
                Intent a = new Intent(this, ArmyActivity.class);
                startActivity(a);
                Toast.makeText(MainActivity.this, "\t\t\t\t\t\t\t\t군종을 선택하면\n해당 군종 입영 관련 정보가 나옵니다.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.apply:
                Intent apply = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mma.go.kr/contents.do?mc=usr0000176"));
                startActivity(apply);
                return true;
            case R.id.delay:
                Intent delay = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mma.go.kr/contents.do?mc=usr0000174"));
                startActivity(delay);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}