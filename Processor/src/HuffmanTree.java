public class HuffmanTree implements Comparable<HuffmanTree>{

    final char c;
    final int f;
    final HuffmanTree left;
    final HuffmanTree right;

    HuffmanTree(char c, int f, HuffmanTree left, HuffmanTree right) {
        this.c = c;
        this.f = f;
        this.left = left;
        this.right = right;
    }

    boolean isLeafNode() {
        if ((this.left == null && this.right == null)) {
            return true;
        } else {
            return false;
        }
    }

    char getChar(){
        return c;
    }

    @Override
    public int compareTo(HuffmanTree o) {
        return this.f - o.f;
    }
}
