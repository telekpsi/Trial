package comtelekpsi.github.trial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VehicleSubActivity extends AppCompatActivity {

    private ScrollView mScrollView;
    private LinearLayout mLinearLayout;
    Context mContext = this;
    private DatabaseReference mDatabase, mDatabaseForms;
    static ArrayList<VehicleForm> mArrayList = new ArrayList();
    ArrayList<Button> buttons = new ArrayList();
    ImageButton mImageButton;
    private LinearLayout mTopBarLayout;
    TableLayout mTableLayout;
    TextView mSectionTextView;
    TextView mUserNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_sub);
        final String vehicleName= getIntent().getStringExtra("VEHICLE_NAME");
        final String username = getIntent().getStringExtra("USER_NAME");
        mUserNameTextView = (TextView) findViewById(R.id.usernameTextView);
        mUserNameTextView.setText(username);
        mTopBarLayout=(LinearLayout) findViewById(R.id.topBarLayout);
        mTableLayout=(TableLayout) findViewById(R.id.tableLayout);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //change this to grab the subsections for the given vehicle.  Either add as additional category in vehicles, or separate
        mDatabaseForms=mDatabase.child("VehiclesMenu").child(vehicleName).child("forms");
        mSectionTextView=(TextView) findViewById(R.id.sectionsTextView);
        ValueEventListener firstListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mLinearLayout=(LinearLayout)findViewById(R.id.linearLayout2);
                mTableLayout.removeAllViews();
                mArrayList.clear();
                buttons.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.hasChildren()) {
                        if (snapshot.getValue(VehicleForm.class).type.equals("VehicleForm")){
                            VehicleForm vForm = snapshot.getValue(VehicleForm.class);
                            mArrayList.add(snapshot.getValue(VehicleForm.class));
                        }
                    }
                }
                mSectionTextView.setText(vehicleName+" Subsections");
                Context context=mTableLayout.getContext();
                for(int i = 0; i < mArrayList.size(); i++){
                    /*LinearLayout bLayout = new LinearLayout(context);
                    mLinearLayout.addView(bLayout);
                    bLayout.setOrientation(LinearLayout.HORIZONTAL);*/
                    TableRow tableRow = new TableRow(context);
                    mTableLayout.addView(tableRow);
                    Button button = new Button(context);
                    TextView textView = new TextView(context);
                    String cb=mArrayList.get(i).completedBy;
                    if (!cb.equals("nobody")){
                        cb = "Completed by "+cb;
                        textView.setText(cb);}
                    final int index=i;
                    buttons.add(button);
                    button.setText(mArrayList.get(i).name);
                    button.setBackgroundColor(Color.BLACK);
                    button.setTextColor(Color.WHITE);
                    button.setId((int) mArrayList.get(i).formID);
                    tableRow.addView(button);
                    tableRow.addView(textView);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(VehicleSubActivity.this, VehicleFormActivity.class);
                            intent.putExtra("FORM_ID", mArrayList.get(index).formID);
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseForms.addValueEventListener(firstListener);
    }
}
