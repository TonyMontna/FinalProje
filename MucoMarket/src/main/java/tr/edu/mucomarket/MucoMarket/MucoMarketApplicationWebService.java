package tr.edu.mucomarket.MucoMarket;
import java.util.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/urun")
public class MucoMarketApplicationWebService {
    public record Urun(String urunAdi, String urunKodu) {};

    private static final List<Urun> URUN_LISTESI = new ArrayList<>();
    static {
        URUN_LISTESI.add(new Urun("ELMA ARMUT", "1"));
        URUN_LISTESI.add(new Urun("SUT EKMEK", "2"));
        URUN_LISTESI.add(new Urun("OYUNCAK YUMURTA", "3"));
    }
   
    @GetMapping("/")
    public List<Urun> listele(){
        return URUN_LISTESI;
    }
   
    @GetMapping("/{no}")
    public Urun bul(@PathVariable String no){
        for(Urun urun: URUN_LISTESI) {
            if(urun.urunKodu().equals(no)) {
                return urun;
            }
        }
        return null;
    }
   
    @DeleteMapping("/{no}")
    public boolean sil(@PathVariable String no) {
        Urun urun = bul(no);
        if(urun != null) {
            URUN_LISTESI.remove(urun);
            return true;
        }
        return false;
    }
   
    @PostMapping("/")
    public Urun ekle(@RequestBody Urun urun) {
        URUN_LISTESI.add(urun);
        return urun;
    }
}