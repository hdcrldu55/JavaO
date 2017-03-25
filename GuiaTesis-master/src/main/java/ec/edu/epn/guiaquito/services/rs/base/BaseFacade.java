package ec.edu.epn.guiaquito.services.rs.base;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public abstract class BaseFacade<T, K extends String> {

    public abstract Response find();

    public abstract Response findById(@PathParam("id") K id);

    public abstract Response create(T t);

    public abstract Response update(@PathParam("id") K id, T t);
}
