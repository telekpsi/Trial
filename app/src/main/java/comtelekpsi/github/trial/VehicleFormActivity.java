package comtelekpsi.github.trial;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class VehicleFormActivity extends AppCompatActivity {
    private long formID;
    private String formType;
    private TextView mTextView, mFormTextView, mNameTextView, mDateTextView, mCompletedByTextView;
    private ScrollView mScrollView;
    private LinearLayout linearLayout, mLinearLayoutHeader;
    private DatabaseReference mDatabase, mDatabaseForm;
    static ArrayList<Item> mArrayList = new ArrayList();
    Context context = this;
    private LinearLayout mTopBarLayout;
    TableLayout mTableLayout;
    TextView mSectionTextView;
    TextView mUserNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_form);
        formID=getIntent().getLongExtra("FORM_ID", -1);
        formType=getIntent().getStringExtra("FORM_TYPE");
        final String username=getIntent().getStringExtra("USER_NAME");
        mUserNameTextView=(TextView)findViewById(R.id.usernameTextView);
        mUserNameTextView.setText(username);
        mCompletedByTextView=(TextView)findViewById(R.id.completedByTextView);
        mCompletedByTextView.setText("Completed by: "+username);
        mDateTextView=(TextView)findViewById(R.id.dateTextView);
        String dateTime = "Filled Out: ";
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateTime.concat(df.format(cal.getTime()));
        mDateTextView.setText(dateTime);
        mTableLayout=(TableLayout) findViewById(R.id.tableLayout2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabaseForm=mDatabase.child("Forms").child(Long.toString(formID));
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTableLayout.removeAllViews();
                mArrayList.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.hasChildren()) {
                        if (snapshot.getValue(Item.class).type.equals("pmrInventory")){
                            Item item = snapshot.getValue(Item.class);
                            mArrayList.add(snapshot.getValue(Item.class));
                            System.out.println(mArrayList.size() + "items");
                        }
                    }
                }
                for (int i=0; i<mArrayList.size(); i++){
                    TableRow tableRow = new TableRow(context);
                    mTableLayout.addView(tableRow);
                    TextView itemText = new TextView(context);
                    tableRow.addView(itemText);
                    itemText.setText(mArrayList.get(i).name);
                    new RadioHelper(mArrayList.get(i),tableRow,context);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseForm.addValueEventListener(listener);
    }
}
