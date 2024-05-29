package com.amenal.amenalbackend.utils.infrastructure;


import com.amenal.amenalbackend.security.auditing.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColorableEntity extends AuditableEntity {
    @Column(name = "status")
    private String status;
}
