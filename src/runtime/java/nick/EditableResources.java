package nick;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** Resource overrides explicitly listed by this source build. */
public final class EditableResources {
    private static final String INDEX = "recovery/editable-resource-index.txt";
    private static final Set<String> NAMES = readIndex();
    private EditableResources() {}

    private static Set<String> readIndex() {
        InputStream input = EditableResources.class.getClassLoader().getResourceAsStream(INDEX);
        if (input == null) return Collections.emptySet();
        SortedSet<String> names = new TreeSet<String>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String name;
            while ((name = reader.readLine()) != null) {
                if (name.isEmpty()) continue;
                if (!name.startsWith("assets/") || name.indexOf('\\') >= 0 || name.indexOf(':') >= 0 || name.indexOf('\0') >= 0)
                    throw new IOException("Invalid editable resource path");
                for (String part : name.split("/", -1))
                    if (part.isEmpty() || part.equals(".") || part.equals("..")) throw new IOException("Invalid editable resource segment");
                if (!names.add(name) || names.size() > 100000) throw new IOException("Invalid editable resource index");
            }
        } catch (IOException error) { throw new IllegalStateException("Cannot read editable resource index", error); }
        return Collections.unmodifiableSet(names);
    }

    public static void mergeInto(Map<String, byte[]> resources) throws IOException {
        for (String name : NAMES) {
            InputStream input = EditableResources.class.getClassLoader().getResourceAsStream(name);
            if (input == null) throw new FileNotFoundException("Missing indexed resource: " + name);
            try (InputStream stream = input; ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[8192]; int count;
                while ((count = stream.read(buffer)) != -1) bytes.write(buffer, 0, count);
                resources.put(name, bytes.toByteArray());
            }
        }
        if (!NAMES.isEmpty()) System.err.println("[recovery] Installed " + NAMES.size() + " editable resource overrides");
    }

    public static URL find(String name) {
        if (!NAMES.contains(name)) return null;
        URL resource = EditableResources.class.getClassLoader().getResource(name);
        if (resource == null) throw new IllegalStateException("Missing indexed resource: " + name);
        return resource;
    }
}
