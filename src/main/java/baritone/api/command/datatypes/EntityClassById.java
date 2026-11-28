package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import java.util.stream.Stream;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

public enum EntityClassById implements IDatatypeFor {
   INSTANCE;

@Override
   public Object get(IDatatypeContext datatypeContext) throws CommandException {
      return this.get2(datatypeContext);
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      return new TabCompleteHelper().append(EntityList.getEntityNameList().stream().map(Object::toString)).filterPrefixNamespaced(datatypeContext.getConsumer().getString()).sortAlphabetically().getStream();
   }

   public Class get2(IDatatypeContext datatypeContext) throws CommandException {
      ResourceLocation location = new ResourceLocation(datatypeContext.getConsumer().getString());

      Class clazz;
      try {
         clazz = (Class)EntityList.idToClassMapping.get(location);
      } catch (NoSuchFieldError e) {
         try {
            Class[] clazzs = new Class[]{ResourceLocation.class};
            clazz = (Class)EntityList.class.getMethod("getClass", clazzs).invoke(null, location);
         } catch (Exception e2) {
            throw new RuntimeException("EntityList.REGISTRY does not exist and failed to call the Forge-replacement method", e2);
         }
      }

      if (clazz == null) {
         throw new IllegalArgumentException("no entity found by that id");
      } else {
         return clazz;
      }
   }
}
