package net.minecraft.client.gui.inventory;

import client.util.CustomContainerAdapter;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiChestContainer extends GuiContainer {
   public double[] coordOffsetArray = null;
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/container/crafting_table.png");

   @Override
   public void updateState2() {
      super.updateState2();
      ScaledResolution scaledResolution = new ScaledResolution(net.minecraft.client.Minecraft.getMinecraft());
      this.coordOffsetArray = new double[]{(double)((float)scaledResolution.getScaledWidth() / 2.0F), (double)((float)scaledResolution.getScaledHeight() / 2.0F)};
   }

   @Override
   public void applyIndex(int count, int count2) {
      this.fontRenderer.getIndex(StatCollector.getText("container.crafting", new Object[0]), 28, 6, 4210752);
      this.fontRenderer.getIndex(StatCollector.getText("container.inventory", new Object[0]), 8, this.count10 - 96 + 2, 4210752);
   }

   public GuiChestContainer(InventoryPlayer inventoryPlayer, World world, BlockPos blockPos) {
      super(new CustomContainerAdapter(inventoryPlayer, world, blockPos));
   }

   public GuiChestContainer(InventoryPlayer inventoryPlayer, World world) {
      this(inventoryPlayer, world, BlockPos.origin);
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
   }
}
