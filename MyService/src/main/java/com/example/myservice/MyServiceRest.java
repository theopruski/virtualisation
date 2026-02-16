package com.example.myservice;

import com.example.myservice.entity.Message;
import com.example.myservice.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MyServiceRest {

    private final MessageRepository repository;

    public MyServiceRest(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
            <html>
            <head><title>Formulaire</title></head>
            <body>
            <h1>Hello</h1>
            <form method="post" action="/" enctype="application/x-www-form-urlencoded">
                <input name="content" placeholder="Écris quelque chose" required/>
                <button type="submit">Send</button>
            </form>
            </body>
            </html>
        """;
    }

    @PostMapping("/")
    public RedirectView handleForm(@RequestParam String content) {
        Message msg = new Message();
        msg.setContent(content);
        repository.saveAndFlush(msg);
        return new RedirectView("http://myservice2.localhost/");
    }
}