package model;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FilesTestIT {

    @Test
    public void it_should_read_and_write() {
        final String path = "src/test/resources/files_test.txt";
        final byte[] bytes = new byte[]{0x60, 0x20, 0x02, 0x2D, 0x45};

        try {
            Files.writeTo(path, bytes);
            final byte[] readContent = Files.getContentFrom(path);

            assertEquals(new String(bytes), new String(readContent));
        } catch (final Exception e) {
            fail(e.getMessage());
        } finally {
            final File file = new File(path);
            file.delete();
        }
    }

}
