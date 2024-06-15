package IT.MS.Client.Services;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import IT.MS.Client.Domains.Models.Client;
import IT.MS.Client.Domains.Models.Requests.AddClientRequest;
import IT.MS.Client.Domains.Models.Responses.AddClientResponse;
import IT.MS.Client.Domains.Services.ClientService;
import IT.MS.Client.Repositories.Interfaces.IClientRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {
    
    @Mock
    private IClientRepository clientRepository;

    @Mock
    private Logger logger;


    @InjectMocks
    private ClientService clientService;

    //#region addClient - Tests
    @Test
    public void When_Receive_Valid_Client_Returns_True() {        
        AddClientRequest clientRequest = new AddClientRequest(
            "Rua XYZ",
            "88070059000105",
            "teste@outlook.com.br",
            "Teste",
            "11999999999");
        
        Client clientResponse = new Client(clientRequest);
        clientResponse.setClientId(1L);

        Optional<Client> client = Optional.of(new Client());

        when(clientRepository.findByDocument(Mockito.any(String.class))).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(clientResponse);

       AddClientResponse response = clientService.addClient(clientRequest);

       assertEquals(true, response.approved); 
       assertEquals(1L, response.clientId); 
    }

    @Test
    public void When_Receive_Invalid_Client_Returns_False() {        
        AddClientRequest clientRequest = new AddClientRequest(
            "Rua XYZ",
            "880700590000",
            "teste@outlook.com.br",
            "Teste",
            "11999999999");
        
        AddClientResponse response = clientService.addClient(clientRequest);

        assertEquals(false, response.approved); 
        assertEquals("Invalid document received", response.errorMessage);
    }

    @Test
    public void When_Receive_Duplicate_Client_Returns_False() {
        AddClientRequest clientRequest = new AddClientRequest(
            "Rua XYZ",
            "88070059000105",
            "teste@outlook.com.br",
            "Teste",
            "11999999999");

        Client clientResponse = new Client(clientRequest);
        clientResponse.setClientId(1L);

        Optional<Client> client = Optional.of(clientResponse);

        when(clientRepository.findByDocument(Mockito.any(String.class))).thenReturn(client);

        AddClientResponse response = clientService.addClient(clientRequest);

        assertEquals(false, response.approved); 
        assertEquals("Duplicated document received", response.errorMessage);
    }
    //#endregion
    //#region getClientByClientId - Tests
    @Test
    public void When_Receive_Valid_ClientId_Returns_Client_Data() {    
        Client clientResponse = new Client("Rua XYZ",
            "88070059000105",
            "teste@outlook.com.br",
            "Teste",
            "11999999999");

        clientResponse.setClientId(1L);

        Optional<Client> client = Optional.of(clientResponse);

        when(clientRepository.findById(Mockito.any(Long.class))).thenReturn(client);

        Client clientFounded = clientService.getClientByClientId(1L);

        assertEquals(clientResponse, clientFounded); 
    }

    @Test
    public void When_Receive_Invalid_ClientId_Returns_Exception() {    
        try{
        Client clientResponse = new Client("Rua XYZ",
            "88070059000105",
            "teste@outlook.com.br",
            "Teste",
            "11999999999");

        clientResponse.setClientId(1L);

        Optional<Client> client = Optional.of(new Client());

        when(clientRepository.findById(Mockito.any(Long.class))).thenReturn(client);

        Client clientFounded = clientService.getClientByClientId(1L);    
        } catch (Exception ex){
            assertEquals("ClientId 1 is not founded", ex.getMessage()); 
        }
    }
    //#endregion
}
