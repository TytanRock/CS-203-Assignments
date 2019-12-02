/**
 * Class to hold all methods and member variables related to the overall graph
 */
class Graph
{
    /* Node array of all nodes in graph */
    private Node[] _nodes;
    /* Integer with number of components in graph */
    private int _numberOfComponents;
    /* String with all the components in string form for graph */
    private String _components;
    /* String with all cycle information in string form for graph */
    private String _cycle;
    /* Boolean of whether a cycle was successfully found */
    private boolean _cycleFound;
    
    /************************************************************/
    /* Method: Constructor for class Graph                      */
    /* Purpose: Instantiate a graph based on the parsable file  */
    /* Parameters:                                              */
    /*    String input - String to be parsed into a graph       */
    /* Returns Instantiated graph                               */
    /************************************************************/
    public Graph(String input)
    {
        String[] allInputs = input.split(" ");

        /* Create array of nodes and fill array with node references */
        _nodes = new Node[Integer.parseInt(allInputs[0])];
        for(int i = 0; i < _nodes.length; i++)
        {
            _nodes[i] = new Node(i+1);
        }

        /* For the remaining inputs, specify edges between nodes */
        for(int i = 1; i < allInputs.length; i++)
        {
            int[] nodesIndices = parseEdge(allInputs[i]);
            _nodes[nodesIndices[0]].addEdge(_nodes[nodesIndices[1]]);
            _nodes[nodesIndices[1]].addEdge(_nodes[nodesIndices[0]]);
        }

        /* Break graph into components */
        calculateComponents();

        /* Determine if there is any cyclical relationship */
        calculateCyclical();
    }

    /************************************************************************/
    /* Method: getComponents                                                */
    /* Purpose: Get the number of components in a graph and what they are   */
    /* Parameters:                                                          */
    /* Returns: String of number of components and what they are            */
    /************************************************************************/
    public String getComponents() 
    {
        /* Formatting to allow proper English */
        if(_numberOfComponents == 1)
            return ("1 component in graph: " + _components);
        else
            return (_numberOfComponents + " components in graph: " + _components); 
    }

    /************************************************************************/
    /* Method: getCycle                                                     */
    /* Purpose: Get the cycle of a graph if it exists                       */
    /* Parameters:                                                          */
    /* Returns: String of any cycles in graph if they exist, or acyclical   */
    /************************************************************************/
    public String getCycle() 
    {
        /* If a cycle is found, return it */
        /* otherwise let user know a cycle was not found */
        if(_cycleFound)
            return _cycle;
        else
            return "Cycle not found";
    }

    /************************************************************************/
    /* Method: clearAccessed                                                */
    /* Purpose: Set the 'accessed' property of every node to false          */
    /* Parameters:                                                          */
    /* Returns: void                                                        */
    /************************************************************************/
    private void clearAccessed()
    {
        /* For every node in nodes, set their accessed to false */
        for(Node node : _nodes)
        {
            node.setAccessed(false);
        }
    }

    /************************************************************************/
    /* Method: parseEdge                                                    */
    /* Purpose: Parse the string representation of an edge                  */
    /* Parameters:                                                          */
    /* String edge - String representation of an edge connecting two nodes  */
    /* Returns: Int array of the two nodes the edge connects                */
    /************************************************************************/
    private int[] parseEdge(String edge)
    {
        /* First create an int array to be filled */
        int[] retval = new int[2];

        /* String copy of parameter to be modified */
        String copy = edge;
        copy = copy.substring(1, copy.length()-1);
        String[] arr = copy.split(",");

        /* Get the index of the node in the 0-indexed array */
        retval[0] = Integer.parseInt(arr[0]) - 1;
        retval[1] = Integer.parseInt(arr[1]) - 1;

        return retval;
    }

    /************************************************************************/
    /* Method: calculateCyclical                                            */
    /* Purpose: Calculate whether a graph has a cyclical component          */
    /* Parameters:                                                          */
    /* Returns: Void                                                        */
    /************************************************************************/
    private void calculateCyclical()
    {
        /* Assume a cycle was not found until we find one */
        _cycleFound = false;
        /* Ensure all nodes are cleared */
        clearAccessed();

        /* For every node that was not already accessed, we will check */
        for(Node node : _nodes)
        {
            _cycle = "Cycle Found! It is: ";
            /* If the node was not accessed, we should check it */
            if(node.getAccessed() == false)
            {
                /* If the search returned a Node, we found a cycle */
                if(depthFirstSearchingForCyclical(node, null) != null)
                {
                    /* We found a cycle */
                    _cycleFound = true;
                    break;
                }
            }
        }
    }

    /************************************************************************/
    /* Method: calculateComponents                                          */
    /* Purpose: Calculate number of components in a graph                   */
    /* Parameters:                                                          */
    /* Returns: Void                                                        */
    /************************************************************************/
    private void calculateComponents()
    {
        /* Ensure every node is cleared */
        clearAccessed();

        /* Assume number of components is 0 */
        int numberOfComponents = 0;
        /* Initialize member variable */
        _components = "";

        /* For every node that was not already accessed */
        /* we will add it to a series of components */
        for(Node node : _nodes)
        {
            if(node.getAccessed() == false)
            {
                /* Increment the number of components since we found one more */
                numberOfComponents ++;

                /* String formatting */
                _components += "{";
                /* Do the depth first search, it will fill _components */
                depthFirstSearch(node);
                _components += "} ";
            }
        }
        _numberOfComponents = numberOfComponents;
    }

    /************************************************************************/
    /* Method: depthFirstSearch                                             */
    /* Purpose: Recursively search graph using depth-first algorithm        */
    /* Parameters:                                                          */
    /*    Node currentNode - Node we are currently at                       */
    /* Returns: Last node in the search                                     */
    /************************************************************************/
    private Node depthFirstSearch(Node currentNode)
    {
        /* This node has been accessed */
        currentNode.setAccessed(true);
        /* Add this node's number to the _components string */
        _components += currentNode.getNumber();

        /* Look at every node this is connected to, if we find one that hasn't
           been accessed yet, recursively call this function on that node */
        for(Node connectedNode : currentNode.getConnectingNodes())
        {
            if(connectedNode.getAccessed() == false)
            {
                _components += " ";
                depthFirstSearch(connectedNode);
            }
        }
        /* If there are no nodes connected to this not already accessed */
        /* return this one */
        return currentNode;
    }
    
    /*****************************************************************************/
    /* Method: depthFirstSearchingForCyclical                                    */
    /* Purpose: Recursively search graph using depth-first algorithm,            */
    /*    Specifically looking for cycles                                        */
    /* Parameters:                                                               */
    /*    Node currentNode - Node we are currently at                            */
    /*    Node previousNode - Node we were last at, used to prevent short cycles */
    /* Returns: Last node in the search or null if no cycle was found            */
    /*****************************************************************************/
    private Node depthFirstSearchingForCyclical(Node currentNode, Node previousNode)
    {
        /* This node has been accessed */
        currentNode.setAccessed(true);
        
        /* First check to see if any connected nodes have already been accessed
           If they have been and they aren't the current node, we found a cycle */
        for(Node connectedNode : currentNode.getConnectingNodes())
        {
            if(connectedNode.getAccessed() == true && connectedNode != previousNode)
            {
                _cycle += connectedNode.getNumber() + " " + currentNode.getNumber();
                return connectedNode;
            }
        }
        /* No cycle detected yet, so:
           Look at every node this is connected to, if we find one that hasn't
           been accessed yet, recursively call this function on that node */
        for(Node connectedNode : currentNode.getConnectingNodes())
        {
            if(connectedNode.getAccessed() == false)
            {
                Node nextNode = depthFirstSearchingForCyclical(connectedNode,
                                                               currentNode);
                /* If we found a cycle return it, otherwise continue the search */
                if(nextNode != null)
                {
                    /* If returned node is this node, do this */
                    if(nextNode == currentNode)
                    {
                        /* Return this node if previous is null */
                        /* Otherwise return previous so recursing isn't broken */
                        return previousNode == null ? currentNode : previousNode;
                    }
                    
                    /* IF returned node is equal to previous node, do this */
                    if(nextNode == previousNode)
                    {
                        /* Finish the cycle appending */
                        _cycle += " " + currentNode.getNumber() + " " + 
                                  previousNode.getNumber();
                    }
                    else
                    {
                        /* Otherwise just add this node to the cycle */
                        _cycle += " " + currentNode.getNumber();
                    }
                    /* Return the returned node */
                    return nextNode;
                }
            }
        }
        /* If there are no nodes connected to this not already accessed */
        /* return null */
        return null;
    }
}