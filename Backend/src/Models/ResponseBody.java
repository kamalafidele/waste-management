package Models;

import java.io.Serializable;
import java.util.List;
/*
 @author : Turinumugisha Souvenir
 */

public class ResponseBody implements Serializable {
    private List<Object> response;

    public List<Object> getResponse() {
        return response;
    }

    public ResponseBody(List<Object> response) {
        this.response = response;
    }
}
