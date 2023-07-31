public class SkipList<T extends Comparable<T>> {

    public void add(T item) {
    }

    public boolean remove(T item) {
        return false;
    }

    public boolean contains(T item) {
        return false;
    }

    public int size() {
        return 0;
    }

    /**
     * Denna metod är enbart till för testning. Den ska returnera antalet nivåer för
     * nod nr i. Skyddsnivån är avsiktlig för att möjliggöra för testfallen att
     * komma åt metoden utan att behöva bryta sig in.
     */
    int levelOfNode(int i) {
        return 1;
    }

}