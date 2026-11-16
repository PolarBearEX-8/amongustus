package cc.baritone.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface HeuristicStrategy {
   AStarRegistry resolveAStarRegistry(InputStream inputStream) throws IOException;

   boolean matchesFile(File file);
}
