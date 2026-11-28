package net.minecraft.entity.boss;

import net.minecraft.util.IChatComponent;

public interface IBossDisplayData {
   IChatComponent getBossDisplayName();

   float getHealth();

   float getBossMaxHealth();
}
