package com.spedire.Spedire.dtos.response;

import com.spedire.Spedire.models.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class DashBoardDto {
    private String userId;
    private String firstName;
    private Set<Role> setOfRole;

}
