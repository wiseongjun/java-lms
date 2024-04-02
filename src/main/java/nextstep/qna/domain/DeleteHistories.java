package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public static DeleteHistories of(DeleteHistory deleteHistory, List<DeleteHistory> deleteHistories) {
        List<DeleteHistory> mergeDeleteHistories = new ArrayList<>();
        mergeDeleteHistories.add(deleteHistory);
        mergeDeleteHistories.addAll(deleteHistories);
        return new DeleteHistories(mergeDeleteHistories);
    }

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void add(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }

    public List<DeleteHistory> get() {
        return Collections.unmodifiableList(deleteHistories);
    }
}