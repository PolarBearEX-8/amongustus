package org.json.simple.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JSONParser {
   public static final int S_INIT = 0;
   public static final int S_IN_FINISHED_VALUE = 1;
   public static final int S_IN_OBJECT = 2;
   public static final int S_IN_ARRAY = 3;
   public static final int S_PASSED_PAIR_KEY = 4;
   public static final int S_IN_PAIR_VALUE = 5;
   public static final int S_END = 6;
   public static final int S_IN_ERROR = -1;
   private LinkedList handlerStatusStack;
   private Yylex lexer = new Yylex((Reader)null);
   private Yytoken token = null;
   private int status = 0;

   public void reset() {
      this.token = null;
      this.status = 0;
      this.handlerStatusStack = null;
   }

   private Map createObjectContainer(ContainerFactory containerFactory) {
      if (containerFactory == null) {
         return new JSONObject();
      } else {
         Map map = containerFactory.createObjectContainer();
         return (Map)(map == null ? new JSONObject() : map);
      }
   }

   public void parse(String str, ContentHandler contentHandler) throws ParseException {
      this.parse(str, contentHandler, false);
   }

   public Object parse2(String str) throws ParseException {
      return this.parse3(str, (ContainerFactory)null);
   }

   public void parse2(Reader reader, ContentHandler contentHandler, boolean flag) throws IOException, ParseException {
      if (!flag) {
         this.reset(reader);
         this.handlerStatusStack = new LinkedList();
      } else if (this.handlerStatusStack == null) {
         flag = false;
         this.reset(reader);
         this.handlerStatusStack = new LinkedList();
      }

      LinkedList handlerStatusStack2 = this.handlerStatusStack;

      try {
         do {
            switch (this.status) {
               case -1:
                  throw new ParseException(this.getPosition(), 1, this.token);
               case 0:
                  contentHandler.startJSON();
                  this.nextToken();
                  switch (this.token.type) {
                     case 0:
                        this.status = 1;
                        handlerStatusStack2.addFirst(new Integer(this.status));
                        if (!contentHandler.primitive(this.token.value)) {
                           return;
                        }
                        break;
                     case 1:
                        this.status = 2;
                        handlerStatusStack2.addFirst(new Integer(this.status));
                        if (!contentHandler.startObject()) {
                           return;
                        }
                        break;
                     case 2:
                     default:
                        this.status = -1;
                        break;
                     case 3:
                        this.status = 3;
                        handlerStatusStack2.addFirst(new Integer(this.status));
                        if (!contentHandler.startArray()) {
                           return;
                        }
                        break;
                  }
                  break;
               case 1:
                  this.nextToken();
                  if (this.token.type == -1) {
                     contentHandler.endJSON();
                     this.status = 6;
                     return;
                  }

                  this.status = -1;
                  throw new ParseException(this.getPosition(), 1, this.token);
               case 2:
                  this.nextToken();
                  switch (this.token.type) {
                     case 0:
                        if (this.token.value instanceof String) {
                           String value = (String)this.token.value;
                           this.status = 4;
                           handlerStatusStack2.addFirst(new Integer(this.status));
                           if (!contentHandler.startObjectEntry(value)) {
                              return;
                           }
                        } else {
                           this.status = -1;
                        }
                        break;
                     case 2:
                        if (handlerStatusStack2.size() > 1) {
                           handlerStatusStack2.removeFirst();
                           this.status = this.peekStatus(handlerStatusStack2);
                        } else {
                           this.status = 1;
                        }

                        if (!contentHandler.endObject()) {
                           return;
                        }
                     case 5:
                        break;
                     default:
                        this.status = -1;
                        break;
                  }
                  break;
               case 3:
                  this.nextToken();
                  switch (this.token.type) {
                     case 0:
                        if (!contentHandler.primitive(this.token.value)) {
                           return;
                        }
                        break;
                     case 1:
                        this.status = 2;
                        handlerStatusStack2.addFirst(new Integer(this.status));
                        if (!contentHandler.startObject()) {
                           return;
                        }
                        break;
                     case 2:
                     default:
                        this.status = -1;
                        break;
                     case 3:
                        this.status = 3;
                        handlerStatusStack2.addFirst(new Integer(this.status));
                        if (!contentHandler.startArray()) {
                           return;
                        }
                        break;
                     case 4:
                        if (handlerStatusStack2.size() > 1) {
                           handlerStatusStack2.removeFirst();
                           this.status = this.peekStatus(handlerStatusStack2);
                        } else {
                           this.status = 1;
                        }

                        if (!contentHandler.endArray()) {
                           return;
                        }
                     case 5:
                        break;
                  }
                  break;
               case 4:
                  this.nextToken();
                  switch (this.token.type) {
                     case 0:
                        handlerStatusStack2.removeFirst();
                        this.status = this.peekStatus(handlerStatusStack2);
                        if (!contentHandler.primitive(this.token.value)) {
                           return;
                        }

                        if (!contentHandler.endObjectEntry()) {
                           return;
                        }
                        break;
                     case 1:
                        handlerStatusStack2.removeFirst();
                        handlerStatusStack2.addFirst(new Integer(5));
                        this.status = 2;
                        handlerStatusStack2.addFirst(new Integer(this.status));
                        if (!contentHandler.startObject()) {
                           return;
                        }
                        break;
                     case 2:
                     case 4:
                     case 5:
                     default:
                        this.status = -1;
                        break;
                     case 3:
                        handlerStatusStack2.removeFirst();
                        handlerStatusStack2.addFirst(new Integer(5));
                        this.status = 3;
                        handlerStatusStack2.addFirst(new Integer(this.status));
                        if (!contentHandler.startArray()) {
                           return;
                        }
                     case 6:
                        break;
                  }
                  break;
               case 5:
                  handlerStatusStack2.removeFirst();
                  this.status = this.peekStatus(handlerStatusStack2);
                  if (!contentHandler.endObjectEntry()) {
                     return;
                  }
                  break;
               case 6:
                  return;
            }

            if (this.status == -1) {
               throw new ParseException(this.getPosition(), 1, this.token);
            }
         } while (this.token.type != -1);
      } catch (IOException e) {
         this.status = -1;
         throw e;
      } catch (ParseException e2) {
         this.status = -1;
         throw e2;
      } catch (RuntimeException e3) {
         this.status = -1;
         throw e3;
      } catch (Error e4) {
         this.status = -1;
         throw e4;
      }

      this.status = -1;
      throw new ParseException(this.getPosition(), 1, this.token);
   }

   public Object parse2(Reader reader, ContainerFactory containerFactory) throws IOException, ParseException {
      this.reset(reader);
      LinkedList list = new LinkedList();
      LinkedList list2 = new LinkedList();

      try {
         do {
            this.nextToken();
            switch (this.status) {
               case -1:
                  throw new ParseException(this.getPosition(), 1, this.token);
               case 0:
                  switch (this.token.type) {
                     case 0:
                        this.status = 1;
                        list.addFirst(new Integer(this.status));
                        list2.addFirst(this.token.value);
                        break;
                     case 1:
                        this.status = 2;
                        list.addFirst(new Integer(this.status));
                        list2.addFirst(this.createObjectContainer(containerFactory));
                        break;
                     case 2:
                     default:
                        this.status = -1;
                        break;
                     case 3:
                        this.status = 3;
                        list.addFirst(new Integer(this.status));
                        list2.addFirst(this.createArrayContainer(containerFactory));
                        break;
                  }
                  break;
               case 1:
                  if (this.token.type == -1) {
                     return list2.removeFirst();
                  }

                  throw new ParseException(this.getPosition(), 1, this.token);
               case 2:
                  switch (this.token.type) {
                     case 0:
                        if (this.token.value instanceof String) {
                           list2.addFirst((String)this.token.value);
                           this.status = 4;
                           list.addFirst(new Integer(this.status));
                        } else {
                           this.status = -1;
                        }
                        break;
                     case 2:
                        if (list2.size() > 1) {
                           list.removeFirst();
                           list2.removeFirst();
                           this.status = this.peekStatus(list);
                        } else {
                           this.status = 1;
                        }
                     case 5:
                        break;
                     default:
                        this.status = -1;
                        break;
                  }
                  break;
               case 3:
                  switch (this.token.type) {
                     case 0:
                        ((List)list2.getFirst()).add(this.token.value);
                        break;
                     case 1:
                        List first2 = (List)list2.getFirst();
                        Map map = this.createObjectContainer(containerFactory);
                        first2.add(map);
                        this.status = 2;
                        list.addFirst(new Integer(this.status));
                        list2.addFirst(map);
                        break;
                     case 2:
                     default:
                        this.status = -1;
                        break;
                     case 3:
                        List first3 = (List)list2.getFirst();
                        List list3 = this.createArrayContainer(containerFactory);
                        first3.add(list3);
                        this.status = 3;
                        list.addFirst(new Integer(this.status));
                        list2.addFirst(list3);
                        break;
                     case 4:
                        if (list2.size() > 1) {
                           list.removeFirst();
                           list2.removeFirst();
                           this.status = this.peekStatus(list);
                        } else {
                           this.status = 1;
                        }
                     case 5:
                        break;
                  }
                  break;
               case 4:
                  switch (this.token.type) {
                     case 0:
                        list.removeFirst();
                        String str = (String)list2.removeFirst();
                        ((Map)list2.getFirst()).put(str, this.token.value);
                        this.status = this.peekStatus(list);
                        break;
                     case 1:
                        list.removeFirst();
                        String str2 = (String)list2.removeFirst();
                        Map first5 = (Map)list2.getFirst();
                        Map map2 = this.createObjectContainer(containerFactory);
                        first5.put(str2, map2);
                        this.status = 2;
                        list.addFirst(new Integer(this.status));
                        list2.addFirst(map2);
                        break;
                     case 2:
                     case 4:
                     case 5:
                     default:
                        this.status = -1;
                        break;
                     case 3:
                        list.removeFirst();
                        String str3 = (String)list2.removeFirst();
                        Map first6 = (Map)list2.getFirst();
                        List list4 = this.createArrayContainer(containerFactory);
                        first6.put(str3, list4);
                        this.status = 3;
                        list.addFirst(new Integer(this.status));
                        list2.addFirst(list4);
                     case 6:
                  }
            }

            if (this.status == -1) {
               throw new ParseException(this.getPosition(), 1, this.token);
            }
         } while (this.token.type != -1);
      } catch (IOException e) {
         throw e;
      }

      throw new ParseException(this.getPosition(), 1, this.token);
   }

   public Object parse3(String str, ContainerFactory containerFactory) throws ParseException {
      StringReader stringReader = new StringReader(str);

      try {
         return this.parse2(stringReader, containerFactory);
      } catch (IOException e) {
         throw new ParseException(-1, 2, e);
      }
   }

   public void reset(Reader reader) {
      this.lexer.yyreset(reader);
      this.reset();
   }

   private int peekStatus(LinkedList list) {
      if (list.size() == 0) {
         return -1;
      } else {
         return (Integer)list.getFirst();
      }
   }

   public int getPosition() {
      return this.lexer.getYychar();
   }

   private void nextToken() throws ParseException, IOException {
      this.token = this.lexer.yylex();
      if (this.token == null) {
         this.token = new Yytoken(-1, null);
      }
   }

   public void parse(String str, ContentHandler contentHandler, boolean flag) throws ParseException {
      StringReader stringReader = new StringReader(str);

      try {
         this.parse2(stringReader, contentHandler, flag);
      } catch (IOException e) {
         throw new ParseException(-1, 2, e);
      }
   }

   public void parse4(Reader reader, ContentHandler contentHandler) throws IOException, ParseException {
      this.parse2(reader, contentHandler, false);
   }

   public Object parse(Reader reader) throws IOException, ParseException {
      return this.parse2(reader, (ContainerFactory)null);
   }

   private List createArrayContainer(ContainerFactory containerFactory) {
      if (containerFactory == null) {
         return new JSONArray();
      } else {
         List list = containerFactory.creatArrayContainer();
         return (List)(list == null ? new JSONArray() : list);
      }
   }
}
