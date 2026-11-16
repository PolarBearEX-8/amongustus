package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class SplineSynchronizer extends GuiContainer {
   public static final ResourceLocation resourceLocation5;
   public IInventory iInventory;
   public IInventory iInventory2;
   public EntityHorse entityHorse;
   public float factor4;
   public float factor5;

   @Override
   public void applyIndex(int count, int count2) {
      this.fontRenderer.getIndex(this.iInventory2.getChatComponent().getString(), 8, 6, 4210752);
      this.fontRenderer.getIndex(this.iInventory.getChatComponent().getString(), 8, this.count10 - 96 + 2, 4210752);
   }

   public SplineSynchronizer(IInventory iInventory3, IInventory iInventory4, EntityHorse entityHorse2) {
      super(new TertiaryContainerAdapter(iInventory3, iInventory4, entityHorse2, Minecraft.getMinecraft().thePlayer));
      this.iInventory = iInventory3;
      this.iInventory2 = iInventory4;
      this.entityHorse = entityHorse2;
      this.enabled = false;
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
      if (this.entityHorse.isActive32()) {
         this.processAction(count3 + 79, count4 + 17, 0, this.count10, 90, 54);
      }

      if (this.entityHorse.isActive44()) {
         this.processAction(count3 + 7, count4 + 35, 0, this.count10 + 54, 18, 18);
      }

      GuiInventory.applyEntityLivingBaseByEntityLivingBase(
         count3 + 51, count4 + 60, 17, (float)(count3 + 51) - this.factor4, (float)(count4 + 75 - 50) - this.factor5, this.entityHorse
      );
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.factor4 = (float)count;
      this.factor5 = (float)count2;
      super.applyIndex(count, count2, factor);
   }

   static {
      String text = "textures/gui/container/horse.png";
      resourceLocation5 = new ResourceLocation(text);
   }
}
