import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;

public class SkipList {

    @Getter
    @Setter
    static class SkipListEntry {

        protected int pos;      // I added this to print the skiplist "nicely"

        static String NEG_INF = "-oo";
        static String POS_INF = "+oo";

        protected String key;
        protected Integer value;

        protected SkipListEntry up;
        protected SkipListEntry down;
        protected SkipListEntry left;
        protected SkipListEntry right;

        public SkipListEntry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object o) {
            SkipListEntry ent;

            try {
                ent = (SkipListEntry) o;    // Test if o is a SkipListEntry...
            } catch (ClassCastException ex) {
                return false;
            }

            return (ent.getKey().equals(key)) && (Objects.equals(ent.getValue(), value));
        }

        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    //First element of the top level
    SkipListEntry head;
    //Last element of the top level
    SkipListEntry tail;

    //number of entries in the Skip List
    int n;

    //height
    int h;

    //coin toss
    Random r;

    /* ----------------------------------------------
     Constructor: empty skiplist

                          null        null
                           ^           ^
                           |           |
     head --->  null <-- -inf <----> +inf --> null
                           |           |
                           v           v
                          null        null
     ---------------------------------------------- */
    public SkipList() {
        SkipListEntry p1, p2;

        p1 = new SkipListEntry(SkipListEntry.NEG_INF, null);
        p2 = new SkipListEntry(SkipListEntry.POS_INF, null);

        p1.right = p2;
        p2.left = p1;

        head = p1;
        tail = p2;

        n = 0;
        h = 0;
        r = new Random();
    }

    //find the largest key x <= k
    private SkipListEntry findEntry(String k) {
        SkipListEntry p;
        p = head;
        while (true) {
            while (!Objects.equals(p.right.key, SkipListEntry.POS_INF) &&
                    p.right.key.compareTo(k) <= 0)
                p = p.right;

            if (p.down != null) {
                p = p.down;
            } else {
                break;
            }
        }
        return p;
    }

    public Integer get(String k) {
        SkipListEntry p;
        p = findEntry(k);
        if (Objects.equals(k, p.key)) {
            return p.value;
        }
        return null;
    }

    public Integer put(String k, Integer v) {
        SkipListEntry p = findEntry(k);
        SkipListEntry q;
        int i;
        if (Objects.equals(k, p.key)) {
            Integer old = p.value;
            p.value = v;
            return old;
        }

        /* --------------------------------------------------------------
        Insert q into the lowest level after SkipListEntry p:

                         p   put q here           p        q
                         |     |                  |        |
		                 V     V                  V        V
        Lower level:    [ ] <------> [ ]    ==>  [ ] <--> [ ] <--> [ ]
        --------------------------------------------------------------- */
        q = new SkipListEntry(k, v);
        q.left = p;
        q.right = p.right;
        p.right.left = q;
        p.right = q;

        i = 0;   //Current level = 0
        while (r.nextDouble() < 0.5 /* Coin toss */) {
            // Coin toss success ! ---> build one more level !!!

            /* -------------------------------------------------------------------
               Check if we need to increase the height of the -oo and +oo "pillars
	           ------------------------------------------------------------------- */
            if (i >= h)   // We reached the top level !!! we need to add a new layer
            {
                SkipListEntry p1, p2;

                h = h + 1;

                p1 = new SkipListEntry(SkipListEntry.NEG_INF, null);
                p2 = new SkipListEntry(SkipListEntry.POS_INF, null);

                p1.right = p2;
                p1.down = head;

                p2.left = p1;
                p2.down = tail;

                head.up = p1;
                tail.up = p2;

                head = p1;
                tail = p2;
            }

            /* ------------------------------------
               Find first element with an UP-link
               ------------------------------------ */
            while (p.up == null) {
                p = p.left;
            }

            p = p.up;

            /* ---------------------------------------------------
               Add one more (k,*) to the column

	           Schema for making the linkage:

                p <--> e(k,*) <--> p.right
                          ^
		                  |
		                  v
		                  q
	            ---------------------------------------------------- */
            SkipListEntry e;
            e = new SkipListEntry(k, null);  // Don't need the value...
            e.left = p;
            e.right = p.right;
            e.down = q;

            /* ---------------------------------------
                  Change the neighboring links..
   	           --------------------------------------- */
            p.right.left = e;
            p.right = e;
            q.up = e;

            q = e;
            i = i + 1;
        }
        n = n + 1;      // One more entry in the Skip List
        return null;
    }

    public Integer remove(String key) {
        SkipListEntry p = findEntry(key);
        if (!Objects.equals(p.key, key)) {
            return null;
        }
        Integer oldValue = p.value;
        while (p != null) {
            p.left.right = p.right;
            p.right.left = p.left;
            p = p.up;
        }
        return oldValue;
    }

    /**
     * Returns the number of entries in the hash table.
     */
    public int size() {
        return n;
    }

    /**
     * Returns whether or not the table is empty.
     */
    public boolean isEmpty() {
        return n == 0;
    }


    ////////////////////////////////////////////////////////////////

    public void printHorizontal() {
        String s = "";
        int i;

        SkipListEntry p;

     /* ----------------------------------
    Record the position of each entry
	---------------------------------- */
        p = head;

        while (p.down != null) {
            p = p.down;
        }

        i = 0;
        while (p != null) {
            p.pos = i++;
            p = p.right;
        }

     /* -------------------
    Print...
	------------------- */
        p = head;

        while (p != null) {
            s = getOneRow(p);
            System.out.println(s);

            p = p.down;
        }
    }

    public String getOneRow(SkipListEntry p) {
        String s;
        int a, b, i;

        a = 0;

        s = "" + p.key;
        p = p.right;


        while (p != null) {
            SkipListEntry q;

            q = p;
            while (q.down != null)
                q = q.down;
            b = q.pos;

            s = s + " <-";


            for (i = a + 1; i < b; i++)
                s = s + "--------";

            s = s + "> " + p.key;

            a = b;

            p = p.right;
        }

        return (s);
    }

    public void printVertical() {
        String s = "";

        SkipListEntry p;

        p = head;

        while (p.down != null)
            p = p.down;

        while (p != null) {
            s = getOneColumn(p);
            System.out.println(s);

            p = p.right;
        }
    }


    public String getOneColumn(SkipListEntry p) {
        String s = "";

        while (p != null) {
            s = s + " " + p.key;

            p = p.up;
        }

        return (s);
    }

    public static void main(String[] args) {
        Random r = new Random();
        SkipList S = new SkipList();

        int i;


        for (i = 0; i < 40; i++) {
            S.put("" + r.nextInt(100), r.nextInt(100));
        }

        S.put("40", 100);
        S.printHorizontal();

        S.remove("40");
        S.printHorizontal();
    }
}
