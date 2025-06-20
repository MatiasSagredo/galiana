package com.galiana_project.cl.galiana_project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galiana_project.cl.galiana_project.model.Director;
import com.galiana_project.cl.galiana_project.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

        void deleteByDirector(Director director);

        @Query("""
                        SELECT obra.nombre, obra.horario, obra.precio FROM Obra obra
                        """)
        List<Obra> findObrasConNombreHorarioPrecio();

        @Query("""
                        SELECT obra FROM Obra obra
                        Where obra.precio <= 10000
                        """)
        List<Obra> findObrasConPrecioMenorIgualA10Mil();

        @Query("""
                        SELECT o FROM Obra o
                        WHERE o.director.id = :id
                        """)
        List<Obra> findObrasDelDirectorId(Long id);
}
