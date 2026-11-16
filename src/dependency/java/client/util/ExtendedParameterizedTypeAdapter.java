package client.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public final class ExtendedParameterizedTypeAdapter implements ParameterizedType {
   @Override
   public Type[] getActualTypeArguments() {
      return new Type[]{TargetStack.class};
   }

   @Override
   public Type getOwnerType() {
      return null;
   }

   @Override
   public Type getRawType() {
      return List.class;
   }
}
