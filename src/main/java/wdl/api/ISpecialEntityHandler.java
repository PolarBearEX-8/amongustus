package wdl.api;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;

public interface ISpecialEntityHandler extends IWDLMod {
   String getSpecialEntityCategory(String str);

   String getSpecialEntityName(Entity entity);

   Multimap getSpecialEntities();

   int getSpecialEntityTrackDistance(String str);
}
