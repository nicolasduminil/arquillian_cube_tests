package fr.simplex_software.arquillian_tests_with_junit_5.container_object.it;

import org.arquillian.cube.*;
import org.arquillian.cube.containerobject.*;

@Cube(value = "payara5", portBinding = {"4848->4848/tcp", "8080->8080/tcp"})
@CubeDockerFile
public class PayaraContainer
{
  @HostIp
  private String host;
  @HostPort(8080)
  private int port;

  public int getPort()
  {
    return port;
  }

  public String getHost()
  {
    return host;
  }
}
