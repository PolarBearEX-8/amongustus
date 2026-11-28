package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiCrafting extends GuiContainer {
   public double[] centerPos = null;
   private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, 4210752);
      this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
   }

   public GuiCrafting(InventoryPlayer inventoryPlayer, World world, BlockPos pos) {
      super(new ContainerWorkbench(inventoryPlayer, world, pos));
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
      this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
   }

   public GuiCrafting(InventoryPlayer inventoryPlayer, World world) {
      this(inventoryPlayer, world, BlockPos.ORIGIN);
   }

   @Override
   public void initGui() {
      super.initGui();
      ScaledResolution sr = new ScaledResolution(this.mc);
      this.centerPos = new double[]{(double)((float)sr.getScaledWidth() / 2.0F), (double)((float)sr.getScaledHeight() / 2.0F)};
   }
}
