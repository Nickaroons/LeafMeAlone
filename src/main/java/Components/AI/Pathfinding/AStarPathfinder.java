package Components.AI.Pathfinding;

import Components.StandardLibs.Component;
import DataTypes.Time;
import DataTypes.Vector2;
import Input.Mouse;
import Objects.GameObject;
import com.MatrixEngine.Engine;
import com.MatrixEngine.ObjectHandler;

import java.awt.*;
import java.util.ArrayList;

public class AStarPathfinder extends Component {

    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    int goalX, goalY;
    private int tileSize;
    private int maxCol, maxRow;
    private int moveSpeed;
    private boolean staticPath, pathStarted;
    private double updateInterval = 0.01;
    private long lastUpdateTime;

    private GameObject objectBeingTracked = null;

    public AStarPathfinder(GameObject gameObject, int tileSize, int maxCol, int maxRow) {
        super(true, gameObject);
        this.tileSize = tileSize;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        instantiateNodes();

    }

    public void instantiateNodes() {
        node = new Node[maxCol][maxRow];
        int row = 0;
        int col = 0;
        while (col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int row = 0;
        int col = 0;
        while (col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);

            //reset open, checked and solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
        // reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
         System.out.println(goalCol + "," + goalRow);
            resetNodes();
            startNode = node[(startCol - (int) (attachedObj.transform.position.getX()/2/tileSize))][startRow - (int) (attachedObj.transform.position.getY()/2/tileSize)];
            currentNode = startNode;
            goalNode = node[goalCol - (int) (attachedObj.transform.position.getX()/2/tileSize)][goalRow - (int) (attachedObj.transform.position.getY()/2/tileSize)];
            openList.add(currentNode);


        int row = 0;
        int col = 0;

        while (col < maxCol && row < maxRow) {
            //Set solid nodes
            //Check Tiles
            for (int i = 0; i < ObjectHandler.getObjects().size(); i++){
                GameObject obj = ObjectHandler.getObjects().get(i);
                if ((objectBeingTracked == null || (!obj.equals(objectBeingTracked))) && (col != goalCol || row != goalRow) && !obj.equals(attachedObj) && obj.hasCollider && col*tileSize+(attachedObj.transform.position.getX()/2) < obj.transform.position.getX() + obj.getWidth() && col*tileSize + tileSize + (attachedObj.transform.position.getX()/2) > obj.transform.position.getX() && row*tileSize+(attachedObj.transform.position.getY()/2) < obj.transform.position.getY() + obj.getHeight() && tileSize + row*tileSize+(attachedObj.transform.position.getY()/2)> obj.transform.position.getY()) {
                    node[col][row].solid = true;
                }
            }

            getCost(node[col][row]);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }

    }

    private void getCost(Node node) {
        // G Cost
        int xDist = Math.abs(node.col - startNode.col);
        int yDist = Math.abs(node.row - startNode.row);
        node.gCost = xDist + yDist;

        //H Cost
        xDist = Math.abs(node.col - goalNode.col);
        yDist = Math.abs(node.row - goalNode.row);
        node.hCost = xDist + yDist;

        //F Cost
        node.fCost = node.hCost + node.gCost;
    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            int row = currentNode.row;
            int col = currentNode.col;

            //check current node
            currentNode.checked = true;
            openList.remove(currentNode);

            if (row - 1 >= 0) {
                openNode(node[col][row-1]);
            }
            if (col - 1 >= 0) {
                openNode(node[col-1][row]);
            }
            if (row + 1 < maxRow) {
                openNode(node[col][row+1]);
            }
            if (col + 1 < maxCol) {
                openNode(node[col+1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodeFCost = Integer.MAX_VALUE;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                }
                // if f cost equal then check g cost
                else if(openList.get(i).fCost == bestNodeFCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // If there is no node in the openlist, end the loop;

            if (openList.size() == 0) {
                break;
            }

            // After the list bestNodeIndex is the next step
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;

        }

        return goalReached;
    }

    public void openNode(Node node) {
        if (node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

    @Override
    public void render(Graphics g) {
        for (Node n : pathList) {
            g.setColor(Color.RED);
            g.drawRect((int) Engine.relativePosition((int) (n.col*tileSize+attachedObj.transform.position.getX()/2), (int) (n.row*tileSize+(attachedObj.transform.position.getY()/2))).getX(), (int) Engine.relativePosition((int) (n.col*tileSize+(attachedObj.transform.position.getX()/2)), (int) (n.row*tileSize+(attachedObj.transform.position.getY()/2))).getY(), tileSize, tileSize);
        }

        for (Node[] a : node) {
            for (Node n : a) {
                if (n.solid) {
                    g.setColor(Color.BLACK);
                    g.drawRect((int) Engine.relativePosition((int) (n.col*tileSize+attachedObj.transform.position.getX()/2), (int) (n.row*tileSize+(attachedObj.transform.position.getY()/2))).getX(), (int) Engine.relativePosition((int) (n.col*tileSize+(attachedObj.transform.position.getX()/2)), (int) (n.row*tileSize+(attachedObj.transform.position.getY()/2))).getY(), tileSize, tileSize);
                }
            }
        }
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - lastUpdateTime > updateInterval * 1000){
            if (objectBeingTracked != null){
                goalX = (int) (objectBeingTracked.transform.position.getX()/tileSize);
                goalY = (int) (objectBeingTracked.transform.position.getY()/tileSize);
            }
                if (!pathStarted) {
                    attachedObj.searchPath(new Vector2(goalX, goalY));
                    pathStarted = true;
                }
                if (!staticPath) {
                    attachedObj.searchPath(new Vector2(goalX, goalY));
                }

            lastUpdateTime = System.currentTimeMillis();
        }
        if (pathList.size() > 0) {
            moveObj();
        }
        if (staticPath) {
            if (pathList.size() > 0 && Math.abs((int) attachedObj.transform.position.getX() - pathList.get(0).col * tileSize) < 2 && (Math.abs((int) attachedObj.transform.position.getY() - pathList.get(0).row * tileSize) < 2)) {
                pathList.remove(0);
            }
        }
    }

    public void findPath(int goalX, int goalY, boolean staticPath) {
        this.goalX = goalX/tileSize;
        this.goalY = goalY/tileSize;
        this.staticPath = staticPath;
        pathStarted = false;
        objectBeingTracked = null;

    }

    public void findPathToGameObject(GameObject obj)  {
        findPath((int)Engine.relativePosition((int) obj.transform.position.getX(), (int) obj.transform.position.getY()).getX(), (int)Engine.relativePosition((int) obj.transform.position.getX(), (int) obj.transform.position.getY()).getY(), false);
        objectBeingTracked = obj;
    }

    public void setTileSize(int size) {
        tileSize = size;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setPathMoveSpeed(int speed) {
        moveSpeed = speed;
    }

    private void moveObj() {
        double xDist = ((pathList.get(0).col * tileSize)) - attachedObj.transform.position.getX()/2;
        double yDist = ((pathList.get(0).row * tileSize)) - attachedObj.transform.position.getY()/2;
        if (xDist >= moveSpeed*Time.deltaTime) {
            attachedObj.transform.position.move(moveSpeed * Time.deltaTime, 0);
        } else if (Math.abs(xDist) < 1) {}
        else {
            attachedObj.transform.position.move(-moveSpeed * Time.deltaTime, 0);
        }
        if (yDist >= moveSpeed*Time.deltaTime) {
            attachedObj.transform.position.move(0, moveSpeed * Time.deltaTime);
        } else if (Math.abs(yDist) < 1) {}
        else {
            attachedObj.transform.position.move(0, -moveSpeed * Time.deltaTime);
        }
    }

    public void setUpdateInterval(double interval) {
        updateInterval = interval;
    }
}
