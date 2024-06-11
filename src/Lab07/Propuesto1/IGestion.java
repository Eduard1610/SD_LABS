package wscompras;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface IGestion {
    @WebMethod
    public int menu();
    @WebMethod
    public Producto[] cargarProductos();
    @WebMethod
    public void mostrarProductos(Producto[] productos);
    @WebMethod
    public double comprarProducto(Producto[] productos, int num, int cantidadUnidades);
    @WebMethod
    public double mostrarCaja();
}