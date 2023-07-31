//Victor Ericson vier1798


/**
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) Det är också den enda av klasserna ni
 * ska lämna in. Glöm inte att namn och användarnamn ska stå i en kommentar
 * högst upp, och att en eventuell paketdeklarationen måste plockas bort vid inlämningen för
 * att koden ska gå igenom de automatiska testerna.
 * <p>
 * De ändringar som är tillåtna är begränsade av följande:
 * <ul>
 * <li>Ni får INTE byta namn på klassen.
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans. Detta gäller också alterntiv
 * till loopar, så som strömmar.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * <li>Ni får INTE låta NÅGON metod ta en parameter av typen
 * BinarySearchTreeNode. Enbart den generiska typen (T eller vad ni väljer att
 * kalla den), String, StringBuilder, StringBuffer, samt primitiva typer är
 * tillåtna.
 * </ul>
 *
 * @param <T>
 * @author henrikbe
 */

public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {
        if (this.data == data) {
            return false;
        }

        int compare = data.compareTo(this.data);

        if (compare < 0) {
            if (left == null) {
                left = new BinarySearchTreeNode<>(data);
                return true;
            } else {
                return left.add(data);
            }
        }
        if (right == null) {
            right = new BinarySearchTreeNode<>(data);
            return true;
        } else {
            return right.add(data);
        }
    }

    private T findMin() {
        if (this.data == null) {
            return null;
        }
        if (this.left == null) {
            return data;
        }
        return left.findMin();
    }

    public BinarySearchTreeNode<T> remove(T data) {

        int compare = data.compareTo(this.data);

        if (compare < 0) {
            if (left != null) {
                left = left.remove(data);
            }
        } else if (compare > 0) {
            if (right != null) {
                right = right.remove(data);
            }
        } else if (this.left != null && this.right != null) {
            this.data = right.findMin();
            right = right.remove(this.data);
        } else {
            return (right != null) ? right : left;
        }
        return this;
    }


    public boolean contains(T data) {
        if (this.data == data) {
            return true;
        }
        int compare = data.compareTo(this.data);

        if (compare < 0) {
            if (left == null) {
                return false;
            } else
                return left.contains(data);
        }
        if (compare > 0) {
            if (right == null) {
                return false;
            } else
                return right.contains(data);
        }
        return false;
    }

    public int size() {
        if (this.left == null && this.right == null) {
            return 1;
        }
        if (this.left == null) {
            return 1 + this.right.size();
        }
        if (this.right == null) {
            return 1 + this.left.size();
        }
        return 1 + this.left.size() + this.right.size();
    }

    public int depth() {
        if (this.left == null && this.right == null) {
            return 0;
        }
        if (this.left == null) {
            return 1 + this.right.depth();
        }
        if (this.right == null) {
            return 1 + this.left.depth();
        }
        return 1 + Math.max(this.left.depth(), this.right.depth());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (left != null) {
            sb.append(left);
            sb.append(", ");
        }
        sb.append(data);
        if (right != null) {
            sb.append(", ");
            sb.append(right);
        }
        return sb.toString();
    }
}
