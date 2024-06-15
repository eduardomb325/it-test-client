package IT.MS.Client.Domains.Models.Requests;

import IT.MS.Client.Domains.Models.Abstractions.BaseClient;

public class AddClientRequest extends BaseClient{

    public AddClientRequest(String address, String document, String email, String name, String phone) {
        this.address = address;
        this.document = document;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}