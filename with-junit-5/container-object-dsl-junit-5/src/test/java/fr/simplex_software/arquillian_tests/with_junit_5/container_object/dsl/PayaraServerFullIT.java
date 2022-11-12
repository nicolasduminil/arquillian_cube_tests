package fr.simplex_software.arquillian_tests.with_junit_5.container_object.dsl;

import io.restassured.http.*;
import org.arquillian.cube.docker.impl.client.containerobject.dsl.*;
import org.arquillian.cube.docker.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import javax.ws.rs.core.*;
import java.net.*;

import static io.restassured.RestAssured.*;
import static java.lang.Thread.*;
import static org.hamcrest.Matchers.*;


@ExtendWith(ContainerDslResolver.class)
public class PayaraServerFullIT
{
  private static final String APP_URL = "http://%s:%d/container-object-dsl-junit-5/hello";
  private URI uri;

  @BeforeEach
  public void before() throws MalformedURLException
  {
    uri = UriBuilder.fromUri(
        String.format(APP_URL, payara.getIpAddress(),
          payara.getBindPort(8080)))
      .build();
  }

  @AfterEach
  public void after()
  {
    uri = null;
  }

  private ContainerDsl payara = new ContainerDsl("payara/server-full:5.2022.4-jdk11")
    .withPortBinding(4848, 8080)
    .withVolume(System.getProperty("user.dir") + "/target/container-object-dsl-junit-5.war",
      "/opt/payara/deployments/container-object-dsl-junit-5.war");

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
