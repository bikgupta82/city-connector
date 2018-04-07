package connector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.StringTokenizer;

@Service
public class ConnectorService {

    @Value("${route.properties}")
    private String fileName;

    @Value("${result.routeExists}")
    private String ROUTE_EXISTS;

    @Value("${result.routeDoesNotExists}")
    private String ROUTE_DOES_NOT_EXISTS;

    public String isCitiesConnected(String origin, String destination) throws IOException {
        BufferedReader br = readFile(fileName);
        String route ="";
        boolean routeExists = false;
        while((route = br.readLine()) != null){
            routeExists = checkIfCitiesAreOnSameRoute(origin, destination, route);
            if(routeExists){
                return ROUTE_EXISTS;
            }
        }
        return ROUTE_DOES_NOT_EXISTS;
    }

    private BufferedReader readFile(String file) throws IOException{
        InputStream is = ConnectorService.class.getClassLoader().getResourceAsStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br;
    }

    private boolean checkIfCitiesAreOnSameRoute(String origin, String destination, String route){
        StringTokenizer st =  new StringTokenizer(route,",");
        boolean originCityIsOnRoute = false, destinationCityIsOnRoute = false;
        String city = "";
        while(st.hasMoreTokens()){
            city = StringUtils.trimAllWhitespace(st.nextToken());
            if(!originCityIsOnRoute){
                originCityIsOnRoute = origin.equalsIgnoreCase(city);
            }
            if(!destinationCityIsOnRoute){
                destinationCityIsOnRoute = destination.equalsIgnoreCase(city);
            }
        }
        return destinationCityIsOnRoute && originCityIsOnRoute;
    }
}