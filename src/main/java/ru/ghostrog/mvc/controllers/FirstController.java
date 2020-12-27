package ru.ghostrog.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {
    //два варианта получения GET параметров в http запрос
    // через httpservlet подходит если необходимо использовать не только параметры
    //т.к. получаем доступ ко всем данным в http запросе
    //если параметры не передаются они заполняются пустыми значениями (String - null)
    @GetMapping("/hello")
    public String HelloPage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        System.out.println("Hello, " + name + " " + surname);

        return "first/hello";
    }

    //использовать если нужны только параметры (обязательно необходимо передавать параметры в http запрос)
    @GetMapping("/goodbye")
    public String goodByePage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              Model model) {
    // через model получаем параметры и выводим их на html странице
//        System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/goodbye";
    }
    //если мы хотим что бы проставлялись значения по умолчанию необходимо дополнить код
//    @GetMapping("/goodbye")
//    public String goodByePage1(@RequestParam(value = "name", required = false) String name,
//                              @RequestParam(value = "surname", required = false) String surname){
//        System.out.println("Hello, " + name + " " + surname);
//        return "first/goodbye";
//    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a", required = false) int a,
                          @RequestParam(value = "b", required = false) int b,
                          @RequestParam(value = "action", required = false) String action,
                          Model model){
        if(action.equals("multiplication")){
            model.addAttribute("message", a + b);
        } else if(action.equals("addition")){
            model.addAttribute("message", a - b);
        }


        return "first/calculator";
    }

}
