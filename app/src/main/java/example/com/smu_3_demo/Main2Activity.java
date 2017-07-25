package example.com.smu_3_demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        final EditText nameInput = (EditText) findViewById(R.id.name);
        final TextView codeInput = (EditText) findViewById(R.id.code);
        Button saveButton = (Button) findViewById(R.id.save);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String code = codeInput.getText().toString();

                Toast.makeText(Main2Activity.this, name + "님 입대를 축하드립니다.\n나라를 지키는 그대가 자랑스럽습니다.", Toast.LENGTH_SHORT).show();
                Log.d("aaa","name"+name+"code"+code );

                SharedPreferences pref = getPref(Main2Activity.this);
                pref.edit().putString(name, code).apply();
                finish();
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        Main2Activity.this);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        Main2Activity.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("해병");
                adapter.add("육군");
                adapter.add("해군");
                adapter.add("공군");
                adapter.add("의경");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String strName = adapter.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        Main2Activity.this);
                                innBuilder.setMessage(strName);
                                innBuilder.setTitle("축하합니다. 당신이 입대할 곳은");
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder.show();
                break;
            case R.id.buttonX:
                ((EditText) findViewById(R.id.name)).setText("");
                break;
            case R.id.buttonXX:
                ((EditText) findViewById(R.id.code)).setText("");
                break;
            default:
                break;
        }
    }
    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
    }
}