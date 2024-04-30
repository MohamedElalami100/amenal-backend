package com.amenal.amenalbackend.utils.core.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Colorable {
    private Integer status;

    public abstract List<List<Colorable>> getSons();

    public abstract List<String> getErrors();

    public Colorable() {
        this.status = 0;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private HashSet<String> checkIfOneSonIsEmptyAndReturnError() {
        HashSet<String> errorsSet = new HashSet<>();
        int idx = 0;
        for (List<Colorable> sonList : this.getSons()) {
            if (sonList.size() == 0)
                errorsSet.add(this.getErrors().get(idx));

            for (Colorable son : sonList) {
                errorsSet.addAll(son.checkIfOneSonIsEmptyAndReturnError());
            }
            idx++;
        }
        return errorsSet;
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
