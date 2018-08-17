package javaherian.yousef.entekhabvahed;

import java.util.ArrayList;

/**
 * this class is for modeling courses
 * cteated by hasan
 */
public class ModelCourse {
    private String name; //name if class
    private int id;
    private int numberOfGroups; // number groups that represent class
    private ArrayList<ModelGroup> groups;//a vector that holding all groups

    public ModelCourse(String name,int id, int numberOfGroups, ArrayList<ModelGroup> groups) {
        this.name = name;
        this.id = id;
        this.numberOfGroups = numberOfGroups;
        this.groups = groups;
    }
    public ModelCourse()
    {
        numberOfGroups = 0;
        name = "";
        groups = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public ArrayList<ModelGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<ModelGroup> groups) {
        this.groups = groups;
    }
}