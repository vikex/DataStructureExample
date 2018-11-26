package com.vikex.structure.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeExample {

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        BinaryTreeNode<Integer> binaryTreeNode = BinaryTree.autoGenerateBinaryTree(binaryTree);

        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        // 前序遍历
        binaryTree.preorderTraversal(binaryTreeNode, result);
        System.out.println("preorderTraversal recursion: " + result);
        binaryTree.preorderTraversal(binaryTreeNode);

        // 中序遍历
        result.clear();
        binaryTree.inorderTraversal(binaryTreeNode, result);
        System.out.println("inorderTraversal recursion: " + result);
        binaryTree.inorderTraversal(binaryTreeNode);

        // 后续遍历
        result.clear();
        binaryTree.postorderTraversal(binaryTreeNode, result);
        System.out.println("postorderTraversal recursion: " + result);
        binaryTree.postorderTraversal(binaryTreeNode);

        // 逐层遍历
        binaryTree.levelTraversal(binaryTreeNode);

        // 计算n层节点个数
        int count = binaryTree.calculateLayerCount(binaryTreeNode, 3);
        System.out.println("layer 3 node count: " + count);

        // 逐层打印
        binaryTree.printBinaryTreeInLayer(binaryTreeNode);
    }
}
