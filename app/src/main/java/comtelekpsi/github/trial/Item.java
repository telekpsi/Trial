package comtelekpsi.github.trial;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by David on 8/23/2017.
 */
@IgnoreExtraProperties
public class Item {

    String name;
    String type;

    public Item(){

    }

    public Item(String name, String type){
        this.name=name;
        this.type=type;
    }
}
