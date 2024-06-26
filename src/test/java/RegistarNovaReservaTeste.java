import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.*;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RegistarNovaReservaTeste {

    @Before
    public void setUp() {
        // This method is called before each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
        Reservas.clearInstance();

    }

    @After
    public void tearDown() {
        // This method is called after each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
        Editoras.clearInstance();
        Reservas.clearInstance();
    }

    @Test
    public void testRegistarNovaReserva() {

        Socio socio = new Socio("João", "sms", "Normal","email", 123456789, "Rua do João", 123456789);
        Socios.getInstance().addSocio(socio);

        RegistarNovaReserva registarNovaReserva = new RegistarNovaReserva();
        String message = registarNovaReserva.registarNovaReserva(Integer.toString(socio.getNumeroDeSocio()), "Aventuras tintin", "Luisa Soares", "25/06/2024");

        assertEquals("Reserva registada com sucesso.", message);
    }


    @Test
    public void testRegistarNovaReservaSocioNaoExiste() {

        RegistarNovaReserva registarNovaReserva = new RegistarNovaReserva();
        String message = registarNovaReserva.registarNovaReserva("1", "Aventuras tintin", "Luisa Soares", "25/06/2024");

        assertEquals("O número do sócio não existe.", message);
    }

    @Test
    public void testRegistarNovaReservaNumeroSocioInvalido() {

        RegistarNovaReserva registarNovaReserva = new RegistarNovaReserva();
        String message = registarNovaReserva.registarNovaReserva("a", "Aventuras tintin", "Luisa Soares", "25/06/2024");

        assertEquals("Número do sócio inválido.", message);
    }

    @Test
    public void testRegistarNovaReservaCamposVazios() {
        Socio socio = new Socio("João", "sms", "Normal","email", 123456789, "Rua do João", 123456789);
        Socios.getInstance().addSocio(socio);

        RegistarNovaReserva registarNovaReserva = new RegistarNovaReserva();
        String message = registarNovaReserva.registarNovaReserva(Integer.toString(socio.getNumeroDeSocio()), "", "", "");

        assertEquals("Por favor, preencha todos os campos.", message);
    }

    @Test
    public void testResgistarNovaReservaSocioComMulta() {
        // Create a member
        Socio socio = new Socio("João", "sms", "Normal","email", 123456789, "Rua do João", 123456789);
        Socios.getInstance().addSocio(socio);

        Editora editora1 = new Editora("Editora1");

        Exemplar exemplar4 = new Exemplar("123", "4", "Autora", "Drama", editora1, "2020", "464", "P1/E5");
        Exemplares.getInstance().addExemplar(exemplar4);

        Date dateWithMulta = new Date();
        dateWithMulta.setTime(dateWithMulta.getTime() - 30 * 24 * 60 * 60 * 1000);  // 30 = número de dias

        // Create a loan for the member with an unpaid fine
        Emprestimo emprestimo = new Emprestimo(socio,exemplar4 ,dateWithMulta );
        Emprestimos.getInstance().addEmprestimo(emprestimo);

        // Attempt to register a new reservation for the member
        RegistarNovaReserva registarNovaReserva = new RegistarNovaReserva();
        String message = registarNovaReserva.registarNovaReserva(Integer.toString(socio.getNumeroDeSocio()), "Aventuras tintin", "Luisa Soares", "25/06/2024");

        // Verify that the reservation was not successful and the correct message was returned
        assertEquals("O sócio tem multa por pagar.", message);
    }

}