package l.generationz.first_program;

/**
 * Created by Julia on 12.12.2017.
 */

public class QuestDetails {
    private String name;
    private String description;
    private String image;
    private String task;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask (){return task;}

    public void setTask (String task){this.task = task;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
