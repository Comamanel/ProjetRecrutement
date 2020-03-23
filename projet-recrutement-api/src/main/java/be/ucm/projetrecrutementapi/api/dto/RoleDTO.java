package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    private Long id;
    private String label;

    public RoleDTO(){}
    public RoleDTO(Role role){
        this.id = role.getId();
        this.label = role.getLabel();
    }
}
