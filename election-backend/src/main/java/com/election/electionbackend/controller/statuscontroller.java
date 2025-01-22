import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @CrossOrigin(origins = "https://election-frontendd.onrender.com")  // Voeg de frontend URL toe
    @GetMapping("/api/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Backend is up and running!");
        return ResponseEntity.ok(response);
    }
}
