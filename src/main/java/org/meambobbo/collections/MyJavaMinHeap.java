package org.meambobbo.collections;

import java.util.ArrayList;

public class MyJavaMinHeap <T extends Comparable> {
    private ArrayList<T> nodes = new ArrayList<>();;
    private int firstNullInd = 0;

    public void insert(T node) {
        if (firstNullInd > nodes.size() - 1) {
            nodes.add(null);
            firstNullInd = nodes.size() - 1;
        }
        nodes.set(firstNullInd, node);
        siftUp(firstNullInd);
        for (int i = firstNullInd; i < nodes.size(); i++) {
            if (nodes.get(i) == null) {
                firstNullInd = i;
                break;
            }
        }
        if (nodes.get(firstNullInd) != null) {
            firstNullInd = firstNullInd + 1;
            nodes.add(null);
        }
    }

    private int parent(int i) {
        return (int) Math.floor((i-1)/2);
    }
    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return left(i) + 1;
    }

    private void siftUp(int i) {
        if (i <= 0 || nodes.get(i) == null) {
            return;
        }
        int p = parent(i);
        if (nodes.get(i).compareTo(nodes.get(p)) < 0) {
            swap(i, p);
            siftUp(p);
        }
    }

    private void swap(int i, int j) {
        T node = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, node);
    }

    public T peek() {
        return nodes.get(0);
    }

    public T pop() {
        T node = nodes.get(0);
        nodes.set(0, null);
        int newNullPos = siftDown(0);
        if (firstNullInd > newNullPos) {
            firstNullInd = newNullPos;
        }
        return node;
    }

    public int size() {
        return nodes.size();
    }

    private int siftDown(int i) {
        int l = left(i);
        int r = right(i);
        T left, right;
        int ind;

        if (l >= nodes.size()) {
            left = null;
            right = null;
        } else if (r >= nodes.size()) {
            left = nodes.get(l);
            right = null;
        } else {
            left = nodes.get(l);
            right = nodes.get(r);
        }

        if (left != null) {
            if (right == null) {
                ind = l;
            } else {
                if (left.compareTo(right) <= 0) {
                    ind = l;
                } else {
                    ind = r;
                }
            }
        } else {
            if (right == null) {
                return i;
            } else {
                ind = r;
            }
        }

        swap(i, ind);
        return siftDown(ind);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T node : nodes) {
            if (node == null) {
                sb.append("null");
            } else {
                sb.append(node.toString());
            }
            sb.append(", ");
        }
        String res = sb.toString();
        return sb.toString().substring(0, res.length() -1);
    }
}
