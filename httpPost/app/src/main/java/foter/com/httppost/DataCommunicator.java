package foter.com.httppost;

import java.util.List;

import foter.com.httppost.server.ResponseObject;

/**
 * Created by pc on 14.02.2018.
 */

public interface DataCommunicator {
    public void dataRetrieve(List<ResponseObject> ro);
}
