package fr.simplex_software.arquillian_tests_with_junit_5.container_object.it;

import io.restassured.http.*;
import org.arquillian.cube.containerobject.*;
import org.jboss.arquillian.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import javax.ws.rs.core.*;
import java.net.*;

import static io.restassured.RestAssured.*;
import static java.lang.Thread.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(ArquillianExtension.class)
public class PayaraContainerIT
{
  private static final String APP_URL = "http://%s:%d/container-object-junit-5/hello";
  private URI uri;

  @Cube
  private PayaraContainer payara;

  @BeforeEach
  public void before() throws MalformedURLException
  {
    uri = UriBuilder.fromUri(
        String.format(APP_URL, payara.getHost(), payara.getPort())).build();
  }

  @AfterEach
  public void after()
  {
    uri = null;
  }

  @Test
  public void testOnPayaraShouldReturnOk()
    throws MalformedURLException, InterruptedException
  {
    sleep(5000);
    given()
      .contentType(ContentType.JSON)
      .get(uri)
      .then()
      .assertThat().statusCode(200)
      .and()
      .body(containsString("Hello World"));
  }
}
