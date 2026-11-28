package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ItemPosListener;
import net.augustus.events.EventItemPos;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;

public class CustomItemPos extends Module implements ItemPosListener {
   public final DoubleValue x;
   public final DoubleValue y;
   public final DoubleValue z;
   public final DoubleValue blockX;
   public final DoubleValue blockY;
   public final DoubleValue blockZ;
   public final DoubleValue scale;
   public final BooleanValue leftHand;

   public CustomItemPos() {
       super("CustomItemPos", "KundenspeziefischeObjektPos", new Color(42, 145, 95), Categorys.RENDER);
      this.x = new DoubleValue(this, "X", 0.13, -1.0, 1.0, 2);
      this.y = new DoubleValue(this, "Y", 0.13, -1.0, 1.0, 2);
      this.z = new DoubleValue(this, "Z", 0.04, -1.0, 1.0, 2);
      this.blockX = new DoubleValue(this, "BlockX", -0.27, -1.0, 1.0, 2);
      this.blockY = new DoubleValue(this, "BlockY", -0.04, -1.0, 1.0, 2);
      this.blockZ = new DoubleValue(this, "BlockZ", 0.0, -1.0, 1.0, 2);
      this.scale = new DoubleValue(this, "Scale", 0.2, 0.0, 2.0, 2);
      this.leftHand = new BooleanValue(this, "LeftHand", false);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(13);
   }

   public void onItemPos(EventItemPos event) {
      ItemStack currentItem = mc.thePlayer.inventory.getCurrentItem();
      if (currentItem == null || !(currentItem.getItem() instanceof ItemMapBase)) {
         event.setPosX(this.x.getValue());
         event.setPosY(this.y.getValue());
         event.setPosZ(this.z.getValue());
         event.setBlockX(this.blockX.getValue());
         event.setBlockY(this.blockY.getValue());
         event.setBlockZ(this.blockZ.getValue());
         event.setScale(this.scale.getValue());
      }
   }

}
