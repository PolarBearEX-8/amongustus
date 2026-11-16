package net.minecraft.block;

import client.setting.EnumFacingXType;
import client.util.ImmutableSetEntry;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class CommonBlockAdapter extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("axis", EnumFacingXType.class);

   public CommonBlockAdapter(Material material) {
      super(material, material.getMapColor());
   }

   public CommonBlockAdapter(Material material, MapColor mapColor) {
      super(material, mapColor);
   }
}
