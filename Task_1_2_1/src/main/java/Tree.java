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
     * @param subtree subtree that should be added
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

    /**
     * method to check trees equals.
     *
     * @param obj - second object to compare
     * @return boolean answer true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tree)) {
            return false;
        }
        if (this.value != ((Tree<?>) obj).value) {
            return false;
        }
        Tree<T> secondTree = (Tree<T>) obj;
        if (this.childrens.size() != secondTree.childrens.size()) {
            return false;
        }
        Iterator<T> iterator1 = this.iterator();
        Iterator<T> iterator2 = secondTree.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.next() != iterator2.next()) {
                return false;
            }
        }
        return true;
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
            return !this.queue.isEmpty();
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

    /**
     * default iterator using dfs.
     *
     * @return dfs iterator
     */
    public Iterator<T> dfsiterator() {
        return new Dfsiterator<T>(this);
    }
    class Dfsiterator<T> implements Iterator<T> {
        private Stack<Tree<T>> stack;
        private int currentModCounter;

        Dfsiterator(Tree<T> root) {
            this.stack = new Stack<Tree<T>>();
            if (root.value != null) {
                this.stack.push(root);
                this.currentModCounter = root.modifycount;
            }
        }

        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if (currentModCounter != modifycount) {
                throw new ConcurrentModificationException();
            }
            return (!this.stack.isEmpty());
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> cur = this.stack.pop();
            for (int i = 0; i < cur.childrens.size(); i++) {
                if (cur.childrens.get(i).value != null) {
                    this.stack.push(cur.childrens.get(i));
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
