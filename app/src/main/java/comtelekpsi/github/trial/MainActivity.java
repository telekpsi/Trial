package comtelekpsi.github.trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String username="DavidEvander";
    private TextView userText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userText=(TextView) findViewById(R.id.usernameTextView);
        userText.setText(username);
    }
    public void onTruck(View view)
    {
        Intent intent = new Intent(MainActivity.this, OnTruckActivity.class);
        intent.putExtra("USER_NAME", username);
        startActivity(intent);
    }

    public void offTruck(View view)
    {
        Intent intent = new Intent(MainActivity.this, OffTruckActivity.class);
        intent.putExtra("USER_NAME", username);
        startActivity(intent);
    }

    public void toDoList(View view){
        Intent intent = new Intent(MainActivity.this, ToDoListActivity.class);
        intent.putExtra("USER_NAME", username);
        startActivity(intent);
    }

    public void qrScanner(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scan the QR code");
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();

    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Unsuccessful scan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String contents=result.getContents();
                System.out.println(contents);
                String type=contents.substring(0,2);
                long formID = Long.parseLong(contents.substring(2));
                System.out.println(type);
                System.out.println(formID);
                //may need to not differentiate vehicle and nonvehicle forms?
                if (type.equals("VF")) {
                    Intent intent = new Intent(MainActivity.this, VehicleFormActivity.class);
                    intent.putExtra("FORM_ID", formID);
                    intent.putExtra("FORM_TYPE", type);
                    intent.putExtra("USER_NAME", username);
                    startActivity(intent);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
