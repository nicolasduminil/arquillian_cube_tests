package fr.simplex_software.arquillian_tests_with_junit_4.basic.tests;

import fr.simplex_software.arquillian_tests_with_junit_4.basic.*;
import io.restassured.http.*;
import org.arquillian.cube.requirement.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.test.api.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import java.net.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(ArquillianConditionalRunner.class)
public class PayaraServerFullIT
{
  @ArquillianResource
  private URL url;

  @Deployment(testable = false)
  public static WebArchive onPayara()
  {
    return ShrinkWrap.create(WebArchive.class, "hello.war").addClass(
      HelloServlet.class);
  }

  @Test
  public void testOnPayaraShouldReturnOk() throws MalformedURLException
  {
    given()
      .contentType(ContentType.JSON)
      .get(new URL(url + "/hello"))
      .then()
      .assertThat().statusCode(200)
      .and()
      .body(containsString("Hello World"));
  }
}
