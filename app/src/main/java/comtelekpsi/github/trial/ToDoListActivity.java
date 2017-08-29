package comtelekpsi.github.trial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ToDoListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase, mDatabaseToDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        /*mDatabaseToDo=mDatabase.child("Forms").child(Long.toString(formID));
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                linearLayout.removeAllViews();
                mArrayList.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.hasChildren()) {
                        if (snapshot.getValue(.class).type.equals("")){

                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseForm.addValueEventListener(listener);
        */
    }
}
