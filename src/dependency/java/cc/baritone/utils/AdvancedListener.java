package cc.baritone.utils;

import java.io.File;
import java.util.Optional;

public interface AdvancedListener {
   Optional resolveOptional(File file);

   DequeEntry getDequeEntry();
}
