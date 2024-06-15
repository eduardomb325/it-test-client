package IT.MS.Client.Domains.Models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import IT.MS.Client.Domains.Extensions.ValidationExtension;
import IT.MS.Client.Domains.Models.Abstractions.BaseClient;
import IT.MS.Client.Domains.Models.Requests.AddClientRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Client extends BaseClient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;@NotNull
    @Size(min=5, message="Address must be at least 5 characters long")
    public String address;

    @NotNull
    @Size(min=11, message="Document must be at least 11 characters long")
    public String document;

    @NotNull
    @Size(min=5, message="Email must be at least 5 characters long")
    public String email;

    @NotNull
    @Size(min=3, message="Name must be at least 5 characters long")
    public String name;

    @NotNull
    @Size(min=5, message="Email must be at least 5 characters long")
    private boolean isActiveClient = true;

    @NotNull
    @Size(min=5, message="Phone must be at least 5 characters long")
    public String phone;

    private Date createAt;
    
    private Date updateAt;

    public Client(){

    }

    public Client(AddClientRequest addClient){
       this.address = addClient.getAddress();
       this.document = addClient.getDocument();
       this.email = addClient.getEmail();
       this.name = addClient.getName();
       this.phone = addClient.getPhone();
       isActiveClient = true;
       createAt = new Date();
       updateAt = new Date(); 
    }

    public Client(String address, String document, String email, String name, String phone) {
       this.address = address;
       this.document = document;
       this.email = email;
       this.name = name;
       this.phone = phone;
       isActiveClient = true;
       createAt = new Date();
       updateAt = new Date(); 
    }

    @JsonIgnore
    public boolean isFoundedClient(){
        return clientId > 0;
    }

    @JsonIgnore
    public ClientValidation isValidClient(){
        boolean isValidClient = true;
        String errorMessage = "";

        if (!isValidName()){
            isValidClient = false;
            errorMessage = "Invalid name received";
        }

        if (isValidClient && !isValidAddress()){
            isValidClient = false;
            errorMessage = "Invalid address received";
        }

        if (isValidClient && !isValidDocument()){
            isValidClient = false;
            errorMessage = "Invalid document received";
        }

        if (isValidClient && !isValidEmail()){
            isValidClient = false;
            errorMessage = "Invalid email received";
        }

        if (isValidClient && !isValidPhone()){
            isValidClient = false;
            errorMessage = "Invalid phone received";
        }

        return new ClientValidation(errorMessage, isValidClient);
    }

    private boolean isValidName(){
        return name.length() > 3;
    }

    private boolean isValidAddress(){
        return address.length() > 5;
    }

    private boolean isValidEmail(){
        return email.length() > 5;
    }

    private boolean isValidPhone(){
        return phone.length() > 5;
    }

    private boolean isValidDocument(){
        return ValidationExtension.ValidateDocument(document);
    }
}
