package IT.MS.Client.Domains.Models.Responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import IT.MS.Client.Domains.Models.ClientValidation;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddClientResponse {
    public boolean approved;

    public Long clientId;
    
    public String errorMessage;

    public AddClientResponse(){

    }
    
    public AddClientResponse(ClientValidation clientValidation, Long clientId) {
        this.approved = clientValidation.isValidClient;
        this.clientId = clientId;
        this.errorMessage = clientValidation.errorMessage;
    }

    public AddClientResponse(boolean approved, String errorMessage) {
        this.approved = approved;
        this.clientId = 0L;
        this.errorMessage = errorMessage;
    }
}
