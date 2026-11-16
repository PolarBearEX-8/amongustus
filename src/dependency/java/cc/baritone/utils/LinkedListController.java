package cc.baritone.utils;

import cc.baritone.command.UnkownInternalController;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LinkedListController {
   public static final int count = 0;
   public static final int count2 = 1;
   public static final int count3 = 2;
   public static final int count4 = 3;
   public static final int count5 = 4;
   public static final int count6 = 5;
   public static final int count7 = 6;
   public static final int count8 = -1;
   public LinkedList linkedList;
   public UnkownInternalController unkownInternalController = new UnkownInternalController((Reader)null);
   public VALUELEFTRecord vALUELEFTRecord = null;
   public int count9 = 0;

   public void applyText(String text, ServiceProvider serviceProvider, boolean flag) throws UnexpectedCharacterException {
      StringReader stringReader = new StringReader(text);

      try {
         this.applyReader(stringReader, serviceProvider, flag);
      } catch (IOException ioException) {
         throw new UnexpectedCharacterException(-1, 2, ioException);
      }
   }

   public void applyReader(Reader reader, ServiceProvider serviceProvider) throws IOException, UnexpectedCharacterException {
      this.applyReader(reader, serviceProvider, false);
   }

   private List resolveList(Service11 service) {
      if (service == null) {
         return new WriterArrayList();
      } else {
         List list = service.getList();
         return (List)(list == null ? new WriterArrayList() : list);
      }
   }

   private Map resolveMap(Service11 service) {
      if (service == null) {
         return new WriterHashMap();
      } else {
         Map map = service.getMap();
         return (Map)(map == null ? new WriterHashMap() : map);
      }
   }

   private void updateState() throws UnexpectedCharacterException, IOException {
      this.vALUELEFTRecord = this.unkownInternalController.getVALUELEFTRecord();
      if (this.vALUELEFTRecord == null) {
         this.vALUELEFTRecord = new VALUELEFTRecord(-1, null);
      }
   }

   public void applyReader(Reader reader) {
      this.unkownInternalController.applyReader(reader);
      this.updateState2();
   }

   public Object resolveTarget(String text) throws UnexpectedCharacterException {
      return this.resolveTarget(text, (Service11)null);
   }

   private int computeCount(LinkedList list) {
      return list.size() == 0 ? -1 : (Integer)list.getFirst();
   }

   public void updateState2() {
      this.vALUELEFTRecord = null;
      this.count9 = 0;
      this.linkedList = null;
   }

   public Object resolveTarget(Reader reader) throws IOException, UnexpectedCharacterException {
      return this.resolveTarget(reader, (Service11)null);
   }

   public Object resolveTarget(String text, Service11 service) throws UnexpectedCharacterException {
      StringReader stringReader = new StringReader(text);

      try {
         return this.resolveTarget(stringReader, service);
      } catch (IOException ioException) {
         throw new UnexpectedCharacterException(-1, 2, ioException);
      }
   }

   public Object resolveTarget(Reader reader, Service11 service) throws IOException, UnexpectedCharacterException {
      this.applyReader(reader);
      LinkedList list = new LinkedList();
      LinkedList list2 = new LinkedList();

      try {
         do {
            this.updateState();
            searchBlock:
            switch (this.count9) {
               case -1:
                  throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
               case 0:
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        this.count9 = 1;
                        list.addFirst(new Integer(this.count9));
                        list2.addFirst(this.vALUELEFTRecord.dataRef);
                        break searchBlock;
                     case 1:
                        this.count9 = 2;
                        list.addFirst(new Integer(this.count9));
                        list2.addFirst(this.resolveMap(service));
                        break searchBlock;
                     case 2:
                     default:
                        this.count9 = -1;
                        break searchBlock;
                     case 3:
                        this.count9 = 3;
                        list.addFirst(new Integer(this.count9));
                        list2.addFirst(this.resolveList(service));
                        break searchBlock;
                  }
               case 1:
                  if (this.vALUELEFTRecord.count == -1) {
                     return list2.removeFirst();
                  }

                  throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
               case 2:
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        if (this.vALUELEFTRecord.dataRef instanceof String) {
                           String dataRef = (String)this.vALUELEFTRecord.dataRef;
                           list2.addFirst(dataRef);
                           this.count9 = 4;
                           list.addFirst(new Integer(this.count9));
                        } else {
                           this.count9 = -1;
                        }
                        break searchBlock;
                     case 2:
                        if (list2.size() > 1) {
                           list.removeFirst();
                           list2.removeFirst();
                           this.count9 = this.computeCount(list);
                        } else {
                           this.count9 = 1;
                        }
                     case 5:
                        break searchBlock;
                     default:
                        this.count9 = -1;
                        break searchBlock;
                  }
               case 3:
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        List first = (List)list2.getFirst();
                        first.add(this.vALUELEFTRecord.dataRef);
                        break searchBlock;
                     case 1:
                        List first2 = (List)list2.getFirst();
                        Map map = this.resolveMap(service);
                        first2.add(map);
                        this.count9 = 2;
                        list.addFirst(new Integer(this.count9));
                        list2.addFirst(map);
                        break searchBlock;
                     case 2:
                     default:
                        this.count9 = -1;
                        break searchBlock;
                     case 3:
                        List first3 = (List)list2.getFirst();
                        List list3 = this.resolveList(service);
                        first3.add(list3);
                        this.count9 = 3;
                        list.addFirst(new Integer(this.count9));
                        list2.addFirst(list3);
                        break searchBlock;
                     case 4:
                        if (list2.size() > 1) {
                           list.removeFirst();
                           list2.removeFirst();
                           this.count9 = this.computeCount(list);
                        } else {
                           this.count9 = 1;
                        }
                     case 5:
                        break searchBlock;
                  }
               case 4:
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        list.removeFirst();
                        String text = (String)list2.removeFirst();
                        Map first4 = (Map)list2.getFirst();
                        first4.put(text, this.vALUELEFTRecord.dataRef);
                        this.count9 = this.computeCount(list);
                        break;
                     case 1:
                        list.removeFirst();
                        String text2 = (String)list2.removeFirst();
                        Map first5 = (Map)list2.getFirst();
                        Map map2 = this.resolveMap(service);
                        first5.put(text2, map2);
                        this.count9 = 2;
                        list.addFirst(new Integer(this.count9));
                        list2.addFirst(map2);
                        break;
                     case 2:
                     case 4:
                     case 5:
                     default:
                        this.count9 = -1;
                        break;
                     case 3:
                        list.removeFirst();
                        String text3 = (String)list2.removeFirst();
                        Map first6 = (Map)list2.getFirst();
                        List list4 = this.resolveList(service);
                        first6.put(text3, list4);
                        this.count9 = 3;
                        list.addFirst(new Integer(this.count9));
                        list2.addFirst(list4);
                     case 6:
                  }
            }

            if (this.count9 == -1) {
               throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
            }
         } while (this.vALUELEFTRecord.count != -1);
      } catch (IOException ioException) {
         throw ioException;
      }

      throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
   }

   public void applyReader(Reader reader, ServiceProvider serviceProvider, boolean flag) throws IOException, UnexpectedCharacterException {
      if (!flag) {
         this.applyReader(reader);
         this.linkedList = new LinkedList();
      } else if (this.linkedList == null) {
         this.applyReader(reader);
         this.linkedList = new LinkedList();
      }

      LinkedList linkedList2 = this.linkedList;

      try {
         do {
            searchBlock:
            switch (this.count9) {
               case -1:
                  throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
               case 0:
                  serviceProvider.updateState();
                  this.updateState();
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        this.count9 = 1;
                        linkedList2.addFirst(new Integer(this.count9));
                        if (!serviceProvider.matchesTarget(this.vALUELEFTRecord.dataRef)) {
                           return;
                        }
                        break searchBlock;
                     case 1:
                        this.count9 = 2;
                        linkedList2.addFirst(new Integer(this.count9));
                        if (!serviceProvider.isActiveAsFlag()) {
                           return;
                        }
                        break searchBlock;
                     case 2:
                     default:
                        this.count9 = -1;
                        break searchBlock;
                     case 3:
                        this.count9 = 3;
                        linkedList2.addFirst(new Integer(this.count9));
                        if (!serviceProvider.isActive2()) {
                           return;
                        }
                        break searchBlock;
                  }
               case 1:
                  this.updateState();
                  if (this.vALUELEFTRecord.count == -1) {
                     serviceProvider.updateState2();
                     this.count9 = 6;
                     return;
                  }

                  this.count9 = -1;
                  throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
               case 2:
                  this.updateState();
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        if (this.vALUELEFTRecord.dataRef instanceof String) {
                           String dataRef = (String)this.vALUELEFTRecord.dataRef;
                           this.count9 = 4;
                           linkedList2.addFirst(new Integer(this.count9));
                           if (!serviceProvider.matchesText(dataRef)) {
                              return;
                           }
                        } else {
                           this.count9 = -1;
                        }
                        break searchBlock;
                     case 2:
                        if (linkedList2.size() > 1) {
                           linkedList2.removeFirst();
                           this.count9 = this.computeCount(linkedList2);
                        } else {
                           this.count9 = 1;
                        }

                        if (!serviceProvider.isActive3()) {
                           return;
                        }
                     case 5:
                        break searchBlock;
                     default:
                        this.count9 = -1;
                        break searchBlock;
                  }
               case 3:
                  this.updateState();
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        if (!serviceProvider.matchesTarget(this.vALUELEFTRecord.dataRef)) {
                           return;
                        }
                        break searchBlock;
                     case 1:
                        this.count9 = 2;
                        linkedList2.addFirst(new Integer(this.count9));
                        if (!serviceProvider.isActiveAsFlag()) {
                           return;
                        }
                        break searchBlock;
                     case 2:
                     default:
                        this.count9 = -1;
                        break searchBlock;
                     case 3:
                        this.count9 = 3;
                        linkedList2.addFirst(new Integer(this.count9));
                        if (!serviceProvider.isActive2()) {
                           return;
                        }
                        break searchBlock;
                     case 4:
                        if (linkedList2.size() > 1) {
                           linkedList2.removeFirst();
                           this.count9 = this.computeCount(linkedList2);
                        } else {
                           this.count9 = 1;
                        }

                        if (!serviceProvider.isActive()) {
                           return;
                        }
                     case 5:
                        break searchBlock;
                  }
               case 4:
                  this.updateState();
                  switch (this.vALUELEFTRecord.count) {
                     case 0:
                        linkedList2.removeFirst();
                        this.count9 = this.computeCount(linkedList2);
                        if (!serviceProvider.matchesTarget(this.vALUELEFTRecord.dataRef)) {
                           return;
                        }

                        if (!serviceProvider.isActive4()) {
                           return;
                        }
                        break searchBlock;
                     case 1:
                        linkedList2.removeFirst();
                        linkedList2.addFirst(new Integer(5));
                        this.count9 = 2;
                        linkedList2.addFirst(new Integer(this.count9));
                        if (!serviceProvider.isActiveAsFlag()) {
                           return;
                        }
                        break searchBlock;
                     case 2:
                     case 4:
                     case 5:
                     default:
                        this.count9 = -1;
                        break searchBlock;
                     case 3:
                        linkedList2.removeFirst();
                        linkedList2.addFirst(new Integer(5));
                        this.count9 = 3;
                        linkedList2.addFirst(new Integer(this.count9));
                        if (!serviceProvider.isActive2()) {
                           return;
                        }
                     case 6:
                        break searchBlock;
                  }
               case 5:
                  linkedList2.removeFirst();
                  this.count9 = this.computeCount(linkedList2);
                  if (!serviceProvider.isActive4()) {
                     return;
                  }
                  break;
               case 6:
                  return;
            }

            if (this.count9 == -1) {
               throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
            }
         } while (this.vALUELEFTRecord.count != -1);
      } catch (IOException ioException) {
         this.count9 = -1;
         throw ioException;
      } catch (UnexpectedCharacterException unexpectedCharacterException) {
         this.count9 = -1;
         throw unexpectedCharacterException;
      } catch (RuntimeException exception) {
         this.count9 = -1;
         throw exception;
      } catch (Error error) {
         this.count9 = -1;
         throw error;
      }

      this.count9 = -1;
      throw new UnexpectedCharacterException(this.getCount(), 1, this.vALUELEFTRecord);
   }

   public void applyText(String text, ServiceProvider serviceProvider) throws UnexpectedCharacterException {
      this.applyText(text, serviceProvider, false);
   }

   public int getCount() {
      return this.unkownInternalController.getCount15();
   }
}
