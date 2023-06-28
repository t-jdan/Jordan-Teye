import java.util.Scanner;

/**
 * @author Jordan Ayiku Teye
 */

public class Airport{
    String airportID;
    String AirportName;
    String City;
    String Country;
    String IATA;
    String ICAO;
    String Latitude;
    String Longtitude;
    String Altitude;
    String Timezone;
    String DST;
    String tz;
    String type;
    String source;

    public String getAirportID() {
        return airportID;
    }

    /**
     * Stores elements of an Airport
     * @param airportID
     * @param airportName
     * @param city
     * @param country
     * @param iATA
     * @param iCAO
     * @param latitude
     * @param longtitude
     * @param altitude
     * @param timezone
     * @param dST
     * @param tz
     * @param type
     * @param source
     */
    public Airport(String airportID, String airportName, String city, String country, String iATA, String iCAO,
            String latitude, String longtitude, String altitude, String timezone, String dST, String tz, String type,
            String source) {
        this.airportID = airportID;
        this.AirportName = airportName;
        this.City = city;
        this.Country = country;
        this.IATA = iATA;
        this.ICAO = iCAO;
        this.Latitude = latitude;
        this.Longtitude = longtitude;
        this.Altitude = altitude;
        this.Timezone = timezone;
        this.DST = dST;
        this.tz = tz;
        this.type = type;
        this.source = source;
    }

    public String getAirportName() {
        return AirportName;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }


    public static Airport createAirportFromID(String airportID){
        Scanner airportFile = FileHandler.useFile("airports.csv");
        Airport airport = null;
        while(airportFile.hasNextLine()){
            String[] listAirports = airportFile.nextLine().split(",");
            if(airportID.equals(listAirports[0])){
                airport = new Airport(listAirports[0], listAirports[1], listAirports[2], listAirports[3], listAirports[4], listAirports[5], listAirports[6], listAirports[7], listAirports[8], listAirports[9], listAirports[10], listAirports[11], listAirports[12], listAirports[13]);
                break;
                }
        }return airport;       
    }

    public static Airport createAirportFromLocation(String airportCity, String airportCountry){
        Scanner airportFile = FileHandler.useFile("airports.csv");
        Airport airport = null;
        while(airportFile.hasNextLine()){
            String[] listAirports = airportFile.nextLine().split(",");
            if(airportCity.equals(listAirports[2]) && airportCountry.equals(listAirports[3])){
                airport = new Airport(listAirports[0], listAirports[1], listAirports[2], listAirports[3], 
                listAirports[4], listAirports[5], listAirports[6], listAirports[7], listAirports[8], listAirports[9], 
                listAirports[10], listAirports[11], listAirports[12], listAirports[13]);
                break;
                }
        }return airport;       
    }
    
}

