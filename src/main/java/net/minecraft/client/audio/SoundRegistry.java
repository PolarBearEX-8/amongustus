package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.RegistrySimple;

public class SoundRegistry extends RegistrySimple {
   private Map soundRegistry;

   public void clearMap() {
      this.soundRegistry.clear();
   }

   public void registerSound(SoundEventAccessorComposite composite) {
      this.putObject(composite.getSoundEventLocation(), composite);
   }

   @Override
   public Map createUnderlyingMap() {
      this.soundRegistry = Maps.newHashMap();
      return this.soundRegistry;
   }
}
