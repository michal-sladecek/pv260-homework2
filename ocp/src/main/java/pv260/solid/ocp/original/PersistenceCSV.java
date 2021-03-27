package pv260.solid.ocp.original;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;

public class PersistenceCSV implements PersistenceInterface {
    private Path csvFile;

    public PersistenceCSV(Path csvFile) {
        this.csvFile = csvFile;
    }

    public void persist(Comment comment) {
        persistCsv(comment);
    }

    private void persistCsv(Comment comment) {
        try (BufferedWriter writer = Files.newBufferedWriter(csvFile,
                UTF_8,
                CREATE,
                APPEND,
                WRITE)) {
            writer.append(formatCsv(comment));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatCsv(Comment comment) {
        return comment.getAuthor() + ", " + comment.getEntered() + ", " + comment.getHeadline() + ", "
                + comment.getText() + System.lineSeparator();
    }
}
