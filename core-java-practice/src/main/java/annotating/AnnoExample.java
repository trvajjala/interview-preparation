package annotating;

import annotating.Comment.Priority;

public class AnnoExample {

    @Comment(author = "ThirupathiReddy", description = "this is used to annotation testing", priority = Priority.HIGH)
    public void doSth() {
        System.out.println(" Hello world ");
    }
}
