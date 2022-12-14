package fr.simplex_software.arquillian_tests.with_junit_5.container_object.dsl;

import io.restassured.http.*;
import org.arquillian.cube.docker.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(ContainerDslResolver.class)
public class PingPongIT
{
  private static final String PING_PONG_URL = "http://%s:%d/";
  private URI uri;

  private ContainerDsl pingpong = new ContainerDsl("jonmorehouse/ping-pong")
    .withPortBinding(8080);

  @BeforeEach
  public void before() throws MalformedURLException
  {
    uri = UriBuilder.fromUri(
        String.format(PING_PONG_URL, pingpong.getIpAddress(),
          pingpong.getBindPort(8080)))
      .build();
  }

  @AfterEach
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
