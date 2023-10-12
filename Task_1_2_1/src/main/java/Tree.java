import java.util.ArrayList;

public class Tree<T> {

    private T value; // the value stored in the node
    private  Tree<T> parent; // parent of the current node
    private ArrayList<Tree<T>> childrens; // all childrens of current node
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();
        System.out.print("Hello\n");
    }

    /**
     * constructor for creating a tree
     *
     * @param obj the value stored in the node
     */
    public Tree(T obj) {
        this.value = obj;
        this.parent = null;
        this.childrens = new ArrayList<>();
    }

//    добавление и удаление элементов/поддеревьев;
    public Tree<T> addChild(T obj) {
        Tree<T> child = new Tree<>(obj);
        child.parent = this;
        this.childrens.add(child);
        return child;
    }

    public void addChild(Tree<T> subtree) {
        subtree.parent = this;
        this.childrens.add(subtree);
    }

    public void remove() {
        this.childrens.clear();
        if(this.parent != null) {
            this.parent.childrens.remove(this);
        }
        this.value = null;

    }

}
