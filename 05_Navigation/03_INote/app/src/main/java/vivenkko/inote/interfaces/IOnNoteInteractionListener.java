package vivenkko.inote.interfaces;

import vivenkko.inote.model.Note;

/**
 * Created by magomez on 22/02/2018.
 */

public interface IOnNoteInteractionListener {
    void onNoteClick(Note note);
    void onNoteLongClick(Note note);
}
