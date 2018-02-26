
package com.jarteaga.googlenoseque.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notas {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("categoria")
    @Expose
    private Categoria categoria;
    @SerializedName("compartido")
    @Expose
    private List<Compartido> compartido = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Notas() {
    }

    /**
     * 
     * @param id
     * @param categoria
     * @param titulo
     * @param compartido
     * @param descripcion
     */
    public Notas(String id, String titulo, String descripcion, Categoria categoria, List<Compartido> compartido) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.compartido = compartido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Compartido> getCompartido() {
        return compartido;
    }

    public void setCompartido(List<Compartido> compartido) {
        this.compartido = compartido;
    }

}
