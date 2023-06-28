import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Uses an Adjacency List to store graph
 * Each Airport value will have an ArrayList of type Airport
 * The ArrayList will represent the connections between each Airport
 * @author Jordan Ayiku Teye
 * 
 */
class Graph <E>{
    int noVertice = 0;
    HashMap<Airport, ArrayList<Airport>> graph = new HashMap<Airport, ArrayList<Airport>>();

    //Adds a node as the key for the HashMap
    //Nodes will store Airports which will be unique
    public void addNode(Airport Node){
        ArrayList<Airport> adjRoutes = new ArrayList<Airport>();
        graph.put(Node, adjRoutes);
        noVertice++;
    }

    //Values of HashMap stores Arrays with connections
    public void addConnection(Airport sourceAirport, Airport destAirport){
        if(graph.containsKey(sourceAirport)){
            graph.get(sourceAirport).add(destAirport);
            graph.get(destAirport).add(sourceAirport);
        }else{System.out.println("");}
    }

    //Removes connections
    public void removeConnection(Airport sourceAirport, Airport destAirport){
        if(graph.containsKey(sourceAirport)){
            graph.get(sourceAirport).remove(destAirport);
            graph.get(destAirport).remove(sourceAirport);
        }else{System.out.println("The Route does not exist");}
    }

    // Searches for Airports that interconnect from chosen Airports
    // and returns an Array of the connected airports
    public ArrayList<Airport> bfs(Airport startAirport, Airport destAirport){
        Node node = new Node(startAirport, null); // tracks parent nodes
        ArrayList<Node> frontier = new ArrayList<Node>();
        ArrayList<Airport> successors = new ArrayList<Airport>();
        Set<Airport> explored = new HashSet<Airport>(); 
        ArrayList<Airport> output = new ArrayList<Airport>();
        
        boolean breakValue = false; // Allows outer loop to be broken

        // returns solution if Airports have a direct connection
        if(graph.get(startAirport).contains(destAirport)){
            output = node.solution_path();
            
        }else{
        // Breadth First Search Algorithm
            // searches only if specified nodes are reachable
            if((!graph.get(startAirport).isEmpty()) && (!graph.get(destAirport).isEmpty())){
                frontier.add(node);
                while(frontier.size()> 0){
                    node = frontier.remove(0);
                    explored.add(node.state);
                    successors = graph.get(node.state);
                    for(int i = 0; i< successors.size(); i++){
                        Node child = new Node(successors.get(i), node);
                        if((!explored.contains(child.state)) && (!frontier.contains(child))){
                            if(graph.get(child.state).contains(destAirport)){
                                output = child.solution_path();
                                output.add(destAirport);
                                breakValue = true;
                                break;
                            }
                            frontier.add(child);
                        }
                    }
                    if(breakValue){
                        break;
                    }
                }
            }else{
                System.out.println("No solution path");
            }
        }
        return output;
    }
}