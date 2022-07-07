package Prime.Test_Task.repo;

import Prime.Test_Task.models.Papers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PapersRepo  extends JpaRepository<Papers, Long> {
}
