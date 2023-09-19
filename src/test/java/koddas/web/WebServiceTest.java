package koddas.web.war;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.Response;

public class WebServiceTest {
    private WebService webService;

    @Before
    public void setUp() {
        webService = new WebService();
    }

    @Test
    public void testHello() {
        Response response = webService.hello();
        assertEquals(200, response.getStatus());
        assertEquals("Hello, World!", response.getEntity());
    }

    // You can add more test methods for other WebService methods here if needed
}

