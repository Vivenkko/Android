package vivenkko.inote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by magomez on 22/02/2018.
 */

public class Note {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("titulo")
    private String title;
    @Expose
    @SerializedName("descripcion")
    private String description;
    @Expose
    @SerializedName("categoria")
    private Category category;
    @Expose
    @SerializedName("compartido")
    private List<SharedWith> sharedWithList;

    public Note() {

    }

    public Note(int id, String title, String description, Category category, List<SharedWith> sharedWithList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.sharedWithList = sharedWithList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<SharedWith> getSharedWithList() {
        return sharedWithList;
    }

    public void setSharedWithList(List<SharedWith> sharedWithList) {
        this.sharedWithList = sharedWithList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (id != note.id) return false;
        if (title != null ? !title.equals(note.title) : note.title != null) return false;
        if (description != null ? !description.equals(note.description) : note.description != null)
            return false;
        if (category != null ? !category.equals(note.category) : note.category != null)
            return false;
        return sharedWithList != null ? sharedWithList.equals(note.sharedWithList) : note.sharedWithList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (sharedWithList != null ? sharedWithList.hashCode() : 0);
        return result;
    }
}
