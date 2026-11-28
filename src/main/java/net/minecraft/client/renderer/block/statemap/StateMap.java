package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Lists;
import java.util.Collections;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class StateMap extends StateMapperBase {
   private final IProperty name;
   private final String suffix;
   private final List ignored;

   public StateMap(IProperty property, String str, List list, StateMap.InnerEntry obj) {
      this(property, str, list);
   }

   private StateMap(IProperty property, String str, List list) {
      this.name = property;
      this.suffix = str;
      this.ignored = list;
   }

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState state) {
      LinkedHashMap map = Maps.newLinkedHashMap(state.getProperties());
      String str;
      if (this.name == null) {
         str = ((ResourceLocation)Block.blockRegistry.getNameForObject(state.getBlock())).toString();
      } else {
         str = this.name.getName((Comparable)map.remove(this.name));
      }

      if (this.suffix != null) {
         str = str + this.suffix;
      }

      for (IProperty property : (Iterable<IProperty>)(this.ignored)) {
         map.remove(property);
      }

      return new ModelResourceLocation(str, this.getPropertyString(map));
   }


   public static class InnerEntry {
   }

   public static class Builder {
      private IProperty name;
      private String suffix;
      private final List ignored = Lists.newArrayList();

      public Builder ignore(IProperty[] propertys) {
         Collections.addAll(this.ignored, propertys);
         return this;
      }

      public StateMap build() {
         return new StateMap(this.name, this.suffix, this.ignored, null);
      }

      public Builder withName(IProperty property) {
         this.name = property;
         return this;
      }

      public Builder withSuffix(String str) {
         this.suffix = str;
         return this;
      }
   }
}
