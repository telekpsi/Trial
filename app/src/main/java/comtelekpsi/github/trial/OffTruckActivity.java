package comtelekpsi.github.trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OffTruckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_truck);
    }

    public void onStretcher(View view)
    {
        Intent intent = new Intent(OffTruckActivity.this, StretchersActivity.class);
        startActivity(intent);
    }

    public void onLadder(View view)
    {
        Intent intent = new Intent(OffTruckActivity.this, LaddersActivity.class);
        startActivity(intent);
    }

    public void onSCBA(View view)
    {
        Intent intent = new Intent(OffTruckActivity.this, SCBAActivity.class);
        startActivity(intent);
    }

    public void onMisc(View view)
    {
        Intent intent = new Intent(OffTruckActivity.this, MiscActivity.class);
        startActivity(intent);
    }
}
