package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandler;
import java.util.Optional;

public interface LocalListener {
   void applyINACTIVEHandler(INACTIVEHandler iNACTIVEHandler);

   Optional ofNullableAsOptional();

   Optional ofNullable();
}
