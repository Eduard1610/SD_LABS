package WSSOAP;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SOAPI {
    @WebMethod
    public List<User> getUsers();

    @WebMethod
    public void addUser(User user);
}
