import java.util.List;
import java.util.ArrayList;

public class Node
{
    /* List of nodes that this node is connected to */
    private List<Node> _connectingNodes;
    /* Boolean of whether this node has been accessed yet */
    private boolean _accessed;
    /* Integer of what number this node is */
    private int _nodeNumber;

    /****************************************************************/
    /* Method: Constructor for class Node                           */
    /* Purpose: Instantiate a Node based on specified node number   */
    /* Parameters:                                                  */
    /*    int number - Number of the node in the graph              */
    /* Returns Instantiated node                                    */
    /****************************************************************/
    public Node(int number)
    {
        _nodeNumber = number;
        _connectingNodes = new ArrayList<Node>();
    }

    /************************************************************/
    /* Method: addEdge                                          */
    /* Purpose: Add edge to this node's list of connected nodes */
    /* Parameters:                                              */
    /*    Node connector - Node to connect to this node         */
    /* Returns Void                                             */
    /************************************************************/
    public void addEdge(Node connector)
    {
        if(_connectingNodes.contains(connector))
            return; /* We already have this node as an edge */
        _connectingNodes.add(connector);
    }

    /* Getter for the connected nodes */
    public List<Node> getConnectingNodes() { return _connectingNodes; }
    
    /* Getter for rather the node is accessed */
    public boolean getAccessed() { return _accessed; }
    /* Setter for rather the node is accessed */
    public void setAccessed(boolean val) { _accessed = val; }
    /* Getter for the node's number */
    public int getNumber() { return _nodeNumber; }
}