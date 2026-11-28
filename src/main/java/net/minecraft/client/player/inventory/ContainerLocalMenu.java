package net.minecraft.client.player.inventory;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class ContainerLocalMenu extends InventoryBasic implements ILockableContainer {
   private String guiID;
   private Map fieldValues = Maps.newHashMap();

   @Override
   public void setLockCode(LockCode lockCode) {
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int getFieldCount() {
      return this.fieldValues.size();
   }

   @Override
   public void setField(int n, int n2) {
      this.fieldValues.put(n, n2);
   }

   public ContainerLocalMenu(String str, IChatComponent component, int n) {
      super(component, n);
      this.guiID = str;
   }

   @Override
   public LockCode getLockCode() {
      return LockCode.EMPTY_CODE;
   }

   @Override
   public String getGuiID() {
      return this.guiID;
   }

   @Override
   public boolean isLocked() {
      return false;
   }

   @Override
   public int getField(int n) {
      return this.fieldValues.containsKey(n) ? (Integer)this.fieldValues.get(n) : 0;
   }
}
