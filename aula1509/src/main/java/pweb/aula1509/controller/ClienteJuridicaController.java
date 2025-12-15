package pweb.aula1509.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pweb.aula1509.model.entity.ClientePF;
import pweb.aula1509.model.entity.ClientePj;
import pweb.aula1509.model.entity.Venda;
import pweb.aula1509.model.repository.ClientePJRepository;

import javax.naming.Binding;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("clientepj")
public class ClienteJuridicaController {

    @Autowired
    ClientePJRepository clientePJRepository;

    @GetMapping("/form")
    public ModelAndView form(ClientePj clientePJ, ModelMap model){
        model.addAttribute("cliente", clientePJ);
        return new ModelAndView("cliente/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("cliente") ClientePj clientePJ){

        //save pessoaFisica
        clientePJRepository.save(clientePJ);
        return new ModelAndView("redirect:/venda/list");
    }

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(required = false) String nome,
            ModelMap model) {

        List<ClientePj> clientes;

        if (nome != null && !nome.trim().isEmpty()) {
            clientes = clientePJRepository.buscarPorNome(nome);
        } else {
            clientes = clientePJRepository.listarTodos();
        }

        model.addAttribute("clientes", clientes);
        model.addAttribute("nome", nome);
        model.addAttribute("rota", "/clientepj/list");

        return new ModelAndView("cliente/listPJ");
    }



}
