package IT.MS.Client.Domains.Services.Interfaces;

import IT.MS.Client.Domains.Models.Client;
import IT.MS.Client.Domains.Models.Requests.AddClientRequest;
import IT.MS.Client.Domains.Models.Responses.AddClientResponse;

public interface IClientService {
    AddClientResponse addClient(AddClientRequest client);

    Client getClientByClientId(Long clientId);

    Client getClientByDocument(String document);
}
