package com.wndexx.tree;

/**
 * 二叉树
 *
 * @author wndexx 2022-03-31 9:24
 */
public class BinaryTree {
    public HeroNode root;

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        this.root.preOrder();
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        this.root.inOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        this.root.postOrder();
    }

    /**
     * 前序遍历查找
     *
     * @param no 待查找的 HeroNode 的 no
     * @return 如果找到，返回找到的结点；否则，返回 null
     */
    public HeroNode preOrderSearch(int no) {
        if (root == null)
            return null;
        return root.preOrderSearch(no);
    }

    /**
     * 中序遍历查找
     *
     * @param no 待查找的 HeroNode 的 no
     * @return 如果找到，返回找到的结点；否则，返回 null
     */
    public HeroNode inOrderSearch(int no) {
        if (root == null)
            return null;
        return root.inOrderSearch(no);
    }

    /**
     * 后序遍历查找
     *
     * @param no 待查找的 HeroNode 的 no
     * @return 如果找到，返回找到的结点；否则，返回 null
     */
    public HeroNode postOrderSearch(int no) {
        if (root == null)
            return null;
        return root.postOrderSearch(no);
    }

    /**
     * 递归删除结点
     * 规定：
     * 1. 如果删除的结点是叶子节点，则删除该结点
     * 2. 如果删除的结点是非叶子结点，则删除该子树
     *
     * @param no 待删除结点的 no
     */
    public void delNode(int no) {
        // 如果树是空树 root = null，没有必要删除
        if (root == null) {
            System.out.println("空树，不能删除");
            return;
        }
        // 如果 root 结点的 no 等于 no，则等价于将二叉树置空
        if (root.no == no) {
            root = null;
            return;
        }
        root.delNode(no);
    }
}

/**
 * HeroNode 结点
 */
class HeroNode {
    public int no;
    public String name;
    /**
     * 左子结点，默认 null
     */
    public HeroNode left;
    /**
     * 右子结点，默认 null
     */
    public HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 1. 先输出父结点
        System.out.println(this);
        // 2. 递归向左子树前序遍历
        if (this.left != null)
            this.left.preOrder();
        // 3. 递归向右子树前序遍历
        if (this.right != null)
            this.right.preOrder();

    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        // 1. 递归向左子树中序遍历
        if (this.left != null)
            this.left.inOrder();
        // 2. 输出父结点
        System.out.println(this);
        // 3. 递归向右子树中序遍历
        if (this.right != null)
            this.right.preOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        // 1. 递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 2. 递归向右子树后序遍历
        if (this.right != null)
            this.right.postOrder();
        // 3. 输出父结点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 待查找的 HeroNode 的 no
     * @return 如果找到，返回找到的结点；否则，返回 null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        // 1. 比较当前结点
        if (this.no == no)
            return this;
        HeroNode resNode = null;
        // 2. 判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
        //    如果左递归前序查找，找到结点，则返回
        if (this.left != null)
            resNode = this.left.preOrderSearch(no);
        if (resNode != null) // 左子树找到
            return resNode;
        // 3. 继续判断当前的结点的右子结点是否为空，如果不为空，则继续向右递归前序查找
        if (this.right != null)
            resNode = this.right.preOrderSearch(no);
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 待查找的 HeroNode 的 no
     * @return 如果找到，返回找到的结点；否则，返回 null
     */
    public HeroNode inOrderSearch(int no) {
        HeroNode resNode = null;
        // 1. 先判断当前结点的左子结点是否为空，如果不为空，则递归中序查找
        if (this.left != null)
            resNode = this.left.inOrderSearch(no);
        if (resNode != null)
            return resNode;
        System.out.println("进入中序查找");
        // 2. 如果没有找到，就和当前结点进行比较，如果找到，则返回当前结点
        if (this.no == no)
            return this;
        // 3. 继续进行右递归的中序查找
        if (this.right != null)
            resNode = this.right.inOrderSearch(no);
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no 待查找的 HeroNode 的 no
     * @return 如果找到，返回找到的结点；否则，返回 null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        // 1. 先判断当前结点的左子结点是否为空，如果不为空，则递归后序查找
        if (this.left != null)
            resNode = this.left.postOrderSearch(no);
        if (resNode != null) // 说明在左子树找到
            return resNode;
        // 2. 如果左子树没有找到，则右子树递归进行后序遍历查找
        if (this.right != null)
            resNode = this.right.postOrderSearch(no);
        if (resNode != null)
            return resNode;
        System.out.println("进入后序查找");
        // 3. 如果左右子树都没有找到，就比较当前结点是不是
        if (this.no == no)
            return this;
        return null;
    }

    /**
     * 递归删除结点
     * 规定：
     * 1. 如果删除的结点是叶子节点，则删除该结点
     * 2. 如果删除的结点是非叶子结点，则删除该子树
     *
     * @param no 待删除结点的 no
     */
    public void delNode(int no) {
        // 1. 如果当前结点的左子结点不为空，并且左子结点就是要删除的结点，就将 this.left = null，结束递归删除
        //    否则，需要向左子树进行递归删除，如果找到，删除并结束递归
        if (this.left != null) {
            if (this.left.no == no) {
                this.left = null;
                return;
            }
            this.left.delNode(no);
        }

        // 2. 如果当前结点的右子结点不为空，并且右子结点就是要删除的结点，就将 this.right = null，结束递归删除
        // 否则，应当向右子树进行递归删除
        if (this.right != null) {
            if (this.right.no == no) {
                this.right = null;
                return;
            }
            this.right.delNode(no);
        }
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}





















