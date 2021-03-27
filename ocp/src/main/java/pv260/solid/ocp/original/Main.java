package pv260.solid.ocp.original;

import java.nio.file.Paths;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Comment comment = new Comment("My comment",
                                      "This is interesting...",
                                      new Date(),
                                      "Pepa Zdepa");
        new PersistenceXML(Paths.get("comments.xml")).persist(comment);
        new PersistenceCSV(Paths.get("comments.csv")).persist(comment);
    }
}
