package co.com.poli.tallerpds.persistence.entity;

public enum ProjectTaskStatus {

    NotStarted("Not Started"),InProgress("In Progress"),Completed("Completed"),Deleted("Deleted");

    private final String text;

    ProjectTaskStatus(final String text){
        this.text = text;
    }

    @Override
    public  String toString(){
        return text;
    }
}
