package client.setting;

import net.minecraft.util.ResourceLocation;

public enum BaseOptionType {
    MENU(new ResourceLocation("minecraft:music.menu"), 20, 600),
    GAME(new ResourceLocation("minecraft:music.game"), 12000, 24000),
    CREATIVE(new ResourceLocation("minecraft:music.game.creative"), 1200, 3600),
    CREDITS(new ResourceLocation("minecraft:music.game.end.credits"), Integer.MAX_VALUE, Integer.MAX_VALUE),
    NETHER(new ResourceLocation("minecraft:music.game.nether"), 1200, 3600),
    END_BOSS(new ResourceLocation("minecraft:music.game.end.dragon"), 0, 0),
    END(new ResourceLocation("minecraft:music.game.end"), 6000, 24000);

   public final ResourceLocation resourceLocation;
   public final int count2;
   public final int count;
   public static final BaseOptionType[] ENUM_VALUES = new BaseOptionType[]{MENU, GAME, CREATIVE, CREDITS, NETHER, END_BOSS, END};

   public int getCount() {
      return this.count;
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public int getCount2() {
      return this.count2;
   }

   private BaseOptionType(ResourceLocation location, int count3, int count4) {
      this.resourceLocation = location;
      this.count2 = count3;
      this.count = count4;
   }


}
