package Base;

import java.io.IOException;
import java.util.Map;

public interface HttpCall {
    void setQueryParams();
    void setPathParams();
    void beforeTest();
    void afterTest();
}
