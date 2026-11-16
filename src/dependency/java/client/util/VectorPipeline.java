package client.util;

import client.event.NativeListener;
import com.google.common.base.Predicate;

public interface VectorPipeline extends NativeListener {
   Predicate predicate2 = new GeometryQueue();
   Predicate predicate = new DampenerSerializer();
}
