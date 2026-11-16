package net.minecraft.client.resources.model;

import client.util.ServiceStringEntry;
import java.util.List;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.EnumFacing;

public interface IBakedModel {
   SpecialItemEntry getSpecialItemEntry();

   boolean isActive();

   List getList();

   boolean isEnabled();

   boolean canExecute();

   ServiceStringEntry getServiceStringEntry();

   List resolveList(EnumFacing enumFacing);
}
