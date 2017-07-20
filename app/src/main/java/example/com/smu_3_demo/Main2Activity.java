package example.com.smu_3_demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText nameInput = (EditText) findViewById(R.id.name);
        final TextView codeInput = (EditText) findViewById(R.id.code);
        Button saveButton = (Button) findViewById(R.id.save);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String code = codeInput.getText().toString();

                Log.d("aaa","name"+name+"code"+code );

                SharedPreferences pref = getPref(Main2Activity.this);
                pref.edit().putString(name, code).apply();
                finish();
            }
        });
    }
    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
    }
}