package pweb.aula1509.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pweb.aula1509.model.entity.ClientePF;
import pweb.aula1509.model.entity.ClientePj;
import pweb.aula1509.model.repository.ClientePFRepository;
import pweb.aula1509.model.repository.ProdutoRepository;

import java.util.List;

@Controller
@RequestMapping("clientepf")
public class ClienteFisicaController {

    @Autowired
    ClientePFRepository clientePFRepository;

    @GetMapping("/form")
    public ModelAndView form(ClientePF clientePF, ModelMap model){
        model.addAttribute("cliente", clientePF);
        return new ModelAndView("cliente/form");
    }

    // Método para para utilizar a validação, visto que o outro método form exige dois parametros;
    public ModelAndView viewForm(ClientePF clientePF) {
        ModelAndView mv = new ModelAndView("cliente/form");
        mv.addObject("cliente", clientePF);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("cliente") @Valid ClientePF clientePF,
                             BindingResult result){
        if (result.hasErrors()) {
            return viewForm(clientePF);
        }
        //save pessoaFisica
        clientePFRepository.save(clientePF);
        return new ModelAndView("venda/list");
    }



    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(required = false) String nome,
            ModelMap model) {

        List<ClientePF> clientes;

        if (nome != null && !nome.trim().isEmpty()) {
            clientes = clientePFRepository.buscarPorNome(nome);
        } else {
            clientes = clientePFRepository.listarTodos();
        }

        model.addAttribute("clientes", clientes);
        model.addAttribute("nome", nome);
        model.addAttribute("rota", "/clientepf/list");

        return new ModelAndView("cliente/listPF");
    }


}
