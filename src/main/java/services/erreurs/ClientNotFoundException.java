package services.erreurs;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(String s) {
        super(s);
    }
}
