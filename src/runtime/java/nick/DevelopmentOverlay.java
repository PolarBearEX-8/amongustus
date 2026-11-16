package nick;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
/** Explicit, local development overrides; original payload remains the fallback. */
public final class DevelopmentOverlay {
    private DevelopmentOverlay() {}
    static int apply(Path path, Map<String, byte[]> cached) throws IOException {
        Map<String, byte[]> pending = new LinkedHashMap<String, byte[]>();
        long total = 0;
        try (JarFile jar = new JarFile(path.toFile())) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (entry.isDirectory() || !name.endsWith(".class")) continue;
                if (!name.matches("[A-Za-z0-9_$]+(/[A-Za-z0-9_$]+)*\\.class") ||
                    name.startsWith("java/") || name.startsWith("javax/") ||
                    name.startsWith("jdk/") || name.startsWith("sun/") || name.startsWith("nick/"))
                    throw new IOException("Disallowed override class: " + name);
                String binary = name.substring(0, name.length()-6).replace('/', '.');
                if (pending.containsKey(binary)) throw new IOException("Duplicate override: " + name);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try (InputStream in = jar.getInputStream(entry)) {
                    byte[] buffer = new byte[8192]; int n;
                    while ((n = in.read(buffer)) != -1) {
                        total += n;
                        if (out.size() + n > 16 * 1024 * 1024 || total > 128L * 1024 * 1024)
                            throw new IOException("Override size limit exceeded");
                        out.write(buffer, 0, n);
                    }
                }
                byte[] data = out.toByteArray();
                if (data.length < 8 || data[0] != (byte)0xca || data[1] != (byte)0xfe ||
                    data[2] != (byte)0xba || data[3] != (byte)0xbe)
                    throw new IOException("Invalid override class: " + name);
                pending.put(binary, data);
            }
        }
        cached.putAll(pending); // Commit only after validating the entire archive.
        return pending.size();
    }
    static void install(Map<String, byte[]> cached) {
        String configured = System.getProperty("augustus.overlay");
        if (configured == null || configured.trim().isEmpty()) return;
        Path path = Paths.get(configured).toAbsolutePath().normalize();
        try {
            int count = apply(path, cached);
            System.err.println("[development] Loaded " + count + " local overrides from " + path);
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot install development overrides: " + path, ex);
        }
    }
}
