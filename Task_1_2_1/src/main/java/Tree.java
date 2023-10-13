import java.util.*;

public class Tree<T> {
    /**
     * the value stored in the node.
     */
    private T value;
    /**
     * parent of the current node.
     */
    private  Tree<T> parent;
    /**
     * all childrens of current node.
     */
    private ArrayList<Tree<T>> childrens;
    /**
     * counter for any modification of current tree.
     */
    private int modifycount = 0;

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        Iterator myiterator = tree.iterator();
        while (myiterator.hasNext()) {
            System.out.print(myiterator.next() + "\n");
        }
        System.out.print("Hello\n");
    }

    /**
     * constructor for creating a tree.
     *
     * @param obj the value stored in the node
     */
    public Tree(T obj) {
        this.value = obj;
        this.parent = null;
        this.childrens = new ArrayList<>();
    }

    /**
     * функция для обновления счетчика изменений для всех предков данной node.
     *
     * @param current node с которой начать обход
     */
    public void updateParents(Tree<T> current) {
        if (current.value == null || current.parent == null) {
            return;
        }
        current.parent.modifycount++;
        updateParents(current.parent);
    }

    /**
     * add children to the tree.
     *
     * @param obj value in node
     * @return new node
     */
    public Tree<T> addChild(T obj) {
        //update modification counter
        this.modifycount++;
        updateParents(this);
        // add child
        Tree<T> child = new Tree<>(obj);
        child.parent = this;
        this.childrens.add(child);
        return child;
    }

    /**
     * add subtree to the tree.
     *
     * @param subtree
     */
    public void addChild(Tree<T> subtree) {
        //update modification counter
        this.modifycount++;
        updateParents(this);
        //add subtree
        subtree.parent = this;
        this.childrens.add(subtree);
    }

    /**
     * delete current tree.
     */
    public void remove() {
        //update modification counter
        this.modifycount++;
        updateParents(this);
        //remove nodes
        this.childrens.clear();
        if (this.parent != null) {
            this.parent.childrens.remove(this);
        }
        this.value = null;
    }

    //-------------------Iterators-----------------------

    /**
     * default iterator using bfs.
     *
     * @return bfs iterator
     */
    public Iterator<T> iterator() {
        return new BfsIterator<T>(this);
    }

    class BfsIterator<T> implements Iterator<T> {
        private Queue<Tree<T>> queue;
        private int currentModCounter;
        BfsIterator(Tree<T> root) {
            this.queue = new ArrayDeque<Tree<T>>();
            if (root.value != null) {
                this.queue.add(root);
                this.currentModCounter = root.modifycount;
            }

        }
        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if (currentModCounter != modifycount) {
                throw new ConcurrentModificationException();
            }
            if (this.queue.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> cur = this.queue.poll();
            for (int i = 0; i < cur.childrens.size(); i++) {
                if (cur.childrens.get(i).value != null) {
                    this.queue.add(cur.childrens.get(i));
                }
            }
            return cur.value;
        }

        @Override
        public void remove() throws ConcurrentModificationException {
            throw new ConcurrentModificationException();
        }
    }

}
