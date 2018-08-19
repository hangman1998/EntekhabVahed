package hoosmand;

import java.util.ArrayList;
import java.util.Random;

import javaherian.yousef.entekhabvahed.ModelCourse;
import javaherian.yousef.entekhabvahed.ModelGroup;
import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.ModelSchedule;

public class FakeClass {

    private ArrayList<ModelCourse> courses;
    private ArrayList<ModelRule> rules;
    private ModelSchedule schedule;

    public FakeClass() {

        courses = new ArrayList<>();
        rules = new ArrayList<>();
        schedule = new ModelSchedule();
        Random random = new Random();
        for (int i=random.nextInt(10);i>0;i--){
            ArrayList<ModelGroup> groups = new ArrayList<ModelGroup>();
            for(int j=random.nextInt(10);j>0;j++){
                groups.add(new ModelGroup("teacher"+random.nextInt(),random.nextInt()));
            }
            courses.add(new ModelCourse("course"+random.nextInt(),random.nextInt(),random.nextInt(),groups));
        }


    }
}
