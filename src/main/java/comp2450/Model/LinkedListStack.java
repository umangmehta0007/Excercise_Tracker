package comp2450.Model;

import com.google.common.base.Preconditions;

public class LinkedListStack<T> implements Stack<T> {

    private Node<T> top;     // top of stack
    private int size;        // number of items

    private void checkValidList() {
        Preconditions.checkState(size >= 0, "Size cannot be negative");
        Preconditions.checkState((size == 0 && top == null) ||(size > 0 && top != null),
                "Top can never be null, when there is atleast on node");
    }

    public LinkedListStack() {
        this.top = null;
        this.size = 0;
        checkValidList();
    }


    @Override
    public void push(T item) {

        Preconditions.checkNotNull(item, "Cannot push null");

        checkValidList();

        top = new Node<>(item, top);
        size++;

        checkValidList();
    }

    @Override
    public T pop() throws EmptyStackException {

        checkValidList();

        if (isEmpty()) {
            throw new EmptyStackException("Cannot pop an empty stack");
        }

        T data = top.data;
        top = top.next;
        size--;

        checkValidList();
        return data;
    }

    @Override
    public T peek() throws EmptyStackException {

        checkValidList();

        if (isEmpty()) {
            throw new EmptyStackException("Cannot peek an empty stack");
        }

        checkValidList();
        return top.data;
    }

    @Override
    public int size() {

        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node<T> {
        private final T data;
        private Node<T> next;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}

