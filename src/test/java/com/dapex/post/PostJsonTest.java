package com.dapex.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PostJsonTest {

    @Autowired
    private JacksonTester<Post> jacksonTester;

    @Test
    void shouldSerializePost() throws Exception {
        Post post = new Post(1, 1, "Hello, World!", "This is my first post.", null);

        String expectedJson = "{\n" +
                "    \"id\": " + post.id() + ",\n" +
                "    \"userId\": " + post.userId() + ",\n" +
                "    \"title\": \"" + post.title() + "\",\n" +
                "    \"body\": \"" + post.body() + "\",\n" +
                "    \"version\": null\n" +
                "}";
        assertThat(jacksonTester.write(post)).isEqualToJson(expectedJson);
    }

    @Test
    void shouldDeserializePost() throws Exception {

        Post post = new Post(1, 1, "Hello, World!", "This is my first post.", null);

        String content = "{\n" +
                "    \"id\": " + post.id() + ",\n" +
                "    \"userId\": " + post.userId() + ",\n" +
                "    \"title\": \"" + post.title() + "\",\n" +
                "    \"body\": \"" + post.body() + "\",\n" +
                "    \"version\": null\n" +
                "}";

        assertThat(jacksonTester.parse(content)).isEqualTo(post);
        assertThat(jacksonTester.parseObject(content).id()).isEqualTo(1);
        assertThat(jacksonTester.parseObject(content).userId()).isEqualTo(1);
        assertThat(jacksonTester.parseObject(content).title()).isEqualTo("Hello, World!");
        assertThat(jacksonTester.parseObject(content).body()).isEqualTo("This is my first post.");
    }

}

