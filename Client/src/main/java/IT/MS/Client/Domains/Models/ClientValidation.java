package IT.MS.Client.Domains.Models;

public class ClientValidation {
    public boolean isValidClient;
    public String errorMessage;

    public ClientValidation(String errorMessage, boolean isValidClient) {
        this.errorMessage = errorMessage;
        this.isValidClient = isValidClient;
    }
}
