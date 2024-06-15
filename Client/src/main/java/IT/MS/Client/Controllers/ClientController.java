package IT.MS.Client.Controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import IT.MS.Client.Domains.Models.Client;
import IT.MS.Client.Domains.Models.Errors.NotFoundedException;
import IT.MS.Client.Domains.Models.Requests.AddClientRequest;
import IT.MS.Client.Domains.Models.Responses.AddClientResponse;
import IT.MS.Client.Domains.Models.Responses.Errors.ErrorResult;
import IT.MS.Client.Domains.Services.Interfaces.IClientService;
import io.swagger.v3.oas.annotations.Operation;


@RequestMapping("/clients")
@RestController
public class ClientController {
    @Autowired
    private IClientService _clientService;
    private Logger _logger = Logger.getLogger(ClientController.class.getName());

    @PostMapping("")
    @ResponseStatus( HttpStatus.OK)
    @Operation(summary = "Add Client to database.")

    public ResponseEntity<AddClientResponse> AddClientController(@RequestBody AddClientRequest client) {
        AddClientResponse clientResponse;

        try {
            _logger.log(Level.INFO, "Method: AddClientController - Start");

            clientResponse = _clientService.addClient(client);
        }
        catch (Exception ex) {
            _logger.log(Level.SEVERE, "Method: AddClientController - Error: {0} ", ex.getMessage());

            clientResponse = new AddClientResponse(false, ex.getMessage());
        }

        return new ResponseEntity<AddClientResponse>(clientResponse, HttpStatus.OK);
    }

    @GetMapping("{clientId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Client details by ClientId.")
    public ResponseEntity getClientByClientIdController(@PathVariable("clientId") Long clientId) {
        try {
            Client client = _clientService.getClientByClientId(clientId);

            return ResponseEntity.ok(client);
        } catch(NotFoundedException ex){
            _logger.log(Level.SEVERE, "Method: AddClientController - Error: {0}", ex.getMessage());

            ErrorResult errorResult = new ErrorResult(ex.getMessage());

            return new ResponseEntity<ErrorResult>(errorResult, HttpStatus.NO_CONTENT);

        } 
        catch (Exception ex) {
            _logger.log(Level.SEVERE, "Method: AddClientController - Error: {0}", ex.getMessage());

            ErrorResult errorResult = new ErrorResult(ex.getMessage());

            return new ResponseEntity<ErrorResult>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("documents/{document}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Client details by Document.")
    public ResponseEntity getClientByDocumentController(@PathVariable("document") String document) {
        try {
            Client client = _clientService.getClientByDocument(document);

            return ResponseEntity.ok(client);
        } catch(NotFoundedException ex){
            _logger.log(Level.SEVERE, "Method: AddClientController - Error: {0}", ex.getMessage());

            ErrorResult errorResult = new ErrorResult(ex.getMessage());

            return new ResponseEntity<ErrorResult>(errorResult, HttpStatus.NO_CONTENT);

        } 
        catch (Exception ex) {
            _logger.log(Level.SEVERE, "Method: AddClientController - Error: {0}", ex.getMessage());

            ErrorResult errorResult = new ErrorResult(ex.getMessage());

            return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
