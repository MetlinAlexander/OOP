import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    void testBfsIterator() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

        List<String> fromBfs = new ArrayList<>();
        Iterator myiterator = tree.iterator();
        while (myiterator.hasNext()) {
            fromBfs.add(myiterator.next().toString());
        }
        String[] arr = new String[]{"R1", "A", "R2", "C", "D"};
        if (arr.length != fromBfs.size()) {
            Assertions.fail();
        }
        for (int i = 0; i < arr.length; i++) {
            if (fromBfs.get(i) != arr[i]) {
                Assertions.fail();
            }
        }
    }

    @Test
    void testDfsIterator() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

        List<String> fromDfs = new ArrayList<>();
        Iterator myiterator = tree.dfsiterator();
        while (myiterator.hasNext()) {
            fromDfs.add(myiterator.next().toString());
        }
        System.out.print(fromDfs.toString());
        String[] arr = new String[]{"R1", "R2", "D", "C", "A"};
        if (arr.length != fromDfs.size()) {
            Assertions.fail();
        }
        for (int i = 0; i < arr.length; i++) {
            if (fromDfs.get(i) != arr[i]) {
                Assertions.fail();
            }
        }
    }

    @Test()
    void testDfsmodify() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        Iterator myiteratordfs = tree.dfsiterator();
        var m = tree.addChild("m");
        assertThrows(ConcurrentModificationException.class, () -> {
            myiteratordfs.next();
        });
    }

    @Test()
    void testBfsmodify() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

        Iterator myiteratorbfs = tree.iterator();
        var m = tree.addChild("m");
        assertThrows(ConcurrentModificationException.class, () -> {
            myiteratorbfs.next();
        });
    }

    @Test
    void equalsTest1() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

        Tree<String> tree2 = new Tree<>("R1");
        var d = tree2.addChild("A");
        Tree<String> subtree2 = new Tree<>("R2");
        subtree2.addChild("C");
        subtree2.addChild("D");
        tree2.addChild(subtree2);

        assertEquals(tree, tree2);
    }

    @Test
    void notequalstest1() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        b.remove();
        subtree.addChild("D");
        tree.addChild(subtree);
        assertNotEquals(tree, subtree);
    }

    @Test
    void nullequalstest1() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        assertNotEquals(tree, null);
    }
}
