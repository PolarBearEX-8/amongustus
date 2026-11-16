package client.setting;

import client.event.PrimaryListener;

public enum AltOptionType implements PrimaryListener {
    EMPTY("empty"),
    POPPY("rose"),
    BLUE_ORCHID("blue_orchid"),
    ALLIUM("allium"),
    HOUSTONIA("houstonia"),
    RED_TULIP("red_tulip"),
    ORANGE_TULIP("orange_tulip"),
    WHITE_TULIP("white_tulip"),
    PINK_TULIP("pink_tulip"),
    OXEYE_DAISY("oxeye_daisy"),
    DANDELION("dandelion"),
    OAK_SAPLING("oak_sapling"),
    SPRUCE_SAPLING("spruce_sapling"),
    BIRCH_SAPLING("birch_sapling"),
    JUNGLE_SAPLING("jungle_sapling"),
    ACACIA_SAPLING("acacia_sapling"),
    DARK_OAK_SAPLING("dark_oak_sapling"),
    MUSHROOM_RED("mushroom_red"),
    MUSHROOM_BROWN("mushroom_brown"),
    DEAD_BUSH("dead_bush"),
    FERN("fern"),
    CACTUS("cactus");

   public final String labelText;
   public static final AltOptionType[] ENUM_VALUES = new AltOptionType[]{
      EMPTY,
      POPPY,
      BLUE_ORCHID,
      ALLIUM,
      HOUSTONIA,
      RED_TULIP,
      ORANGE_TULIP,
      WHITE_TULIP,
      PINK_TULIP,
      OXEYE_DAISY,
      DANDELION,
      OAK_SAPLING,
      SPRUCE_SAPLING,
      BIRCH_SAPLING,
      JUNGLE_SAPLING,
      ACACIA_SAPLING,
      DARK_OAK_SAPLING,
      MUSHROOM_RED,
      MUSHROOM_BROWN,
      DEAD_BUSH,
      FERN,
      CACTUS
   };

   @Override
   public String getText() {
      return this.labelText;
   }

   private AltOptionType(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
