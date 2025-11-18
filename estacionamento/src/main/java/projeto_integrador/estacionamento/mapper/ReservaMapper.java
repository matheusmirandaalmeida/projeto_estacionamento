package projeto_integrador.estacionamento.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import projeto_integrador.estacionamento.dto.ReservaResponse;
import projeto_integrador.estacionamento.entity.Reserva;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(source = "cliente.idCliente", target = "clienteId")
    @Mapping(source = "vaga.idVaga", target = "vagaId")
    @Mapping(source = "horario.inicio", target = "inicio")
    @Mapping(source = "horario.fim", target = "fim")
    @Mapping(source = "status", target = "status")
    ReservaResponse toResponse(Reserva reserva);

}
