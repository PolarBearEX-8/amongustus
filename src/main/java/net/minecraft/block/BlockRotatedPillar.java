package net.minecraft.block;

import net.minecraft.util.EnumFacing;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;

public abstract class BlockRotatedPillar extends Block {
   public static final PropertyEnum AXIS;

   static {
      AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);
   }

   public BlockRotatedPillar(Material material) {
      super(material, material.getMaterialMapColor());
   }

   public BlockRotatedPillar(Material material, MapColor mapColor) {
      super(material, mapColor);
   }
}
