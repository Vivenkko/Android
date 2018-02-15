
package vivenkko.vweather.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private Double all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clouds() {
    }

    /**
     * 
     * @param all
     */
    public Clouds(Double all) {
        super();
        this.all = all;
    }

    public Double getAll() {
        return all;
    }

    public void setAll(Double all) {
        this.all = all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds clouds = (Clouds) o;

        return all != null ? all.equals(clouds.all) : clouds.all == null;
    }

    @Override
    public int hashCode() {
        return all != null ? all.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
