package process;
        import android.content.Context;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.Map;

        import heidari.mohammad.entekhabvahed.ModelScheduleVersion1;
        import javaherian.yousef.entekhabvahed.ModelCourse;
        import javaherian.yousef.entekhabvahed.ModelGroup;
        import javaherian.yousef.entekhabvahed.ModelRule;
        import javaherian.yousef.entekhabvahed.ModelSchedule;
        import static javaherian.yousef.entekhabvahed.Global.db;

public class processClass {
    private ArrayList<ModelRule> mRules;
    private ArrayList<ModelCourse> mCourses;
    private int numberOfCourses=0;
    private ArrayList<ModelSchedule> mSchedules;
    private ModelSchedule sampleSchedule;


    private Context mContext;
    public processClass(Context context) {
        mContext=context;
        mCourses=db.readCourses();
        mRules=db.readRule();
        numberOfCourses=mCourses.size();
        mSchedules  =db.readAllSchedule();
        sampleSchedule = new ModelSchedule();
    }
    private class comp implements Comparator<ModelSchedule> {

        @Override
        public int compare(ModelSchedule schedule, ModelSchedule t1) {
            if (schedule.getTotalScore() > t1.getTotalScore())
                return -1;
            else if (schedule.getTotalScore() == t1.getTotalScore())
             return 0;
            else
                return +1;
        }
    }
    private int scheduleScoreCalculator(ModelSchedule schedule)
    {
        //calculating total score of each schedule
         int score = 0;
         boolean flag0, flag1;
         for (ModelRule rule :mRules)
         {
         flag0 = true;////do all groups adhere rule
         flag1 = false;////does any group meets the conditions of the rule
         for (Map.Entry<ModelCourse, ModelGroup> itr : schedule.getMap().entrySet())
         {
         //checking teacher name
         if (!rule.getTeacher().isEmpty() && !rule.getTeacher().equals(itr.getValue().getTeacherName())  )
           continue;
         //checking course name
         if (!rule.getCourse().isEmpty() && !rule.getCourse().equals(itr.getKey().getName()))
         continue;
         //checking the day
         if (rule.getDay() != ModelRule.ALL  && itr.getValue().getDay1() != rule.getDay()
                 && itr.getValue().getDay2() != rule.getDay()
                 && itr.getValue().getDay3() != rule.getDay())
             continue;
         //groups passing this ifs are candidates of the rule
         flag1 = true;

         if (rule.getStartTime() == 0 && rule.getFinishTime() == 0)
         break;

         if (rule.getDay() == ModelRule.ALL ) {
             //checking that all of the timings are according to the rule
             boolean valid = true;
            if (rule.getStartTime() != 0){
                if (rule.getStartTimeRelation() == ModelRule.LESS_THAN)
                {
                    if (  ((itr.getValue().getDay1() != ModelGroup.NULL)  && (itr.getValue().getStartTime1() > rule.getStartTime()))
                            || ((itr.getValue().getDay2() != ModelGroup.NULL)  && (itr.getValue().getStartTime2() > rule.getStartTime()))
                    ||((itr.getValue().getDay3() != ModelGroup.NULL)  && (itr.getValue().getStartTime3() > rule.getStartTime())))
                        valid=false;
                }
                if (rule.getStartTimeRelation() == ModelRule.MORE_THAN)
                {
                    if (  ((itr.getValue().getDay1() != ModelGroup.NULL)  && (itr.getValue().getStartTime1() < rule.getStartTime()))
                            || ((itr.getValue().getDay2() != ModelGroup.NULL)  && (itr.getValue().getStartTime2() < rule.getStartTime()))
                            ||((itr.getValue().getDay3() != ModelGroup.NULL)  && (itr.getValue().getStartTime3() < rule.getStartTime())))
                        valid=false;
                }
            }

             if (rule.getFinishTime() != 0){
                 if (rule.getFinishTimeRelation() == ModelRule.LESS_THAN)
                 {
                     if (  ((itr.getValue().getDay1() != ModelGroup.NULL)  && (itr.getValue().getFinishTime1() > rule.getFinishTime()))
                             || ((itr.getValue().getDay2() != ModelGroup.NULL)  && (itr.getValue().getFinishTime2() > rule.getFinishTime()))
                             ||((itr.getValue().getDay3() != ModelGroup.NULL)  && (itr.getValue().getFinishTime3() > rule.getFinishTime())))
                         valid=false;
                 }
                 if (rule.getFinishTimeRelation() == ModelRule.MORE_THAN)
                 {
                     if (  ((itr.getValue().getDay1() != ModelGroup.NULL)  && (itr.getValue().getFinishTime1() < rule.getFinishTime()))
                             || ((itr.getValue().getDay2() != ModelGroup.NULL)  && (itr.getValue().getFinishTime2() < rule.getFinishTime()))
                             ||((itr.getValue().getDay3() != ModelGroup.NULL)  && (itr.getValue().getFinishTime3() < rule.getFinishTime())))
                         valid=false;
                 }
             }
             if (valid) continue;
         }
         //otherwise we only need to be sure that timings with the same day of the rule are according to the rule
         else {
             boolean valid = true;
             if (rule.getStartTime() != 0){
                 if (rule.getStartTimeRelation() == ModelRule.LESS_THAN)
                 {
                     if (  ((itr.getValue().getDay1() != rule.getDay())  && (itr.getValue().getStartTime1() > rule.getStartTime()))
                             || ((itr.getValue().getDay2() != rule.getDay())  && (itr.getValue().getStartTime2() > rule.getStartTime()))
                             ||((itr.getValue().getDay3() != rule.getDay())  && (itr.getValue().getStartTime3() > rule.getStartTime())))
                         valid=false;
                 }
                 if (rule.getStartTimeRelation() == ModelRule.MORE_THAN)
                 {
                     if (  ((itr.getValue().getDay1() != rule.getDay())  && (itr.getValue().getStartTime1() < rule.getStartTime()))
                             || ((itr.getValue().getDay2() != rule.getDay())  && (itr.getValue().getStartTime2() < rule.getStartTime()))
                             ||((itr.getValue().getDay3() != rule.getDay())  && (itr.getValue().getStartTime3() < rule.getStartTime())))
                         valid=false;
                 }
             }

             if (rule.getFinishTime() != 0){
                 if (rule.getFinishTimeRelation() == ModelRule.LESS_THAN)
                 {
                     if (  ((itr.getValue().getDay1() != rule.getDay())  && (itr.getValue().getFinishTime1() > rule.getFinishTime()))
                             || ((itr.getValue().getDay2() != rule.getDay())  && (itr.getValue().getFinishTime2() > rule.getFinishTime()))
                             ||((itr.getValue().getDay3() !=rule.getDay())  && (itr.getValue().getFinishTime3() > rule.getFinishTime())))
                         valid=false;
                 }
                 if (rule.getFinishTimeRelation() == ModelRule.MORE_THAN)
                 {
                     if (  ((itr.getValue().getDay1() != rule.getDay())  && (itr.getValue().getFinishTime1() < rule.getFinishTime()))
                             || ((itr.getValue().getDay2() != rule.getDay())  && (itr.getValue().getFinishTime2() < rule.getFinishTime()))
                             ||((itr.getValue().getDay3() != rule.getDay())  && (itr.getValue().getFinishTime3() < rule.getFinishTime())))
                         valid=false;
                 }
             }
             if (valid) continue;

         }


         //not according to the rule so we will terminate the process for this rule
         flag0 = false;
         break;
         }

         if (flag0&& flag1)
         score += rule.getScore();
         }
         return score;
    }
    private void sorter()
    {
        for (ModelSchedule schedule : mSchedules)
            schedule.setTotalScore(scheduleScoreCalculator(schedule));
        Collections.sort(mSchedules,new comp());
    }
    private boolean isScheduleValid(ModelSchedule schedule)
    {
         ModelGroup[] array = schedule.getMap().values().toArray(new ModelGroup[0]);
         int day,st1,st2,ft1,ft2;
        for (int i=0;i<array.length;i++)
            {
                for (int j=i+1;j<array.length;j++)
                {

                    if (array[i].getDay1() != ModelGroup.NULL){
                        day=array[i].getDay1();
                       st1=array[i].getStartTime1();
                       ft1=array[i].getFinishTime1();

                        if (array[j].getDay1() == day){
                            st2=array[j].getStartTime1();
                            ft2=array[j].getFinishTime1();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                        if (array[j].getDay2() == day){
                            st2=array[j].getStartTime2();
                            ft2=array[j].getFinishTime2();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                        if (array[j].getDay3() == day){
                            st2=array[j].getStartTime3();
                            ft2=array[j].getFinishTime3();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                    }


                    if (array[i].getDay2() != ModelGroup.NULL){
                        day=array[i].getDay2();
                        st1=array[i].getStartTime2();
                        ft1=array[i].getFinishTime2();

                        if (array[j].getDay1() == day){
                            st2=array[j].getStartTime1();
                            ft2=array[j].getFinishTime1();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                        if (array[j].getDay2() == day){
                            st2=array[j].getStartTime2();
                            ft2=array[j].getFinishTime2();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                        if (array[j].getDay3() == day){
                            st2=array[j].getStartTime3();
                            ft2=array[j].getFinishTime3();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                    }

                    if (array[i].getDay3() != ModelGroup.NULL){

                        day=array[i].getDay3();
                        st1=array[i].getStartTime3();
                        ft1=array[i].getFinishTime3();

                        if (array[j].getDay1() == day){
                            st2=array[j].getStartTime1();
                            ft2=array[j].getFinishTime1();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                        if (array[j].getDay2() == day){
                            st2=array[j].getStartTime2();
                            ft2=array[j].getFinishTime2();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                        if (array[j].getDay3() == day){
                            st2=array[j].getStartTime3();
                            ft2=array[j].getFinishTime3();
                            if (ft1>st2 && ft2>st1) return false;
                        }
                    }
                }
            }
        return true;
    }

    private void scheduleCreator(int itr)
    {

        if (itr == numberOfCourses)
        {
            sampleSchedule.setUniqueId(mSchedules.size());
            mSchedules.add(sampleSchedule.clone());
        }
        else
        {
            for (int i = 0; i<mCourses.get(itr).getGroups().size(); i++)
            {
                sampleSchedule.addToMap(mCourses.get(itr),mCourses.get(itr).getGroups().get(i));
                if (isScheduleValid(sampleSchedule))
                    scheduleCreator(itr+1);
            }
            sampleSchedule.deleteFromMap(mCourses.get(itr));
        }
    }

    private void doProcess(){
        Toast.makeText(mContext, "Processing!", Toast.LENGTH_SHORT).show();
        mCourses.clear();
        mRules.clear();
        mCourses=db.readCourses();
        mRules=db.readRule();
        numberOfCourses=mCourses.size();
        mSchedules.clear();
        scheduleCreator(0);
        sorter();
    }
    public int scheduleSize(){
        if (db.getNeedToUpdate() == 0)
            return mSchedules.size();
        doProcess();
        db.deleteAllSchedule();
        for (ModelSchedule schedule : mSchedules)
            db.addSchedule(schedule.getUniqueId(),schedule.getTotalScore(),schedule.getMap());
        db.setNeedToUpdate(0);
        return  mSchedules.size();
    }
    public ModelScheduleVersion1 getSchedule(int index){
        if (db.getNeedToUpdate() ==0)
            return new ModelScheduleVersion1(mSchedules.get(index));
        doProcess();
        db.deleteAllSchedule();
        for (ModelSchedule schedule : mSchedules)
            db.addSchedule(schedule.getUniqueId(),schedule.getTotalScore(),schedule.getMap());
        db.setNeedToUpdate(0);
        return new ModelScheduleVersion1(mSchedules.get(index));
    }
}