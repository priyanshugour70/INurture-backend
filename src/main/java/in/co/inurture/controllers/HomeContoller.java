package in.co.inurture.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeContoller {

    @GetMapping
    public String Test(){
        return "This is the Testing of the project for checking is it proper working or not ..!";
    }

}
