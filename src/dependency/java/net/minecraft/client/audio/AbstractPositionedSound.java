package net.minecraft.client.audio;

import client.util.ISoundExtension;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractPositionedSound extends PositionedSound implements ISoundExtension {
   public boolean enabled = false;

   public AbstractPositionedSound(ResourceLocation location) {
      super(location);
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }
}
