package IT.MS.Client.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import IT.MS.Client.Domains.Models.Client;
import IT.MS.Client.Domains.Models.ClientValidation;
import IT.MS.Client.Domains.Models.Errors.NotFoundedException;
import IT.MS.Client.Domains.Models.Requests.AddClientRequest;
import IT.MS.Client.Domains.Models.Responses.AddClientResponse;
import IT.MS.Client.Domains.Models.Responses.Errors.ErrorResult;
import IT.MS.Client.Domains.Services.ClientService;

@SpringBootTest
public class ClientControllers {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    //#region addClient - Tests

    @Test
    public void When_Receive_Valid_Client_Returns_ClientApproval_True() {
         AddClientRequest clientRequest = new AddClientRequest(
            "Rua XYZ",
            "88070059000105",
            "teste@outlook.com.br",
            "Teste",
            "11999999999");

        ClientValidation clientValidation = new ClientValidation(null, true);

        AddClientResponse mockResponse = new AddClientResponse(clientValidation, 1L);

        when(clientService.addClient((Mockito.any(AddClientRequest.class)))).thenReturn(mockResponse);

        ResponseEntity<AddClientResponse> response = clientController.AddClientController(clientRequest);

        AddClientResponse addClientResponse = response.getBody();

        assertEquals(true, addClientResponse.approved); 
        assertEquals(1L, addClientResponse.clientId);
    }

    @Test
    public void When_Receive_Exception_Returns_ClientApproval_False() {
         AddClientRequest clientRequest = new AddClientRequest(
            "Rua XYZ",
            "88070059000105",
            "teste@outlook.com.br",
            "Teste",
            "11999999999");

        ClientValidation clientValidation = new ClientValidation(null, true);

        AddClientResponse mockResponse = new AddClientResponse(clientValidation, 1L);

        when(clientService.addClient((Mockito.any(AddClientRequest.class)))).thenThrow(NullPointerException.class);

        ResponseEntity<AddClientResponse> response = clientController.AddClientController(clientRequest);

        AddClientResponse addClientResponse = response.getBody();

        assertEquals(false, addClientResponse.approved); 
    }

    //#endregion
   //#region getClientByClientIdController - Tests
    @Test
    public void When_Receive_Valid_ClientId_Returns_Client_Data(){
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "teste@outlook.com.br",
            "Teste",
            "11999999999"
        );

        client.setClientId(1L);

        when(clientService.getClientByClientId(Mockito.any(Long.class))).thenReturn(client);

        ResponseEntity clientResponse = clientController.getClientByClientIdController(1L);

        Client clientReceived = (Client) clientResponse.getBody();

        assertEquals(client.name, clientReceived.name); 
        assertEquals(client.getClientId(), clientReceived.getClientId()); 
    }

    @Test
    public void When_Receive_Not_Found_ClientId_Returns_NoContent(){

        when(clientService.getClientByClientId(Mockito.any(Long.class))).thenThrow(NotFoundedException.class);

        ResponseEntity clientResponse = clientController.getClientByClientIdController(1L);

        ErrorResult errorReceived = (ErrorResult) clientResponse.getBody();

        assertEquals(HttpStatus.NO_CONTENT, clientResponse.getStatusCode()); 
    }

    @Test
    public void When_Has_Error_Returns_InternalServerError(){

        when(clientService.getClientByClientId(Mockito.any(Long.class))).thenThrow(NullPointerException.class);

        ResponseEntity clientResponse = clientController.getClientByClientIdController(1L);

        ErrorResult errorReceived = (ErrorResult) clientResponse.getBody();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, clientResponse.getStatusCode()); 
    }
   //#endregion
  //#region getClientByClientIdController - Tests
  @Test
  public void When_Receive_Valid_Document_Returns_Client_Data(){
      Client client = new Client(
          "Rua XYZ",
          "24390742094",
          "teste@outlook.com.br",
          "Teste",
          "11999999999"
      );

      client.setClientId(1L);

      when(clientService.getClientByDocument(Mockito.any(String.class))).thenReturn(client);

      ResponseEntity clientResponse = clientController.getClientByDocumentController("24390742094");

      Client clientReceived = (Client) clientResponse.getBody();

      assertEquals(client.name, clientReceived.name); 
      assertEquals(client.getClientId(), clientReceived.getClientId()); 
      assertEquals(client.address, clientReceived.address);  
      assertEquals(client.document, clientReceived.document);  
      assertEquals(client.email, clientReceived.email);  
      assertEquals(client.name, clientReceived.name);  
      assertEquals(client.phone, clientReceived.phone);
  }

  @Test
  public void When_Receive_Not_Found_Document_Returns_NoContent(){

      when(clientService.getClientByDocument(Mockito.any(String.class))).thenThrow(NotFoundedException.class);

      ResponseEntity clientResponse = clientController.getClientByDocumentController("24390742094");

      ErrorResult errorReceived = (ErrorResult) clientResponse.getBody();

      assertEquals(HttpStatus.NO_CONTENT, clientResponse.getStatusCode()); 
  }

  @Test
  public void When_Has_Error_In_Document_Method_Returns_InternalServerError(){

      when(clientService.getClientByDocument(Mockito.any(String.class))).thenThrow(NullPointerException.class);

      ResponseEntity clientResponse = clientController.getClientByDocumentController("24390742094");

      ErrorResult errorReceived = (ErrorResult) clientResponse.getBody();

      assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, clientResponse.getStatusCode()); 
  }
 //#endregion
}
