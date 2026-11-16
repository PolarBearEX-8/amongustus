package net.minecraft.block;

import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class BaseBlockAdapter extends Block {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);

   public BaseBlockAdapter(Material material) {
      super(material);
   }

   public BaseBlockAdapter(Material material, MapColor mapColor) {
      super(material, mapColor);
   }
}
