package cc.baritone.utils;

import java.io.IOException;
import java.io.Writer;

public interface WriterCallback {
   void applyWriter(Writer writer) throws IOException;
}
