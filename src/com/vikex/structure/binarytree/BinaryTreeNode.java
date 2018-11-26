package com.vikex.structure.binarytree;

public class BinaryTreeNode<T> {
    public T element;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T element) {
        this.element = element;
    }

    public BinaryTreeNode(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(element);
    }
}
