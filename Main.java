import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Jordan Teye
 * 
 */

public class Main{

    // used to find data of type Airport using a string airportID
    static HashMap<String, Airport> names = new HashMap<>();  

        
        // creates a graph to allow BFS to be used
    static Graph<String> createGraph(){
        Graph<String> graph = new Graph<>();
        
        Scanner routesFile = FileHandler.useFile("routes.csv");
        Scanner airportFile = FileHandler.useFile("airports.csv");
        while(routesFile.hasNextLine()){
            String[] routesData = routesFile.nextLine().split(",");
            while(airportFile.hasNextLine()){
                String[] airportData = airportFile.nextLine().split(",");
                Airport tempAirport = new Airport(airportData[0], airportData[1], airportData[2], airportData[3], airportData[4], 
                airportData[5], airportData[6], airportData[7], airportData[8], airportData[9], airportData[10],
                 airportData[11], airportData[12], airportData[13]);
                graph.addNode(tempAirport);
                names.put(tempAirport.airportID, tempAirport);
            }
            if((names.containsKey(routesData[3]) && names.containsKey(routesData[5]))){
                graph.addConnection(names.get(routesData[3]), names.get(routesData[5]));
            }
        }
        routesFile.close();
        airportFile.close();
        return graph;
    }

    /**
     * Necessary incase a city has more than one Airport
     * @param startCity
     * @param startCountry
     * @return A list of AirportIDs
     */
    static ArrayList<String> getAirportIDs(String startCity, String startCountry){
        Scanner data = FileHandler.useFile("airports.csv");
        ArrayList<String> airportIDs = new ArrayList<String>();
        while(data.hasNextLine()){
            String[] dataList = data.nextLine().split(",");
            if(startCity.equals(dataList[2]) && startCountry.equals(dataList[3])){
                airportIDs.add(dataList[0]);
            }
        } 
        data.close();
        return airportIDs;
    }

    /**
     * Finds the airline name and stops in routes file
     * based on a found Route from one Airport to another
     * @param sourceID
     * @param destID
     * @return An Array containing the Airline name and number of Stops
     */
    static String[] findRoute(Airport sourceID, Airport destID){
        Scanner routesFile = FileHandler.useFile("routes.csv");
        String airlineName = "";
        String noStops="";
        String[] value = new String[2];
            while(routesFile.hasNextLine()){
                String[] routesData = routesFile.nextLine().split(",");
                if(routesData[3].equals(sourceID.airportID) && routesData[5].equals(destID.airportID)){
                    airlineName = routesData[0];
                    noStops = routesData[7];
                }
            }
            value[0] = airlineName;
            value[1] = noStops;
            return value;
    }

    /**
     * 
     * @param filename
     * @return
     */
    static ArrayList<String[]> getInput(String filename){
        Scanner file = FileHandler.useFile(filename);
        String[] startLocation = file.nextLine().split(", ");
        String[] endLocation = file.nextLine().split(", ");
        ArrayList<String[]> input = new ArrayList<String[]>();
        input.add(startLocation);
        input.add(endLocation);
        return input;
    }

            // Searches and outputs based on the input file
    static void output(String filename){
        Graph<String> graph = createGraph();
        ArrayList<String[]> input = getInput(filename);
        ArrayList<Airport> path = new ArrayList<Airport>();
        Airport startAirport;
        Airport destAirport;

        //get Start Airport
        String startCity = input.get(0)[0];
        System.out.println(startCity);
        String startCountry = input.get(0)[1];
        System.out.println(startCountry);
        ArrayList<String> startAirportIDs = getAirportIDs(startCity, startCountry);

        //get Destination Airport
        String destCity = input.get(1)[0];
        System.out.println(destCity);
        String destCountry = input.get(1)[1];
        System.out.println(destCountry);
        ArrayList<String> destAirportIDs = getAirportIDs(destCity, destCountry);
        
        // BFS to find connecting Airports
        for(int i=0; i<startAirportIDs.size(); i++){
            startAirport = names.get(startAirportIDs.get(i));
            for(int j = 0; j<destAirportIDs.size(); j++){
                destAirport = names.get(destAirportIDs.get(j));
                if(!graph.bfs(startAirport, destAirport).isEmpty())
                    path = graph.bfs(startAirport, destAirport);
            }
        }
        // write to file
        int flightNumber = 0;
        int stopNumber = 0;
        String nameOfFile = startCity.toLowerCase() + "-" + destCity.toLowerCase() + "_output.txt";
        FileWriter file = FileHandler.writeToFile(nameOfFile);

        // loops each Airport in the path array list
        for(int i = 0; i< path.size()-1;i++){
            flightNumber++;
            String[] route = findRoute(path.get(i), path.get(i+1));
            String airline = route[0];
            String stops = route[1];
            stopNumber =+ Integer.parseInt(stops);
        
            try {
                file.write(String.format("\t%s. %s from %s to %s %s stops\n", i+1, airline, path.get(i).IATA, path.get(i+1).IATA, stops));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        try {
            file.write("Total flights: " + flightNumber);
            file.write("\nTotal additional stops: " + stopNumber);
            file.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
        
    public static void main(String[] args){
        
        output("Input.txt");
        
    }
}
