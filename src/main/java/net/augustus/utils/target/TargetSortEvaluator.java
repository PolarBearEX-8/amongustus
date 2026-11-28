package net.augustus.utils.target;

import net.augustus.utils.rotation.Rotation;
import java.util.function.ToDoubleFunction;
import net.minecraft.entity.Entity;

public interface TargetSortEvaluator {
   ToDoubleFunction createKeyExtractor(Rotation rotation, Entity entity, double d, boolean flag);
}
