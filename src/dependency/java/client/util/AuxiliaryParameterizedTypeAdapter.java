package client.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public final class AuxiliaryParameterizedTypeAdapter implements ParameterizedType {
   @Override
   public Type getOwnerType() {
      return null;
   }

   @Override
   public Type[] getActualTypeArguments() {
      return new Type[]{GameProfileEntry.class};
   }

   @Override
   public Type getRawType() {
      return List.class;
   }
}
