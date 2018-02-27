package vivenkko.inote.retrofit;

import vivenkko.inote.model.Note;

/**
 * Created by magomez on 22/02/2018.
 */

public interface IOnNoteInteractionListener {
    void onNoteDobleClick(Note note);
    void onTrashNoteClick(Note note);
}
