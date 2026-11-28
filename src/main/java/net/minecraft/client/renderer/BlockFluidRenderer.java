package net.minecraft.client.renderer;

import net.augustus.modules.render.XRay;
import net.augustus.modules.render.helper.XRayRenderMode;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.optifine.CustomColors;
import net.optifine.config.Config;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;

public class BlockFluidRenderer {
   private TextureAtlasSprite[] atlasSpritesLava = new TextureAtlasSprite[2];
   private TextureAtlasSprite[] atlasSpritesWater = new TextureAtlasSprite[2];

   public void initAtlasSprites() {
      TextureMap textureMapBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
      this.atlasSpritesLava[0] = textureMapBlocks.getAtlasSprite("minecraft:blocks/lava_still");
      this.atlasSpritesLava[1] = textureMapBlocks.getAtlasSprite("minecraft:blocks/lava_flow");
      this.atlasSpritesWater[0] = textureMapBlocks.getAtlasSprite("minecraft:blocks/water_still");
      this.atlasSpritesWater[1] = textureMapBlocks.getAtlasSprite("minecraft:blocks/water_flow");
   }

   public boolean renderFluid(IBlockAccess world, IBlockState state, BlockPos pos, WorldRenderer worldRenderer) {
      if (XRay.enabled && XRay.getBlockRenderMode(state.getBlock(), state, pos) == XRayRenderMode.HIDE) {
         return false;
      } else {
         try {
            if (Config.isShaderPackLoaded()) {
               SVertexBuilder.pushEntity(state, pos, world, worldRenderer);
            }

            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            BlockLiquid block = (BlockLiquid)state.getBlock();
            block.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
            TextureAtlasSprite[] textureAtlasSprites = block.getMaterial() == Material.lava ? this.atlasSpritesLava : this.atlasSpritesWater;
            RenderEnv renderEnv = worldRenderer.getRenderEnv(state, pos);
            int fluidColor = CustomColors.getFluidColor(world, state, pos, renderEnv);
            float f = (float)(fluidColor >> 16 & 0xFF) / 255.0F;
            float f2 = (float)(fluidColor >> 8 & 0xFF) / 255.0F;
            float f3 = (float)(fluidColor & 0xFF) / 255.0F;
            boolean flag = block.shouldSideBeRendered(world, x, y + 1, z, EnumFacing.UP);
            boolean flag2 = block.shouldSideBeRendered(world, x, y - 1, z, EnumFacing.DOWN);
            boolean[] borderFlags = renderEnv.getBorderFlags();
            borderFlags[0] = block.shouldSideBeRendered(world, x, y, z - 1, EnumFacing.NORTH);
            borderFlags[1] = block.shouldSideBeRendered(world, x, y, z + 1, EnumFacing.SOUTH);
            borderFlags[2] = block.shouldSideBeRendered(world, x - 1, y, z, EnumFacing.WEST);
            borderFlags[3] = block.shouldSideBeRendered(world, x + 1, y, z, EnumFacing.EAST);
            if (flag || flag2 || borderFlags[0] || borderFlags[1] || borderFlags[2] || borderFlags[3]) {
               boolean flag3 = false;
               float f4 = 0.5F;
               float f5 = 1.0F;
               float f6 = 0.8F;
               float f7 = 0.6F;
               Material material = block.getMaterial();
               float fluidHeight = this.getFluidHeight(world, x, y, z, material);
               float fluidHeight2 = this.getFluidHeight(world, x, y, z + 1, material);
               float fluidHeight3 = this.getFluidHeight(world, x + 1, y, z + 1, material);
               float fluidHeight4 = this.getFluidHeight(world, x + 1, y, z, material);
               double x2 = (double)pos.getX();
               double y2 = (double)pos.getY();
               double z2 = (double)pos.getZ();
               float f8 = 0.001F;
               if (flag) {
                  flag3 = true;
                  TextureAtlasSprite textureAtlasSprite = textureAtlasSprites[0];
                  float flowDirection = (float)BlockLiquid.getFlowDirection(world, pos, material);
                  if (flowDirection > -999.0F) {
                     textureAtlasSprite = textureAtlasSprites[1];
                  }

                  worldRenderer.setSprite(textureAtlasSprite);
                  fluidHeight -= f8;
                  fluidHeight2 -= f8;
                  fluidHeight3 -= f8;
                  fluidHeight4 -= f8;
                  float f9;
                  float f10;
                  float f11;
                  float f12;
                  float f13;
                  float f14;
                  float f15;
                  float f16;
                  if (flowDirection < -999.0F) {
                     f9 = textureAtlasSprite.getInterpolatedU(0.0);
                     f13 = textureAtlasSprite.getInterpolatedV(0.0);
                     f10 = f9;
                     f14 = textureAtlasSprite.getInterpolatedV(16.0);
                     f11 = textureAtlasSprite.getInterpolatedU(16.0);
                     f15 = f14;
                     f12 = f11;
                     f16 = f13;
                  } else {
                     float f17 = MathHelper.sin(flowDirection) * 0.25F;
                     float f18 = MathHelper.cos(flowDirection) * 0.25F;
                     float f19 = 8.0F;
                     f9 = textureAtlasSprite.getInterpolatedU((double)(8.0F + (-f18 - f17) * 16.0F));
                     f13 = textureAtlasSprite.getInterpolatedV((double)(8.0F + (-f18 + f17) * 16.0F));
                     f10 = textureAtlasSprite.getInterpolatedU((double)(8.0F + (-f18 + f17) * 16.0F));
                     f14 = textureAtlasSprite.getInterpolatedV((double)(8.0F + (f18 + f17) * 16.0F));
                     f11 = textureAtlasSprite.getInterpolatedU((double)(8.0F + (f18 + f17) * 16.0F));
                     f15 = textureAtlasSprite.getInterpolatedV((double)(8.0F + (f18 - f17) * 16.0F));
                     f12 = textureAtlasSprite.getInterpolatedU((double)(8.0F + (f18 - f17) * 16.0F));
                     f16 = textureAtlasSprite.getInterpolatedV((double)(8.0F + (-f18 - f17) * 16.0F));
                  }

                  int n = block.getMixedBrightnessForBlock(world, pos.getX(), pos.getY(), pos.getZ());
                  int n2 = n >> 16 & 65535;
                  int n3 = n & 65535;
                  float f20 = f5 * f;
                  float f21 = f5 * f2;
                  float f22 = f5 * f3;
                  worldRenderer.pos(x2 + 0.0, y2 + (double)fluidHeight, z2 + 0.0)
                     .color(f20, f21, f22, 1.0F)
                     .tex((double)f9, (double)f13)
                     .lightmap(n2, n3)
                     .endVertex();
                  worldRenderer.pos(x2 + 0.0, y2 + (double)fluidHeight2, z2 + 1.0)
                     .color(f20, f21, f22, 1.0F)
                     .tex((double)f10, (double)f14)
                     .lightmap(n2, n3)
                     .endVertex();
                  worldRenderer.pos(x2 + 1.0, y2 + (double)fluidHeight3, z2 + 1.0)
                     .color(f20, f21, f22, 1.0F)
                     .tex((double)f11, (double)f15)
                     .lightmap(n2, n3)
                     .endVertex();
                  worldRenderer.pos(x2 + 1.0, y2 + (double)fluidHeight4, z2 + 0.0)
                     .color(f20, f21, f22, 1.0F)
                     .tex((double)f12, (double)f16)
                     .lightmap(n2, n3)
                     .endVertex();
                  if (block.hasOpenNeighbor(world, pos.getX(), pos.getY() + 1, pos.getZ())) {
                     worldRenderer.pos(x2 + 0.0, y2 + (double)fluidHeight, z2 + 0.0)
                        .color(f20, f21, f22, 1.0F)
                        .tex((double)f9, (double)f13)
                        .lightmap(n2, n3)
                        .endVertex();
                     worldRenderer.pos(x2 + 1.0, y2 + (double)fluidHeight4, z2 + 0.0)
                        .color(f20, f21, f22, 1.0F)
                        .tex((double)f12, (double)f16)
                        .lightmap(n2, n3)
                        .endVertex();
                     worldRenderer.pos(x2 + 1.0, y2 + (double)fluidHeight3, z2 + 1.0)
                        .color(f20, f21, f22, 1.0F)
                        .tex((double)f11, (double)f15)
                        .lightmap(n2, n3)
                        .endVertex();
                     worldRenderer.pos(x2 + 0.0, y2 + (double)fluidHeight2, z2 + 1.0)
                        .color(f20, f21, f22, 1.0F)
                        .tex((double)f10, (double)f14)
                        .lightmap(n2, n3)
                        .endVertex();
                  }
               }

               if (flag2) {
                  worldRenderer.setSprite(textureAtlasSprites[0]);
                  float minU = textureAtlasSprites[0].getMinU();
                  float maxU = textureAtlasSprites[0].getMaxU();
                  float minV = textureAtlasSprites[0].getMinV();
                  float maxV = textureAtlasSprites[0].getMaxV();
                  int n4 = block.getMixedBrightnessForBlock(world, pos.getX(), pos.getY() - 1, pos.getZ());
                  int n5 = n4 >> 16 & 65535;
                  int n6 = n4 & 65535;
                  float faceBrightness = FaceBakery.getFaceBrightness(EnumFacing.DOWN);
                  worldRenderer.pos(x2, y2, z2 + 1.0)
                     .color(f * faceBrightness, f2 * faceBrightness, f3 * faceBrightness, 1.0F)
                     .tex((double)minU, (double)maxV)
                     .lightmap(n5, n6)
                     .endVertex();
                  worldRenderer.pos(x2, y2, z2)
                     .color(f * faceBrightness, f2 * faceBrightness, f3 * faceBrightness, 1.0F)
                     .tex((double)minU, (double)minV)
                     .lightmap(n5, n6)
                     .endVertex();
                  worldRenderer.pos(x2 + 1.0, y2, z2)
                     .color(f * faceBrightness, f2 * faceBrightness, f3 * faceBrightness, 1.0F)
                     .tex((double)maxU, (double)minV)
                     .lightmap(n5, n6)
                     .endVertex();
                  worldRenderer.pos(x2 + 1.0, y2, z2 + 1.0)
                     .color(f * faceBrightness, f2 * faceBrightness, f3 * faceBrightness, 1.0F)
                     .tex((double)maxU, (double)maxV)
                     .lightmap(n5, n6)
                     .endVertex();
                  flag3 = true;
               }

               for (int i = 0; i < 4; i++) {
                  int n7 = 0;
                  int n8 = 0;
                  if (i == 0) {
                     n8--;
                  }

                  if (i == 1) {
                     n8++;
                  }

                  if (i == 2) {
                     n7--;
                  }

                  if (i == 3) {
                     n7++;
                  }

                  BlockPos pos2 = pos.add(n7, 0, n8);
                  TextureAtlasSprite textureAtlasSprite2 = textureAtlasSprites[1];
                  worldRenderer.setSprite(textureAtlasSprite2);
                  if (borderFlags[i]) {
                     float f23;
                     float f24;
                     double d;
                     double d2;
                     double d3;
                     double d4;
                     if (i == 0) {
                        f23 = fluidHeight;
                        f24 = fluidHeight4;
                        d = x2;
                        d3 = x2 + 1.0;
                        d2 = z2 + (double)f8;
                        d4 = z2 + (double)f8;
                     } else if (i == 1) {
                        f23 = fluidHeight3;
                        f24 = fluidHeight2;
                        d = x2 + 1.0;
                        d3 = x2;
                        d2 = z2 + 1.0 - (double)f8;
                        d4 = z2 + 1.0 - (double)f8;
                     } else if (i == 2) {
                        f23 = fluidHeight2;
                        f24 = fluidHeight;
                        d = x2 + (double)f8;
                        d3 = x2 + (double)f8;
                        d2 = z2 + 1.0;
                        d4 = z2;
                     } else {
                        f23 = fluidHeight4;
                        f24 = fluidHeight3;
                        d = x2 + 1.0 - (double)f8;
                        d3 = x2 + 1.0 - (double)f8;
                        d2 = z2;
                        d4 = z2 + 1.0;
                     }

                     flag3 = true;
                     float interpolatedU = textureAtlasSprite2.getInterpolatedU(0.0);
                     float interpolatedU2 = textureAtlasSprite2.getInterpolatedU(8.0);
                     float interpolatedV = textureAtlasSprite2.getInterpolatedV((double)((1.0F - f23) * 16.0F * 0.5F));
                     float interpolatedV2 = textureAtlasSprite2.getInterpolatedV((double)((1.0F - f24) * 16.0F * 0.5F));
                     float interpolatedV3 = textureAtlasSprite2.getInterpolatedV(8.0);
                     int n9 = block.getMixedBrightnessForBlock(world, pos2.getX(), pos2.getY(), pos2.getZ());
                     int n10 = n9 >> 16 & 65535;
                     int n11 = n9 & 65535;
                     float f25 = i < 2 ? FaceBakery.getFaceBrightness(EnumFacing.NORTH) : FaceBakery.getFaceBrightness(EnumFacing.WEST);
                     float f26 = f5 * f25 * f;
                     float f27 = f5 * f25 * f2;
                     float f28 = f5 * f25 * f3;
                     worldRenderer.pos(d, y2 + (double)f23, d2)
                        .color(f26, f27, f28, 1.0F)
                        .tex((double)interpolatedU, (double)interpolatedV)
                        .lightmap(n10, n11)
                        .endVertex();
                     worldRenderer.pos(d3, y2 + (double)f24, d4)
                        .color(f26, f27, f28, 1.0F)
                        .tex((double)interpolatedU2, (double)interpolatedV2)
                        .lightmap(n10, n11)
                        .endVertex();
                     worldRenderer.pos(d3, y2 + 0.0, d4).color(f26, f27, f28, 1.0F).tex((double)interpolatedU2, (double)interpolatedV3).lightmap(n10, n11).endVertex();
                     worldRenderer.pos(d, y2 + 0.0, d2).color(f26, f27, f28, 1.0F).tex((double)interpolatedU, (double)interpolatedV3).lightmap(n10, n11).endVertex();
                     worldRenderer.pos(d, y2 + 0.0, d2).color(f26, f27, f28, 1.0F).tex((double)interpolatedU, (double)interpolatedV3).lightmap(n10, n11).endVertex();
                     worldRenderer.pos(d3, y2 + 0.0, d4).color(f26, f27, f28, 1.0F).tex((double)interpolatedU2, (double)interpolatedV3).lightmap(n10, n11).endVertex();
                     worldRenderer.pos(d3, y2 + (double)f24, d4)
                        .color(f26, f27, f28, 1.0F)
                        .tex((double)interpolatedU2, (double)interpolatedV2)
                        .lightmap(n10, n11)
                        .endVertex();
                     worldRenderer.pos(d, y2 + (double)f23, d2)
                        .color(f26, f27, f28, 1.0F)
                        .tex((double)interpolatedU, (double)interpolatedV)
                        .lightmap(n10, n11)
                        .endVertex();
                  }
               }

               worldRenderer.setSprite((TextureAtlasSprite)null);
               return flag3;
            }
         } finally {
            if (Config.isShaderPackLoaded()) {
               SVertexBuilder.popEntity(worldRenderer);
            }
         }

         return false;
      }
   }

   public BlockFluidRenderer() {
      this.initAtlasSprites();
   }

   private float getFluidHeight(IBlockAccess world, int n, int n2, int n3, Material material) {
      int n4 = 0;
      float f = 0.0F;

      for (int i = 0; i < 4; i++) {
         int n5 = n - (i & 1);
         int n6 = n3 - (i >> 1 & 1);
         if (world.getBlockState2(n5, n2 + 1, n6).getBlock().getMaterial() == material) {
            return 1.0F;
         }

         IBlockState blockState2 = world.getBlockState2(n5, n2, n6);
         Material material2 = blockState2.getBlock().getMaterial();
         if (material2 != material) {
            if (!material2.isSolid()) {
               f++;
               n4++;
            }
         } else {
            int value = (Integer)blockState2.getValue(BlockLiquid.LEVEL);
            if (value >= 8 || value == 0) {
               f += BlockLiquid.getLiquidHeightPercent(value) * 10.0F;
               n4 += 10;
            }

            f += BlockLiquid.getLiquidHeightPercent(value);
            n4++;
         }
      }

      return 1.0F - f / (float)n4;
   }
}
