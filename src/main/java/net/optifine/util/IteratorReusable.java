package net.optifine.util;

import java.util.Iterator;
import java.util.List;

public interface IteratorReusable extends Iterator {
   void setList(List list);
}
