package net.minecraft.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiScreenHorseInventory extends GuiContainer {
   private static final ResourceLocation horseGuiTextures;
   private IInventory playerInventory;
   private IInventory horseInventory;
   private EntityHorse horseEntity;
   private float mousePosx;
   private float mousePosY;

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(horseGuiTextures);
      int n3 = (this.width - this.xSize) / 2;
      int n4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(n3, n4, 0, 0, this.xSize, this.ySize);
      if (this.horseEntity.isChested()) {
         this.drawTexturedModalRect(n3 + 79, n4 + 17, 0, this.ySize, 90, 54);
      }

      if (this.horseEntity.canWearArmor()) {
         this.drawTexturedModalRect(n3 + 7, n4 + 35, 0, this.ySize + 54, 18, 18);
      }

      GuiInventory.drawEntityOnScreen(n3 + 51, n4 + 60, 17, (float)(n3 + 51) - this.mousePosx, (float)(n4 + 75 - 50) - this.mousePosY, this.horseEntity);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.mousePosx = (float)mouseX;
      this.mousePosY = (float)mouseY;
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      this.fontRendererObj.drawString(this.horseInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
      this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }

   public GuiScreenHorseInventory(IInventory inventory, IInventory inventory2, EntityHorse entityHorse) {
      super(new ContainerHorseInventory(inventory, inventory2, entityHorse, Minecraft.getMinecraft().thePlayer));
      this.playerInventory = inventory;
      this.horseInventory = inventory2;
      this.horseEntity = entityHorse;
      this.allowUserInput = false;
   }

   static {
      String str = "textures/gui/container/horse.png";
      horseGuiTextures = new ResourceLocation(str);
   }
}
