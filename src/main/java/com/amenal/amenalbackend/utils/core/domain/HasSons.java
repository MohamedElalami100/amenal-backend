package com.amenal.amenalbackend.utils.core.domain;

import com.amenal.amenalbackend.budget.core.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashSet;
import java.util.List;

public interface HasSons {
    @JsonIgnore
    List<List<HasSons>> getSons();

    @JsonIgnore
    List<String> getErrors();

    default HashSet<String> checkIfOneSonIsEmptyAndReturnError() {
        HashSet<String> errorsSet = new HashSet<>();
        int idx = 0;
        for (List<HasSons> sonList : this.getSons()) {
            if (sonList.size() == 0)
                errorsSet.add(this.getErrors().get(idx));

            for (HasSons son : sonList) {
                errorsSet.addAll(son.checkIfOneSonIsEmptyAndReturnError());
            }
            idx++;
        }
        return errorsSet;
    }

}
