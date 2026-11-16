package client.event;

import client.util.VectorFormatter;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface PreRenderProcessor {
   boolean matchesVectorFormatter(VectorFormatter vectorFormatter, DamageSource damageSource, float factor);

   World getWorld2();
}
