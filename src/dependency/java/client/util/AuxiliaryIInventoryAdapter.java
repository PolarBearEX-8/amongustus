package client.util;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.LockCode;

public class AuxiliaryIInventoryAdapter extends InventoryLargeChest implements IInventoryExtension {
   public String labelText2;
   public Map map = Maps.newHashMap();

   public AuxiliaryIInventoryAdapter(String text, IChatComponent iChatComponent, int count) {
      super(iChatComponent, count);
      this.labelText2 = text;
   }

   @Override
   public LockCode getLockCode() {
      return LockCode.EMPTY_CODE;
   }

   @Override
   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public void setLockCode(LockCode lockCode) {
   }

   @Override
   public int processAction(int count) {
      return this.map.containsKey(count) ? (Integer)this.map.get(count) : 0;
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int processAction3() {
      return this.map.size();
   }

   @Override
   public void processAction(int count, int count2) {
      this.map.put(count, count2);
   }

   @Override
   public boolean isActive16() {
      return false;
   }
}
