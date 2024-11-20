package pe.uni.buenaventurabackend.modules.iperc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.iperc.models.requests.IpercEditablesRequest;
import pe.uni.buenaventurabackend.modules.iperc.service.IpercService;

import java.util.List;

@RestController
@RequestMapping("/api/iperc")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class IpercController {
    private IpercService ipercService;

    @GetMapping("/listaIperc")
    public List<IpercEditablesRequest> listaIperc() {
        try{
            return ipercService.listaIperc();
        } catch (Exception e){
            return null;
        }
    }

}
