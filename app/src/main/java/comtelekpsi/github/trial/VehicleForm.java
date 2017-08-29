package comtelekpsi.github.trial;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by David on 8/14/2017.
 */
@IgnoreExtraProperties
public class VehicleForm {
    public String type;
    public String name;
    public String completedBy;
    public String vehicle;
    public long formID;
    public String formType;
    public VehicleForm(){
    }

    public VehicleForm(String type, String name, String completedBy, String vehicle, long formID){
        this.type=type;
        this.name=name;
        this.completedBy=completedBy;
        this.vehicle=vehicle;
        this.formID=formID;
    }
    public VehicleForm(String type, String name, String completedBy, String vehicle, long formID, String formType){
        this.type=type;
        this.name=name;
        this.completedBy=completedBy;
        this.vehicle=vehicle;
        this.formID=formID;
        this.formType=formType;
    }
}
