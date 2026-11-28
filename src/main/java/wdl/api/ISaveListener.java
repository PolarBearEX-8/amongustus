package wdl.api;

import java.io.File;

public interface ISaveListener extends IWDLMod {
   void afterChunksSaved(File file);
}
