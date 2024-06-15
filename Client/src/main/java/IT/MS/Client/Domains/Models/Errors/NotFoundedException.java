package IT.MS.Client.Domains.Models.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NO_CONTENT, reason="Item searched not founded.")
public class NotFoundedException extends RuntimeException {
    public NotFoundedException(String errorMessage) {
        super(errorMessage);
    }
}
