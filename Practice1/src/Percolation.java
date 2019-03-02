public class Percolation {

    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qf;

    /**
     * Creates N-by-N grid, with all sites blocked.
     */
    public Percolation(int N) {
        size = N;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    /**
     * Opens site (row i, column j) if it is not already.
     */
    public void open(int i, int j) {
        opened[i - 1][j - 1] = true;
        if (i == 1) {
            qf.union(getQFIndex(i, j), top);
        }
        if (i == size) {
            qf.union(getQFIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return opened[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (0 < i && i <= size && 0 < j && j <= size) {
            return qf.connected(top, getQFIndex(i , j));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    private int getQFIndex(int i, int j) {
        return size * (i - 1) + j;
    }

    class WeightedQuickUnionUF {

        private int[] id;
        private int[] sz;
        private int count;

        public WeightedQuickUnionUF(int n){
            count = n;
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i<n; i++)
                id[i]=i;
            for (int i = 0; i<n; i++)
                sz[i] = 1;
        }

        public int count(){
            return count;
        }

        public boolean connected(int p, int q){
            return find(p) == find(q);
        }

        private int find(int p){
            while(p != id[p])
                p=id[p];
            return p;
        }

        public void union(int p, int q){
            int i = find(p);
            int j = find(q);
            if (i==j) return;
            if (sz[i]<sz[j]){
                id[i] = j;
                sz[j] +=sz[i];
            }else{
                id[j] = i;
                sz[i] += sz[j];
            }
            count--;
        }

        public String toString(){
            StringBuilder str = new StringBuilder();
            for (int i:id){
                str.append(i);
                str.append(" ");
            }
            return str.toString();
        }
    }

}