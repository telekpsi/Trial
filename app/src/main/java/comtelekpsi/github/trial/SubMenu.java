package comtelekpsi.github.trial;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by David on 8/11/2017.
 */
@IgnoreExtraProperties
public class SubMenu {

    public String type;
    public VehicleForm vForm;
    public String name;
    public String form;
    public long idNum;


    public SubMenu(){

    }
    public SubMenu(String type, String name, String form, long idNum){
        this.type=type;
        this.name=name;
        this.form=form;
        this.idNum=idNum;
    }
    public SubMenu(String type, String name, long idNum, VehicleForm vForm){
        this.type=type;
        this.name=name;
        this.vForm=vForm;
        this.idNum=idNum;
    }
}
