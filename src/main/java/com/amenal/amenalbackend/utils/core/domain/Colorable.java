package com.amenal.amenalbackend.utils.core.domain;

import java.util.HashSet;
import java.util.stream.Collectors;
import com.amenal.amenalbackend.security.auditing.AuditableEntity;

public abstract class Colorable extends AuditableEntity implements HasSons{
    private Integer status;

    public Colorable() {
        this.status = 0;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String peutEtreFiger() {
        HashSet<String> errorsSet = this.checkIfOneSonIsEmptyAndReturnError();
        if (errorsSet.isEmpty()) {
            this.status = 1;
            return "";
        }
        return errorsSet.stream()
                .collect(Collectors.joining("\n"));
    }

    public String figer() {
        if (this.status == 1 || (this.status == 0 && this.peutEtreFiger().isEmpty())) {
            this.status = 2;
            return "";
        } else if (this.status == 2 || this.status == 3) {
            return "";
        }
        return this.peutEtreFiger();
    }

    public Boolean valider() {
        if (this.status >= 2) {
            this.status = 3;
            return true;
        }
        return false;
    }

}
