package com.top;

public class SLNode<E> {

    public SLNode<E>[] next;

    private E value;
    private int level;

    @SuppressWarnings("unchecked")
    public SLNode(E value, int level)
    {
        this.value = value;
        this.level = level;
        // 0 is the base level
        this.next = new SLNode[level+1];
    }

    public E getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "[ level " + level + " | value "+value + " ]";
    }
}
