package pv260.solid.ocp.original;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.jdom2.output.Format.getPrettyFormat;

public class PersistenceXML implements PersistenceInterface {
    private Path xmlFile;

    public PersistenceXML(Path xmlFile) {
        this.xmlFile = xmlFile;
    }

    public void persist(Comment comment) {
        persistXml(comment);
    }

    private void persistXml(Comment comment) {
        try {
            if (!Files.exists(xmlFile)) {
                prepareEmptyXml();
            }
            Document doc = new SAXBuilder().build(xmlFile.toFile());
            appendComment(comment,
                    doc);
            writeXml(doc);
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareEmptyXml() throws IOException {
        Document doc = new Document(new Element("comments"));
        XMLOutputter xmlOut = new XMLOutputter();
        try (Writer w = Files.newBufferedWriter(xmlFile)) {
            xmlOut.output(doc,
                    w);
        }
    }

    private void appendComment(Comment commentData,
                               Document doc) {
        Element commentElement = new Element("comment");
        commentElement.addContent(new Element("author").setText(commentData.getAuthor()));
        commentElement.addContent(new Element("entered").setText(String.valueOf(commentData.getEntered()
                .getTime())));
        commentElement.addContent(new Element("headline").setText(commentData.getHeadline()));
        commentElement.addContent(new Element("text").setText(commentData.getText()));
        doc.getRootElement()
                .addContent(commentElement);
    }

    private void writeXml(Document doc) throws IOException {
        XMLOutputter xmlOut = new XMLOutputter();
        xmlOut.setFormat(getPrettyFormat());
        try (Writer w = Files.newBufferedWriter(xmlFile)) {
            xmlOut.output(doc,
                    w);
        }
    }
}
