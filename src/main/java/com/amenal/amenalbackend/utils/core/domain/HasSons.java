package com.amenal.amenalbackend.utils.core.domain;

import java.util.HashSet;
import java.util.List;

public interface HasSons {
    List<List<HasSons>> getSons();

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
