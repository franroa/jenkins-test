package franroa.core;

import franroa.exceptions.ResourceNotFoundException;
import org.javalite.activejdbc.ModelDelegate;

public abstract class Model extends org.javalite.activejdbc.Model {
    public Model() {
    }

    public static <T extends org.javalite.activejdbc.Model> T findOrFail(Class<T> modelClass, Object id) {
        org.javalite.activejdbc.Model model;
        try {
            model = ModelDelegate.findById(modelClass, id);
        } catch (Exception var4) {
            throw new ResourceNotFoundException();
        }

        if(model == null) {
            throw new ResourceNotFoundException();
        } else {
            return (T) model;
        }
    }
}
