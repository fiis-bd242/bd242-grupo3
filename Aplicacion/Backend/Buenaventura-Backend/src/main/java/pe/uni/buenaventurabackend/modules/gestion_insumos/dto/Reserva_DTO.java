package pe.uni.buenaventurabackend.modules.gestion_insumos.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva_DTO {
    private Integer idReserva;
    private LocalDate fecha;
    private LocalTime hora;

    public Reserva_DTO(Integer idReserva, LocalDate fecha, LocalTime hora) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
