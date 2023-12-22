import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        File file = new File("src/main/resources/notebook.json");
        file.delete();
        System.setOut(new PrintStream(output));
    }

    @Test
    @Order(1)
    void testMain() throws IOException, ParseException, java.text.ParseException {
        Main.main(new String[]{"-add", "note1", "text hi"});
        Main.main(new String[]{"-add", "note2", "hello world"});

        Main.main(new String[]{"-show"});
        String[] actual = output.toString().
                replace("\r", "").split("\n");
        assertEquals(actual[0], "Title: note1");
        assertEquals(actual[1], "content: text hi");

        assertEquals(actual[4], "Title: note2");
        assertEquals(actual[5], "content: hello world");
        output.reset();

        Main.main(new String[]{"-rm", "note1"});
        Main.main(new String[]{"-rm", "note2"});

        Main.main(new String[]{"-show"});

        actual = output.toString().replace("\r", "").split("\n");
        output.reset();
        assertEquals(actual[0], "");
    }

    @Test
    @Order(2)
    void testSort() throws IOException, ParseException, java.text.ParseException {
        Main.main(new String[]{"-add", "note1", "text hi"});
        Main.main(new String[]{"-add", "note2", "hello world"});

        Main.main(new String[]{"-show", "22.12.2023 20:00", "31.12.2023 20:14", "note1"});
        String[] actual = output.toString().
                replace("\r", "").split("\n");
        assertEquals(actual[0], "Title: note1");
        assertEquals(actual[1], "content: text hi");
        output.reset();

        Main.main(new String[]{"-rm", "note1"});
        Main.main(new String[]{"-rm", "note2"});
    }
}