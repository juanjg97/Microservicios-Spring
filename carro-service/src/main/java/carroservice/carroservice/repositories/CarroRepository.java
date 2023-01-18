package carroservice.carroservice.repositories;

import carroservice.carroservice.domain.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Integer> {
    List<Carro> findByIdUsuario(int idUsuario);
}
