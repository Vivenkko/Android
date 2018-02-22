package vivenkko.inote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by magomez on 22/02/2018.
 */

public class SharedWith {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("sharedWithName")
    private int name;

    public SharedWith() {

    }

    public SharedWith(int id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SharedWith that = (SharedWith) o;

        if (id != that.id) return false;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name;
        return result;
    }
}
