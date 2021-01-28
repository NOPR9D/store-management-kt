import com.boucham.storeManagement.controller.added
import com.boucham.storeManagement.controller.notFound
import com.boucham.storeManagement.models.Store
import com.boucham.storeManagement.repository.StoreRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/stores")
class StoreController(@Autowired val repository: StoreRepository) {
    val logger = LogFactory.getLog(StoreController::class.java)

    @GetMapping()
    fun getStores(): List<Store> = repository.findAll()

    @GetMapping("/{id}")
    fun getStore(@PathVariable id: String, response: HttpServletResponse): Optional<Store>? {
        val result = repository.findById(id);
        if (result.isEmpty) return notFound(response);
        return result
    }

    @PostMapping()
    fun createStore(@RequestBody store: Store, response: HttpServletResponse): String? {
        val newStore = repository.insert(store)
        return added(response)
    }

    @DeleteMapping("/{id}")
    fun removeStore(@PathVariable id: String, response: HttpServletResponse): Optional<Store>? {
        val result = repository.findById(id);
        if (result.isEmpty) return notFound(response);
        return result
    }
}