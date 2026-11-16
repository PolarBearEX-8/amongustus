package client.setting;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum CrafterIronType {
    IRON(GlobalOptionType.IRON, CrafterLeatherType.IRON, Items.ironIngot),
    DIAMOND(GlobalOptionType.DIAMOND, CrafterLeatherType.DIAMOND, Items.diamond),
    STICKS(null, null, Items.stick),
    PLANKS(null, null, Item.processAction(5)),
    AIR(null, null, null);

   public GlobalOptionType globalOptionType;
   public CrafterLeatherType crafterLeatherType;
   public Item item;
   public static final CrafterIronType[] ENUM_VALUES = new CrafterIronType[]{IRON, DIAMOND, STICKS, PLANKS, AIR};

   private CrafterIronType(GlobalOptionType globalOptionType2, CrafterLeatherType crafterLeatherType2, Item item2) {
      this.globalOptionType = globalOptionType2;
      this.crafterLeatherType = crafterLeatherType2;
      this.item = item2;
   }


}
