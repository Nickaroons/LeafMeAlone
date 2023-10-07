package com.MatrixEngine.Components.AI.Pathfinding;

public class Node {
    Node parent;
    public int col, row;
    int gCost, hCost, fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node (int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return "Node{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
