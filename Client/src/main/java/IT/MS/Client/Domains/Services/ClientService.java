package IT.MS.Client.Domains.Services;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import IT.MS.Client.Domains.Models.Client;
import IT.MS.Client.Domains.Models.ClientValidation;
import IT.MS.Client.Domains.Models.Errors.NotFoundedException;
import IT.MS.Client.Domains.Models.Requests.AddClientRequest;
import IT.MS.Client.Domains.Models.Responses.AddClientResponse;
import IT.MS.Client.Domains.Services.Interfaces.IClientService;
import IT.MS.Client.Repositories.Interfaces.IClientRepository;

@Component
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository _clientRepository;
    private Logger _logger = Logger.getLogger(ClientService.class.getName());

    @Override
    public AddClientResponse addClient(AddClientRequest addClientRequest) {
        _logger.log(Level.INFO, "Method: AddClient - Start");

        Long clientId = 0L;

        Client client = new Client(addClientRequest);

        ClientValidation clientValidation = client.isValidClient();

        _logger.log(Level.INFO, "Method: AddClient - Is valid client? {0}", clientValidation.isValidClient);

        if (clientValidation.isValidClient){
            Client clientFounded = getClientByDocument(client.getDocument());

            if (clientFounded.getClientId() > 0){
                clientValidation = new ClientValidation("Duplicated document received", false);
            } else {
                Client clientSaved = _clientRepository.save(client);
    
                clientId = clientSaved.getClientId();
    
                _logger.log(Level.INFO, "Method: AddClient - ClientId: {0}", clientId);
            }
        }

        AddClientResponse addClientResponse = new AddClientResponse(clientValidation, clientId);

        _logger.log(Level.INFO, "Method: AddClient - Finish ");

        return addClientResponse;
    }

    @Override
    public Client getClientByClientId(Long clientId) {
        _logger.log(Level.INFO, "Method: getClientByClientId - Start - Client: {0}", clientId);

        Optional<Client> optClient = _clientRepository.findById(clientId);

        Client client = new Client();

        if (optClient.isPresent()) client = optClient.get();

        boolean isFoundedClient = client.isFoundedClient();

        _logger.log(Level.INFO, "Method: getClientByClientId - Is client is founded? {0}", isFoundedClient);

        if (!isFoundedClient) throw new NotFoundedException("ClientId {0} is not founded".replace("{0}",  clientId.toString()));

        return client;
    }

    @Override
    public Client getClientByDocument(String document){
        _logger.log(Level.INFO, "Method: getClientByDocument - Start - Document: {0}", document);

        Optional<Client> optClient = _clientRepository.findByDocument(document);

        Client client = new Client();

        boolean isClientFounded = optClient.isPresent();

        if (isClientFounded) client = optClient.get();

        _logger.log(Level.INFO, "Method: getClientByDocument - Finish - Is client founded? {0}", isClientFounded);

        return client;
    }
}
