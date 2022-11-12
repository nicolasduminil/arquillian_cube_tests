package fr.simplex_software.arquillian_tests_with_junit_4.dsl.it;

import io.restassured.http.*;
import org.arquillian.cube.docker.impl.client.containerobject.dsl.*;
import org.arquillian.cube.requirement.*;
import org.junit.*;
import org.junit.runner.*;

import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(ArquillianConditionalRunner.class)
public class PingPongIT
{
  private static final String PING_PONG_URL = "http://%s:%d/";
  private URI uri;

  @DockerContainer
  Container pingpong = Container.withContainerName("pingpong")
    .fromImage("jonmorehouse/ping-pong")
    .withPortBinding("18080->8080")
    .build();

  @Before
  public void before() throws MalformedURLException
  {
    uri = UriBuilder.fromUri(
        String.format(PING_PONG_URL, pingpong.getIpAddress(),
          pingpong.getBindPort(8080)))
      .build();
  }

  @After
  public void after()
  {
    uri = null;
  }

  @Test
  public void shouldReturnOk() throws IOException
  {
    given()
      .contentType(ContentType.JSON)
      .get(uri)
      .then()
      .assertThat().statusCode(200)
      .and()
      .body(containsString("OK"));
  }
}
