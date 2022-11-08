package fr.simplex_software.arquillian_tests.with_junit_5.container_object.dsl;

import org.arquillian.cube.docker.impl.client.containerobject.dsl.*;
import org.arquillian.cube.docker.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.io.*;
import java.net.*;

import static org.assertj.core.api.Assertions.*;

@Disabled("Test disabled because JUnit5 isn't supported yet")
@ExtendWith(ContainerDslResolver.class)
public class PingPongIT
{
  @DockerContainer
  Container pingpong = Container.withContainerName("pingpong")
    .fromImage("jonmorehouse/ping-pong")
    .withPortBinding(8080)
    .build();

  @Test
  public void should_return_ok_as_pong() throws IOException
  {
    String response = ping(pingpong.getIpAddress(), pingpong.getBindPort(8080));
    assertThat(response).containsSequence("OK");
  }

  public String ping(String ip, int port) throws IOException
  {
    URL url = new URL("http://" + ip + ":" + port);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(
      new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuilder response = new StringBuilder();

    while ((inputLine = in.readLine()) != null)
    {
      response.append(inputLine);
    }
    in.close();

    return response.toString();
  }
}
