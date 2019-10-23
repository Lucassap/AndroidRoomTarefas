package br.com.digitalhouse.appmytasks.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//TODO: Implementar a anotação necessária para entidade e seus campos
@Entity(tableName = "tarefas")
public class Tarefa implements Parcelable {
    @ColumnInfo(name = "nome")
    String nome;

    @ColumnInfo(name = "descricao")
    String descricao;

    @PrimaryKey(autoGenerate = true)
    private long id;

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    protected Tarefa(Parcel in) {
        nome = in.readString();
        descricao = in.readString();
        id = in.readLong();
    }

    public static final Creator<Tarefa> CREATOR = new Creator<Tarefa>() {
        @Override
        public Tarefa createFromParcel(Parcel in) {
            return new Tarefa(in);
        }

        @Override
        public Tarefa[] newArray(int size) {
            return new Tarefa[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeLong(id);
    }

    //TODO: Implementar os atributos necessários para a classe modelo
}
