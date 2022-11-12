package fr.simplex_software.arquillian_tests_with_junit_4.dsl.it;

import io.restassured.http.*;
import org.arquillian.cube.containerobject.*;
import org.arquillian.cube.requirement.*;
import org.junit.*;
import org.junit.runner.*;

import javax.ws.rs.core.*;
import java.net.*;

import static io.restassured.RestAssured.*;
import static java.lang.Thread.*;
import static org.hamcrest.Matchers.*;

@RunWith(ArquillianConditionalRunner.class)
public class PayaraContainerIT
{
  private static final String APP_URL = "http://%s:%d/container-object-junit-4/hello";
  private URI uri;

  @Cube
  PayaraContainer payara;

  @Before
  public void before() throws MalformedURLException
  {
    uri = UriBuilder.fromUri(
        String.format(APP_URL, payara.getHost(), payara.getPort())).build();
  }

  @After
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
