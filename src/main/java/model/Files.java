package model;

import java.io.*;

final class Files {

    /// Should not be instantiated
    private Files() {}

    static String getContentFrom(final String path) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(path));
        final String content = reader.lines()
                .reduce((file, line) -> file + line)
                .orElse("");
        reader.close();
        return content;
    }

    static void writeTo(final String path, final String content) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.close();
    }
}
