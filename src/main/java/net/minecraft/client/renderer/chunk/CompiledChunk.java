package net.minecraft.client.renderer.chunk;

import net.minecraft.client.renderer.WorldRenderer;
import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;

public class CompiledChunk {
   public static final CompiledChunk DUMMY = new CompiledChunk.DefaultCompiledChunk();
   private final boolean[] layersUsed = new boolean[RenderChunk.ENUM_WORLD_BLOCK_LAYERS.length];
   private final boolean[] layersStarted = new boolean[RenderChunk.ENUM_WORLD_BLOCK_LAYERS.length];
   private boolean empty = true;
   private final List tileEntities = Lists.newArrayList();
   private SetVisibility setVisibility = new SetVisibility();
   private WorldRenderer.State state;
   private BitSet[] animatedSprites = new BitSet[RenderChunk.ENUM_WORLD_BLOCK_LAYERS.length];

   public boolean isLayerEmpty(EnumWorldBlockLayer enumWorldBlockLayer) {
      return !this.layersUsed[enumWorldBlockLayer.ordinal()];
   }

   public BitSet getAnimatedSprites(EnumWorldBlockLayer enumWorldBlockLayer) {
      return this.animatedSprites[enumWorldBlockLayer.ordinal()];
   }

   public List getTileEntities() {
      return this.tileEntities;
   }

   public void addTileEntity(TileEntity tileEntity) {
      this.tileEntities.add(tileEntity);
   }

   public WorldRenderer.State getState() {
      return this.state;
   }

   public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
      return this.setVisibility.isVisible(facing, facing2);
   }

   public void setLayerUsed(EnumWorldBlockLayer enumWorldBlockLayer) {
      this.empty = false;
      this.layersUsed[enumWorldBlockLayer.ordinal()] = true;
   }

   public void setVisibility(SetVisibility setVisibility2) {
      this.setVisibility = setVisibility2;
   }

   public void setAnimatedSprites(EnumWorldBlockLayer enumWorldBlockLayer, BitSet bitSet) {
      this.animatedSprites[enumWorldBlockLayer.ordinal()] = bitSet;
   }

   public boolean isLayerStarted(EnumWorldBlockLayer enumWorldBlockLayer) {
      return this.layersStarted[enumWorldBlockLayer.ordinal()];
   }

   public void setLayerStarted(EnumWorldBlockLayer enumWorldBlockLayer) {
      this.layersStarted[enumWorldBlockLayer.ordinal()] = true;
   }

   public void setState(WorldRenderer.State state2) {
      this.state = state2;
   }

   public boolean isEmpty() {
      return this.empty;
   }


   public static final class DefaultCompiledChunk extends CompiledChunk {
      @Override
      public void setAnimatedSprites(EnumWorldBlockLayer enumWorldBlockLayer, BitSet bitSet) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
         return false;
      }

      @Override
      public void setLayerUsed(EnumWorldBlockLayer enumWorldBlockLayer) {
         throw new UnsupportedOperationException();
      }

      @Override
      public void setLayerStarted(EnumWorldBlockLayer enumWorldBlockLayer) {
         throw new UnsupportedOperationException();
      }
   }
}
