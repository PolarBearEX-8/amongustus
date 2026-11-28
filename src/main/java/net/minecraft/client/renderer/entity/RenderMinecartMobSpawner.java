package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;

public class RenderMinecartMobSpawner extends RenderMinecart {
   public void renderCartContents2(EntityMinecartMobSpawner entityMinecartMobSpawner, float f, IBlockState state) {
      super.renderCartContents(entityMinecartMobSpawner, f, state);
      if (state.getBlock() == Blocks.mob_spawner) {
         TileEntityMobSpawnerRenderer.renderMob(entityMinecartMobSpawner.getMobSpawnerLogic(), entityMinecartMobSpawner.posX, entityMinecartMobSpawner.posY, entityMinecartMobSpawner.posZ, f);
      }
   }

   public RenderMinecartMobSpawner(RenderManager renderManager2) {
      super(renderManager2);
   }

   @Override
   public void renderCartContents(EntityMinecart entityMinecart, float f, IBlockState state) {
      this.renderCartContents2((EntityMinecartMobSpawner)entityMinecart, f, state);
   }
}
