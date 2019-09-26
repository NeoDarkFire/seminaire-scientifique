package model;

import java.io.*;
import java.nio.file.Paths;

final class Files {

    /// Should not be instantiated
    private Files() {}

    static byte[] getContentFrom(final String path) throws IOException {
        return java.nio.file.Files.readAllBytes(Paths.get(path).toAbsolutePath());
    }

    static void writeTo(final String path, final byte[] content) throws IOException {
        java.nio.file.Files.write(Paths.get(path).toAbsolutePath(), content);
    }
}
