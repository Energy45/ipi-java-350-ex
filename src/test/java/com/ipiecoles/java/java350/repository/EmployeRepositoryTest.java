package com.ipiecoles.java.java350.repository;


import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest

class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;


    @Test
    void testFindLastMatriclule0Emplye(){
        //GIVEB
        employeRepository.deleteAll();

        //WHEN
        String lastMatriclue = employeRepository.findLastMatricule();

        //THEN
        Assertions.assertThat(lastMatriclue).isNull();


    }

    @Test
    void testFindLastMatricule1Employe(){
        //Given
        employeRepository.deleteAll();
        //Insérer des données en base
        employeRepository.save(new Employe("Doe","Jhon", "T12345", LocalDate.now(),1500d,1,1.0));

        //

        //Exécuter des requêtes en base
        String lastMatricule = employeRepository.findLastMatricule();
        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("12345");
    }

    @Test
    void testFindLastMatriculeEmploye(){
        //GIven
        employeRepository.save(new Employe("Doe","Jhon", "T12345", LocalDate.now(),1500d,1,1.0));
        employeRepository.save(new Employe("Doe","Smit", "M40325", LocalDate.now(),1500d,1,1.0));
        employeRepository.save(new Employe("Doe","jim", "C06432", LocalDate.now(),1500d,1,1.0));

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("40325");

    }

    @Test
    void testAvgPerformanceEmploye() {
        employeRepository.save(new Employe("Doe","Jhon", "C12345", LocalDate.now(),1500d,5,1.0));
        employeRepository.save(new Employe("Doe","Smit", "C40325", LocalDate.now(),1500d,5,1.0));
        employeRepository.save(new Employe("Doe","jim", "C06432", LocalDate.now(),1500d,2,1.0));

        Double avgPerformance = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        Assertions.assertThat(avgPerformance).isEqualTo(4);
    }

    @BeforeEach
    @AfterEach
    void purgeBdd(){
        employeRepository.deleteAll();
    }


}




