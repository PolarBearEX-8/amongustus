package client.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public final class InternalParameterizedTypeAdapter implements ParameterizedType {
   @Override
   public Type getOwnerType() {
      return null;
   }

   @Override
   public Type[] getActualTypeArguments() {
      return new Type[]{String.class, CustomListEntry.class};
   }

   @Override
   public Type getRawType() {
      return Map.class;
   }
}
