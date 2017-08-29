package comtelekpsi.github.trial;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
public class OnTruckActivity extends AppCompatActivity {
    public int j;
    private ScrollView mScrollView;
    Context mContext = this;
    private DatabaseReference mDatabase, mDatabaseVehiclesMenu;
    private LinearLayout mLinearLayout, mLinearLayout2, mTopBarLayout;
    static ArrayList<SubMenu> mArrayList = new ArrayList();
    ArrayList<Button> buttons = new ArrayList();
    ImageButton mImageButton;
    TextView mUserNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_truck);
        final String username = getIntent().getStringExtra("USER_NAME");
        mScrollView=(ScrollView) findViewById(R.id.scrollView);
        mTopBarLayout = (LinearLayout) findViewById(R.id.topBarLayout);
        mImageButton = (ImageButton) findViewById(R.id.logoutButton);
        mUserNameTextView = (TextView) findViewById(R.id.usernameTextView);
        mUserNameTextView.setText(username);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabaseVehiclesMenu=mDatabase.child("VehiclesMenu");
        final Context context = this;


        ValueEventListener firstListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mLinearLayout=(LinearLayout)findViewById(R.id.linearLayout);
                mLinearLayout.removeAllViews();
                mLinearLayout.addView(mTopBarLayout);
                j=(int)dataSnapshot.getChildrenCount();
                mArrayList.clear();
                buttons.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.hasChildren()) {
                        if (snapshot.getValue(SubMenu.class).type.equals("submenu")){
                            SubMenu submenu = snapshot.getValue(SubMenu.class);
                            mArrayList.add(snapshot.getValue(SubMenu.class));
                            System.out.println(mArrayList.size());
                        }
                    }
                }
                TextView mTextView = new TextView(mContext);
                //mTextView = (TextView) findViewById(R.id.textView);
                mTextView.setText("Active Vehicles List");
                mLinearLayout.addView(mTextView);
                for(int i = 0; i < mArrayList.size(); i++){
                    Button button = new Button(mContext);
                    final int index=i;
                    buttons.add(button);
                    button.setText(mArrayList.get(i).name);
                    button.setBackgroundColor(Color.BLACK);
                    button.setTextColor(Color.WHITE);
                    button.setId((int)mArrayList.get(i).idNum);
                    mLinearLayout.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(OnTruckActivity.this, VehicleSubActivity.class);
                            intent.putExtra("VEHICLE_NAME", mArrayList.get(index).name);
                            intent.putExtra("USER_NAME", username);
                            startActivity(intent);
                        }
                    });
                    //need to create on click function and set on click listener that opens a new view/activity/fragment
                    //and passes through the form to open
                    //later, also need to add text to right of button indicating whether it has already been completed, and by whom
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseVehiclesMenu.addValueEventListener(firstListener);
        //mDatabaseVehiclesMenu.removeEventListener(firstListener);


    }
}
