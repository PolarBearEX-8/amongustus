package cc.baritone.api;

import java.nio.file.Path;

public interface InternalINACTIVEHandlerExtension extends INACTIVEHandler {
   void applyPath(Path path, boolean flag) throws Exception;

   void applyIndex(int count, int count2);
}
