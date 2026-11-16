package cc.baritone.utils;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;

public interface Listener12Extension extends SpecialListener {
   String resolveTextByText(String text);

   int computeCount(String text);

   Multimap getMultimap();

   String resolveText(Entity entity);
}
