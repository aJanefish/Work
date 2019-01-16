package com.algorithm.redblack;

public class RedBlackNode {


    protected RedBlackNode parent;
    protected RedBlackNode left;
    protected RedBlackNode right;

    protected int values;
    protected Color color = Color.BLACK;

    public RedBlackNode(RedBlackNode parent, RedBlackNode left, RedBlackNode right, int values, Color color) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.values = values;
        this.color = color;
    }


    protected Color getNegateColor() {
        if (this.color == Color.BLACK) {
            return Color.RED;
        }
        return Color.BLACK;
    }

    protected boolean isRed() {
        return color == Color.RED;
    }

    protected void NegateColor() {
        if (this.color == Color.BLACK) {
            this.color = Color.RED;
        } else {
            this.color = Color.BLACK;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "values=" + values +
                ", color=" + color +
                '}';
    }
}

