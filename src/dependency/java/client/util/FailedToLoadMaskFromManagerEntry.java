package client.util;

import java.util.HashMap;

public abstract class FailedToLoadMaskFromManagerEntry {
   public FailedToLoadMaskFromManager failedToLoadMaskFromManager = MoreCosmeticsManager.getMoreCosmeticsManager().getFailedToLoadMaskFromManager();
   public HashMap hashMap = new HashMap();

   public abstract void updateState();
}
