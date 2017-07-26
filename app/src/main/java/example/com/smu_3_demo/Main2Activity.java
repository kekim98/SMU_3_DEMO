package example.com.smu_3_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static example.com.smu_3_demo.R.id.btn;

public class Main2Activity extends AppCompatActivity {

    public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        final EditText nameInput = (EditText) findViewById(R.id.name);
        final EditText codeInput = (EditText) findViewById(R.id.code);
        final Button saveButton = (Button) findViewById(R.id.save);


        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nameInput.getText())&&(TextUtils.isEmpty(codeInput.getText()))) {
                    Toast.makeText(Main2Activity.this, R.string.push_name_code, Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(nameInput.getText())){
                    Toast.makeText(Main2Activity.this, R.string.push_name, Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(codeInput.getText())){
                    Toast.makeText(Main2Activity.this, R.string.push_code, Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    String name = nameInput.getText().toString();
                    String code = codeInput.getText().toString();

                    Toast.makeText(Main2Activity.this, name+"님 환영합니다. 그대가 자랑스럽습니다.", Toast.LENGTH_SHORT).show();

                    SharedPreferences pref = getPref(Main2Activity.this);
                    pref.edit().putString(name, code).apply();

                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification.Builder mBuilder = (Notification.Builder) new Notification.Builder(Main2Activity.this);

                    Intent notifyIntent = new Intent(Main2Activity.this, Main3Activity.class);
                    notifyIntent.putExtra("name",name);
                    notifyIntent.putExtra("code",code);
                    notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent notifyPendingIntent = PendingIntent.getActivity(Main2Activity.this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    mBuilder.setSmallIcon(R.drawable.ic_stat_name);
                    mBuilder.setWhen(System.currentTimeMillis());
                    mBuilder.setContentTitle("신청 완료!");
                    mBuilder.setContentText("이름 : "+name+"\n학번:"+code);
                    mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                    mBuilder.setContentIntent(notifyPendingIntent);
                    mBuilder.setAutoCancel(true);

                    mNotificationManager.notify(1, mBuilder.build());
                    finish();

                }
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case btn:

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        Main2Activity.this);
                alertBuilder.setTitle(R.string.choose);

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
                                Button btn = (Button) findViewById(R.id.btn);
                                btn.setText(strName);
                                String button = btn.getText().toString();

                                innBuilder.setTitle(R.string.cong);
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