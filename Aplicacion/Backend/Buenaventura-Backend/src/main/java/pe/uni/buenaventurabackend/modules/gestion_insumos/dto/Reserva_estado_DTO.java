package pe.uni.buenaventurabackend.modules.gestion_insumos.dto;

public class Reserva_estado_DTO {
    private Integer idReserva;
    private String nombreEstado;

    public Reserva_estado_DTO(Integer idReserva, String nombreEstado) {
        this.idReserva = idReserva;
        this.nombreEstado = nombreEstado;
    }
    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
