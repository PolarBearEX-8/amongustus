package net.augustus.render;

import net.augustus.render.esp.AbstractEspBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEspBounds extends AbstractEspBox {
   private final TileEntity tileEntity;

   public static TileEntityEspBounds of(TileEntity tileEntity2) {
      return new TileEntityEspBounds(tileEntity2);
   }

   private TileEntityEspBounds(TileEntity tileEntity2) {
      this.tileEntity = tileEntity2;
   }

   @Override
   public void updateBounds(float f) {
      this.x = (float)((double)this.tileEntity.getPos().getX() - mc.getRenderManager().viewerPosX);
      this.y = (float)((double)this.tileEntity.getPos().getY() - mc.getRenderManager().viewerPosY);
      this.z = (float)((double)this.tileEntity.getPos().getZ() - mc.getRenderManager().viewerPosZ);
      this.halfWidth = 0.5F;
      this.height = 1.0F;
      this.widthExpand = 0.0F;
      this.topExpand = 0.0F;
   }
}
