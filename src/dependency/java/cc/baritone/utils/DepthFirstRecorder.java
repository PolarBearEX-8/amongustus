package cc.baritone.utils;

import java.io.File;

public interface DepthFirstRecorder extends SpecialListener {
   void applyFile(File file);
}
