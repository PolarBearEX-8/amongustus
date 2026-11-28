package net.minecraft.entity.player;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public enum EnumPlayerModelParts {
   CAPE(0, "cape"),
   JACKET(1, "jacket"),
   LEFT_SLEEVE(2, "left_sleeve"),
   RIGHT_SLEEVE(3, "right_sleeve"),
   LEFT_PANTS_LEG(4, "left_pants_leg"),
   RIGHT_PANTS_LEG(5, "right_pants_leg"),
   HAT(6, "hat");

private final int partId;
   private final int partMask;
   private final String partName;
   private final IChatComponent name;

   public int getPartMask() {
      return this.partMask;
   }

   public IChatComponent getName() {
      return this.name;
   }

   private EnumPlayerModelParts(int n, String str) {
      this.partId = n;
      this.partMask = 1 << n;
      this.partName = str;
      this.name = new ChatComponentTranslation("options.modelPart." + str, new Object[0]);
   }

   public String getPartName() {
      return this.partName;
   }

   public int getPartId() {
      return this.partId;
   }
}
