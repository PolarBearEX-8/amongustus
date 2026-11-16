package nick;

import java.io.*;
import java.util.*;
import java.util.jar.*;

/** Installs the overlay produced by this source build, before optional local overrides. */
public final class EmbeddedOverlay {
    private EmbeddedOverlay() {}

    static int apply(InputStream resource, Map<String, byte[]> cached) throws IOException {
        if (resource == null) throw new IOException("Embedded source overlay is missing");
        Map<String, byte[]> pending = new LinkedHashMap<String, byte[]>();
        long total = 0;
        try (JarInputStream in = new JarInputStream(resource)) {
            JarEntry entry;
            while ((entry = in.getNextJarEntry()) != null) {
                String name = entry.getName();
                if (entry.isDirectory() || !name.endsWith(".class")) continue;
                if (!name.matches("[A-Za-z0-9_$]+(/[A-Za-z0-9_$]+)*\\.class"))
                    throw new IOException("Disallowed embedded class: " + name);
                String binary = name.substring(0, name.length() - 6).replace('/', '.');
                if (pending.containsKey(binary)) throw new IOException("Duplicate embedded class: " + name);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[8192]; int n;
                while ((n = in.read(buffer)) != -1) {
                    total += n;
                    if (out.size() + n > 16 * 1024 * 1024 || total > 512L * 1024 * 1024)
                        throw new IOException("Embedded overlay size limit exceeded");
                    out.write(buffer, 0, n);
                }
                byte[] data = out.toByteArray();
                if (data.length < 8 || data[0] != (byte)0xca || data[1] != (byte)0xfe
                        || data[2] != (byte)0xba || data[3] != (byte)0xbe)
                    throw new IOException("Invalid embedded class: " + name);
                pending.put(binary, data);
            }
        }
        cached.putAll(pending);
        return pending.size();
    }

    static void install(Map<String, byte[]> cached) {
        try {
            int count = apply(EmbeddedOverlay.class.getResourceAsStream("/recovery/compiled-source-overlay.jar"), cached);
            System.err.println("[recovery] Installed " + count + " source-build overrides");
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot install embedded source overlay", ex);
        }
    }
}
