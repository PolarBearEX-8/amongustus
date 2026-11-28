package wdl.api;

import java.util.List;

public interface IEntityAdder extends IWDLMod {
   int getDefaultEntityTrackDistance(String str);

   String getEntityCategory(String str);

   List getModEntities();
}
