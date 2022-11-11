package fr.simplex_software.arquillian_tests_with_junit_4.container_object.it;

import io.restassured.http.*;
import org.arquillian.cube.docker.impl.client.containerobject.dsl.*;
import org.arquillian.cube.requirement.*;
import org.junit.*;
import org.junit.runner.*;

import javax.ws.rs.core.*;
import java.net.*;

import static io.restassured.RestAssured.*;
import static java.lang.Thread.*;
import static org.hamcrest.Matchers.*;

@RunWith(ArquillianConditionalRunner.class)
public class PayaraServerFullIT
{
  private static final String APP_URL = "http://%s:%d/container-object-dsl-junit-4/hello";
  private URI uri;

  @Before
  public void before() throws MalformedURLException
  {
    uri = UriBuilder.fromUri(
        String.format(APP_URL, payara.getIpAddress(),
          payara.getBindPort(8080)))
      .build();
  }

  @After
  public void after()
  {
    uri = null;
  }

  @DockerContainer
  Container payara = Container.withContainerName("payara5")
    .fromBuildDirectory(System.getProperty("user.dir"))
    .withPortBinding(8080)
    .withPortBinding(4848)
    .withVolume(System.getProperty("user.dir") + "/target/container-object-dsl-junit-4.war",
      "/opt/payara/deployments/container-object-dsl-junit-4.war")
    .build();

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
