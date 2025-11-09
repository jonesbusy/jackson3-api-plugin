package tools.jackson.databind.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.jvnet.hudson.test.junit.jupiter.RealJenkinsExtension;

/**
 * Basic tests for {@link JsonMapper}.
 */
class JSONMapperTest {

    @RegisterExtension
    private final RealJenkinsExtension extension = new RealJenkinsExtension();

    @Test
    void smokes() throws Throwable {
        extension.then(r -> {
            JsonMapper mapper = JsonMapper.builder().build();
            // language=json
            String content =
                    """
                            {
                              "bar": {
                                  "id": "123"
                            }
                            }""";
            Foo foo = mapper.readValue(content.getBytes(StandardCharsets.UTF_8), Foo.class);
            assertNotNull(foo.getBar());
            assertEquals("123", foo.getBar().getId());
        });
    }

    @SuppressWarnings("unused")
    static class Foo {
        private Bar bar;

        public Bar getBar() {
            return bar;
        }

        public void setBar(Bar bar) {
            this.bar = bar;
        }
    }

    @SuppressWarnings("unused")
    static class Bar {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
