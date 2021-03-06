package com.ipiecoles.java.java350.repository;


import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

//@ExtendWith(SpringExtension.class)
//@DataJdbcTest
@SpringBootTest

public class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;


    @Test
    public void testFindLastMatriclule0Emplye(){
        //GIVEB
        employeRepository.deleteAll();

        //WHEN
        String lastMatriclue = employeRepository.findLastMatricule();

        //THEN
        Assertions.assertThat(lastMatriclue).isNull();


    }

    @Test
    public void testFindLastMatricule1Employe(){
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
    public void testFindLastMatricluleNEmploey(){
        //GIven
        employeRepository.save(new Employe("Doe","Jhon", "T12345", LocalDate.now(),1500d,1,1.0));
        employeRepository.save(new Employe("Doe","Smit", "M40325", LocalDate.now(),1500d,1,1.0));
        employeRepository.save(new Employe("Doe","jim", "C06432", LocalDate.now(),1500d,1,1.0));

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("40325");

    }
    @BeforeEach
    @AfterEach
    public void purgeBdd(){
        employeRepository.deleteAll();
    }


}




