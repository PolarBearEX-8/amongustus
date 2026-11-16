package client.setting;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum GlobalOptionType {
    WOOD(0, 59, 2.0F, 0.0F, 15),
    STONE(1, 131, 4.0F, 1.0F, 5),
    IRON(2, 250, 6.0F, 2.0F, 14),
    DIAMOND(3, 1561, 8.0F, 3.0F, 10),
    GOLD(0, 32, 12.0F, 0.0F, 22);

   public final int count;
   public final int count3;
   public final float factor;
   public final float factor2;
   public final int count2;
   public static final GlobalOptionType[] ENUM_VALUES = new GlobalOptionType[]{WOOD, STONE, IRON, DIAMOND, GOLD};

   public int getCount3() {
      return this.count3;
   }

   public Item getItem() {
      return this == WOOD
         ? Item.processAction2(Blocks.block142)
         : (
            this == STONE
               ? Item.processAction2(Blocks.block65)
               : (this == GOLD ? Items.goldIngot : (this == IRON ? Items.ironIngot : (this == DIAMOND ? Items.diamond : null)))
         );
   }

   public int getCount() {
      return this.count;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public int getCount2() {
      return this.count2;
   }

   public float getFactor() {
      return this.factor;
   }

   private GlobalOptionType(int count4, int count5, float factor3, float factor4, int count6) {
      this.count = count4;
      this.count3 = count5;
      this.factor = factor3;
      this.factor2 = factor4;
      this.count2 = count6;
   }


}
