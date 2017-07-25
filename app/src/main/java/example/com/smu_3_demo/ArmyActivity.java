package example.com.smu_3_demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ArmyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_army);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.soldier:
                Intent b = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.katc.mil.kr/katc/"));
                startActivity(b);
                break;
            case R.id.airforce:
                Intent c = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.airforce.mil.kr:8081/user/indexMain.action?handle=1&siteId=last2"));
                startActivity(c);
                break;
            case R.id.navy:
                Intent d = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.navy.mil.kr/user/boardList.do?handle=182&siteId=navy&id=navy_050601000000"));
                startActivity(d);
                break;
            case R.id.marine:
                Intent e = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.rokmc.mil.kr/recruit/index.do"));
                startActivity(e);
                break;
            case R.id.katusa:
                Intent f = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mma.go.kr/contents.do?mc=mma0000525"));
                startActivity(f);
                break;
            case R.id.powerforce:
                Intent g = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.swc.mil.kr/swc/"));
                startActivity(g);
                break;
            case R.id.police:
                Intent h = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ap.police.go.kr/ap/main.do"));
                startActivity(h);
                break;
            case R.id.policearmy:
                Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mma.go.kr/contents.do?mc=mma0000501"));
                startActivity(j);
                break;
            case R.id.bochung:
                Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mma.go.kr/contents.do?mc=mma0000760"));
                startActivity(k);
                break;
            case R.id.sanup:
                Intent l = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mma.go.kr/contents.do?mc=mma0000760"));
                startActivity(l);
                break;
            case R.id.fireman:
                Intent m = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nfsa.go.kr/"));
                startActivity(m);
                break;
            case R.id.kongik:
                Intent n = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sbm.mma.go.kr/caisSHBS/"));
                startActivity(n);
                break;
            default:
                break;
        }
    }
}
