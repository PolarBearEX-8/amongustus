package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.render.RenderPlayer;
import client.util.BuildManager;
import client.util.UsersManager;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;

public class CompositeItemEntry implements PreMotionDispatcher {
   public RenderPlayer renderPlayer = null;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityLivingBaseByEntityLivingBase(livingEntity, posZ, posX3);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor, float factor2) {
      if (BuildManager.isActive68() && livingEntity instanceof AbstractClientPlayer) {
         AbstractClientPlayer player = (AbstractClientPlayer)livingEntity;
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.applyState8();
         GlStateManager.enableLighting();
         UsersManager.applyFloatEntryRegistryByFloatEntryRegist(this.renderPlayer.getAuxiliaryFloatEntryBridge(), player, factor, factor2);
         GlStateManager.disableAlpha();
      }
   }

   public static void applyMap(Map map) {
      Set keys = map.keySet();
      boolean flag = false;

      for (Object element : keys) {
         Object entry = map.get(element);
         if (entry instanceof RenderPlayer) {
            RenderPlayer renderPlayer2 = (RenderPlayer)entry;
            renderPlayer2.add(new CompositeItemEntry(renderPlayer2));
            flag = true;
         }
      }

      if (!flag) {
         BuildManager.applyText3("PlayerItemsLayer not registered");
      }
   }

   public CompositeItemEntry(RenderPlayer renderPlayer2) {
      this.renderPlayer = renderPlayer2;
   }
}
