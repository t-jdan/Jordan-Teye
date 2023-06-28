import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class Node {
    Airport state;
    Node parent;
    
    /**
     * Represents a node in a graph
     * @param state
     * @param parent
     */
    public Node(Airport state, Node parent) {
        this.state = state;
        this.parent = parent;
    }
    
    /**
     * 
     * @return ArrayList in the order of the path taken
     */
    public ArrayList<Airport> solution_path(){
        ArrayList<Airport> path = new ArrayList<Airport>();
        path.add(this.state);
        Node parent_node = this.parent;

        while(!Objects.equals(parent_node, null)){
            Airport parent_state = parent_node.state;
            path.add(parent_state);
            parent_node = parent_node.parent;

            if(Objects.equals(parent_node, null)){
                break;
            }
            parent_state = parent_node.state;
        }
        Collections.reverse(path);
        return path;
    } 
}
