package com.example.myservice2;

import com.example.myservice2.entity.Message;
import com.example.myservice2.repository.MessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class MyService2Rest {

    private final MessageRepository repository;

    public MyService2Rest(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String showMessages() {
        String rows = repository.findAll().stream()
                .map(m -> "<tr><td>" + m.getId() + "</td><td>" + m.getContent() + "</td></tr>")
                .collect(Collectors.joining());

        return "<h1>Test</h1>" +
            "<table border='1'>" +
            "<thead><tr><th>ID</th><th>Content</th></tr></thead>" +
            "<tbody>" + rows + "</tbody>" +
            "</table>";
    }
}
