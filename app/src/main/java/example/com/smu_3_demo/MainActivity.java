package example.com.smu_3_demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static example.com.smu_3_demo.Main2Activity.DEMO_PREFERENCE;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> mListAdapter;
    ListView nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = (ListView) findViewById(R.id.nameList);
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

        nameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                final String name = (String) adapterView.getAdapter().getItem(i);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.del)
                        .setMessage("신청자 이름 : "+ name)
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                SharedPreferences pref = getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.remove(name);
                                editor.commit();

                                refresh();
                                dialogInterface.dismiss();
                            }
                        })
                        .show();

                return true;
            }
        });

    }

    public void onClick(View v) {
        final SharedPreferences preferences = getSharedPreferences("DEMO_PREFERENCE", 0);
        final SharedPreferences.Editor editor = preferences.edit();

        Map<String, ?> values = preferences.getAll();
        for (String key : values.keySet()) {
            editor.remove(key);
            Log.d("test",key);
            editor.commit();
            editor.apply();
        }

        mListAdapter.notifyDataSetChanged();

            mListAdapter.clear();
        Toast.makeText(MainActivity.this, R.string.send, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(MainActivity.this, R.string.welcome, Toast.LENGTH_LONG).show();
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