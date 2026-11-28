package wdl.api;

import net.minecraft.entity.Entity;

public interface IEntityEditor extends IWDLMod {
   void editEntity(Entity entity);

   boolean shouldEdit(Entity entity);
}
