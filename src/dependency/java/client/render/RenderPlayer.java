package client.render;

import client.ClientGlobals;
import client.module.Module;
import client.setting.ItemNoneType;
import client.setting.TabListCapeType;
import client.util.AuxiliaryFloatEntryBridge;
import client.util.ComparatorEntry;
import client.util.GeometryFilter;
import client.util.ListenerAdapter5;
import client.util.PassiveFloatEntry;
import client.util.ScoreboardData;
import client.util.SlotManager;
import client.util.TertiaryListenerBridge;
import client.util.ThePlayerNameManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.DiamondIngot;
import net.minecraft.item.GoldenChestplate;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderPlayer extends RendererLivingEntity {
   public boolean enabled5;

   public void applyAbstractClientPlayer(AbstractClientPlayer player, double posX, double posY, double posZ, String text, float factor, double offset) {
      double offset2 = posY;
      if (offset < 100.0) {
         ThePlayerNameManager thePlayerNameManager = player.getThePlayerNameManager();
         ScoreboardData customItemEntry = thePlayerNameManager.getCustomItemEntry(2);
         if (customItemEntry != null) {
            ComparatorEntry comparatorEntry = thePlayerNameManager.getComparatorEntry(player.getText(), customItemEntry);
            this.applyEntity(player, comparatorEntry.getIndex() + " " + customItemEntry.getLabelText(), posX, posY, posZ, 64);
            offset2 = posY + (double)((float)this.getFontRendererAsFontRenderer().indexOffset * 1.15F * factor);
         }
      }

      super.applyEntity(player, posX, offset2, posZ, text, factor, offset);
   }

   public ResourceLocation resolveResourceLocation(AbstractClientPlayer player) {
      return player.getResourceLocation2();
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      this.applyAbstractClientPlayerByAbstractClientPl((AbstractClientPlayer)livingEntity, posX, posY, posZ);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return this.getAuxiliaryFloatEntryBridge();
   }

   public void applyAbstractClientPlayer(AbstractClientPlayer player) {
      float factor = 1.0F;
      GlStateManager.translate3f2(factor, factor, factor);
      AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge = this.getAuxiliaryFloatEntryBridge();
      this.applyAbstractClientPlayer4(player);
      auxiliaryFloatEntryBridge.factor = 0.0F;
      auxiliaryFloatEntryBridge.enabled = auxiliaryFloatEntryBridge.enabled5 = false;
      auxiliaryFloatEntryBridge.applyEntity(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      auxiliaryFloatEntryBridge.updateState();
   }

   public void applyAbstractClientPlayerByAbstractClientPl(AbstractClientPlayer player, float posX, float posY, float posZ) {
      if (player.canExecute() && player.isState5()) {
         GlStateManager.rotate(player.getFactor2(), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(this.computeFactor(player), 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else {
         super.applyEntityLivingBase(player, posX, posY, posZ);
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyAbstractClientPlayer((AbstractClientPlayer)livingEntity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, String text, float factor, double offset) {
      this.applyAbstractClientPlayer((AbstractClientPlayer)entity, posX, posY, posZ, text, factor, offset);
   }

   public void applyAbstractClientPlayer2(AbstractClientPlayer player) {
      float factor = 1.0F;
      GlStateManager.translate3f2(factor, factor, factor);
      AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge = this.getAuxiliaryFloatEntryBridge();
      this.applyAbstractClientPlayer4(player);
      auxiliaryFloatEntryBridge.enabled5 = false;
      auxiliaryFloatEntryBridge.factor = 0.0F;
      auxiliaryFloatEntryBridge.applyEntity(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      auxiliaryFloatEntryBridge.updateState2();
   }

   public void applyAbstractClientPlayer(AbstractClientPlayer player, float factor) {
      float factor2 = 0.9375F;
      GlStateManager.translate3f3(factor2, factor2, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyAbstractClientPlayer((AbstractClientPlayer)livingEntity, factor);
   }

   public void applyAbstractClientPlayer(AbstractClientPlayer player, double posX, double posY, double posZ, float factor, float factor2) {
      if (!player.isActive24() || this.renderManager.entity == player) {
         if (((Module)ClientGlobals.moduleManager.brightPlayers).isToggled()) {
            GlStateManager.applyState3();
         }

         double offset = posY;
         if (player.isSprinting() && !(player instanceof EntityPlayerSP)) {
            offset = posY - 0.125;
         }

         this.applyAbstractClientPlayer4(player);
         super.applyEntityLivingBase(player, posX, offset, posZ, factor, factor2);
      }
   }

   private void applyAbstractClientPlayer4(AbstractClientPlayer player) {
      AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge = this.getAuxiliaryFloatEntryBridge();
      if (player.isActive23()) {
         auxiliaryFloatEntryBridge.setEnabled(false);
         auxiliaryFloatEntryBridge.boxesManager3.enabled3 = true;
         auxiliaryFloatEntryBridge.boxesManager4.enabled3 = true;
      } else {
         ItemStack stack = player instanceof EntityPlayerSP ? SlotManager.itemStack : player.inventoryPlayer.getCurrentItem();
         auxiliaryFloatEntryBridge.setEnabled(true);
         auxiliaryFloatEntryBridge.boxesManager4.enabled3 = player.checkTabListCapeType(TabListCapeType.HAT);
         auxiliaryFloatEntryBridge.boxesManager11.enabled3 = player.checkTabListCapeType(TabListCapeType.JACKET);
         auxiliaryFloatEntryBridge.boxesManager12.enabled3 = player.checkTabListCapeType(TabListCapeType.LEFT_PANTS_LEG);
         auxiliaryFloatEntryBridge.boxesManager10.enabled3 = player.checkTabListCapeType(TabListCapeType.RIGHT_PANTS_LEG);
         auxiliaryFloatEntryBridge.boxesManager14.enabled3 = player.checkTabListCapeType(TabListCapeType.LEFT_SLEEVE);
         auxiliaryFloatEntryBridge.boxesManager13.enabled3 = player.checkTabListCapeType(TabListCapeType.RIGHT_SLEEVE);
         auxiliaryFloatEntryBridge.count6 = 0;
         auxiliaryFloatEntryBridge.enabled6 = false;
         auxiliaryFloatEntryBridge.enabled5 = player.isSprinting();
         if (stack == null) {
            auxiliaryFloatEntryBridge.count5 = 0;
         } else {
            auxiliaryFloatEntryBridge.count5 = 1;
            if (player.getEntityId2() > 0) {
               ItemNoneType itemNoneType = stack.processAction4();
               if (itemNoneType == ItemNoneType.BLOCK) {
                  auxiliaryFloatEntryBridge.count5 = 3;
               } else if (itemNoneType == ItemNoneType.BOW) {
                  auxiliaryFloatEntryBridge.enabled6 = true;
               }
            }
         }
      }
   }

   @Override
   public void updateState() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   public void applyAbstractClientPlayerByAbstractClientPl(AbstractClientPlayer player, double posX, double posY, double posZ) {
      if (player.canExecute() && player.isState5()) {
         super.applyEntityLivingBase(player, posX + (double)player.factor38, posY + (double)player.factor31, posZ + (double)player.factor36);
      } else {
         super.applyEntityLivingBase(player, posX, posY, posZ);
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.applyAbstractClientPlayerByAbstractClientPl((AbstractClientPlayer)livingEntity, posX, posY, posZ);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyAbstractClientPlayer((AbstractClientPlayer)entity, posX, posY, posZ, factor, factor2);
   }

   public AuxiliaryFloatEntryBridge getAuxiliaryFloatEntryBridge() {
      return (AuxiliaryFloatEntryBridge)super.getPassiveFloatEntry();
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((AbstractClientPlayer)entity);
   }

   public RenderPlayer(RenderManager renderManager, boolean flag) {
      super(renderManager, new AuxiliaryFloatEntryBridge(0.0F, flag), 0.5F);
      this.enabled5 = flag;
      this.add(new GeometryFilter(this));
      this.add(new TertiaryListenerBridge(this));
      this.add(new RendererLivingEntityEntry(this));
      this.add(new DiamondIngot(this));
      this.add(new GoldenChestplate(this));
      this.add(new ListenerAdapter5(this.getAuxiliaryFloatEntryBridge().boxesManager3));
   }

   public RenderPlayer(RenderManager renderManager) {
      this(renderManager, false);
   }
}
