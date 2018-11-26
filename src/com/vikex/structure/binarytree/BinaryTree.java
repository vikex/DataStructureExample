package com.vikex.structure.binarytree;

import java.util.*;

public class BinaryTree<T> {

    /**
     *            1
     *         /     \
     *        /       \
     *       2         3
     *     /   \        \
     *    /     \        \
     *   4       5        6
     *    \     / \      /
     *     \   /   \    /
     *      7 8     9  10
     */
    public static BinaryTreeNode<Integer> autoGenerateBinaryTree(BinaryTree<Integer> binaryTree) {
        List<Integer> binaryTreeData = new LinkedList<>();
        binaryTreeData.add(1);

        binaryTreeData.add(2);
        binaryTreeData.add(4);
        // 创建空节点
        binaryTreeData.add(null);
        binaryTreeData.add(7);
        binaryTreeData.add(null);
        binaryTreeData.add(null);
        binaryTreeData.add(5);
        binaryTreeData.add(8);
        binaryTreeData.add(null);
        binaryTreeData.add(null);
        binaryTreeData.add(9);
        binaryTreeData.add(null);
        binaryTreeData.add(null);
        binaryTreeData.add(3);
        binaryTreeData.add(null);
        binaryTreeData.add(6);
        binaryTreeData.add(10);
        binaryTreeData.add(null);
        binaryTreeData.add(null);
        binaryTreeData.add(null);

        return binaryTree.generateBinaryTree(binaryTreeData);
    }

    // 先序创建二叉树
    public BinaryTreeNode<T> generateBinaryTree(List<T> binaryTreeData) {
        BinaryTreeNode<T> root = null;
        T data = null;
        if (!binaryTreeData.isEmpty() && ((data = binaryTreeData.remove(0)) != null)) {
            root = new BinaryTreeNode<T>(data);
            root.left = generateBinaryTree(binaryTreeData);
            root.right = generateBinaryTree(binaryTreeData);
        }
        return root;
    }

    // 前序遍历二叉树（DLR），时间复杂度O(n)，空间复杂度O(n)
    // 前序遍历首先访问根结点然后遍历左子树，最后遍历右子树

    // 递归方式
    public void preorderTraversal(BinaryTreeNode<T> root, List<BinaryTreeNode<T>> result) {
        if (root != null) {
            result.add(root);
            preorderTraversal(root.left, result);
            preorderTraversal(root.right, result);
        }
    }

    // 非递归方式
    public void preorderTraversal(BinaryTreeNode<T> root) {
        List<BinaryTreeNode<T>> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> currentNode = root;

        while (currentNode != null || !stack.empty()) {
            if (currentNode != null) {
                // 当前节点不为空，访问并压入栈中。并将当前节点赋值为左儿子
                stack.push(currentNode);
                result.add(currentNode);
                currentNode = currentNode.left;
            } else {
                // 当前节点为空：
                //   1、当p指向的左儿子时，此时栈顶元素必然是它的父节点
                //   2、当p指向的右儿子时，此时栈顶元素必然是它的爷爷节点
                // 取出栈顶元素，赋值为right
                currentNode = stack.pop();
                currentNode = currentNode.right;
            }
        }

        System.out.println("preOrderTraversal: " + result);
    }

    // 中序遍历二叉树（LDR），时间复杂度O(n)，空间复杂度O(n)
    // 中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。

    // 递归方式
    public void inorderTraversal(BinaryTreeNode<T> root, List<BinaryTreeNode<T>> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root);
            inorderTraversal(root.right, result);
        }
    }

    // 非递归方式
    public void inorderTraversal(BinaryTreeNode<T> root) {
        List<BinaryTreeNode<T>> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> currentNode = root;

        while (currentNode != null || !stack.empty()) {
            if (currentNode != null) {
                // 当前节点不为空，压入栈中。并将当前节点赋值为左儿子
                stack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                // 当前节点为空：
                //   1、当p指向的左儿子时，此时栈顶元素必然是它的父节点
                //   2、当p指向的右儿子时，此时栈顶元素必然是它的爷爷节点
                // 取出并访问栈顶元素，赋值为right
                currentNode = stack.pop();
                result.add(currentNode);
                currentNode = currentNode.right;
            }
        }

        System.out.println("inorderTraversal: " + result);
    }


    // 后序遍历（LRD），先依次遍历左右子树，最后根结点

    // 递归方式
    public void postorderTraversal(BinaryTreeNode<T> root, List<BinaryTreeNode<T>> result) {
        if (root != null) {
            postorderTraversal(root.left, result);
            postorderTraversal(root.right, result);
            result.add(root);
        }
    }

    // 非递归方式
    // 后序遍历的非递归算法是三种顺序中最复杂的，原因在于，后序遍历是先访问左、右子树,再访问根节点，而在非递归算法中，
    // 利用栈回退到时，并不知道是从左子树回退到根节点，还是从右子树回退到根节点，如果从左子树回退到根节点，此时就应该
    // 去访问右子树，而如果从右子树回退到根节点，此时就应该访问根节点。所以相比前序和后序，必须得在压栈时添加信息，以
    // 便在退栈时可以知道是从左子树返回，还是从右子树返回进而决定下一步的操作。
    public void postorderTraversal(BinaryTreeNode<T> root) {

        // 带有tag的树节点
        class BinaryTreeNodeWrapper<T> {
            BinaryTreeNode<T> binaryTreeNode;
            char tag;

            public BinaryTreeNodeWrapper(BinaryTreeNode<T> binaryTreeNode, char tag) {
                this.binaryTreeNode = binaryTreeNode;
                this.tag = tag;
            }
        }

        List<BinaryTreeNode<T>> result = new ArrayList<>();

        Stack<BinaryTreeNodeWrapper<T>> stack=new Stack<>();
        BinaryTreeNode<T> current = root;
        BinaryTreeNodeWrapper<T> currentWrapper;

        while (current != null || !stack.isEmpty()) {
            // 遍历左子树
            while (current != null) {
                currentWrapper = new BinaryTreeNodeWrapper<T>(current, 'L');
                stack.push(currentWrapper);
                current = current.left;
            }

            // 左右子树访问完毕访问根节点
            while (!stack.isEmpty() && stack.peek().tag == 'R') {
                currentWrapper = stack.pop();
                result.add(currentWrapper.binaryTreeNode);
            }

            // 遍历右子树
            if (!stack.isEmpty()) {
                currentWrapper = stack.peek();
                currentWrapper.tag = 'R';
                current = currentWrapper.binaryTreeNode.right;
            }
        }

        System.out.println("postorderTraversal: " + result);
    }

    // 逐层遍历，逐层遍历的空间复杂性均为O (n)，时间复杂性为O(n)
    // 在逐层遍历过程中，按从顶层到底层的次序访问树中元素，在同一层中，从左到右进行访问。
    // 逐层遍历与前序、中序、后序遍历不同。逐层遍历用到了队列，而前序、中序、后序需要用到栈。

    public void levelTraversal(BinaryTreeNode<T> root) {
        List<BinaryTreeNode<T>> result = new ArrayList<>();
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> n = queue.poll();
            result.add(n);

            if (n.left != null) {
                queue.offer(n.left);
            }

            if (n.right != null) {
                queue.offer(n.right);
            }
        }

        System.out.println("levelTraversal: " + result);
    }

    // 求解第n层节点的个数，递归方式
    // 如果layer=0，则存在一个节点返回1。否则计算layer-1层的左子树和右子树的个数。
    public int calculateLayerCount(BinaryTreeNode<T> root, int layer) {
        if(root == null) {
            return 0;
        }

        if (layer == 0) {
            // 根节点
            return 1;
        }

        return calculateLayerCount(root.right, layer - 1) +
                calculateLayerCount(root.left, layer -1);
    }

    // 逐层打印，按逐层遍历方式，用每层的节点个数控制是否是最后节点，以便打印
    public void printBinaryTreeInLayer(BinaryTreeNode<T> root) {
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        // 存放每层的节点
        List<BinaryTreeNode<T>> layerNodeList = new ArrayList<>();
        // key为层数，value为该层的节点
        Map<Integer, BinaryTreeNode<T>[]> result = new HashMap<>();
        int layer = 0;
        int currentLayerCount = 0;
        int nextLayerCount = 0;

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> n = queue.poll();
            currentLayerCount--;
            layerNodeList.add(n);

            if (n.left != null) {
                queue.offer(n.left);
                nextLayerCount++;
            }

            if (n.right != null) {
                queue.offer(n.right);
                nextLayerCount++;
            }

            if (currentLayerCount <= 0) {
                int[] a = new int[0];

                BinaryTreeNode[] layerNodeArray = layerNodeList.toArray(new BinaryTreeNode[layerNodeList.size()]);
                result.put(layer, layerNodeArray);
                layer++;
                layerNodeList.clear();
                currentLayerCount = nextLayerCount;
                nextLayerCount = 0;
            }
        }

        System.out.println("printBinaryTreeInLayer:");
        for (Map.Entry<Integer, BinaryTreeNode<T>[]> entry : result.entrySet()) {
            System.out.println("    第" + entry.getKey() + "层: " + Arrays.toString(entry.getValue()));
        }
    }

}
