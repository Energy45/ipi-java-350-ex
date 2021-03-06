package com.ipiecoles.java.java350.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    void shouldGetNombreAnneeAncienneteEqualsToTenYears() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.of(2011, 1, 1)); //1er Janvier 2011

        //When
        Integer anneeAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        assertThat(anneeAnciennete).isEqualTo(10);
    }

    @Test
    void shouldGetNombreAnneeAncienneteEqualsToZeroYear() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now()); //Date courante

        //When
        Integer anneeAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        assertThat(anneeAnciennete).isEqualTo(0);
    }

    @Test
    void shouldGetNombreAnneeAncienneteIsNegative() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(5));

        //When
        Integer anneeAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        assertThat(anneeAnciennete).isNegative();
    }

    @Test
    void shouldGetNombreAnneeAncienneteThrownIllegalArgumentExceptionBecauseDateEmbaucheIsNull() {
        //Given
        Employe employe = new Employe();

        //When
        assertThatThrownBy(employe::getNombreAnneeAnciennete)
                //Then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("La date d'embauche n'est pas renseignée !");

    }

    @ParameterizedTest(name = "La prime d'anciennete pour l'employé " +
            "(Matricule : {0}, anneeAnciennete : {1}, performance : {2}, tempsPartiel : {3}, primeAnnuelle : {4})")
    @CsvSource({
            "'M6445', 11, 1, 2.0, 5600.0",
            "'T5482', 3, 1, 1.0, 1300.0",
            "'C4582', 18, 2, 1.5, 6150.0",
            "'C0001', 6, 0, 1.0, 900.0"
    })
    void testGetPrimeAnciennete(String matricule, Integer anneeEmbauche, Integer performance, Double tempsPartiel, Double primeAnnuelle) {
        Employe employe = new Employe();
        employe.setMatricule(matricule);
        employe.setDateEmbauche(LocalDate.now().minusYears(anneeEmbauche));
        employe.setPerformance(performance);
        employe.setTempsPartiel(tempsPartiel);

        assertThat(employe.getPrimeAnnuelle()).isEqualTo(primeAnnuelle);
    }
}
