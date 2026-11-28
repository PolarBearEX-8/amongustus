package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;

public abstract class StateMapperBase implements IStateMapper {
   public Map mapStateModelLocations = Maps.newLinkedHashMap();

   public String getPropertyString(Map map) {
      StringBuilder sb = new StringBuilder();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         if (sb.length() != 0) {
            sb.append(",");
         }

         IProperty key = (IProperty)entry.getKey();
         Comparable value = (Comparable)entry.getValue();
         sb.append(key.getName());
         sb.append("=");
         sb.append(key.getName(value));
      }

      if (sb.length() == 0) {
         sb.append("normal");
      }

      return sb.toString();
   }

   @Override
   public Map putStateModelLocations(Block block) {
      UnmodifiableIterator it = block.getBlockState().getValidStates().iterator();

      while (it.hasNext()) {
         IBlockState state = (IBlockState)it.next();
         this.mapStateModelLocations.put(state, this.getModelResourceLocation(state));
      }

      return this.mapStateModelLocations;
   }

   public abstract ModelResourceLocation getModelResourceLocation(IBlockState state);
}
