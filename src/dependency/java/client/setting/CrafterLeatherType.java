package client.setting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.Items;

public enum CrafterLeatherType {
    LEATHER("leather", 5, new int[]{1, 3, 2, 1}, 15),
    CHAIN("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
    IRON("iron", 15, new int[]{2, 6, 5, 2}, 9),
    GOLD("gold", 7, new int[]{2, 5, 3, 1}, 25),
    DIAMOND("diamond", 33, new int[]{3, 8, 6, 3}, 10);

   public final String labelText;
   public final int count2;
   public final int[] countArray;
   public final int count;
   public static final CrafterLeatherType[] ENUM_VALUES = new CrafterLeatherType[]{LEATHER, CHAIN, IRON, GOLD, DIAMOND};

   public String getLabelText() {
      return this.labelText;
   }

   public Item getItem() {
      return this == LEATHER
         ? Items.leather
         : (this == CHAIN ? Items.ironIngot : (this == GOLD ? Items.goldIngot : (this == IRON ? Items.ironIngot : (this == DIAMOND ? Items.diamond : null))));
   }

   public int computeCount(int count3) {
      return this.countArray[count3];
   }

   public int computeCountByIndex(int count3) {
      return ItemGoldenBoots.getCountArray()[count3] * this.count2;
   }

   public int getCount() {
      return this.count;
   }

   private CrafterLeatherType(String text, int count3, int[] countArray2, int count4) {
      this.labelText = text;
      this.count2 = count3;
      this.countArray = countArray2;
      this.count = count4;
   }


}
