package IT.MS.Client.Domains.Models.Abstractions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class BaseClient {
    @NotNull
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
    @Size(min=5, message="Phone must be at least 5 characters long")
    public String phone;

    public BaseClient(){

    }
}
