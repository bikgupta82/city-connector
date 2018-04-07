package connector.controller;

import connector.service.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ConnectorController {

    @Autowired
    private ConnectorService service;
    
    @RequestMapping(name="/connected/{origin}/{destination}", method= RequestMethod.GET)
    public String checkCityConnectivity(@RequestParam("origin") String origin, @RequestParam("destination") String destination) throws IOException {
        return service.isCitiesConnected(origin,destination);
    }
    
}
