package comtelekpsi.github.trial;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;

/**
 * Created by David on 8/23/2017.
 */

public class RadioHelper{
    Item item;
    TableRow tableRow;

    public RadioHelper(){

    }
    public RadioHelper(Item item, TableRow tableRow, Context context){
        this.item=item;
        this.tableRow = tableRow;
        RadioGroup radioGroup=new RadioGroup(context);
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        tableRow.addView(radioGroup);
        RadioButton pRadioButton = new RadioButton(context);
        pRadioButton.setText("Present");
        RadioButton mRadioButton = new RadioButton(context);
        mRadioButton.setText("Missing");
        RadioButton rRadioButton = new RadioButton(context);
        rRadioButton.setText("Repairs Needed");
        radioGroup.addView(pRadioButton);
        radioGroup.addView(mRadioButton);
        radioGroup.addView(rRadioButton);
    }
}
