package IT.MS.Client.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import IT.MS.Client.Domains.Models.Client;
import IT.MS.Client.Domains.Models.ClientValidation;

@SpringBootTest
public class ClientTests {

    @Test
    public void when_Receive_Valid_Client_Returns_Valid_Equals_True() {
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "teste@outlook.com.br",
            "Teste",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(true, clientValidation.isValidClient); 
    }

    
    @Test
    public void when_Receive_Empty_Address_Returns_Valid_Equals_False() {
        Client client = new Client(
            "",
            "24390742094",
            "teste@outlook.com.br",
            "Teste",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid address received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Invalid_Address_Returns_Valid_Equals_False() {
        Client client = new Client(
            "as",
            "24390742094",
            "teste@outlook.com.br",
            "Teste",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid address received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Empty_Document_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "",
            "teste@outlook.com.br",
            "Teste",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid document received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Invalid_Document_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "24390742000",
            "teste@outlook.com.br",
            "Teste",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid document received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Empty_Email_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "",
            "Teste",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid email received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Invalid_Email_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "ewe",
            "Teste",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid email received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Empty_Name_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "teste@outlook.com.br",
            "",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid name received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Invalid_Name_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "teste@outlook.com.br",
            "T",
            "11999999999"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid name received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Empty_Phone_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "teste@outlook.com.br",
            "Teste",
            ""
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid phone received", clientValidation.errorMessage); 
    }

    @Test
    public void when_Receive_Invalid_Phone_Returns_Valid_Equals_False() {
        Client client = new Client(
            "Rua XYZ",
            "24390742094",
            "teste@outlook.com.br",
            "Teste",
            "12"
        );

        ClientValidation clientValidation = client.isValidClient();

        assertEquals(false, clientValidation.isValidClient); 
        assertEquals("Invalid phone received", clientValidation.errorMessage); 
    }
}
