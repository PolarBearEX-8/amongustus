package client.util;

import client.setting.CombatFactorProperty;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public enum AngleAccumulator {
    BASE("base", "b"),
    SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "),
    SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"),
    SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "),
    SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "),
    STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"),
    STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "),
    STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "),
    STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"),
    STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "),
    STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "),
    STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"),
    STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "),
    STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "),
    CROSS("cross", "cr", "# #", " # ", "# #"),
    STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "),
    TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"),
    TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "),
    TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # "),
    TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "),
    DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "),
    DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"),
    DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "),
    DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "),
    CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "),
    RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "),
    HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "),
    HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "),
    HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"),
    HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"),
    BORDER("border", "bo", "###", "# #", "###"),
    CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.block144)),
    CREEPER("creeper", "cre", new ItemStack(Items.skull, 1, 4)),
    GRADIENT("gradient", "gra", "# #", " # ", " # "),
    GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
    BRICKS("bricks", "bri", new ItemStack(Blocks.block100)),
    SKULL("skull", "sku", new ItemStack(Items.skull, 1, 1)),
    FLOWER("flower", "flo", new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.OXEYE_DAISY.getCount())),
    MOJANG("mojang", "moj", new ItemStack(Items.goldenApple, 1, 1));

   public String labelText;
   public String labelText2;
   public String[] stringArray = new String[3];
   public ItemStack itemStack;
   public static final AngleAccumulator[] ENUM_VALUES = new AngleAccumulator[]{
      BASE,
      SQUARE_BOTTOM_LEFT,
      SQUARE_BOTTOM_RIGHT,
      SQUARE_TOP_LEFT,
      SQUARE_TOP_RIGHT,
      STRIPE_BOTTOM,
      STRIPE_TOP,
      STRIPE_LEFT,
      STRIPE_RIGHT,
      STRIPE_CENTER,
      STRIPE_MIDDLE,
      STRIPE_DOWNRIGHT,
      STRIPE_DOWNLEFT,
      STRIPE_SMALL,
      CROSS,
      STRAIGHT_CROSS,
      TRIANGLE_BOTTOM,
      TRIANGLE_TOP,
      TRIANGLES_BOTTOM,
      TRIANGLES_TOP,
      DIAGONAL_LEFT,
      DIAGONAL_RIGHT,
      DIAGONAL_LEFT_MIRROR,
      DIAGONAL_RIGHT_MIRROR,
      CIRCLE_MIDDLE,
      RHOMBUS_MIDDLE,
      HALF_VERTICAL,
      HALF_HORIZONTAL,
      HALF_VERTICAL_MIRROR,
      HALF_HORIZONTAL_MIRROR,
      BORDER,
      CURLY_BORDER,
      CREEPER,
      GRADIENT,
      GRADIENT_UP,
      BRICKS,
      SKULL,
      FLOWER,
      MOJANG
   };

   public static AngleAccumulator resolveAngleAccumulator(String text) {
      for (AngleAccumulator angleAccumulator : values()) {
         if (angleAccumulator.labelText2.equals(text)) {
            return angleAccumulator;
         }
      }

      return null;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private AngleAccumulator(String text, String text2, ItemStack stack) {
      this(text, text2);
      this.itemStack = stack;
   }



   public boolean isActiveAsFlag() {
      return this.itemStack != null || this.stringArray[0] != null;
   }

   public boolean isActive() {
      return this.itemStack != null;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   private AngleAccumulator(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
   }

   private AngleAccumulator(String text, String text2, String text3, String text4, String text5) {
      this(text, text2);
      this.stringArray[0] = text3;
      this.stringArray[1] = text4;
      this.stringArray[2] = text5;
   }

   public String[] getStringArray() {
      return this.stringArray;
   }
}
