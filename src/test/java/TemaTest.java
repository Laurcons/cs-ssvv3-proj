import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TemaTest {
    private TemaXMLRepo temaFileRepository;
    private TemaValidator temaValidator;
    private Service service;

    @BeforeEach
    public void setup() {
        File xml = new File("fisiere/temaTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.temaFileRepository = new TemaXMLRepo("fisiere/temaTest.xml");
        this.temaValidator = new TemaValidator();
        this.service = new Service(null, null, this.temaFileRepository, this.temaValidator, null, null);
    }

    @AfterEach
    public void removeXML() {
        new File("fisiere/temaTest.xml").delete();
    }

    @Test
    public void testTemaIdNotInteger() {
        Tema newTema1 = new Tema("", "a", 1, 1);
        assertThrows(ValidationException.class, () -> this.service.addTema(newTema1));
    }

    @Test
    public void testTemaIdNull() {
        Tema newTema1 = new Tema(null, "a", 1, 1);
        assertThrows(ValidationException.class, () -> this.service.addTema(newTema1));
    }

}